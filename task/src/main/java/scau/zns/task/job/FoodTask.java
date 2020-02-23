package scau.zns.task.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.constant.OrderStatus;
import scau.zns.task.feign.FoodFeignClient;
import scau.zns.task.feign.OrderFeignClient;
import scau.zns.task.mapper.SalesStatisticsMapper;
import scau.zns.task.pojo.SalesStatistics;
import scau.zns.task.vo.*;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class FoodTask {

    private Logger logger = LoggerFactory.getLogger(FoodTask.class);

    @Autowired
    private SalesStatisticsMapper salesStatisticsMapper;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private FoodFeignClient foodFeignClient;

//    @Scheduled(cron = "0 */1 * * * ?")
    public void test(){
        System.out.println("1分钟过去了。。。");
    }

    // 每隔3分钟设置过期订单
    @Scheduled(cron = "0 */3 * * * ?")
    public void setInvalidOrders(){
        logger.info("FoodTask setInvalidOrders start...");
        Map<String, Object> map = new HashMap<>();
        map.put("status", OrderStatus.UNPAID);
        map.put("expireTime", getCurrentTime());
        map.put("limit", 100);
        OrderPageResponse<OrderVO> orderResp = orderFeignClient.getOrderList(map);
        List<OrderVO> orders = orderResp.getData();
        for(OrderVO order : orders){
            orderFeignClient.updateOrderStatus(order.getOrderId(), OrderStatus.INVALID);
        }
        logger.info("FoodTask {} orders setInvalidOrders end...", orders.size());
    }

    // 每隔10分钟统计商品日销量和月销量
    @Scheduled(cron = "0 */10 * * * ?")
    public void foodStatistics() {
        logger.info("FoodTask foodStatistics start...");
        // 查出所有的商品
        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put("limit", 1000);
        BasePageResponse<FoodVO> foodResponse = foodFeignClient.foodList(foodMap);
        List<FoodVO> foodList = foodResponse.getData();
        // 查出当天所有订单明细
        List<OrderDetail> dayOrderDetails = getDayOrderDetails();
        for(FoodVO food : foodList){
            Integer daySalesNum = getSalesNum(food.getId(), dayOrderDetails);
            Example example = new Example(SalesStatistics.class);
            example.createCriteria().andEqualTo("targetId", food.getId()).andIn("type",Arrays.asList(1,2));
            example.orderBy("type");
            List<SalesStatistics> salesStatistics = salesStatisticsMapper.selectByExample(example);
            SalesStatistics daySalesStatistics = null;
            SalesStatistics monthSalesStatistics = null;
            boolean newStat = false;
            if(CollectionUtils.isEmpty(salesStatistics)){
                //统计变量初始化
                daySalesStatistics = new SalesStatistics();
                monthSalesStatistics = new SalesStatistics();
                daySalesStatistics.setType(1);
                monthSalesStatistics.setType(2);
                daySalesStatistics.setSales("{}");
                daySalesStatistics.setTargetId(food.getId() + "");
                daySalesStatistics.setTotal(0);
                monthSalesStatistics.setSales("{}");
                monthSalesStatistics.setTargetId(food.getId() + "");
                monthSalesStatistics.setTotal(0);
                newStat = true;
            }else{
                daySalesStatistics = salesStatistics.get(0);
                monthSalesStatistics = salesStatistics.get(1);
            }
            // 1、按天统计
            JSONObject daySalesObject = JSONObject.parseObject(daySalesStatistics.getSales());
            String currentDate = getCurrentDate();
            daySalesObject.put(currentDate, daySalesNum);
            daySalesStatistics.setSales(JSONObject.toJSONString(daySalesObject));
            int daySalesTotal = 0;
            for(Object num : daySalesObject.values()){
                daySalesTotal += (Integer)num;
            }
            daySalesStatistics.setTotal(daySalesTotal);
            if(newStat){
                salesStatisticsMapper.insertSelective(daySalesStatistics);
            }else{
                salesStatisticsMapper.updateByPrimaryKeySelective(daySalesStatistics);
            }
            // 2、按月统计
            JSONObject monthSalesObject = JSONObject.parseObject(monthSalesStatistics.getSales());
            String currentMonth = getCurrentMonth();
            // 统计某一个月
            Integer monthSalesNum = 0;
            for(Map.Entry<String, Object> entry : daySalesObject.entrySet()){
                String oneDay = entry.getKey();
                Integer daySalesCount = (Integer) entry.getValue();
                if(oneDay.startsWith(currentMonth)){
                    monthSalesNum += daySalesCount;
                }
            }
            monthSalesObject.put(currentMonth, monthSalesNum);
            monthSalesStatistics.setSales(JSONObject.toJSONString(monthSalesObject));
            // 统计所有月份的销量
            int monthSalesTotal = 0;
            for(Object num : monthSalesObject.values()){
                monthSalesTotal += (Integer)num;
            }
            monthSalesStatistics.setTotal(monthSalesTotal);
            if(newStat){
                salesStatisticsMapper.insertSelective(monthSalesStatistics);
            }else{
                salesStatisticsMapper.updateByPrimaryKeySelective(monthSalesStatistics);
            }
        }
        logger.info("FoodTask foodStatistics end...");
    }


    // 统计订单成交额
    public void orderStatistics() {
        // 1、按天统计

        // 2、按月统计

    }

    public Integer getSalesNum(int foodId, List<OrderDetail> dayOrderDetails){
        int count = 0;
        for(OrderDetail orderDetail : dayOrderDetails){
            if(foodId == orderDetail.getFoodId()){
                count += orderDetail.getNumber();
            }
        }
        return count;
    }

    public List<OrderDetail> getDayOrderDetails(){
        String currentDate = getCurrentDate();
        Map<String, Object> orderDayMap = new HashMap<>();
        //todo 待优化
        orderDayMap.put("limit", 1000);
        orderDayMap.put("beginTime", currentDate + " 00:00:00");
        orderDayMap.put("endTime", currentDate + " 23:59:59");
        OrderPageResponse<OrderVO> orderDayResponse = orderFeignClient.getOrderList(orderDayMap);
        List<OrderVO> dayOrders = orderDayResponse.getData();
        List<OrderDetail> dayOrderDetails = new ArrayList<>();
        dayOrders.forEach(orderVO -> {
            if(orderVO.getStatus() != OrderStatus.UNPAID && orderVO.getStatus() != OrderStatus.INVALID){
                List<OrderDetail> orderDetails = orderVO.getOrderDetails();
                dayOrderDetails.addAll(orderDetails);
            }
        });
        return dayOrderDetails;
    }
    public List<OrderDetail> getMonthOrderDetails(){
        String currentDate = getCurrentMonth();
        Map<String, Object> orderMonthMap = new HashMap<>();
        //todo 待优化
        orderMonthMap.put("limit", 1000);
        orderMonthMap.put("beginTime", currentDate + " 00:00:00");
        orderMonthMap.put("endTime", currentDate + " 23:59:59");
        OrderPageResponse<OrderVO> orderDayResponse = orderFeignClient.getOrderList(orderMonthMap);
        List<OrderVO> dayOrders = orderDayResponse.getData();
        List<OrderDetail> dayOrderDetails = new ArrayList<>();
        dayOrders.forEach(orderVO -> {
            List<OrderDetail> orderDetails = orderVO.getOrderDetails();
            dayOrderDetails.addAll(orderDetails);
        });
        return dayOrderDetails;
    }

    private String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }
    private String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return sdf.format(now);
    }
    private String getCurrentMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = new Date();
        return sdf.format(now);
    }
}

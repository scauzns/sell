package scau.zns.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.OrderStatus;
import scau.zns.common.constant.ResponseCode;
import scau.zns.common.utils.idworker.Sid;
import scau.zns.order.mapper.OrderDetailMapper;
import scau.zns.order.mapper.OrderMapper;
import scau.zns.order.pojo.Orders;
import scau.zns.order.pojo.OrderDetail;
import scau.zns.order.service.OrderService;
import scau.zns.order.vo.OrderPageRequest;
import scau.zns.order.vo.OrderPageResponse;
import scau.zns.order.vo.OrderVO;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Sid sid;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public BaseResponse commitOrder(OrderVO orderVO) {
        String orderId = sid.nextShort();
        orderVO.setOrderId(orderId);
        Orders order = new Orders();
        BeanUtils.copyProperties(orderVO, order);
        order.setStatus(OrderStatus.UNPAID);
        //设置订单过期时间
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 3);
        order.setExpireTime(now.getTime());
        List<OrderDetail> orderDetails = orderVO.getOrderDetails();
        if(orderDetails == null || orderDetails.size()==0){
            return new BaseResponse(ResponseCode.FAILED, "订单商品不能为空！");
        }
        int rows = orderMapper.insertSelective(order);
        if(rows == 0){
            return new BaseResponse(ResponseCode.FAILED, "新增未知异常！");
        }
        orderDetails.forEach(foodItem -> {
            foodItem.setOrderId(orderId);
            String foodItemId = sid.nextShort();
            foodItem.setId(foodItemId);
            orderDetailMapper.insertSelective(foodItem);
        });
        return new BaseResponse(orderId);
    }

    @Override
    @Transactional
    public BaseResponse payOrder(String orderId, BigDecimal payMoney) {
        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setPayMoney(payMoney);
        order.setStatus(OrderStatus.PROCESSING);
        int rows = orderMapper.updateByPrimaryKeySelective(order);
        if(rows == 0){
            return new BaseResponse(ResponseCode.FAILED, "更新未知异常！");
        }
        return BaseResponse.success();
    }

    @Override
    public OrderPageResponse<OrderVO> orderList(OrderPageRequest request) {
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        if(request != null && Strings.isNotBlank(request.getUserId())){
            criteria.andEqualTo("userId", request.getUserId());
        }
        if(request != null && request.getStatus() != null){
            criteria.andEqualTo("status", request.getStatus());
        }
        if(request != null && Strings.isNotBlank(request.getExpireTime())){
            criteria.andLessThan("expireTime", request.getExpireTime());
        }
        if(request != null && Strings.isNotBlank(request.getBeginTime())){
            criteria.andGreaterThanOrEqualTo("createTime", request.getBeginTime());
        }
        if(request != null && Strings.isNotBlank(request.getEndTime())){
            criteria.andLessThanOrEqualTo("createTime", request.getEndTime());
        }
        example.setOrderByClause("update_time desc");
        PageHelper.startPage(request.getPage(), request.getLimit());
        Page<Orders> orders = (Page<Orders>)orderMapper.selectByExample(example);
        return new OrderPageResponse<>(convert(orders), orders.getTotal(), orders.getPages());
    }

    @Override
    public BaseResponse queryOrder(String orderId) {
        Orders order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            return new BaseResponse(ResponseCode.FAILED, "订单不存在！");
        }
        Example example = new Example(OrderDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(example);
        OrderVO vo = new OrderVO();
        vo.setOrderDetails(orderDetails);
        BeanUtils.copyProperties(order, vo);
        return new BaseResponse(vo);
    }

    @Override
    public BaseResponse queryOrderDetails(String orderId) {
        Orders order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            return new BaseResponse(ResponseCode.FAILED, "订单不存在！");
        }
        Example example = new Example(OrderDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(example);
        return new BasePageResponse<>(orderDetails, (long) orderDetails.size());
    }

    @Override
    @Transactional
    public BaseResponse updateOrderStatus(String orderId, int status) {
        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setStatus(status);
        int rows = orderMapper.updateByPrimaryKeySelective(order);
        if(rows==0){
            return new BaseResponse(ResponseCode.FAILED, "更新未知异常！");
        }
        return BaseResponse.success();
    }

    public List<OrderVO> convert(List<Orders> orders){
        List<OrderVO> vos = new ArrayList<>();
        for(Orders order : orders){
            Example example = new Example(OrderDetail.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orderId", order.getOrderId());
            List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(example);
            OrderVO vo = new OrderVO();
            BeanUtils.copyProperties(order, vo);
            vo.setOrderDetails(orderDetails);
            vos.add(vo);
        }
        return vos;
    }
}

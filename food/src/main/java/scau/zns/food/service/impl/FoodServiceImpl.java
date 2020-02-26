package scau.zns.food.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.OrderStatus;
import scau.zns.food.exception.FoodBusinessException;
import scau.zns.food.feign.OrderFeignClient;
import scau.zns.food.mapper.CommentMapper;
import scau.zns.food.mapper.FoodCategoryMapper;
import scau.zns.food.mapper.FoodMapper;
import scau.zns.food.mapper.SalesStatisticsMapper;
import scau.zns.food.pojo.Comment;
import scau.zns.food.pojo.Food;
import scau.zns.food.pojo.FoodCategory;
import scau.zns.food.pojo.SalesStatistics;
import scau.zns.food.service.FoodService;
import scau.zns.food.vo.FoodVO;
import tk.mybatis.mapper.entity.Example;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private SalesStatisticsMapper salesStatisticsMapper;

    @Override
    public BaseResponse query(int id) {
        Food food = foodMapper.selectByPrimaryKey(id);
        if(food == null){
            return new BaseResponse();
        }
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        List<FoodVO> convert = convert(foods);
        Food target = convert.get(0);
        return new BaseResponse(target);
    }

    @Override
    @Transactional
    public BasePageResponse<FoodVO> list(Food food, BasePageRequest pageRequest) {
        Example example = new Example(Food.class);
        Example.Criteria criteria = example.createCriteria();
        if(food != null && Strings.isNotBlank(food.getTitle())){
            criteria.andLike("title", "%" + food.getTitle() + "%");
        }
        if(food != null && food.getcId() != null){
            criteria.andEqualTo("cId", food.getcId());
        }
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getLimit());
        Page<Food> foods = (Page)foodMapper.selectByExample(example);
        return new BasePageResponse<>(convert(foods), foods.getTotal());
    }

    public List<FoodVO> convert(List<Food> foods){
        List<FoodVO> vos = new ArrayList<>();
        Map<Integer, String> categoryMap = getCategoryMap();
        for(Food food : foods){
            FoodVO vo = new FoodVO();
            BeanUtils.copyProperties(food,vo);
            vo.setcName(categoryMap.get(food.getcId()));
            List<Comment> foodComments = getFoodComments(food.getId());
            double praiseRate = calPraiseRate(foodComments);
            vo.setComments(foodComments);
            vo.setPraiseRate(praiseRate);
            vo.setSales(getFoodMonthSales(food.getId()));
            vos.add(vo);
        }
        return vos;
    }

    public List<Comment> getFoodComments(Integer foodId){
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("foodId", foodId);
        return commentMapper.selectByExample(example);
    }

    public Integer getFoodMonthSales(Integer foodId){
        Example example = new Example(SalesStatistics.class);
        example.createCriteria().andEqualTo("type", 2).andEqualTo("targetId", foodId);
        SalesStatistics salesStatistics = salesStatisticsMapper.selectOneByExample(example);
        if(salesStatistics == null){
            return 0;
        }
        JSONObject sale = JSONObject.parseObject(salesStatistics.getSales());
        String currentMonth = getCurrentMonth();
        return (Integer)sale.getOrDefault(currentMonth, 0);
    }

    private String getCurrentMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = new Date();
        return sdf.format(now);
    }

    public double calPraiseRate(List<Comment> foodComments){
        if(CollectionUtils.isEmpty(foodComments)){
            return 0;
        }
        double totalComments = foodComments.size();
        double goodComments = 0;
        for(Comment comment : foodComments){
            if(comment.getStar() >= 3){
                goodComments++;
            }
        }
        double praiseRate = goodComments/totalComments;
        return Double.parseDouble(String.format("%.2f", praiseRate));
    }

    @Override
    @Transactional
    public BaseResponse add(Food food) {
        checkEmptyFood(food);
        int rows = foodMapper.insertSelective(food);
        if(rows == 0){
            return BaseResponse.failed();
        }
        return BaseResponse.success();
    }

    @Override
    public BaseResponse del(int id) {
        int rows = foodMapper.deleteByPrimaryKey(id);
        if(rows == 0){
            return BaseResponse.failed();
        }
        return BaseResponse.success();
    }

    @Override
    @Transactional
    public BaseResponse update(Food food) {
        int rows = foodMapper.updateByPrimaryKeySelective(food);
        if(rows == 0){
            return BaseResponse.failed();
        }
        return BaseResponse.success();
    }

    @Override
    @Transactional
    public BaseResponse addComment(List<Comment> comments) {
        if(CollectionUtils.isEmpty(comments)){
            throw new FoodBusinessException("评论列表不能为空！！");
        }
        for(Comment comment : comments){
            int rows = commentMapper.insertSelective(comment);
            if(rows == 0){
                throw new FoodBusinessException("新增评价未知异常！！");
            }
        }
        BaseResponse orderStatusResponse = orderFeignClient.updateOrderStatus(comments.get(0).getOrderId(), OrderStatus.FINISHED);
        if(orderStatusResponse.getCode() != 0){
            throw new FoodBusinessException("更新订单状态失败！！");
        }
        return BaseResponse.success();
    }

    @Override
    public BasePageResponse<FoodVO> foodRange(Integer type, Integer top) {
        List<Food> foods = new ArrayList<>();
        List<SalesStatistics> foodRange = salesStatisticsMapper.getFoodRange(type, top);
        for(SalesStatistics salesStatistics : foodRange){
            Integer foodId = Integer.parseInt(salesStatistics.getTargetId());
            Food food = foodMapper.selectByPrimaryKey(foodId);
            foods.add(food);
        }
        return new BasePageResponse<>(convert(foods), (long) foods.size());
    }

    public void checkEmptyFood(Food food){
        if(food==null){
            throw new FoodBusinessException("新增的食物不能为空");
        }else if(food.getcId() == null){
            throw new FoodBusinessException("新增食物的类目不能为空");
        }else if(Strings.isBlank(food.getTitle())){
            throw new FoodBusinessException("新增食物的名称不能为空");
        }else if(food.getPrice() == null){
            throw new FoodBusinessException("新增食物的价格不能为空");
        }
    }

    public Map<Integer, String> getCategoryMap(){
        List<FoodCategory> foodCategories = foodCategoryMapper.selectAll();
        Map<Integer, String> categoryMap = new HashMap<>();
        for(FoodCategory category : foodCategories){
            categoryMap.put(category.getId(), category.getcName());
        }
        return categoryMap;
    }
}

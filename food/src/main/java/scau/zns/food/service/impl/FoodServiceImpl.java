package scau.zns.food.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.food.exception.FoodBusinessException;
import scau.zns.food.mapper.FoodCategoryMapper;
import scau.zns.food.mapper.FoodMapper;
import scau.zns.food.pojo.Food;
import scau.zns.food.pojo.FoodCategory;
import scau.zns.food.service.FoodService;
import scau.zns.food.vo.FoodVO;
import tk.mybatis.mapper.entity.Example;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    @Override
    public BaseResponse query(int id) {
        Food food = foodMapper.selectByPrimaryKey(id);
        if(food == null){
            return new BaseResponse();
        }
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        return new BaseResponse(convert(foods));
    }

    @Override
    @Transactional
    public BasePageResponse<FoodVO> list(Food food, BasePageRequest pageRequest) {
        Example example = new Example(Food.class);
        Example.Criteria criteria = example.createCriteria();
        if(food != null && Strings.isNotBlank(food.getTitle())){
            criteria.andLike("title", "%" + food.getTitle() + "%");
        }
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getLimit());
        Page<Food> foods = (Page)foodMapper.selectByExample(example);
        return new BasePageResponse<>(convert(foods), foods.getTotal());
    }

    public List<FoodVO> convert(List<Food> foods){
        List<FoodVO> vos = new ArrayList<>();
        Map<Integer, String> categoryMap = getCategoryMap();
        //todo 查询月销量、统计好评率等
        for(Food food : foods){
            FoodVO vo = new FoodVO();
            BeanUtils.copyProperties(food,vo);
            vo.setcName(categoryMap.get(food.getcId()));
            vos.add(vo);
        }
        return vos;
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

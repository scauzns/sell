package scau.zns.food.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.ResponseCode;
import scau.zns.food.mapper.FoodCategoryMapper;
import scau.zns.food.pojo.FoodCategory;
import scau.zns.food.service.FoodCategoryService;
import tk.mybatis.mapper.entity.Example;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    @Override
    public BasePageResponse<FoodCategory> list(BasePageRequest request) {
        if(request != null){
            PageHelper.startPage(request.getPage(), request.getLimit());
        }
        Page<FoodCategory> res = (Page)foodCategoryMapper.selectAll();
        return new BasePageResponse<>(res.getResult(), res.getTotal());
    }

    @Override
    @Transactional
    public BaseResponse insert(FoodCategory foodCategory) {
        Example example = new Example(FoodCategory.class);
        example.createCriteria().andEqualTo("cName", foodCategory.getcName());
        FoodCategory tmp = foodCategoryMapper.selectOneByExample(example);
        if(tmp != null){
            return new BaseResponse(ResponseCode.FAILED, "类目名称重复了！");
        }
        int rows = foodCategoryMapper.insertSelective(foodCategory);
        if(rows == 0){
            return new BaseResponse(ResponseCode.FAILED, "新增类目未知异常！");
        }
        return BaseResponse.success();
    }

    @Override
    public int del(int id) {
        return foodCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public BaseResponse update(FoodCategory foodCategory) {
        Example example = new Example(FoodCategory.class);
        example.createCriteria().andEqualTo("cName", foodCategory.getcName()).andNotEqualTo("id",foodCategory.getId());
        FoodCategory tmp = foodCategoryMapper.selectOneByExample(example);
        if(tmp != null){
            return new BaseResponse(ResponseCode.FAILED, "类目名称重复了！");
        }
        foodCategoryMapper.updateByPrimaryKeySelective(foodCategory);
        return BaseResponse.success();
    }
}

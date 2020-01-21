package scau.zns.food.service;

import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.food.pojo.FoodCategory;


public interface FoodCategoryService {
    BasePageResponse<FoodCategory> list(BasePageRequest request);
    BaseResponse insert(FoodCategory foodCategory);
    int del(int id);
    BaseResponse update(FoodCategory foodCategory);
}

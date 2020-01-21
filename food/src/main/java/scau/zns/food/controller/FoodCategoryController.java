package scau.zns.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.ResponseCode;
import scau.zns.food.pojo.FoodCategory;
import scau.zns.food.service.FoodCategoryService;

@RestController
@RequestMapping("/foodCategory")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @RequestMapping("/list")
    public BasePageResponse categorylist(BasePageRequest request){
        return foodCategoryService.list(request);
    }

    @PostMapping("/insert")
    public BaseResponse addCate(@RequestBody FoodCategory category){
        return foodCategoryService.insert(category);
    }

    @RequestMapping("/del/{id}")
    public BaseResponse delCategory(@PathVariable("id") int id){
        foodCategoryService.del(id);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse updateCate(@RequestBody FoodCategory category){
        return foodCategoryService.update(category);
    }
}

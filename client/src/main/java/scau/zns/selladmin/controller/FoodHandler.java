package scau.zns.selladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.selladmin.feign.FoodFeignClient;
import scau.zns.selladmin.vo.Comment;
import scau.zns.selladmin.vo.Food;
import scau.zns.selladmin.vo.FoodCategory;
import scau.zns.selladmin.vo.FoodVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/foodService")
public class FoodHandler {

    @Autowired
    private FoodFeignClient foodFeignClient;

    @RequestMapping("/categoryList")
    public ModelAndView categoryList(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("food/category");
        return mv;
    }

    @RequestMapping("/foodList")
    public ModelAndView foodList(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("food/food");
        return mv;
    }

    @RequestMapping("/toInsertCategory")
    public ModelAndView toInsertCategory(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("food/addCategory");
        return mv;
    }

    @RequestMapping("/toEditCategory")
    public ModelAndView toEditCategory(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("food/editCategory");
        return mv;
    }

    @RequestMapping("/toAddFood")
    public ModelAndView toAddFood(){
        ModelAndView mv = new ModelAndView();
        BasePageResponse categoryListRes = foodFeignClient.categorylist(null);
        List<FoodCategory> categoryList = categoryListRes.getData();
        mv.addObject("categoryList",categoryList);
        mv.setViewName("food/addFood");
        return mv;
    }

    @RequestMapping("/toEditFood")
    public ModelAndView toEditFood(){
        ModelAndView mv = new ModelAndView();
        BasePageResponse categoryListRes = foodFeignClient.categorylist(null);
        List<FoodCategory> categoryList = categoryListRes.getData();
        mv.addObject("categoryList",categoryList);
        mv.setViewName("food/editFood");
        return mv;
    }


    @GetMapping("/getFoodList")
    @ResponseBody
    public BasePageResponse<FoodVO> foodList(Food food, BasePageRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("cId",food.getcId());
        map.put("title", food.getTitle());
        map.put("page", request.getPage());
        map.put("limit", request.getLimit());
        return foodFeignClient.foodList(map);
    }

    @GetMapping("/queryFood")
    @ResponseBody
    public BaseResponse queryFood(Integer id){
        return foodFeignClient.queryFood(id);
    }

    @GetMapping("/getCateList")
    @ResponseBody
    public BasePageResponse categorylist(BasePageRequest request){
        return foodFeignClient.categorylist(request);
    }

    @GetMapping("/delFood/{foodId}")
    @ResponseBody
    BaseResponse delFood(@PathVariable("foodId") int foodId){
        return foodFeignClient.delFood(foodId);
    }

    @GetMapping("/delFoodCategory/{cId}")
    @ResponseBody
    BaseResponse delFoodCategory(@PathVariable("cId") int cId){
        return foodFeignClient.delCategory(cId);
    }

    @PostMapping("/uploadFoodImg")
    @ResponseBody
    public BaseResponse uploadImg(@RequestParam("file") MultipartFile file) throws IOException{
        return foodFeignClient.uploadImg(file);
    }

    @PostMapping("/updateFood")
    @ResponseBody
    public BaseResponse updateFood(@RequestBody Food food){
        return foodFeignClient.updateFood(food);
    }

    @PostMapping("/addFood")
    @ResponseBody
    public BaseResponse addFood(@RequestBody Food food){
        return foodFeignClient.addFood(food);
    }

    @PostMapping("/addCate")
    @ResponseBody
    public BaseResponse addCate(@RequestBody FoodCategory category){
        return foodFeignClient.addCate(category);
    }

    @PostMapping("/updateCate")
    @ResponseBody
    public BaseResponse updateCate(@RequestBody FoodCategory category){
        return foodFeignClient.updateCate(category);
    }

    @PostMapping("/addComment")
    @ResponseBody
    public BaseResponse addComment(@RequestBody List<Comment> comments){
        return foodFeignClient.newComment(comments);
    }

    @GetMapping("/getFoodRange")
    @ResponseBody
    public BasePageResponse<FoodVO> getFoodRange(Integer type, Integer top){
        return foodFeignClient.getFoodRange(type, top);
    }
}

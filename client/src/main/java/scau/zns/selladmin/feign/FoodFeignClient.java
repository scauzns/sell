package scau.zns.selladmin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.selladmin.config.MultipartSupportConfig;
import scau.zns.common.constant.URIs;
import scau.zns.selladmin.vo.Comment;
import scau.zns.selladmin.vo.Food;
import scau.zns.selladmin.vo.FoodCategory;
import scau.zns.selladmin.vo.FoodVO;

import java.util.List;
import java.util.Map;


@FeignClient(name = "food", url = URIs.FOOD_SERVER, configuration = MultipartSupportConfig.class)
public interface FoodFeignClient {

    @GetMapping(URIs.FOOD_LIST)
//    BasePageResponse<FoodVO> foodList(@RequestParam("food") Food food, @RequestParam("request") BasePageRequest request);
    BasePageResponse<FoodVO> foodList(@RequestParam Map<String, Object> map);

    @GetMapping(URIs.FOOD_QUERY + "/{foodId}")
    BaseResponse queryFood(@PathVariable("foodId") Integer id);

    @RequestMapping(value = URIs.CATEGORY_LIST, method = RequestMethod.GET)
    BasePageResponse categorylist(@RequestParam("request") BasePageRequest request);

    @GetMapping(URIs.DEL_FOOD + "/{foodId}")
    BaseResponse delFood(@PathVariable("foodId") int foodId);

    @RequestMapping(value = URIs.DEL_FOOD_CATEGORY + "/{id}", method = RequestMethod.GET)
    BaseResponse delCategory(@PathVariable("id") int id);

    @PostMapping(URIs.ADD_CATEGORY)
    BaseResponse addCate(@RequestBody FoodCategory category);

    @PostMapping(URIs.ADD_FOOD)
    BaseResponse addFood(@RequestBody Food food);

    @PostMapping(URIs.UPDATE_CATEGORY)
    BaseResponse updateCate(@RequestBody FoodCategory category);

    @PostMapping(URIs.UPDATE_FOOD)
    BaseResponse updateFood(@RequestBody Food food);

    //feign 跨模块上传文件参考：http://www.likecool.top/2019/08/29/springcloud%E8%B7%A8%E6%A8%A1%E5%9D%97%E4%B8%8A%E4%BC%A0%E6%96%87%E4%BB%B6%E9%97%AE%E9%A2%98current-request-is-not-a-multipart-request/
    @PostMapping(value = URIs.UPLOAD_FOOD_IMG, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    BaseResponse uploadImg(@RequestPart("file") MultipartFile file);

    @PostMapping(URIs.FOOD_COMMENT)
    BaseResponse newComment(@RequestBody List<Comment> comments);

    @GetMapping(URIs.FOOD_RANGE)
    BasePageResponse<FoodVO> getFoodRange(@RequestParam("type") Integer type, @RequestParam("top") Integer top);
}

package scau.zns.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.FileConstant;
import scau.zns.common.constant.ResponseCode;
import scau.zns.food.exception.FoodBusinessException;
import scau.zns.food.pojo.Comment;
import scau.zns.food.pojo.Food;
import scau.zns.food.service.FoodService;
import scau.zns.food.vo.FoodVO;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/query/{foodId}")
    public BaseResponse queryFood(@PathVariable("foodId") Integer id){
        return foodService.query(id);
    }

    @GetMapping("/list")
    public BasePageResponse<FoodVO> foodList(Food food, BasePageRequest request){
        return foodService.list(food, request);
    }

    @PostMapping("/add")
    public BaseResponse addFood(@RequestBody Food food){
        BaseResponse response = null;
        try {
             response = foodService.add(food);
        }catch (FoodBusinessException e){
            response = new BaseResponse(ResponseCode.FAILED, e.getMessage());
        }catch (Exception e){
            response = new BaseResponse(ResponseCode.FAILED, e.getMessage());
        }
        return response;
    }

    @GetMapping("/del/{foodId}")
    public BaseResponse delFood(@PathVariable("foodId") int foodId){
        return foodService.del(foodId);
    }

    @PostMapping("/update")
    public BaseResponse updateFood(@RequestBody Food food){
        return foodService.update(food);
    }

    @PostMapping("/upload/img")
    public BaseResponse uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        File res = null;
        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = FileConstant.IMG_UPLOAD_PATH;
            System.out.println("文件名称"+file.getOriginalFilename());
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，没有就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文档中
            res = new File(path + File.separator + filename);
            file.transferTo(res);
        } else {
            return new BaseResponse(ResponseCode.FAILED,"图片不能为空！");
        }
        return new BaseResponse(FileConstant.VIRTUAL_PATH + file.getOriginalFilename());
    }


    @PostMapping("/newComment")
    public BaseResponse newComment(@RequestBody List<Comment> comments){
        return foodService.addComment(comments);
    }

    @GetMapping("/getFoodRange")
    public BasePageResponse<FoodVO> getFoodRange(Integer type, Integer top){
        return foodService.foodRange(type, top);
    }

}

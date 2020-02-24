package scau.zns.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.FileConstant;
import scau.zns.common.constant.ResponseCode;
import scau.zns.common.utils.idworker.Sid;
import scau.zns.user.pojo.User;
import scau.zns.user.pojo.UserAddress;
import scau.zns.user.pojo.UserSearch;
import scau.zns.user.service.UserService;
import scau.zns.user.vo.UserLogInRequest;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

    @GetMapping("/test")
    public Object test(){
        return sid.nextShort();
    }

    @PostMapping("/register")
    public BaseResponse userRegister(@RequestBody User user){
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public BaseResponse userLogin(@RequestBody UserLogInRequest request){
        return userService.userLogIn(request);
    }

    @GetMapping("/frozen/{userId}")
    public BaseResponse userFrozen(@PathVariable("userId")String userId){
        return userService.userFrozen(userId);
    }

    @GetMapping("/search")
    public BaseResponse userSearch(UserSearch userSearch){
        return userService.userSearch(userSearch);
    }

    @GetMapping("/hotSearch")
    public BaseResponse getHotSearch(){
        return userService.getHotSearch();
    }

    @GetMapping("/list")
    public BasePageResponse<User> userList(User user, BasePageRequest request){
        return userService.userList(user, request);
    }

    @GetMapping("/query/{userId}")
    public BaseResponse userQuery(@PathVariable("userId")String userId){
        return userService.queryUser(userId);
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

    @PostMapping("/newAddress")
    public BaseResponse newAddress(@RequestBody UserAddress userAddress){
        return userService.addAddress(userAddress);
    }

    @PostMapping("/editAddress")
    public BaseResponse editAddress(@RequestBody UserAddress userAddress){
        return userService.editAddress(userAddress);
    }

    @GetMapping("/delAddress/{addressId}")
    public BaseResponse delAddress(@PathVariable("addressId") String addressId){
        return userService.delAddress(addressId);
    }

    @GetMapping("/addressList/{userId}")
    public BasePageResponse<UserAddress> addressList(@PathVariable("userId") String userId){
        return userService.addressList(userId);
    }


}

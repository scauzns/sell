package scau.zns.selladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.selladmin.feign.UserFeignClient;
import scau.zns.selladmin.vo.User;
import scau.zns.selladmin.vo.UserAddress;
import scau.zns.selladmin.vo.UserLogInRequest;
import scau.zns.selladmin.vo.UserSearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userService")
public class UserHandler {
    @Autowired
    private UserFeignClient userFeignClient;

    @PostMapping("/regist")
    @ResponseBody
    public BaseResponse userRegister(@RequestBody User user){
        return userFeignClient.userRegister(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public BaseResponse userLogin(@RequestBody UserLogInRequest request){
        return userFeignClient.userLogin(request);
    }

    @PostMapping("/uploadUserImg")
    @ResponseBody
    public BaseResponse uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        return userFeignClient.uploadImg(file);
    }

    @PostMapping("/addAddress")
    @ResponseBody
    public BaseResponse newAddress(@RequestBody UserAddress userAddress){
        return userFeignClient.newAddress(userAddress);
    }

    @GetMapping("/addressList")
    @ResponseBody
    public BasePageResponse<UserAddress> addressList(String userId){
        return userFeignClient.addressList(userId);
    }

    @GetMapping("/delAddress")
    @ResponseBody
    public BaseResponse delAddress(String addressId){
        return userFeignClient.delAddress(addressId);
    }

    @GetMapping("/hotSearch")
    @ResponseBody
    public BaseResponse getHotSearch(){
        return userFeignClient.getHotSearch();
    }

    @GetMapping("/userSearch")
    @ResponseBody
    public BaseResponse userSearch(UserSearch userSearch){
        Map<String, Object> map = new HashMap<>();
        map.put("content", userSearch.getContent());
        return userFeignClient.userSearch(map);
    }
}

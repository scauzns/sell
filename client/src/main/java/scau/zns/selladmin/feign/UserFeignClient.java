package scau.zns.selladmin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.selladmin.config.MultipartSupportConfig;
import scau.zns.common.constant.URIs;
import scau.zns.selladmin.vo.User;
import scau.zns.selladmin.vo.UserAddress;
import scau.zns.selladmin.vo.UserLogInRequest;
import scau.zns.selladmin.vo.UserSearch;

import java.util.Map;

@FeignClient(name = "user", url = URIs.USER_SERVER, configuration = MultipartSupportConfig.class)
public interface UserFeignClient {

    @PostMapping(URIs.USER_REGIST)
    BaseResponse userRegister(@RequestBody User user);

    @PostMapping(URIs.USER_LOGIN)
    BaseResponse userLogin(@RequestBody UserLogInRequest request);

    @GetMapping(value = URIs.USER_FROZEN + "/{userId}")
    BaseResponse userFrozen(@PathVariable("userId")String userId);

    @GetMapping(URIs.USER_SEARCH)
    BaseResponse userSearch(UserSearch userSearch);

    @GetMapping(URIs.USER_LIST)
    BasePageResponse<User> userList(@RequestParam Map<String, Object> map);

    @PostMapping(value = URIs.USER_UPLOAD_IMG, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    BaseResponse uploadImg(@RequestPart("file") MultipartFile file);

    @PostMapping(URIs.USER_NEWADDRESS)
    BaseResponse newAddress(@RequestBody UserAddress userAddress);

    @GetMapping(value = URIs.USER_ADDRESS_LIST + "/{userId}")
    BasePageResponse<UserAddress> addressList(@PathVariable("userId") String userId);

    @GetMapping(value = URIs.USER_DELADDRESS + "/{addressId}")
    BaseResponse delAddress(@PathVariable("addressId") String addressId);
}

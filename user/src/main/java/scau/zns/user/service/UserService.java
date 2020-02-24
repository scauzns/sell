package scau.zns.user.service;

import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.user.pojo.User;
import scau.zns.user.pojo.UserAddress;
import scau.zns.user.pojo.UserSearch;
import scau.zns.user.vo.UserLogInRequest;

public interface UserService {
    //注册
    BaseResponse userRegister(User user);
    //登录
    BaseResponse userLogIn(UserLogInRequest logInRequest);
    //搜索
    BaseResponse userSearch(UserSearch userSearch);
    //冻结
    BaseResponse userFrozen(String userId);
    //用户列表
    BasePageResponse<User> userList(User user, BasePageRequest request);
    //查找用户
    BaseResponse queryUser(String userId);
    //收货地址列表
    BasePageResponse<UserAddress> addressList(String userId);
    //新增收货地址
    BaseResponse addAddress(UserAddress userAddress);
    //删除收获地址
    BaseResponse delAddress(String addressId);
    //修改收获地址
    BaseResponse editAddress(UserAddress userAddress);
    //热搜
    BaseResponse getHotSearch();
}

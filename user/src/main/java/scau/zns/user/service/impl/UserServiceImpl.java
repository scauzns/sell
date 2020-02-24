package scau.zns.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.ResponseCode;
import scau.zns.common.constant.UserStatus;
import scau.zns.common.utils.idworker.Sid;
import scau.zns.user.mapper.UserAddressMapper;
import scau.zns.user.mapper.UserMapper;
import scau.zns.user.mapper.UserSearchMapper;
import scau.zns.user.pojo.User;
import scau.zns.user.pojo.UserAddress;
import scau.zns.user.pojo.UserSearch;
import scau.zns.user.service.UserService;
import scau.zns.user.vo.UserLogInRequest;
import scau.zns.user.vo.UserVO;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private UserSearchMapper userSearchMapper;

    @Autowired
    private Sid sid;

    @Override
    @Transactional
    public BaseResponse userRegister(User user) {
        if(user == null){
            return new BaseResponse(ResponseCode.FAILED, "用户信息不能为空！");
        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", user.getName());
        User userSearchByName = userMapper.selectOneByExample(example);
        if(userSearchByName != null){
            return new BaseResponse(ResponseCode.FAILED, "用户名称已存在！");
        }

        String id = sid.nextShort();
        user.setId(id);
        try {
            userMapper.insertSelective(user);
        }catch (Exception e){
            logger.error("occured an error" , e);
            return new BaseResponse(ResponseCode.FAILED, e.getMessage());
        }
        user = userMapper.selectByPrimaryKey(id);
        UserVO vo = new UserVO();
        String token = UUID.randomUUID().toString();
        BeanUtils.copyProperties(user, vo);
        vo.setToken(token);
        return new BaseResponse(vo);
    }

    @Override
    @Transactional
    public BaseResponse userLogIn(UserLogInRequest logInRequest) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", logInRequest.getName());
        User user = userMapper.selectOneByExample(example);
        if(user == null){
            return new BaseResponse(1, "账户不存在");
        }
        if(!logInRequest.getPassword().equals(user.getPassword())){
            return new BaseResponse(2, "密码错误！");
        }
        UserVO vo = new UserVO();
        String token = UUID.randomUUID().toString();
        BeanUtils.copyProperties(user, vo);
        vo.setToken(token);
        return new BaseResponse(vo);
    }

    @Override
    @Transactional
    public BaseResponse userSearch(UserSearch userSearch) {
        userSearchMapper.insertSelective(userSearch);
        return BaseResponse.success();
    }

    @Override
    @Transactional
    public BaseResponse userFrozen(String userId) {
        User user = new User();
        user.setId(userId);
        user.setStatus(UserStatus.FROZEN);
        userMapper.updateByPrimaryKeySelective(user);
        return BaseResponse.success();
    }

    @Override
    public BasePageResponse<User> userList(User user, BasePageRequest request) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user != null){
            if(Strings.isNotEmpty(user.getName())){
                criteria.andLike("name", user.getName());
            }
        }
        criteria.andEqualTo("status", UserStatus.VALID);
        example.setOrderByClause("update_time desc");
        PageHelper.startPage(request.getPage(), request.getLimit());
        Page<User> users = (Page<User>)userMapper.selectByExample(example);
        return new BasePageResponse<>(users, users.getTotal());
    }

    @Override
    public BaseResponse queryUser(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return BaseResponse.failed();
        }
        return new BaseResponse(user);
    }

    @Override
    public BasePageResponse<UserAddress> addressList(String userId) {
        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<UserAddress> userAddresses = userAddressMapper.selectByExample(example);
        return new BasePageResponse<>(userAddresses, (long) userAddresses.size());
    }

    @Override
    public BaseResponse addAddress(UserAddress userAddress) {
        String addressId = sid.nextShort();
        userAddress.setId(addressId);
        userAddressMapper.insertSelective(userAddress);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse delAddress(String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddressMapper.deleteByPrimaryKey(userAddress);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse editAddress(UserAddress userAddress) {
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse getHotSearch() {
        List<String> hotSearch = userSearchMapper.getHotSearch();
        return new BaseResponse(hotSearch);
    }


}

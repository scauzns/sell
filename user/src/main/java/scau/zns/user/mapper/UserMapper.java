package scau.zns.user.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}

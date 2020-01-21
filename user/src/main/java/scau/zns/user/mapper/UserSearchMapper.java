package scau.zns.user.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.user.pojo.UserSearch;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserSearchMapper extends Mapper<UserSearch> {
}

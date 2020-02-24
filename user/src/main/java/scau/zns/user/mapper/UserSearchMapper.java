package scau.zns.user.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.zns.user.pojo.UserSearch;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserSearchMapper extends Mapper<UserSearch> {

    @Select("select content from user_search group by content order by count(content) desc limit 4")
    List<String> getHotSearch();
}

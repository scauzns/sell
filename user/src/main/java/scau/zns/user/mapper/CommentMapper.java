package scau.zns.user.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.user.pojo.Comment;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CommentMapper extends Mapper<Comment> {
}

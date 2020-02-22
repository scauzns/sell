package scau.zns.food.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.food.pojo.Comment;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CommentMapper extends Mapper<Comment> {
}

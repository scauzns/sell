package scau.zns.food.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.zns.food.pojo.SalesStatistics;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SalesStatisticsMapper extends Mapper<SalesStatistics> {
    @Select("select * from sales_statistics where type = #{type} order by total desc limit #{top}")
    @Results({
        @Result(id = true, column = "id", property = "id"),
        @Result(column = "target_id", property = "targetId"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime")
    })
    List<SalesStatistics> getFoodRange(@Param("type")Integer type, @Param("top") Integer top);
}

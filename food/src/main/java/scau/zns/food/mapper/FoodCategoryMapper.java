package scau.zns.food.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.food.pojo.FoodCategory;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface FoodCategoryMapper extends Mapper<FoodCategory> {
}

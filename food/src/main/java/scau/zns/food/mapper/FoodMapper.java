package scau.zns.food.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.food.pojo.Food;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface FoodMapper extends Mapper<Food> {
}

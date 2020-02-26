package scau.zns.food.service;

import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.food.pojo.Comment;
import scau.zns.food.pojo.Food;
import scau.zns.food.vo.FoodVO;

import java.util.List;

public interface FoodService {
    BaseResponse query(int id);
    BasePageResponse<FoodVO> list(Food food, BasePageRequest pageRequest);
    BaseResponse add(Food food);
    BaseResponse del(int id);
    BaseResponse update(Food food);
    //新增评价
    BaseResponse addComment(List<Comment> comments);
    //销量排行榜
    BasePageResponse<FoodVO> foodRange(Integer type, Integer top);
}

package scau.zns.order.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.order.pojo.Orders;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderMapper extends Mapper<Orders>{
}

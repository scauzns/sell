package scau.zns.order.mapper;

import org.springframework.stereotype.Repository;
import scau.zns.order.pojo.OrderDetail;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderDetailMapper extends Mapper<OrderDetail>{
}

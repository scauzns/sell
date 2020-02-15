package scau.zns.order.service;

import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.order.pojo.Orders;
import scau.zns.order.vo.OrderVO;

public interface OrderService {
    //提交订单
    BaseResponse commitOrder(OrderVO orderVO);

    //支付订单
    BaseResponse payOrder(String orderId, Long payMoney);

    //订单列表
    BasePageResponse<OrderVO> orderList(Orders order, BasePageRequest request);

    //查询订单
    BaseResponse queryOrder(String orderId);

    //更新订单状态
    BaseResponse updateOrderStatus(String orderId, int status);
}

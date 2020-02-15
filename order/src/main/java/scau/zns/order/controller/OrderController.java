package scau.zns.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.order.pojo.Orders;
import scau.zns.order.service.OrderService;
import scau.zns.order.vo.OrderVO;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/commitOrder")
    public BaseResponse commitOrder(@RequestBody OrderVO orderVO){
        return orderService.commitOrder(orderVO);
    }


    @GetMapping("/payOrder")
    public BaseResponse payOrder(String orderId, Long payMoney){
        return orderService.payOrder(orderId, payMoney);
    }

    @GetMapping("/updateOrderStatus")
    public BaseResponse updateOrderStatus(String orderId, int status){
        return orderService.updateOrderStatus(orderId, status);
    }

    @GetMapping("/queryOrder")
    public BaseResponse queryOrder(String orderId){
        return orderService.queryOrder(orderId);
    }

    @GetMapping("/orderList")
    public BasePageResponse<OrderVO> getOrderList(Orders order, BasePageRequest request){
        return orderService.orderList(order, request);
    }
}

package scau.zns.selladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BaseResponse;
import scau.zns.selladmin.feign.OrderFeignClient;
import scau.zns.selladmin.vo.OrderPageRequest;
import scau.zns.selladmin.vo.OrderPageResponse;
import scau.zns.selladmin.vo.OrderVO;
import scau.zns.selladmin.vo.Orders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orderRouter")
public class OrderHandler {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @RequestMapping("/orderList")
    public ModelAndView userList(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order/order");
        return mv;
    }

    @RequestMapping("/getOrderDetails")
    public ModelAndView getOrderDetails(String orderId){
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderId", orderId);
        mv.setViewName("order/orderDetails");
        return mv;
    }

    /*@PostMapping("/commitOrder")
    @ResponseBody
    public BaseResponse commitOrder(@RequestBody OrderVO orderVO){
        return orderFeignClient.commitOrder(orderVO);
    }

    @GetMapping("/payOrder")
    @ResponseBody
    public BaseResponse payOrder(String orderId, BigDecimal payMoney){
        return orderFeignClient.payOrder(orderId, payMoney);
    }

    @GetMapping("/updateOrderStatus")
    @ResponseBody
    public BaseResponse updateOrderStatus(String orderId, int status){
        return orderFeignClient.updateOrderStatus(orderId, status);
    }

    @GetMapping("/queryOrder")
    @ResponseBody
    public BaseResponse queryOrder(String orderId){
        return orderFeignClient.queryOrder(orderId);
    }

    @GetMapping("/getOrderList")
    @ResponseBody
    public OrderPageResponse<OrderVO> getOrderList(OrderPageRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("userId",request.getUserId());
        map.put("status",request.getStatus());
        map.put("expireTime",request.getExpireTime());
        map.put("beginTime",request.getBeginTime());
        map.put("endTime",request.getEndTime());
        map.put("page", request.getPage());
        map.put("limit", request.getLimit());
        return orderFeignClient.getOrderList(map);
    }*/
}

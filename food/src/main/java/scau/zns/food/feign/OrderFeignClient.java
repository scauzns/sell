package scau.zns.food.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.URIs;
import scau.zns.food.vo.OrderPageResponse;
import scau.zns.food.vo.OrderVO;

import java.math.BigDecimal;
import java.util.Map;

@FeignClient(name = "order", url = URIs.ORDER_SERVER)
public interface OrderFeignClient {
    @PostMapping(URIs.ORDER_COMMIT)
    BaseResponse commitOrder(@RequestBody OrderVO orderVO);

    @GetMapping(URIs.ORDER_PAY)
    BaseResponse payOrder(@RequestParam("orderId") String orderId, @RequestParam("payMoney") BigDecimal payMoney);

    @GetMapping(URIs.ORDER_UPDATE_STATUS)
    BaseResponse updateOrderStatus(@RequestParam("orderId") String orderId, @RequestParam("status") int status);

    @GetMapping(URIs.ORDER_QUERY)
    BaseResponse queryOrder(String orderId);

    @GetMapping(URIs.ORDER_LIST)
    OrderPageResponse<OrderVO> getOrderList(@RequestParam Map<String, Object> map);
}

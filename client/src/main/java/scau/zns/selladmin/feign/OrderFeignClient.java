package scau.zns.selladmin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import scau.zns.common.base.BasePageRequest;
import scau.zns.common.base.BasePageResponse;
import scau.zns.common.base.BaseResponse;
import scau.zns.selladmin.constant.URIs;
import scau.zns.selladmin.vo.OrderVO;

import java.util.Map;

@FeignClient(name = "order", url = URIs.ORDER_SERVER)
public interface OrderFeignClient {
    @PostMapping(URIs.ORDER_COMMIT)
    BaseResponse commitOrder(@RequestBody OrderVO orderVO);

    @GetMapping(URIs.ORDER_PAY)
    BaseResponse payOrder(String orderId, Long payMoney);

    @GetMapping(URIs.ORDER_UPDATE_STATUS)
    BaseResponse updateOrderStatus(String orderId, int status);

    @GetMapping(URIs.ORDER_QUERY)
    BaseResponse queryOrder(String orderId);

    @GetMapping(URIs.ORDER_LIST)
    BasePageResponse<OrderVO> getOrderList(@RequestParam Map<String, Object> map);
}

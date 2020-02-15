package scau.zns.order.vo;

import scau.zns.order.pojo.Orders;
import scau.zns.order.pojo.OrderDetail;

import java.util.List;

public class OrderVO extends Orders {
    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

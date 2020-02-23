package scau.zns.task.vo;


import java.util.List;

public class OrderVO extends Orders {
    private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return super.toString() + "OrderDetails{" +
                "orderDetails=" + orderDetails +
                '}';
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }



    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

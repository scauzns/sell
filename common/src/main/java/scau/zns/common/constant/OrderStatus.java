package scau.zns.common.constant;

public interface OrderStatus {
    int UNPAID = 1; //未支付
    int PROCESSING = 2; //支付成功，订单待受理
    int FINISHED = 3; // 已完成
    int INVALID = 4; // 失效
}

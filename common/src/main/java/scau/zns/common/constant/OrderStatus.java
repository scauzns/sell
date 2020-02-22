package scau.zns.common.constant;

public interface OrderStatus {
    int UNPAID = 1; //未支付
    int PROCESSING = 2; //支付成功，订单待受理
    int UNCOMMENT = 3; // 已受理，待评论
    int FINISHED = 4; // 已完成
    int INVALID = 5; // 失效
}

package scau.zns.task.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private String orderId;

    private String userId;

    /**
     * 运费
     */
    private BigDecimal sendCost;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 实付金额
     */
    private BigDecimal payMoney;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 用户联系电话
     */
    private String mobile;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 订单状态
     */
    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date expireTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取运费
     *
     * @return send_cost - 运费
     */
    public BigDecimal getSendCost() {
        return sendCost;
    }

    /**
     * 设置运费
     *
     * @param sendCost 运费
     */
    public void setSendCost(BigDecimal sendCost) {
        this.sendCost = sendCost;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取实付金额
     *
     * @return pay_money - 实付金额
     */
    public BigDecimal getPayMoney() {
        return payMoney;
    }

    /**
     * 设置实付金额
     *
     * @param payMoney 实付金额
     */
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * 获取收货人
     *
     * @return receiver - 收货人
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置收货人
     *
     * @param receiver 收货人
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取用户联系电话
     *
     * @return mobile - 用户联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置用户联系电话
     *
     * @param mobile 用户联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取收货地址
     *
     * @return address - 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货地址
     *
     * @param address 收货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取订单状态
     *
     * @return status - 订单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", sendCost=" + sendCost +
                ", totalAmount=" + totalAmount +
                ", payMoney=" + payMoney +
                ", receiver='" + receiver + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", expireTime=" + expireTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
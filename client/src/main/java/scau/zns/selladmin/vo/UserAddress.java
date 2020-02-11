package scau.zns.selladmin.vo;

import java.util.Date;

public class UserAddress {
    private String id;

    private String userId;

    /**
     * 接收者
     */
    private String receiver;

    private String phone;

    private String address;

    /**
     * 默认选择： 1是  0否
     */
    private Integer defaultChoose;

    /**
     * 0 有效 1无效
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取接收者
     *
     * @return receiver - 接收者
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置接收者
     *
     * @param receiver 接收者
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDefaultChoose() {
        return defaultChoose;
    }

    public void setDefaultChoose(Integer defaultChoose) {
        this.defaultChoose = defaultChoose;
    }

    /**
     * 获取0 有效 1无效
     *
     * @return status - 0 有效 1无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 有效 1无效
     *
     * @param status 0 有效 1无效
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}
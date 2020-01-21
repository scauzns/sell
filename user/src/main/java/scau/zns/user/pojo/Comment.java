package scau.zns.user.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Comment {
    @Id
    @GeneratedValue(generator = "JDBC") //返回自增主键
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "order_id")
    private String orderId;

    private String content;

    //店家回复
    private String reply;

    @Column(name = "reply_time")
    private Date replyTime;


    /**
     * 0 有效  1无效
     */
    private Byte status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return food_id
     */
    public Integer getFoodId() {
        return foodId;
    }

    /**
     * @param foodId
     */
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 获取0 有效  1无效
     *
     * @return status - 0 有效  1无效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0 有效  1无效
     *
     * @param status 0 有效  1无效
     */
    public void setStatus(Byte status) {
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
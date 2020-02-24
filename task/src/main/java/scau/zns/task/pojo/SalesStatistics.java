package scau.zns.task.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sales_statistics")
public class SalesStatistics {
    @Id
    private Integer id;

    /**
     * 商品id 或者 订单记录id
     */
    @Column(name = "target_id")
    private String targetId;

    /**
     * 统计类型：1商品按日统计、2商品按月统计、3订单按日统计、4订单按月统计
     */
    private Integer type;

    /**
     * 统计数据(Json格式)
     */
    private String sales;

    private Double total;

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
     * 获取商品id 或者 订单id
     *
     * @return target_id - 商品id 或者 订单id
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * 设置商品id 或者 订单id
     *
     * @param targetId 商品id 或者 订单id
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取统计类型：1商品按日统计、2商品按月统计、3订单按日统计、4订单按月统计
     *
     * @return type - 统计类型：1商品按日统计、2商品按月统计、3订单按日统计、4订单按月统计
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置统计类型：1商品按日统计、2商品按月统计、3订单按日统计、4订单按月统计
     *
     * @param type 统计类型：1商品按日统计、2商品按月统计、3订单按日统计、4订单按月统计
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取统计数据(Json格式)
     *
     * @return sales - 统计数据(Json格式)
     */
    public String getSales() {
        return sales;
    }

    /**
     * 设置统计数据(Json格式)
     *
     * @param sales 统计数据(Json格式)
     */
    public void setSales(String sales) {
        this.sales = sales;
    }

    /**
     * @return total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Double total) {
        this.total = total;
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
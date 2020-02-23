package scau.zns.task.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Food {

    private Integer id;

    /**
     * 类目id
     */
    private Integer cId;

    /**
     * 食物名称
     */
    private String title;

    /**
     * 商品描述
     */
    private String fDesc;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 食品封面图
     */
    private String cover;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
     * 获取类目id
     *
     * @return c_id - 类目id
     */
    public Integer getcId() {
        return cId;
    }

    /**
     * 设置类目id
     *
     * @param cId 类目id
     */
    public void setcId(Integer cId) {
        this.cId = cId;
    }

    /**
     * 获取食物名称
     *
     * @return title - 食物名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置食物名称
     *
     * @param title 食物名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品描述
     *
     * @return f_desc - 商品描述
     */
    public String getfDesc() {
        return fDesc;
    }

    /**
     * 设置商品描述
     *
     * @param fDesc 商品描述
     */
    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    /**
     * 获取售价
     *
     * @return price - 售价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置售价
     *
     * @param price 售价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取食品封面图
     *
     * @return cover - 食品封面图
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置食品封面图
     *
     * @param cover 食品封面图
     */
    public void setCover(String cover) {
        this.cover = cover;
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
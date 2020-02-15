package scau.zns.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_title")
    private String foodTitle;

    /**
     * 封面
     */
    @Column(name = "food_cover")
    private String foodCover;

    /**
     * 售价
     */
    @Column(name = "sell_price")
    private Long sellPrice;

    /**
     * 商品数量
     */
    private Integer number;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
     * @return food_title
     */
    public String getFoodTitle() {
        return foodTitle;
    }

    /**
     * @param foodTitle
     */
    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    /**
     * 获取封面
     *
     * @return food_cover - 封面
     */
    public String getFoodCover() {
        return foodCover;
    }

    /**
     * 设置封面
     *
     * @param foodCover 封面
     */
    public void setFoodCover(String foodCover) {
        this.foodCover = foodCover;
    }

    /**
     * 获取售价
     *
     * @return sell_price - 售价
     */
    public Long getSellPrice() {
        return sellPrice;
    }

    /**
     * 设置售价
     *
     * @param sellPrice 售价
     */
    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * 获取商品数量
     *
     * @return number - 商品数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置商品数量
     *
     * @param number 商品数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}
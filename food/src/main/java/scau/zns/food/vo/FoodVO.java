package scau.zns.food.vo;

import scau.zns.food.pojo.Food;

public class FoodVO extends Food {
    private String cName; //类目名称
    private int sales = 0;  //销量
    private int praiseRate;  //好评率

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(int praiseRate) {
        this.praiseRate = praiseRate;
    }
}

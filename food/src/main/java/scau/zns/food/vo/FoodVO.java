package scau.zns.food.vo;

import scau.zns.food.pojo.Comment;
import scau.zns.food.pojo.Food;

import java.util.List;

public class FoodVO extends Food {
    private String cName; //类目名称
    private int sales = 0;  //销量
    private double praiseRate;  //好评率
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

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

    public double getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(double praiseRate) {
        this.praiseRate = praiseRate;
    }
}

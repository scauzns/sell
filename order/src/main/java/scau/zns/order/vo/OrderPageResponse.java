package scau.zns.order.vo;

import scau.zns.common.base.BasePageResponse;

import java.util.List;

public class OrderPageResponse<T> extends BasePageResponse<T> {
    private Integer totalPage;

    public OrderPageResponse(List<T> data, Long count, Integer totalPage){
        super(data, count);
        this.totalPage = totalPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}

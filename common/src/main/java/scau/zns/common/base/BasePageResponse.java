package scau.zns.common.base;

import java.util.List;

/**
 * 页面分页返回值
 */
public class BasePageResponse<T> extends BaseResponse {

    private List<T> data;
    private Long count;

    public BasePageResponse() {
    }

    public BasePageResponse(List<T> data, Long count) {
        super(0, "操作成功");
        this.data = data;
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
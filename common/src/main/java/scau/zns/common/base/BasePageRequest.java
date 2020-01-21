package scau.zns.common.base;


public class BasePageRequest {

    private Integer page = 1;

    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset(){
        if (page < 1 ) {
            this.page = 1;
        }
        return (page - 1) * this.limit;
    }
}

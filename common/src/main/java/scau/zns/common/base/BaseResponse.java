package scau.zns.common.base;

public class BaseResponse {

    public static Integer SUCCESS_CODE =  0 ;
    public static Integer FAILED_CODE = -1;
    private Integer code;
    private String msg = "";
    private Object data;

    public BaseResponse() {
    }
    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(Object data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    public BaseResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse success(){
        return new BaseResponse(SUCCESS_CODE, "操作成功");
    }

    public static BaseResponse failed(){
        return new BaseResponse(FAILED_CODE, "操作失败");
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
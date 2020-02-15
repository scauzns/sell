package scau.zns.selladmin.constant;

public interface URIs {
    // 商品服务URI
    String FOOD_SERVER = "http://localhost:8061";
    String FOOD_LIST = "/food/list";
    String FOOD_QUERY = "/food/query";
    String CATEGORY_LIST = "/foodCategory/list";
    String DEL_FOOD = "/food/del";
    String DEL_FOOD_CATEGORY = "/foodCategory/del";
    String ADD_CATEGORY = "/foodCategory/insert";
    String ADD_FOOD = "/food/add";
    String UPDATE_CATEGORY = "/foodCategory/update";
    String UPDATE_FOOD = "/food/update";
    String UPLOAD_FOOD_IMG = "/food/upload/img";

    //用户服务URI
    String USER_SERVER = "http://localhost:8062";
    String USER_REGIST = "/user/register";
    String USER_LOGIN = "/user/login";
    String USER_FROZEN = "/user/frozen";
    String USER_SEARCH = "/user/search";
    String USER_LIST = "/user/list";
    String USER_QUERY = "/user/query";
    String USER_UPLOAD_IMG = "/user/upload/img";
    String USER_NEWADDRESS = "/user/newAddress";
    String USER_EDITADDRESS = "/user/editAddress";
    String USER_DELADDRESS = "/user/delAddress";
    String USER_ADDRESS_LIST = "/user/addressList";
    String USER_COMMENT = "/user/newComment";
    String USER_COMMENT_REPLY = "/user/replyComment";

    //订单服务URI
    String ORDER_SERVER = "http://localhost:8063";
    String ORDER_COMMIT = "/order/commitOrder";
    String ORDER_PAY = "/order/payOrder";
    String ORDER_UPDATE_STATUS = "/order/updateOrderStatus";
    String ORDER_QUERY = "/order/queryOrder";
    String ORDER_LIST = "/order/orderList";
}

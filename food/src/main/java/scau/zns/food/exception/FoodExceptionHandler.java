package scau.zns.food.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.zns.common.base.BaseExceptionHandler;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.ResponseCode;


@ControllerAdvice
public class FoodExceptionHandler extends BaseExceptionHandler {

    Logger logger = LoggerFactory.getLogger(FoodExceptionHandler.class);

    @ExceptionHandler(FoodBusinessException.class)
    @ResponseBody
    public BaseResponse exceptionHandler(FoodBusinessException e){
        logger.error(" error " , e);
        return new BaseResponse(ResponseCode.FAILED, e.getMessage());
    }
}

package scau.zns.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.zns.common.base.BaseExceptionHandler;
import scau.zns.common.base.BaseResponse;
import scau.zns.common.constant.ResponseCode;


@ControllerAdvice
public class UserExceptionHandler extends BaseExceptionHandler {

    Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserBusinessException.class)
    @ResponseBody
    public BaseResponse exceptionHandler(UserBusinessException e){
        logger.error(" error " , e);
        return new BaseResponse(ResponseCode.FAILED, e.getMessage());
    }
}

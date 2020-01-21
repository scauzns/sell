package scau.zns.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.zns.common.constant.ResponseCode;

import java.lang.annotation.Inherited;

@ControllerAdvice
public class BaseExceptionHandler {
    Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    /**
     *  捕获未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e){
        logger.error(" error " , e);
        return new BaseResponse(ResponseCode.FAILED, e.getMessage());
    }
}

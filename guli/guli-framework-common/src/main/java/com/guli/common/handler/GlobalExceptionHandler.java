package com.guli.common.handler;

import com.baomidou.mybatisplus.extension.api.R;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.util.ExceptionUtil;
import com.guli.common.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e){
       // e.printStackTrace();
        //log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResult.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public ResponseResult error(BadSqlGrammarException e){
       // e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResult.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseResult error(JsonParseException e){
        //e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResult.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResponseResult error(GuliException e){
       // e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResult.error().message(e.getMessage()).code(e.getCode());
    }
}

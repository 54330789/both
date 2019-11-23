package com.guli.common.handler;

import com.baomidou.mybatisplus.extension.api.R;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.ResponseResult;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e){
        e.printStackTrace();
        return ResponseResult.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public ResponseResult error(BadSqlGrammarException e){
        e.printStackTrace();
        return ResponseResult.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseResult error(JsonParseException e){
        e.printStackTrace();
        return ResponseResult.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResponseResult error(GuliException e){
        e.printStackTrace();
        return ResponseResult.error().message(e.getMessage()).code(e.getCode());
    }
}

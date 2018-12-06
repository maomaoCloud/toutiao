package com.tiaotiaopoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.tiaotiaopoker.Constants.ApiCode.SUCCESS;


public class JsonResult {
    private Boolean success;
    private Integer code;
    private String  msg;
    private Object  resData;

    public static JsonResult SUCCESS() {
        return SUCCESS( "success" );
    }

    public static JsonResult SUCCESS(String desc) {
        return SUCCESS( desc, null );
    }

    public static JsonResult SUCCESS(String desc,
                                     Object data) {
        return new JsonResult( true, SUCCESS, desc, data );
    }

    public static JsonResult FAILED() {
        return FAILED( "failed" );
    }

    public static JsonResult FAILED(String desc) {
        return FAILED( desc, null );
    }

    public static JsonResult FAILED(String desc,
                                    Object data) {
        return new JsonResult( false, Constants.ApiCode.FAILED, desc, data );
    }

    public JsonResult() {
    }

    public JsonResult(Boolean success,
                      Integer code,
                      String msg,
                      Object resData) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.resData = resData;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    @Override
    public String toString() {
        try {
            String jsonData = new ObjectMapper().writeValueAsString( this );
            return jsonData;
        } catch (JsonProcessingException e) {
        }
        return "{}";
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResData() {
        return resData;
    }

    public void setResData(Object resData) {
        this.resData = resData;
    }
}
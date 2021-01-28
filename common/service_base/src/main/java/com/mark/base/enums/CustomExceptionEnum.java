package com.mark.base.enums;

/**
 * 自定义异常枚举
 *
 * @author 木可
 * @version 1.0
 * @date 2020/12/20 12:12
 */
public enum CustomExceptionEnum {
    /**
     * 文章相关异常
     */
    NO_ARTICLE(20001, "文章不存在"),
    NO_ARTICLE_CONTENT(20001, "文章内容不存在"),

    /**
     * 服务调用相关异常
     */
    REQUEST_SERVICE_FAIL(20002, "服务调用失败"),

    /**
     * OSS服务相关
     */
    FILE_UPLOAD_ERROR(20003, "文件上传失败");


    private final Integer code;
    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CustomExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

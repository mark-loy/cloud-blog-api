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
     * 文章内容为空
     */
    NO_ARTICLE_CONTENT(20001, "文章内容为空");

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

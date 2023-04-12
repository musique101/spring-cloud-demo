package com.uplus.order.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataEnum {

    NOT_FOUND(-100001, "数据不存在"),
    REQUEST_ILLEGALARGUMENT(-100002, "请求不合法"),
    FAILURE_OPER(-100003, "操作失败"),
    SYSTEM_ERROR(-100004, "系统异常"),
    ID_ERROR(-100005, "ID错误"),
    ID_EMPTY(-100006, "ID不能为空");

    private int code;
    private String desc;

}

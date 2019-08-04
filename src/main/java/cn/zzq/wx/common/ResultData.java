package cn.zzq.wx.common;

import lombok.Data;

//todo: 完善该类
@Data
public class ResultData<T> {

    private T data;

    private Integer code = 200;

    private String msg;

    private Boolean success = true;
}
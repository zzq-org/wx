package cn.zzq.wx.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
*   异常类
* */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrCodeException extends RuntimeException {
    //异常编号
    private String errCode;
    //异常信息
    private String errMsg;
    //todo: 有问题，待确认
}

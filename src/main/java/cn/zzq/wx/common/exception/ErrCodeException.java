package cn.zzq.wx.common.exception;

import cn.zzq.wx.common.enumeration.ErrCodeEnum;
import lombok.Getter;

/*
*   异常类
* */
@Getter
public class ErrCodeException extends RuntimeException {
    private int errorCode;
    private final String errorMsg;

    public ErrCodeException(ErrCodeEnum errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public ErrCodeException(ErrCodeEnum errorCode, String errorMsg) {
        this(errorCode.getCode(), errorMsg);
    }

    public ErrCodeException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrCodeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

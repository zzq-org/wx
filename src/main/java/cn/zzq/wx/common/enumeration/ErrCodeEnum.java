package cn.zzq.wx.common.enumeration;

import lombok.Getter;

/**
 * 异常类型枚举
 */
@Getter
public enum ErrCodeEnum {
    SYS_ERR("1000","系统错误"),
    PARAM_ERR("2000","参数错误"),
    CODE_ERR("3000","代码错误"),
    NET_ENUM("4000","网络错误");

    private String code;
    private String msg;

    ErrCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //todo: 有问题，待确认
}

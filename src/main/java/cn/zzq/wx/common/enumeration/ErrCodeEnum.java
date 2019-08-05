package cn.zzq.wx.common.enumeration;

import cn.zzq.wx.common.exception.ErrCodeException;
import lombok.Getter;

/**
 * 异常类型枚举
 */
@Getter
public enum ErrCodeEnum {
    /**
     * 成功
     */
    OK(200, "成功"),
    PARAM(400, "参数错误或业务异常"),
    AUTH(401, "认证失败"),
    FORBIDDEN(403, "访问拒绝"),
    NOT_EXIST(404, "资源不存在"),
    NOT_SUPPORT(405, "方法不支持"),
    SYSTEM_ERROR(500, "系统内部错误");

    private Integer code;
    private String message;

    ErrCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrCodeEnum getErrorByCode(int code) {
        ErrCodeEnum[] codes = values();
        for (ErrCodeEnum ec : codes) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        for (ErrCodeEnum ec : values()) {
            System.out.println(ec.getCode() + "|" + ec.getMessage());
        }
        throw new ErrCodeException(ErrCodeEnum.PARAM,"啥降低阿萨德");
    }
}

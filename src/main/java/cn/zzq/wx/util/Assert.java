package cn.zzq.wx.util;

import cn.zzq.wx.common.enumeration.ErrCodeEnum;
import cn.zzq.wx.common.exception.ErrCodeException;

/**
 * 断言类
 */
public class Assert {
    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new ErrCodeException(ErrCodeEnum.PARAM, message);
        }
    }
}
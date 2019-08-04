package cn.zzq.wx.util;

import cn.hutool.http.HttpUtil;
import cn.zzq.wx.common.constant.WxConstant;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取微信Token的工具类
 * token获取方式两种
 * 1)被动获取：计时器，在服务启动时获取，之后每隔115分钟获取一次
 * 2)主动获取：凡是使用到token的地方，一旦调用token返回过期或失效信息，需要主动调用方法获取token
 *
 * 获取token方法：静态方法 getToken()
 * 调用token方式：静态字段 WX_TOKEN
 */
@Component
public class WxTokenUtil {
    //获取token
    public static void getToken() {
        //获取授权后的URL
        String myTokenUrl = TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appSecret);
        //发送请求，接收结果并解析成map
        Map map = JSON.parseObject(HttpUtil.get(myTokenUrl), Map.class);
        if (map.containsKey(WxConstant.SUCCESS_KEY)){
            WxConstant.WX_TOKEN = ((String) map.get(WxConstant.SUCCESS_KEY));
            System.out.println("============获得了token:" + WxConstant.WX_TOKEN + "==============");
        } else {
            //todo: 获取token异常时的处理
            String errCode = (String) map.get(WxConstant.ERROR_KEY);
            System.out.println("获取token异常，错误码："+errCode);
        }
    }

    //获取token的url
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //身份认证
    private static String appId;
    private static String appSecret;

    //给静态常量赋值
    @Value("${wx.appID}")
    public void setAppId(String appId){
        WxTokenUtil.appId = appId;
    }
    @Value("${wx.appSecret}")
    public void setAppSecret(String appSecret){
        WxTokenUtil.appSecret = appSecret;
    }
}

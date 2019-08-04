package cn.zzq.wx.util;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.http.HttpUtil;
import cn.zzq.wx.common.constant.WxConstant;

public class WxMenuUtil {

    private static final String CRE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static final String DEL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    //创建公众号菜单
    public static void createWxMenu(){
        //获取Url
        String creMenuUrl = CRE_MENU_URL.replace(WxConstant.ACCESS_TOKEN_CONST, WxConstant.WX_TOKEN);
        //读取json文件内容为字符串
        String jsonStr = new FileReader("template/wx_menu.json").readString();
        //发送post请求创建菜单
        String res = HttpUtil.post(creMenuUrl, jsonStr);
        System.out.println(res);
    }

    //删除公众号菜单
    public static void delWxMenu(){
        //获取Url
        String delMenuUrl = DEL_MENU_URL.replace(WxConstant.ACCESS_TOKEN_CONST, WxConstant.WX_TOKEN);
        //解析json字符串
        String res = HttpUtil.get(delMenuUrl);
        System.out.println(res);
    }
}

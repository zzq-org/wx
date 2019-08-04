package cn.zzq.wx.bs.web.controller;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.SecureUtil;
import cn.zzq.wx.util.WxMenuUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
@Validated
public class WxConfigController {

    //微信接入token
    @Value("${wx.signature.token}")
    private String signatureToken;

    /**
     * 微信接口匹配、注册
     * @param signature
     * @param nonce
     * @param timestamp
     * @param echostr
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    @ResponseBody
    public String signature(String signature, String nonce, String timestamp, String echostr) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strings = {signatureToken, timestamp, nonce};
        Arrays.sort(strings);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (signature.equals(SecureUtil.sha1(builder.toString()))){
            return echostr;
        }
        return "";
    }

    /**
     * 接收用户发送给公众号的消息，并返回响应信息
     * @param xml 微信用xml格式传递到服务器的数据
     *
     * todo: 如果有接收消息并回复的功能，是否需要判重？
     * todo: 判重 --> 使用msgID+缓存，防止重复提交，AOP+自定义注解实现
     */
    @RequestMapping(value = "index",method = RequestMethod.POST)
    public String getMsgAndResponse(@RequestBody String xml, Model model) {
        //接收的信息，解析xml为map
        Map<String, Object> xmlMap = XmlUtil.xmlToMap(xml);
        //todo: 功能多了时需要抽取
        if ("event".equals(xmlMap.get("MsgType"))){
            if ("subscribe".equals(xmlMap.get("Event"))){//关注公众号时触发自动回复
                xmlMap.put("MsgType", "text");
                xmlMap.put("Content", "感谢您关注XXX公众号，请输入XXX执行XXX功能");
            } else if ("CLICK".equals(xmlMap.get("Event"))){//点击菜单触发事件
                if ("TEST_BUTTON".equals(xmlMap.get("EventKey"))){//判断点击的按钮
                    xmlMap.put("MsgType", "text");
                    xmlMap.put("Content", "点锤子点，没看到是测试吗，啥子功能都没得，傻逼");
                }
            }
        }
        model.addAttribute("map", xmlMap);
        return "respMsg";
    }

    /**
     * 创建公众号菜单
     */
    @RequestMapping("createMenu")
    public void createMenu(){
        WxMenuUtil.delWxMenu();
        WxMenuUtil.createWxMenu();
    }
}

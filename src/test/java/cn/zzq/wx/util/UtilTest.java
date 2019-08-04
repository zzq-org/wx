package cn.zzq.wx.util;

import cn.hutool.core.io.file.FileReader;
import cn.zzq.wx.WxApp;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxApp.class)
public class UtilTest {

    @Test
    public void test() {
        FileReader fileReader = new FileReader("template/wx_menu.json");
        String jsonStr = fileReader.readString();
        Map<String,Object> map = JSON.parseObject(jsonStr, Map.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "========" + entry.getValue());
        }
    }

    @Test
    public void test2() {
        WxMenuUtil.createWxMenu();
    }
}

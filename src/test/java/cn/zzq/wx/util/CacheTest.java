package cn.zzq.wx.util;

import cn.zzq.wx.WxApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxApp.class)
public class CacheTest {

    @Resource
    private CacheManager cacheManager;

    @Test
    public void testCache() throws Exception {
        Cache wxMsgCache = cacheManager.getCache("WxMsg");
        if (wxMsgCache != null) {
            wxMsgCache.put("key", "test");
            System.out.println(wxMsgCache.get("key",String.class));
            Thread.sleep(5000);
            System.out.println(wxMsgCache.get("key",String.class));
        }
    }
}

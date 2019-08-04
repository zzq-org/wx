package cn.zzq.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务注解支持
@ServletComponentScan(basePackages = {"cn.zzq.wx.config.druid"})//Servlet、Filter扫描
public class WxApp {
    public static void main(String[] args) {
        SpringApplication.run(WxApp.class, args);
    }
}

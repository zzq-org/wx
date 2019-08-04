package cn.zzq.wx.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 */
@Configuration
@MapperScan("cn.zzq.wx.bs.mapper")//扫描mapper接口
public class MybatisConfig {

}

package cn.zzq.wx.config.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail wxTokenQuartzDetail(){
        return JobBuilder.newJob(WxTokenQuartzJob.class).withIdentity("wxTokenQuartzJob").storeDurably().build();
    }

    @Bean
    public Trigger wxTokenQuartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(115) //设置时间周期单位分钟
//                .withIntervalInSeconds(3) //测试用，3秒
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(wxTokenQuartzDetail())
                .withIdentity("wxTokenQuartzJob")
                .withSchedule(scheduleBuilder)
                .startAt(new Date())//启动时执行
                .build();
    }
}
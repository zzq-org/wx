package cn.zzq.wx.config.quartz;

import cn.zzq.wx.util.WxTokenUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class WxTokenQuartzJob extends QuartzJobBean {

    /**
     * 执行定时任务，定时获取微信token
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        WxTokenUtil.getToken();
    }
}

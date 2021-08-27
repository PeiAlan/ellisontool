package com.ellison.tool;

import com.ellison.constant.QuartzConst;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * 定时任务Job工具类
 *
 * @Author Ellison Pei
 * @Date 2021/8/26 15:31
 * @version 1.0
 **/
public class EllisonQuartzUtil {

    public static Long getTaskIdByJobKey(JobKey jobKey) {
        String name = jobKey.getName();
        return Long.valueOf(StringUtils.replace(name, QuartzConst.JOB_KEY_PREFIX, ""));
    }

    public static Integer getTaskIdByTriggerKey(TriggerKey triggerKey) {
        String name = triggerKey.getName();
        return Integer.valueOf(StringUtils.replace(name, QuartzConst.TRIGGER_KEY_PREFIX, ""));
    }

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long taskId) {
        return TriggerKey.triggerKey(QuartzConst.TRIGGER_KEY_PREFIX + taskId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long taskId) {
        return JobKey.jobKey(QuartzConst.JOB_KEY_PREFIX + taskId);
    }
}

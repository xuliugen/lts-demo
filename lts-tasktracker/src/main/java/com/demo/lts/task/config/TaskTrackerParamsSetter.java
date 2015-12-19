package com.demo.lts.task.config;

import com.demo.lts.task.bean.TaskTrackerCfgBean;
import com.lts.core.constant.Level;
import com.lts.tasktracker.TaskTracker;

import java.util.Map;

/**
 * 设置TackTracker的参数
 * Created by xuliugen on 15/12/9.
 */
public class TaskTrackerParamsSetter {

    public static TaskTracker setTaskTrackerParams(TaskTrackerCfgBean cfg) {
        final TaskTracker taskTracker = new TaskTracker();
        //加载TaskTracker的配置文件
        taskTracker.setJobRunnerClass(cfg.getJobRunnerClass());
        taskTracker.setRegistryAddress(cfg.getRegistryAddress());
        taskTracker.setNodeGroup(cfg.getNodeGroup());
        taskTracker.setClusterName(cfg.getClusterName());
        taskTracker.setWorkThreads(cfg.getWorkThreads());
        taskTracker.setDataPath(cfg.getDataPath());
        // 业务日志级别
        if (cfg.getBizLoggerLevel() == null) {
            taskTracker.setBizLoggerLevel(Level.INFO);
        } else {
            taskTracker.setBizLoggerLevel(cfg.getBizLoggerLevel());
        }

        for (Map.Entry<String, String> config : cfg.getConfigs().entrySet()) {
            taskTracker.addConfig(config.getKey(), config.getValue());
        }

        return taskTracker;
    }
}

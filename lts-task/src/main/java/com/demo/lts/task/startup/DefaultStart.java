package com.demo.lts.task.startup;

import com.demo.lts.task.cfg.TaskTrackerCfg;
import com.lts.core.constant.Level;
import com.lts.tasktracker.TaskTracker;

import java.util.Map;

/**
 * Created by xuliugen on 15/12/9.
 */
public class DefaultStart {

    public static TaskTracker start(TaskTrackerCfg cfg) {
        final TaskTracker taskTracker = new TaskTracker();
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

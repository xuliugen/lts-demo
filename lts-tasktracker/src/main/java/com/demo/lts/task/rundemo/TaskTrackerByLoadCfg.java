package com.demo.lts.task.rundemo;

import com.demo.lts.task.bean.TaskTrackerCfgBean;
import com.demo.lts.task.exception.TaskTrackerCfgException;
import com.demo.lts.task.loader.TaskTrackerCfgLoader;
import com.demo.lts.task.config.TaskTrackerParamsSetter;
import com.lts.tasktracker.TaskTracker;

/**
 * 使用编码的方式启动tasktracker
 * Created by xuliugen on 15/12/4.
 */
public class TaskTrackerByLoadCfg {

    public static void main(String[] args) {

        String confPath = args[0];
        try {
            TaskTrackerCfgBean cfg = TaskTrackerCfgLoader.load(confPath);
            final TaskTracker taskTracker;

            if (cfg.isUseSpring()) {
                taskTracker = TaskTrackerBySpring.start(confPath);
            } else {
                taskTracker = TaskTrackerParamsSetter.setTaskTrackerParams(cfg);
            }

            taskTracker.start();
            System.out.println("MyJobRunner TaskTracker 启动成功!");

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    taskTracker.stop();
                }
            }));
        } catch (TaskTrackerCfgException e) {
            System.err.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
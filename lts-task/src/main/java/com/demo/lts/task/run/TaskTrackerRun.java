package com.demo.lts.task.run;

import com.demo.lts.task.cfg.TaskTrackerCfg;
import com.demo.lts.task.exception.CfgException;
import com.demo.lts.task.loader.TaskTrackerCfgLoader;
import com.demo.lts.task.startup.DefaultStart;
import com.demo.lts.task.startup.SpringStart;
import com.lts.tasktracker.TaskTracker;

/**
 * 编码方式启动tasktracker
 * Created by xuliugen on 15/12/4.
 */
public class TaskTrackerRun {

    public static void main(String[] args) {

        String confPath = args[0];
        try {
            TaskTrackerCfg cfg = TaskTrackerCfgLoader.load(confPath);
            final TaskTracker taskTracker;

            if (cfg.isUseSpring()) {
                taskTracker = SpringStart.start(confPath);
            } else {
                taskTracker = DefaultStart.start(cfg);
            }

            taskTracker.start();
            System.out.println("MyRunner TaskTracker 启动成功!");

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    taskTracker.stop();
                }
            }));

        } catch (CfgException e) {
            System.err.println("Error:" + e.getMessage());
            e.printStackTrace();
        }

    }
}
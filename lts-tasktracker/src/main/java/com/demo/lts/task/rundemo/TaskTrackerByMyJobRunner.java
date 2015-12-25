package com.demo.lts.task.rundemo;

import com.demo.lts.task.myrunner.MyJobRunner;
import com.lts.tasktracker.TaskTracker;

/**
 * 运行自定义的JobRunner
 * Created by xuliugen on 15/12/16.
 */
public class TaskTrackerByMyJobRunner {

    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.setJobRunnerClass(MyJobRunner.class);
        taskTracker.setRegistryAddress("zookeeper://172.16.61.101:2181");
        taskTracker.addConfig("lts.monitor.url", "http:/172.16.61.101:8081");
        taskTracker.setNodeGroup("jhd_myrunner_taskTracker");
        taskTracker.setClusterName("jhd_cluster");
        taskTracker.setWorkThreads(20);
        try {
            taskTracker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("MyJobRunner TaskTracker 启动成功!");
    }
}

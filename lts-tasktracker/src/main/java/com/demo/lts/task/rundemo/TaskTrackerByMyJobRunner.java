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
        taskTracker.setRegistryAddress("zookeeper://192.168.31.229:2181");
        taskTracker.addConfig("lts.monitor.url", "http:/192.168.31.229:8081");
        taskTracker.setNodeGroup("xuliugen_node_taskTracker");
        taskTracker.setClusterName("xuliugen_cluster");
        taskTracker.setWorkThreads(20);
        taskTracker.start();
        System.out.println("MyJobRunner TaskTracker 启动成功!");
    }
}

package com.demo.lts.task.run;

import com.demo.lts.task.jobrunner.MyRunner;
import com.lts.tasktracker.TaskTracker;

/**
 * Created by xuliugen on 15/12/16.
 */
public class TaskTrackerStartUpLocal {

    public static void main(String[] args){
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.setJobRunnerClass(MyRunner.class);
        taskTracker.setRegistryAddress("zookeeper://127.0.0.1:2181");
        taskTracker.addConfig("lts.monitor.url","http:/localhost:8081");
        taskTracker.setNodeGroup("jihuiduo_task_node_group");
        taskTracker.setClusterName("jihuiduo_cluster");
        taskTracker.setWorkThreads(20);
        taskTracker.start();
        System.out.println("MyRunner TaskTracker 启动成功!");
    }
}

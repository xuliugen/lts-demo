package com.demo.lts.client.run;

import com.demo.lts.client.listener.MasterChangeListenerImpl;
import com.demo.lts.client.listener.NodeChangeListenerImpl;
import com.lts.core.domain.Job;
import com.lts.jobclient.JobClient;
import com.lts.jobclient.RetryJobClient;
import com.lts.jobclient.domain.Response;

import java.util.Calendar;

/**
 * 通过编码方式部署client
 * Created by xuliugen on 15/12/3.
 */
public class JobClientByCommon {

    public static void main(String[] args) {

        // 启动一个客户端，用来接收反馈信息
        JobClient myJobClient = new RetryJobClient();
        myJobClient.setNodeGroup("xuliugen_job_group");
        myJobClient.setRegistryAddress("zookeeper://192.168.31.229:2181");
        myJobClient.setClusterName("xuliugen_cluster");
        // master节点变化的监听器
        myJobClient.addMasterChangeListener(new MasterChangeListenerImpl());
        myJobClient.addNodeChangeListener(new NodeChangeListenerImpl());
        myJobClient.start();

        // 提交任务
        Job myJob = new Job();
        myJob.setTaskId("xuliugen_task_one");
        // 设置任务类型
        myJob.setParam("time", Calendar.getInstance().getTime().toString());
        myJob.setParam("name", "xuliugen");
        myJob.setTaskTrackerNodeGroup("xuliugen_node_taskTracker");
        //myJob.setCronExpression("0/5 * * * * ? ");  // 支持 cronExpression表达式
        // myJob.setTriggerTime(new Date()); // 支持指定时间执行
        // 如果不设置指定时间或者cron表达式，提交的就是单次实时任务
        Response myJobResponse = myJobClient.submitJob(myJob);
        System.out.println("任务执行结果");
        System.out.println(myJobResponse.toString());
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

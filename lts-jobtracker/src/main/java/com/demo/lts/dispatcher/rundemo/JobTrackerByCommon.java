package com.demo.lts.dispatcher.rundemo;

import com.lts.jobtracker.JobTracker;
import com.lts.jobtracker.support.policy.OldDataDeletePolicy;

/**
 * 启动jobtracker：手动的设置配置信息
 * Created by xuliugen on 15/12/16.
 */
public class JobTrackerByCommon {

    public static void main(String[] args) {
        JobTracker jobTracker = new JobTracker();
        jobTracker.setRegistryAddress("zookeeper://192.168.31.229:2181");
        //LTS-admin的监控地址
        jobTracker.addConfig("lts.monitor.url", "http://192.168.31.229:8081");

        jobTracker.setIdentity("xuliugen_JobTracker");
        jobTracker.setListenPort(3502);
        jobTracker.setClusterName("xuliugen_cluster");
        jobTracker.addConfig("job.logger", "mysql");
        jobTracker.addConfig("job.queue", "mysql");
        jobTracker.addConfig("zk.client", "zkclient");

        jobTracker.addConfig("job.queue", "mysql");
        jobTracker.addConfig("jdbc.url", "jdbc:mysql://192.168.31.229:3306/lts");
        jobTracker.addConfig("jdbc.username", "root");
        jobTracker.addConfig("jdbc.password", "root");
        jobTracker.setOldDataHandler(new OldDataDeletePolicy());
        jobTracker.start();
        System.out.println("JobTracker 启动成功");
    }
}

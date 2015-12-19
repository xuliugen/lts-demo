package com.demo.lts.client.run;

import com.demo.lts.client.listener.MasterChangeListenerImpl;
import com.lts.core.domain.Job;
import com.lts.core.listener.MasterChangeListener;
import com.lts.jobclient.JobClient;
import com.lts.jobclient.domain.Response;
import com.lts.spring.JobClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Properties;

/**
 * 使用注解获得client实例并运行
 * Created by xuliugen on 15/12/8.
 */
@Configuration
public class JobClientByAnnotation {

    public static void main(String[] args) throws Exception {
        JobClient myJobClient = JobClientByAnnotation.getJobClient();
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

        }
    }

    /**
     * 获取JobClient
     */
    @Bean(name = "jobClient")
    public static JobClient getJobClient() throws Exception {
        JobClientFactoryBean factoryBean = new JobClientFactoryBean();
        factoryBean.setClusterName("xuliugen_cluster");
        factoryBean.setRegistryAddress("zookeeper://119.29.17.244:2181");
        factoryBean.setNodeGroup("xuliugen_job_group");
        // master节点发生变化的监听器
        factoryBean.setMasterChangeListeners(new MasterChangeListener[]{
                new MasterChangeListenerImpl()
        });

        Properties configs = new Properties();
        configs.setProperty("job.fail.store", "leveldb");
        // 监控频率，单位：分钟，整数。默认1分钟
        //configs.setProperty("lts.monitor.interval","3");
        // 底层的通信框架,netty或者mina
        //configs.setProperty("lts.remoting","netty");
        // 底层序列化方式 fastjson, hessian2 ，java
        configs.setProperty("lts.remoting.serializable.default", "fastjson");
        factoryBean.setConfigs(configs);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}

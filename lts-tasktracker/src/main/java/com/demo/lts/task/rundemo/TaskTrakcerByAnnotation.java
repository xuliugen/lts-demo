package com.demo.lts.task.rundemo;

import com.demo.lts.client.listener.MasterChangeListenerImpl;
import com.demo.lts.task.myrunner.MyJobRunner;
import com.lts.core.listener.MasterChangeListener;
import com.lts.spring.TaskTrackerAnnotationFactoryBean;
import com.lts.tasktracker.TaskTracker;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 注解的方式获得TaskTracker实例
 * Created by xuliugen on 15/12/8.
 */
@Configuration
public class TaskTrakcerByAnnotation implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public TaskTracker getTaskTracker() throws Exception {
        TaskTrackerAnnotationFactoryBean factoryBean = new TaskTrackerAnnotationFactoryBean();
        factoryBean.setApplicationContext(applicationContext);
        factoryBean.setClusterName("xuliugen_cluster");
        factoryBean.setJobRunnerClass(MyJobRunner.class);
        factoryBean.setNodeGroup("xuliugen_TaskTracker");
        factoryBean.setBizLoggerLevel("INFO");
        factoryBean.setRegistryAddress("zookeeper://192.168.31.229:2181");
        factoryBean.setMasterChangeListeners(new MasterChangeListener[]{
                new MasterChangeListenerImpl()
        });
        factoryBean.setWorkThreads(20);
        Properties configs = new Properties();
        configs.setProperty("job.fail.store", "leveldb");
        factoryBean.setConfigs(configs);

        factoryBean.afterPropertiesSet();
//        factoryBean.setTaskTrackerParams();
        return factoryBean.getObject();
    }
}

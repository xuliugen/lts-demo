package com.demo.lts.client.annotation;

import com.demo.lts.client.listener.MasterChangeListenerImpl;
import com.lts.core.listener.MasterChangeListener;
import com.lts.jobclient.JobClient;
import com.lts.spring.JobClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 使用注解获得client实例
 * Created by xuliugen on 15/12/8.
 */
@Configuration
public class JobClientConfig {

    @Bean(name = "jobClient")
    public JobClient getJobClient() throws Exception {
        JobClientFactoryBean factoryBean = new JobClientFactoryBean();
        factoryBean.setClusterName("xuliugen_cluster");
        factoryBean.setRegistryAddress("zookeeper://119.29.17.244:2181");
        factoryBean.setNodeGroup("xuliugen_jobClient");
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

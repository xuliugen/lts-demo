package com.demo.lts.dispatcher.rundemo;

import com.demo.lts.client.listener.MasterChangeListenerImpl;
import com.demo.lts.dispatcher.bean.JobTrackerCfgBean;
import com.demo.lts.dispatcher.loader.JobTrackerCfgLoader;
import com.lts.jobtracker.JobTracker;

import java.util.Map;

/**
 * 启动jobtracker：这个是从配置文件中读取配置信息
 * Created by xuliugen on 15/12/8.
 */
public class JobTrackerByLoadCfg {

    public static void main(String[] args) {
        try {
            String confPath = args[0];
            // 读配置
            JobTrackerCfgBean cfg = JobTrackerCfgLoader.load(confPath);

            final JobTracker jobTracker = new JobTracker();
            jobTracker.setRegistryAddress(cfg.getRegistryAddress());
            jobTracker.setListenPort(cfg.getListenPort());
            jobTracker.setClusterName(cfg.getClusterName());
            jobTracker.addMasterChangeListener(new MasterChangeListenerImpl());

            for (Map.Entry<String, String> config : cfg.getConfigs().entrySet()) {
                jobTracker.addConfig(config.getKey(), config.getValue());
            }

            jobTracker.start();
            System.out.println("jobtracker 启动成功!");

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                public void run() {
                    jobTracker.stop();
                }
            }));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

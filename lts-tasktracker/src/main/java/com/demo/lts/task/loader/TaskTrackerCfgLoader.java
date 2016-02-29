package com.demo.lts.task.loader;

import com.demo.lts.task.bean.TaskTrackerCfgBean;
import com.demo.lts.task.exception.TaskTrackerCfgException;
import com.lts.core.commons.utils.Assert;
import com.lts.core.commons.utils.StringUtils;
import com.lts.core.constant.Level;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 加载TaskTracker的配置文件tasktracker.bean
 * Created by xuliugen on 15/12/9.
 */
public class TaskTrackerCfgLoader {

    public static TaskTrackerCfgBean load(String confPath) throws TaskTrackerCfgException {

        String cfgPath = confPath + "/tasktracker.bean";
        String log4jPath = confPath + "/log4j.properties";

        Properties conf = new Properties();
        File file = new File(cfgPath);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new TaskTrackerCfgException("can not find " + cfgPath);
        }
        try {
            conf.load(is);
        } catch (IOException e) {
            throw new TaskTrackerCfgException("Read " + cfgPath + " error.", e);
        }

        TaskTrackerCfgBean cfg = new TaskTrackerCfgBean();

        try {
            String registryAddress = conf.getProperty("registryAddress");
            Assert.hasText(registryAddress, "registryAddress can not be null.");
            cfg.setRegistryAddress(registryAddress);

            String clusterName = conf.getProperty("clusterName");
            Assert.hasText(clusterName, "clusterName can not be null.");
            cfg.setClusterName(clusterName);

            String jobRunnerClass = conf.getProperty("jobRunnerClass");
            Assert.hasText(jobRunnerClass, "jobRunnerClass can not be null.");
            cfg.setJobRunnerClass(Class.forName(jobRunnerClass));

            String nodeGroup = conf.getProperty("nodeGroup");
            Assert.hasText(nodeGroup, "nodeGroup can not be null.");
            cfg.setNodeGroup(nodeGroup);

            String workThreads = conf.getProperty("workThreads");
            Assert.hasText(workThreads, "workThreads can not be null.");
            cfg.setWorkThreads(Integer.parseInt(workThreads));

            cfg.setDataPath(conf.getProperty("dataPath"));

            String useSpring = conf.getProperty("useSpring");
            if (StringUtils.isNotEmpty(useSpring)) {
                cfg.setUseSpring(Boolean.valueOf(useSpring));
            }

            String bizLoggerLevel = conf.getProperty("bizLoggerLevel");
            if (StringUtils.isNotEmpty(bizLoggerLevel)) {
                cfg.setBizLoggerLevel(Level.valueOf(bizLoggerLevel));
            }

            Map<String, String> configs = new HashMap<String, String>();
            for (Map.Entry<Object, Object> entry : conf.entrySet()) {
                String key = entry.getKey().toString();
                if (key.startsWith("configs.")) {
                    String value = entry.getValue() == null ? null : entry.getValue().toString();
                    configs.put(key.replace("configs.", ""), value);
                }
            }

            cfg.setConfigs(configs);
        } catch (Exception e) {
            throw new TaskTrackerCfgException(e);
        }

        //  log4j 配置文件路径
        PropertyConfigurator.configure(log4jPath);

        return cfg;
    }
}

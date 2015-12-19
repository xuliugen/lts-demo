package com.demo.lts.dispatcher.loader;

import com.demo.lts.dispatcher.bean.JobTrackerCfgBean;
import com.demo.lts.dispatcher.exception.JobTrackerCfgException;
import com.lts.core.commons.utils.StringUtils;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 启动配置加载
 * Created by xuliugen on 15/12/9.
 */
public class JobTrackerCfgLoader {

    public static JobTrackerCfgBean load(String confPath) throws JobTrackerCfgException {

        String cfgPath = confPath + "/jobtrackerRun.bean";
        String log4jPath = confPath + "/log4j.properties";

        Properties conf = new Properties();
        File file = new File(cfgPath);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new JobTrackerCfgException("can not find " + cfgPath);
        }
        try {
            conf.load(is);
        } catch (IOException e) {
            throw new JobTrackerCfgException("Read " + cfgPath + " error.", e);
        }

        JobTrackerCfgBean cfg = new JobTrackerCfgBean();
        String registryAddress = conf.getProperty("registryAddress");
        if (StringUtils.isEmpty(registryAddress)) {
            throw new JobTrackerCfgException("registryAddress can not be null.");
        }
        cfg.setRegistryAddress(registryAddress);

        String clusterName = conf.getProperty("clusterName");
        if (StringUtils.isEmpty(clusterName)) {
            throw new JobTrackerCfgException("clusterName can not be null.");
        }
        cfg.setClusterName(clusterName);

        String listenPort = conf.getProperty("listenPort");
        if (StringUtils.isEmpty(listenPort) || !StringUtils.isInteger(listenPort)) {
            throw new JobTrackerCfgException("listenPort can not be null.");
        }
        cfg.setListenPort(Integer.parseInt(listenPort));

        Map<String, String> configs = new HashMap<String, String>();
        for (Map.Entry<Object, Object> entry : conf.entrySet()) {
            String key = entry.getKey().toString();
            if (key.startsWith("configs.")) {
                String value = entry.getValue() == null ? null : entry.getValue().toString();
                configs.put(key.replace("configs.", ""), value);
            }
        }

        cfg.setConfigs(configs);

        //  log4j 配置文件路径
        PropertyConfigurator.configure(log4jPath);

        return cfg;
    }
}

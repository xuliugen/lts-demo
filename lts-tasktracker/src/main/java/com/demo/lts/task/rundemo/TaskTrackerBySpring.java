package com.demo.lts.task.rundemo;

import com.lts.core.logger.Logger;
import com.lts.core.logger.LoggerFactory;
import com.lts.tasktracker.TaskTracker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用Spring的方式启动TaskTracker
 * Created by xuliugen on 15/12/9.
 */
public class TaskTrackerBySpring {

    public static TaskTracker start(String cfgPath) {
        System.setProperty("lts.tasktracker.bean.path", cfgPath);

        ApplicationContext context = new LTSXmlApplicationContext(
                new String[]{"classpath*:spring/*.xml"}
        );
        return (TaskTracker) context.getBean("ltsTaskTracker");
    }
}

/**
 * LTSXML的ApplicationContext
 */
class LTSXmlApplicationContext extends AbstractXmlApplicationContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(LTSXmlApplicationContext.class);
    private Resource[] configResources;

    public LTSXmlApplicationContext(String[] paths) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Resource> resourceList = new ArrayList<Resource>();
        if (paths != null && paths.length > 0) {
            for (String path : paths) {
                try {
                    Resource[] resources = resolver.getResources(path);
                    if (resources != null && resources.length > 0) {
                        Collections.addAll(resourceList, resources);
                    }
                } catch (IOException e) {
                    LOGGER.error("resolve resource error: [path={}]", path, e);
                }
            }
        }

        configResources = new Resource[resourceList.size()];
        resourceList.toArray(configResources);

        refresh();
    }

    @Override
    protected Resource[] getConfigResources() {
        return configResources;
    }
}
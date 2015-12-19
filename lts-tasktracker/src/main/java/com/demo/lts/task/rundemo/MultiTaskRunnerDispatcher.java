package com.demo.lts.task.rundemo;

import com.lts.core.domain.Action;
import com.lts.core.domain.Job;
import com.lts.tasktracker.Result;
import com.lts.tasktracker.runner.JobRunner;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个Runner作为dispatcher,去实现单个taskTracker执行多任务
 * Created by xuliugen on 15/12/7.
 */
public class MultiTaskRunnerDispatcher implements JobRunner {

    private static final ConcurrentHashMap<String, JobRunner> JOB_RUNNER_MAP = new ConcurrentHashMap<String, JobRunner>();

    static {
        JOB_RUNNER_MAP.put("jobA", new JobRunnerA()); // 也可以从Spring中拿
        JOB_RUNNER_MAP.put("jobB", new JobRunnerB());
    }

    public Result run(Job job) throws Throwable {
        String type = job.getParam("type");
        return JOB_RUNNER_MAP.get(type).run(job);
    }
}

class JobRunnerA implements JobRunner {

    public Result run(Job job) throws Throwable {
        return new Result(Action.EXECUTE_SUCCESS, "execute job A");
    }
}

class JobRunnerB implements JobRunner {

    public Result run(Job job) throws Throwable {
        return new Result(Action.EXECUTE_SUCCESS, "execute job B");
    }
}
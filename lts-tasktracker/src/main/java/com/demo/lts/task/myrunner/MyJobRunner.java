package com.demo.lts.task.myrunner;

import com.lts.core.domain.Action;
import com.lts.core.domain.Job;
import com.lts.tasktracker.Result;
import com.lts.tasktracker.logger.BizLogger;
import com.lts.tasktracker.runner.JobRunner;
import com.lts.tasktracker.runner.LtsLoggerFactory;

/**
 * 自定义的任务执行类
 * Created by xuliugen on 15/12/4.
 */
public class MyJobRunner implements JobRunner {

    private final static BizLogger logger = LtsLoggerFactory.getBizLogger();

    public Result run(Job job) throws Throwable {
        // 取出提交的任务里面的参数来记日志
        String committer;
        try {
            //获取JobClient中的参数name
            committer = job.getParam("name");
            System.out.println("this is " + committer);
            logger.info("the log:" + job.getParam(committer));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Action.EXECUTE_EXCEPTION, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "job executed success。committer:" + committer);
    }
}

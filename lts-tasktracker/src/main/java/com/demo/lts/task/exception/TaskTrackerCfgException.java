package com.demo.lts.task.exception;

/**
 * Created by xuliugen on 15/12/9.
 */
public class TaskTrackerCfgException extends Exception {

    private static final long serialVersionUID = -661377294271386745L;

    public TaskTrackerCfgException() {
        super();
    }

    public TaskTrackerCfgException(String message) {
        super(message);
    }

    public TaskTrackerCfgException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskTrackerCfgException(Throwable cause) {
        super(cause);
    }
}

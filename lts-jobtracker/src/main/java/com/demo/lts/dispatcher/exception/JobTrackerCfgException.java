package com.demo.lts.dispatcher.exception;

/**
 * Created by xuliugen on 15/12/9.
 */
public class JobTrackerCfgException extends Exception{

    private static final long serialVersionUID = -661377294271386745L;

    public JobTrackerCfgException() {
        super();
    }

    public JobTrackerCfgException(String message) {
        super(message);
    }

    public JobTrackerCfgException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobTrackerCfgException(Throwable cause) {
        super(cause);
    }
}

package com.demo.lts.task.exception;

/**
 * Created by xuliugen on 15/12/9.
 */
public class CfgException extends Exception {

    private static final long serialVersionUID = -661377294271386745L;

    public CfgException() {
        super();
    }

    public CfgException(String message) {
        super(message);
    }

    public CfgException(String message, Throwable cause) {
        super(message, cause);
    }

    public CfgException(Throwable cause) {
        super(cause);
    }
}

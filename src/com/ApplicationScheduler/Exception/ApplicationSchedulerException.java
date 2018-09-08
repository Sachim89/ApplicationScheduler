package com.ApplicationScheduler.Exception;

/**
 * Exception Handler for Application Scheduler
 * @author Saranya
 *
 */
@SuppressWarnings("serial")
public class ApplicationSchedulerException extends Exception{
	private final String reason;
    /**
     * Associates the exceptions thrown by other classes.
     * @param reason	The exception message.
     */
    public ApplicationSchedulerException(String reason){
        this.reason = reason;
    }

    /** Returns the Exception Message. */
    public String toString(){
        return reason;
    }

}

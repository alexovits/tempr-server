package edu.endava.tempr.api.exception;

/**
 * Created by zsoltszabo on 5/11/17.
 */
public class OutOfHistogramRangeException extends Exception {
    public final String message;

    public OutOfHistogramRangeException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}

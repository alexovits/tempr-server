package edu.endava.tempr.api.exception;

/**
 * Created by zsoltszabo on 5/15/17.
 */
public class SensorNotFoundException extends Exception {
    public final String message;

    public SensorNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

package edu.endava.tempr.api.exception;

/**
 * Created by zsoltszabo on 5/12/17.
 */
public class ThermostatNotFoundException extends Exception {
    public final String message;

    public ThermostatNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

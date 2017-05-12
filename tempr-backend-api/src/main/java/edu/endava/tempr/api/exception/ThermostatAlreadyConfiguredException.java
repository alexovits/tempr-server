package edu.endava.tempr.api.exception;

/**
 * Created by zsoltszabo on 5/12/17.
 */
public class ThermostatAlreadyConfiguredException extends Exception {
    final String message;

    public ThermostatAlreadyConfiguredException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

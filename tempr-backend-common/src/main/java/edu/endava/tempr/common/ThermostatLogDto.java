package edu.endava.tempr.common;

import java.time.LocalDateTime;

/**
 * Created by zsoltszabo on 19/12/2016.
 */
public class ThermostatLogDto extends AbstractDto {
    private LocalDateTime logTimeStamp;
    private String token;
    private String extTemp;
    private String intTemp;

    public LocalDateTime getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(LocalDateTime logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public String getExtTemp() {
        return extTemp;
    }

    public void setExtTemp(String extTemp) {
        this.extTemp = extTemp;
    }

    public String getIntTemp() {
        return intTemp;
    }

    public void setIntTemp(String intTemp) {
        this.intTemp = intTemp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ThermostatLogDto{" +
                "logTimeStamp='" + logTimeStamp + '\'' +
                ", extTemp='" + extTemp + '\'' +
                ", intTemp='" + intTemp + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

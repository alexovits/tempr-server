package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 19/12/2016.
 */
public class ThermostatLogDto extends AbstractDto {
    private String logTimeStamp;
    private String deviceId;
    private String extTemp;
    private String intTemp;

    public String getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(String logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
}

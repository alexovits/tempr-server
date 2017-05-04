package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public class ThermostatDto extends AbstractDto {
    private String token;
    private String name;
    private Short configured;
    private Long userId;
    private Integer desiredTemperature;

    public ThermostatDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getConfigured() {
        return configured;
    }

    public void setConfigured(Short configured) {
        this.configured = configured;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(Integer desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    @Override
    public String toString() {
        return String.format("ThermostatDTO{ name=%1$s, token=%2$s, configured=%3$d, userId=%4$d, desiredTemperature=%5$d}",
                name, token, configured, userId, desiredTemperature);
    }
}

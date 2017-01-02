package edu.endava.tempr.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public class ThermostatDto extends AbstractDto{
    private String token;
    private String name;
    private Short configured;
    private Long userId;
    private List<ThermostatLogDto> thermostatLogDtoList = new ArrayList<>();

    public ThermostatDto(){}

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getConfigured() {
        return configured;
    }

    public void setConfigured(Short configured) {
        this.configured = configured;
    }

    @Override
    public String toString() {
        return "ThermostatDto{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", configured='" + configured + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public List<ThermostatLogDto> getThermostatLogDtoList() {
        return thermostatLogDtoList;
    }

    public void setThermostatLogDtoList(List<ThermostatLogDto> thermostatLogDtoList) {
        this.thermostatLogDtoList = thermostatLogDtoList;
    }
}

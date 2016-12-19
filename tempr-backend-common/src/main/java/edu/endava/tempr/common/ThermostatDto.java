package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public class ThermostatDto extends AbstractDto{
    private String token;
    private String name;
    private Long userId;

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

    @Override
    public String toString() {
        return "ThermostatDto{" +
                "devicename='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

package edu.endava.tempr.common;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends AbstractDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<ThermostatDto> thermostatDtoList = new ArrayList<>();

    public UserDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public List<ThermostatDto> getThermostatDtoList() {
        return thermostatDtoList;
    }

    public void setThermostatDtoList(List<ThermostatDto> thermostatDtoList) {
        this.thermostatDtoList = thermostatDtoList;
    }
}

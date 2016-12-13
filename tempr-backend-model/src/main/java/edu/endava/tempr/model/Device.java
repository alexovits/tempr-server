package edu.endava.tempr.model;

import javax.persistence.*;

/**
 * Created by zsoltszabo on 13/12/2016.
 */
@Entity
@Table(name = "Devices")
public class Device extends BaseEntity {
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "devicename", nullable = false)
    private String deviceName;

    @Column(name = "userId", nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long userId;

    public Device(){}

    public Device(String token, String deviceName, Long userId){
        this.userId = userId;
        this.deviceName = deviceName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getUserid() {
        return userId;
    }

    public void setUserid(Long userid) {
        this.userId = userid;
    }
}

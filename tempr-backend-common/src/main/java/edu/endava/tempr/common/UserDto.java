package edu.endava.tempr.common;

public class UserDto extends AbstractDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String userType; //CR: Is this ok? The enum is present in the model module.

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return String.format("UserDTO{ username=%1$s, password=%2$s, firstName=%3$s, lastName=%4$s, email=%5$s, userType=%6$s}",
                username, password, firstName, lastName, email, userType);
    }
}

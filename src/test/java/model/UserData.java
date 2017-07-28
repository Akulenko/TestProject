package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class UserData {
    @XStreamAlias("firstName")
    private String firstName;

    @XStreamAlias("lastName")
    private String lastName;

    @XStreamAlias("email")
    private String email;

    @XStreamAlias("password")
    private String password;


    public UserData(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
}

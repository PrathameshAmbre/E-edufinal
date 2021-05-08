package com.example.eedu;

public class UserInformation {
    public String email;
    public String name;
    public String surname;

    public UserInformation(String getemail, long time){
    }

    public UserInformation(String name,String surname){
        this.email=email;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

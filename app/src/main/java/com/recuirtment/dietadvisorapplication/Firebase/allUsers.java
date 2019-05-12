package com.recuirtment.dietadvisorapplication.Firebase;

public class allUsers {

    public String username;
    public String email;
    public  String pass;
    public String ages;
    public String phone;
    public String address;
    public String gender;
    public String provider;

    public allUsers(String username, String email, String pass, String ages, String phone, String address, String gender,String provider) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.ages = ages;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.provider=provider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}

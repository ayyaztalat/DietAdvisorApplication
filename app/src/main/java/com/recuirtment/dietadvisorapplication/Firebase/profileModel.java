package com.recuirtment.dietadvisorapplication.Firebase;

import android.widget.Spinner;

public class profileModel {
   public String username;
    public String email;
    public  String pass;
    public String ages;
    public String phone;
    public String address;
    public String gender;
    public String provider;




    public profileModel(String username, String email, String pass, String ages, String phone, String address, String gender,String provider) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.ages = ages;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.provider=provider;
    }

   /* public String getUserName() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return ages;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }*/
}

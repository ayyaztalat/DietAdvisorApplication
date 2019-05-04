package com.recuirtment.dietadvisorapplication.Pre;

import android.content.Context;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

public class Preferences {

    public Preferences(Context context) {
        new Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context.getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

    }

    public static String  getTempPass() {
        return Prefs.getString("passwordTemp","");
    }

    public static String getTempEmail() {
        return   Prefs.getString("emailTemp","");
    }

    public void setChecking(Boolean checking){
        Prefs.putBoolean("checking",checking);
    }

    public Boolean getChecking(){
        return Prefs.getBoolean("checking",false);
    }

    public void setTempPassword(String password) {
        Prefs.putString("passwordTemp",password);
    }

    public void setTempEmail(String email) {
        Prefs.putString("emailTemp",email);
    }

    public void setEmail(String email) {
        Prefs.putString("email",email);
    }

    public void setUsername(String username) {
        Prefs.putString("username",username);
    }

    public void setPass(String pass) {
        Prefs.putString("pass",pass);
    }

    public void setAge(String ages) {
        Prefs.putString("ages",ages);
    }

    public void setPhone(String phone) {
        Prefs.putString("phone",phone);
    }

    public String getEmail() {
        return Prefs.getString("email","");
    }

    public String getUsername() {
        return Prefs.getString("username","");
    }

    public String getPass() {
        return  Prefs.getString("pass","");
    }

    public String getAge() {
        return Prefs.getString("ages","");
    }

    public String getPhone() {
        return   Prefs.getString("phone","");
    }

    public void setAddress(String address) {
        Prefs.putString("address",address);
    }
    public String getAddress(){
        return Prefs.getString("address","");
    }

    public Boolean getLoginCheck() {
        return  Prefs.getBoolean("login_check",false);
    }

    public void setLoginCheck(boolean b) {
        Prefs.putBoolean("login_check",b);
    }

    public void clearall() {
        Prefs.clear();

    }

    public void setGender(String gender) {
        Prefs.putString("gender",gender);
    }
    public String getGender(){
        return Prefs.getString("gender","");
    }

    public void setRating(float rating) {
        Prefs.putFloat("rating",rating);
    }

    public void setMessageReview(String messageReview) {
        Prefs.putString("messageReview",messageReview);
    }

    public float getRating() {
        return Prefs.getFloat("rating",0);
    }

    public String getMessageReview() {
        return Prefs.getString("messageReview","");
    }

    public int getServicesAdded() {
        return Prefs.getInt("service_added",0);
    }
    public void setServiceAdded(int value){
        Prefs.putInt("service_added",value);
    }

    public void setFood(String food) {
        Prefs.putString("food",food);
    }

    public void setDibitc(String dibatis) {
        Prefs.putString("dibatic",dibatis);
    }

    public void setVege(String vege) {
        Prefs.putString("vege",vege);
    }

    public String getFood() {
       return Prefs.getString("food","");
    }

    public String getDibitc() {
       return Prefs.getString("dibatic","");
    }

    public String getVege() {
      return   Prefs.getString("vege","");
    }
}

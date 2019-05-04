package com.recuirtment.dietadvisorapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

public class Signup extends AppCompatActivity {
    EditText user_name_field,mail_input_field,password_input_field,confirm_input_field,age,phone_input_field,address_input_field;
    TextView next_button;
    ImageView back_press;
    Spinner gender_spinner;
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        preferences=new Preferences(this);

        user_name_field=findViewById(R.id.user_name_field);
        mail_input_field=findViewById(R.id.mail_input_field);
        password_input_field=findViewById(R.id.password_input_field);
        confirm_input_field=findViewById(R.id.confirm_input_field);
        address_input_field=findViewById(R.id.address_input_field);
        age=findViewById(R.id.age);
        gender_spinner=findViewById(R.id.gender_spinner);
        phone_input_field=findViewById(R.id.phone_input_field);
        next_button=findViewById(R.id.next_button);
        back_press=findViewById(R.id.back_press);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick();
            }
        });

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onclick() {
        String username=user_name_field.getText().toString().trim();
        String email=mail_input_field.getText().toString().trim();
        String pass=password_input_field.getText().toString().trim();
        String confirm_pass=confirm_input_field.getText().toString().trim();
        String ages=age.getText().toString().trim();
        String phone=phone_input_field.getText().toString().trim();
        String address=address_input_field.getText().toString().trim();
        String gender=gender_spinner.getSelectedItem().toString();


        if (TextUtils.isEmpty(username)){
            user_name_field.setError("Please enter UserName");
        }else if (TextUtils.isEmpty(email)){
            mail_input_field.setError("Please enter email");
        }else if (TextUtils.isEmpty(pass)){
            password_input_field.setError("Please enter Password");
        }else if (TextUtils.isEmpty(confirm_pass)){
            confirm_input_field.setError("Please Confirm Password");
        }else if (TextUtils.isEmpty(ages)){
            age.setError("Please Confirm Password");
        }else if (TextUtils.isEmpty(phone)){
            phone_input_field.setError("Please Confirm Password");
        }else if (TextUtils.isEmpty(address)){
            address_input_field.setError("Please enter address");
        }else if (TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Please enter gender", Toast.LENGTH_SHORT).show();
        }else{

            if (!pass.equalsIgnoreCase(confirm_pass)){
                confirm_input_field.setError("Password and Confirm Password doesnot match");
            }else if (phone.length()<10){
                phone_input_field.setError("Phone number is not valid");
            }else {
                callStartAPI(username,email,pass,ages,phone,address,gender);
            }

        }
    }

    private void callStartAPI(String username, String email, String pass, String ages, String phone,String address,String gender) {
        preferences.setEmail(email);
        preferences.setUsername(username);
        preferences.setPass(pass);
        preferences.setAge(ages);
        preferences.setPhone(phone);
        preferences.setAddress(address);
        preferences.setGender(gender);

        startActivity(new Intent(getApplicationContext(),HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}

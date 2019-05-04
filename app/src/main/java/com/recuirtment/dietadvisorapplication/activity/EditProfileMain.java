package com.recuirtment.dietadvisorapplication.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

public class EditProfileMain extends AppCompatActivity {

    EditText user_name_field,mail_input_field,age,phone_input_field;
    Button save;
    ImageView imageView3;
    Spinner gender_spinner;
    Preferences preferences;
    ImageView back_press;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_main);

        preferences=new Preferences(this);
        user_name_field=findViewById(R.id.user_name_field);
        mail_input_field=findViewById(R.id.mail_input_field);
        age=findViewById(R.id.age);
        phone_input_field=findViewById(R.id.phone_input_field);
        back_press=findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save=findViewById(R.id.save);

        imageView3=findViewById(R.id.imageView3);
        gender_spinner=findViewById(R.id.gender_spinner);


        user_name_field.setText(preferences.getUsername());
        mail_input_field.setText(preferences.getEmail());
        age.setText(preferences.getAge());
        phone_input_field.setText(preferences.getPhone());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDataSaving();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPasswordResetDialogue();
            }
        });


    }

    private void callPasswordResetDialogue() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.popup_signup, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(EditProfileMain.this);

        final EditText oldpass = alertLayout.findViewById(R.id.old_password);
        final EditText newPass = alertLayout.findViewById(R.id.new_password);
        final EditText confirmPass = alertLayout.findViewById(R.id.confirm_password);
        final Button changePass = alertLayout.findViewById(R.id.btn_change_pass);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        dialog.show();

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass=oldpass.getText().toString().trim();
                String newpass=newPass.getText().toString().trim();
                String confirmpass=confirmPass.getText().toString().trim();

                if (!oldPass.equalsIgnoreCase(preferences.getPass())){
                    oldpass.setError("Password doesnot match");
                }else if (TextUtils.isEmpty(oldPass)){
                    oldpass.setError("Please enter old password");
                }else if (TextUtils.isEmpty(newpass)){
                    newPass.setError("Please enter new Password");
                }else if (TextUtils.isEmpty(confirmpass)){
                    confirmPass.setError("Please confirm password first");
                }else if (!newpass.equalsIgnoreCase(confirmpass)){
                    Toast.makeText(EditProfileMain.this, "New Password doesnot match with confirm password", Toast.LENGTH_SHORT).show();
                    confirmPass.setError("New Password doesnot match with confirm password");
                }else{
                    preferences.setPass(newpass);
                    dialog.dismiss();
                }
            }
        });




    }

    private void callDataSaving() {
        String user=user_name_field.getText().toString().trim();
        String mail=mail_input_field.getText().toString().trim();
        String ages=age.getText().toString().trim();
        String phone=phone_input_field.getText().toString().trim();

        String gender=gender_spinner.getSelectedItem().toString();

        preferences.setUsername(user);
        preferences.setEmail(mail);
        preferences.setAge(ages);
        preferences.setPhone(phone);
        preferences.setGender(gender);

        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


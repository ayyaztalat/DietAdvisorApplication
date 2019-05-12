package com.recuirtment.dietadvisorapplication.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.recuirtment.dietadvisorapplication.Firebase.profileModel;
import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

public class Signup extends AppCompatActivity {
    EditText user_name_field,mail_input_field,password_input_field,confirm_input_field,age,phone_input_field,address_input_field;
    TextView next_button;
    ImageView back_press;
    Spinner gender_spinner;
    Spinner provide_value;
    Preferences preferences;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        preferences=new Preferences(this);
        firebaseAuth = FirebaseAuth.getInstance();
        user_name_field=findViewById(R.id.user_name_field);
        mail_input_field=findViewById(R.id.mail_input_field);
        password_input_field=findViewById(R.id.password_input_field);
        confirm_input_field=findViewById(R.id.confirm_input_field);
        address_input_field=findViewById(R.id.address_input_field);
        age=findViewById(R.id.age);
        gender_spinner=findViewById(R.id.gender_spinner);
        provide_value=findViewById(R.id.provide_value);
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
        String provider=provide_value.getSelectedItem().toString();


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
                callStartAPI(username,email,pass,ages,phone,address,gender,provider);
            }

        }
    }

    private DatabaseReference mDatabase;
    private void callStartAPI(final String username, final String email, final String pass, final String ages, final String phone, final String address, final String gender, final String provider) {
        preferences.setEmail(email);
        preferences.setUsername(username);
        preferences.setPass(pass);
        preferences.setAge(ages);
        preferences.setPhone(phone);
        preferences.setAddress(address);
        preferences.setGender(gender);
        preferences.setProvider(provider);


        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                     //   progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Signup.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                           /* */
                           callDataSavingAPI(email,username,pass,ages,phone,address,gender,provider);
                        }
                    }
                });

       /* startActivity(new Intent(getApplicationContext(),HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();*/
    }

    private void callDataSavingAPI(String email, String username, String pass, String ages, String phone, String address, String gender, String provider) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        assert user != null;
        String userId = user.getUid();

        profileModel model=new profileModel(username,email,pass,ages,phone,address,gender,provider);


        mDatabase.child(userId).child("users").setValue(model);
        startActivity(new Intent(Signup.this, HomeActivity.class));
        finish();

    }
}

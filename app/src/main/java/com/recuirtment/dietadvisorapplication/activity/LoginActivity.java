package com.recuirtment.dietadvisorapplication.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText input_email,input_password;
    private CheckBox remember_me;
    private TextView forgot_password,guest;
    private Button login_button,signup_button;
    private Boolean checking;
    Preferences preferences;
    private String TAG="Login Class";
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        preferences=new Preferences(this);
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        remember_me=findViewById(R.id.remember_me);
        forgot_password=findViewById(R.id.forgot_password);
        guest=findViewById(R.id.guest);
        login_button=findViewById(R.id.login_button);
        signup_button=findViewById(R.id.signup_button);

        checking=preferences.getChecking();
        if (checking){
            input_email.setText(Preferences.getTempEmail());
            input_password.setText(Preferences.getTempPass());
            remember_me.setChecked(true);
        }else{
            Log.e(TAG, "onCreate: Nothing to show" );
        }
        onclicks();
    }

    private void onclicks() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLoginAPI();
            }
        });

        forgot_password.setVisibility(View.GONE);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // callPasswordResetDialogue();
            }
        });

        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checking=isChecked;
                preferences.setChecking(checking);

            }
        });

       /* guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WelcomeGuestActivity.class));
            }
        });*/

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
                finish();
            }
        });
    }

   /* private void callPasswordResetDialogue() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.popup_forgot_pass, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);

        final EditText forgot_password_input = alertLayout.findViewById(R.id.forgot_password_input);
        final Button btn_change_pass = alertLayout.findViewById(R.id.btn_change_pass);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        dialog.show();
        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Email Sent", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });*/


    private void callLoginAPI(){
        String email=input_email.getText().toString().trim();
        String password=input_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            input_email.setError("Please enter Email first");
        }else if (TextUtils.isEmpty(password))
        {
            input_password.setError("Please enter password");

        }else if (!isEmailValid(email)){
            input_email.setError("Please enter valid email");
        }
        else{

            checkLoginProcess(email,password);

        }

    }

    private void checkLoginProcess(String email, final String password) {
        if (!email.equalsIgnoreCase(preferences.getEmail())){
            Toast.makeText(this, "Wrong Email Address", Toast.LENGTH_SHORT).show();
        }else if (!password.equalsIgnoreCase(preferences.getPass())){
            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
        }else{
           /* preferences.setTempEmail(email);
            preferences.setTempPassword(password);*/

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            //progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // there was an error
                                if (password.length() < 6) {
                                    input_password.setError("Password size is 9 digits");
                                } else {
                                    Toast.makeText(LoginActivity.this, "Wrong username and Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

            /*startActivity(new Intent(getApplicationContext(),HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();*/
        }
    }


    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

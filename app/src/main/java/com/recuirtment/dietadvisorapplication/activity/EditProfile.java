package com.recuirtment.dietadvisorapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

public class EditProfile extends AppCompatActivity {


    TextView user_name_field,mail_input_field,gender_input_field,age_input_field,phone_input_field;
    Button Edit_profile_cell;
    ImageView back_press;
    Preferences preferences;


    @Override
    protected void onRestart() {
        super.onRestart();
        user_name_field.setText(preferences.getUsername());
        mail_input_field.setText(preferences.getEmail());
        gender_input_field.setText(preferences.getGender());
        phone_input_field.setText(preferences.getPhone());
        age_input_field.setText(preferences.getAge());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferences=new Preferences(this);

        user_name_field=findViewById(R.id.user_name_field);
        mail_input_field=findViewById(R.id.mail_input_field);
        gender_input_field=findViewById(R.id.gender_input_field);
        age_input_field=findViewById(R.id.age_input_field);
        phone_input_field=findViewById(R.id.phone_input_field);

        Edit_profile_cell=findViewById(R.id.Edit_profile_cell);
        back_press=findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        user_name_field.setText(preferences.getUsername());
        mail_input_field.setText(preferences.getEmail());
        gender_input_field.setText(preferences.getGender());
        phone_input_field.setText(preferences.getPhone());
        age_input_field.setText(preferences.getAge());


        Edit_profile_cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditProfileMain.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

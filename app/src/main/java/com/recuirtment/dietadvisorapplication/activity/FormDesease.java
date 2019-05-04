package com.recuirtment.dietadvisorapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

public class FormDesease extends AppCompatActivity {

    TextView name_value,age_value;
    EditText food_value,dibatic_value,vegeterian_value;

    Button save_form;
    ImageView back_press;
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_desease);

        preferences=new Preferences(this);

        name_value=findViewById(R.id.name_value);
        age_value=findViewById(R.id.age_value);
        food_value=findViewById(R.id.food_value);
        dibatic_value=findViewById(R.id.dibatic_value);
        vegeterian_value=findViewById(R.id.vegeterian_value);
        save_form=findViewById(R.id.save_form);
        back_press=findViewById(R.id.back_press);

        name_value.setText(preferences.getUsername());
        age_value.setText(preferences.getAge());

        onclick();
    }

    private void onclick() {

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        save_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDataSave();
            }
        });

    }


    private void callDataSave() {
        String food=food_value.getText().toString().trim();
        String dibatis=dibatic_value.toString().trim();
        String vege=vegeterian_value.getText().toString().trim();

        if (TextUtils.isEmpty(food)){
            food_value.setError("Please enter food");
        }else if (TextUtils.isEmpty(dibatis)){
            dibatic_value.setError("Please enter Dibatic");
        }else if (TextUtils.isEmpty(vege)){
            vegeterian_value.setError("Please enter vegeterian state");
        }else{
            preferences.setFood(food);
            preferences.setDibitc(dibatis);
            preferences.setVege(vege);

            preferences.setServiceAdded(1);

            finish();
        }

    }
}

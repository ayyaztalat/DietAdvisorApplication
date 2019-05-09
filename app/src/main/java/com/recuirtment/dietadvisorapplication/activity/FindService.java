package com.recuirtment.dietadvisorapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.recuirtment.dietadvisorapplication.BuildConfig;
import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;
import com.recuirtment.dietadvisorapplication.chat_tes.MainActivity;

public class FindService extends AppCompatActivity {
    ImageView back_press;
    TextView share_konnect,added_services,add_another_service;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_service);
        preferences=new Preferences(this);

        int value=  preferences.getServicesAdded();

        back_press=findViewById(R.id.back_press);
        share_konnect=findViewById(R.id.share_konnect);
        added_services=findViewById(R.id.added_services);
        add_another_service=findViewById(R.id.add_another_service);

        share_konnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shreMethodology();}
        });

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (value==0){
            add_another_service.setAlpha((float) 0.7);
            add_another_service.setEnabled(false);
        }else{
            add_another_service.setAlpha((float) 1.0);
            add_another_service.setEnabled(true);
        }

        added_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FormDesease.class));
            }
        });

        add_another_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }


    private void shreMethodology() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }
}

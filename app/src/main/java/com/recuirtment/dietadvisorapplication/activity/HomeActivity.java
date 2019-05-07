package com.recuirtment.dietadvisorapplication.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recuirtment.dietadvisorapplication.BuildConfig;
import com.recuirtment.dietadvisorapplication.Firebase.profileModel;
import com.recuirtment.dietadvisorapplication.Pre.Preferences;
import com.recuirtment.dietadvisorapplication.R;

public class HomeActivity extends AppCompatActivity {

    Preferences preferences;
    ImageView logout_icon,profile_icon;
    TextView share_konnect,Provide_service,find_service;
    FirebaseAuth auth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        assert user != null;
        String auths=user.getUid();

        preferences=new Preferences(this);

        preferences.setLoginCheck(true);
        share_konnect=findViewById(R.id.share_konnect);

        logout_icon=findViewById(R.id.logout_icon);
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

// this listener will be called when there is change in firebase user session
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            // user auth state is changed - user is null
                            // launch login activity
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                };
               // preferences.clearall();
            }
        });

        mDatabase=FirebaseDatabase.getInstance().getReference();

        mDatabase.child(auths).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileModel model=dataSnapshot.getValue(profileModel.class);

                assert model != null;
                preferences.setUsername(model.username);
                preferences.setAddress(model.address);
                preferences.setAge(model.ages);
                preferences.setGender(model.gender);
                preferences.setPhone(model.phone);
                preferences.setPass(model.pass);
                preferences.setEmail(model.email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Provide_service=findViewById(R.id.Provide_service);
        find_service=findViewById(R.id.find_service);

        profile_icon=findViewById(R.id.profile_icon);
        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditProfile.class));
            }
        });
        share_konnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shreMethodology();}
        });

        Provide_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        find_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FindService.class));
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

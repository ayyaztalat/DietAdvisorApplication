package com.recuirtment.dietadvisorapplication.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recuirtment.dietadvisorapplication.Firebase.ModelCalls;
import com.recuirtment.dietadvisorapplication.Firebase.profileModel;
import com.recuirtment.dietadvisorapplication.R;

public class NeutritianClass extends AppCompatActivity {
    private DatabaseReference allDatabaseUsersReference;
    FirebaseAuth mAuth;
    private String messageSenderId;
    String key;

    EditText moring1_Text,Evening1_text,Night1_text;
    EditText moring2_Text,Evening2_text,Night2_text;
    EditText moring3_Text,Evening3_text,Night3_text;
    EditText moring4_Text,Evening4_text,Night4_text;
    EditText moring5_Text,Evening5_text,Night5_text;
    EditText moring6_Text,Evening6_text,Night6_text;
    EditText moring7_Text,Evening7_text,Night7_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutritian_class);

        Intent intent=getIntent();
        if (intent!=null){
            key=intent.getStringExtra("key");
        }


        moring1_Text=findViewById(R.id.moring1_Text);
        moring2_Text=findViewById(R.id.moring2_Text);
        moring3_Text=findViewById(R.id.moring3_Text);
        moring4_Text=findViewById(R.id.moring4_Text);
        moring5_Text=findViewById(R.id.moring5_Text);
        moring6_Text=findViewById(R.id.moring6_Text);
        moring7_Text=findViewById(R.id.moring7_Text);

        Evening1_text=findViewById(R.id.Evening1_text);
        Evening2_text=findViewById(R.id.Evening2_text);
        Evening3_text=findViewById(R.id.Evening3_text);
        Evening4_text=findViewById(R.id.Evening4_text);
        Evening5_text=findViewById(R.id.Evening5_text);
        Evening6_text=findViewById(R.id.Evening6_text);
        Evening7_text=findViewById(R.id.Evening7_text);

        Night1_text=findViewById(R.id.Night1_text);
        Night2_text=findViewById(R.id.Night2_text);
        Night3_text=findViewById(R.id.Night3_text);
        Night4_text=findViewById(R.id.Night4_text);
        Night5_text=findViewById(R.id.Night5_text);
        Night6_text=findViewById(R.id.Night6_text);
        Night7_text=findViewById(R.id.Night7_text);


        mAuth= FirebaseAuth.getInstance();
        messageSenderId=mAuth.getCurrentUser().getUid();
        allDatabaseUsersReference= FirebaseDatabase.getInstance().getReference();

        allDatabaseUsersReference.child(messageSenderId).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelCalls model=dataSnapshot.getValue(ModelCalls.class);

                assert model != null;
               moring1_Text.setText(model.morning1);
               moring2_Text.setText(model.morning2);
               moring3_Text.setText(model.morning3);
               moring4_Text.setText(model.morning4);
               moring5_Text.setText(model.morning5);
               moring6_Text.setText(model.morning6);
               moring7_Text.setText(model.morning7);

               Evening1_text.setText(model.evening1);
                Evening2_text.setText(model.evening2);
                Evening3_text.setText(model.evening3);
                Evening4_text.setText(model.evening4);
                Evening5_text.setText(model.evening5);
                Evening6_text.setText(model.evening6);
                Evening7_text.setText(model.evening7);

                Night1_text.setText(model.night1);
                Night2_text.setText(model.night2);
                Night3_text.setText(model.night3);
                Night4_text.setText(model.night4);
                Night5_text.setText(model.night5);
                Night6_text.setText(model.night6);
                Night7_text.setText(model.night7);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(NeutritianClass.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}

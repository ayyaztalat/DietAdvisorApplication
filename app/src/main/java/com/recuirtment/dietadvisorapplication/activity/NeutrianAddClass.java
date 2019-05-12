package com.recuirtment.dietadvisorapplication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.recuirtment.dietadvisorapplication.Firebase.ModelCalls;
import com.recuirtment.dietadvisorapplication.Firebase.allUsers;
import com.recuirtment.dietadvisorapplication.R;

import java.util.ArrayList;


import com.google.firebase.database.Query;

import javax.security.auth.callback.Callback;

public class NeutrianAddClass extends AppCompatActivity {
    private DatabaseReference friendReference;
    private FirebaseAuth mAuth;

    Button save_data;
    public static String key;
    EditText moring1_Text,Evening1_text,Night1_text;
    EditText moring2_Text,Evening2_text,Night2_text;
    EditText moring3_Text,Evening3_text,Night3_text;
    EditText moring4_Text,Evening4_text,Night4_text;
    EditText moring5_Text,Evening5_text,Night5_text;
    EditText moring6_Text,Evening6_text,Night6_text;
    EditText moring7_Text,Evening7_text,Night7_text;
    private String sender_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutrian_add_class);

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


        mAuth = FirebaseAuth.getInstance();
        sender_user_id = mAuth.getCurrentUser().getUid();

        friendReference = FirebaseDatabase.getInstance().getReference().child("friends");

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senduploadData();
            }
        });
    }

    public void callNextUploadData(){
        String morning1=moring1_Text.getText().toString().trim();
        String morning2=moring2_Text.getText().toString().trim();
        String morning3=moring3_Text.getText().toString().trim();
        String morning4=moring4_Text.getText().toString().trim();
        String morning5=moring5_Text.getText().toString().trim();
        String morning6=moring6_Text.getText().toString().trim();
        String morning7=moring7_Text.getText().toString().trim();
        String evening1=Evening1_text.getText().toString().trim();
        String evening2=Evening2_text.getText().toString().trim();
        String evening3=Evening3_text.getText().toString().trim();
        String evening4=Evening4_text.getText().toString().trim();
        String evening5=Evening5_text.getText().toString().trim();
        String evening6=Evening6_text.getText().toString().trim();
        String evening7=Evening7_text.getText().toString().trim();
        String night1=Night1_text.getText().toString().trim();
        String night2=Night2_text.getText().toString().trim();
        String night3=Night3_text.getText().toString().trim();
        String night4=Night4_text.getText().toString().trim();
        String night5=Night5_text.getText().toString().trim();
        String night6=Night6_text.getText().toString().trim();
        String night7=Night7_text.getText().toString().trim();

        ModelCalls modelCalls=new ModelCalls(morning1,morning2,morning3,morning4,morning5,morning6,morning7,evening1,evening2,
                evening3,evening4,evening5,evening6,evening7,night1,night2,night3,night4,night5,night6,night7);

        friendReference.child(key).child(sender_user_id).setValue(modelCalls);

    }

    private DatabaseReference allDatabaseUsersReference;

    private void senduploadData() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.popup_signup, null);
        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(NeutrianAddClass.this);

        final RecyclerView recycler_all_users = alertLayout.findViewById(R.id.recycler_all_users);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recycler_all_users.setHasFixedSize(true);
        recycler_all_users.setLayoutManager(linearLayoutManager);
        allDatabaseUsersReference= FirebaseDatabase.getInstance().getReference();



        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        dialog.show();

        Query searchPeopleAndFriends=allDatabaseUsersReference.child("users");
        FirebaseRecyclerAdapter<allUsers,AllUsersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<allUsers, AllUsersViewHolder>
                        (
                                allUsers.class,
                                R.layout.all_users_display_layout,
                                AllUsersViewHolder.class,
                                searchPeopleAndFriends
                        ) {
                    @Override
                    protected void populateViewHolder(AllUsersViewHolder viewHolder, allUsers model, final int position) {

                        viewHolder.setName(model.getUsername());
                        viewHolder.setStatus(model.getPhone());

                        viewHolder.view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                key=getRef(position).getKey();
                                dialog.dismiss();
                                callNextUploadData();
                            }
                        });

                    }
                };
        recycler_all_users.setAdapter(firebaseRecyclerAdapter);

    }


    public static class AllUsersViewHolder extends RecyclerView.ViewHolder {

        View view;
        public AllUsersViewHolder(View itemView) {

            super(itemView);
            view=itemView;
        }
        public void setName(String Name){
            TextView name=(TextView)view.findViewById(R.id.all_users_username);
            name.setText(Name);
        }
        public void setStatus(String status){
            TextView Status=(TextView)view.findViewById(R.id.all_users_status);
            Status.setText(status);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        key=null;
    }
}

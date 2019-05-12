package com.recuirtment.dietadvisorapplication.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.recuirtment.dietadvisorapplication.Firebase.allUsers;
import com.recuirtment.dietadvisorapplication.R;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference allDatabaseUsersReference;
    private String key;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

         RecyclerView recycler_all_users =findViewById(R.id.recycler_all_users);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recycler_all_users.setHasFixedSize(true);
        recycler_all_users.setLayoutManager(linearLayoutManager);
        allDatabaseUsersReference= FirebaseDatabase.getInstance().getReference();




        Query searchPeopleAndFriends=allDatabaseUsersReference.child("users");
        FirebaseRecyclerAdapter<allUsers, NeutrianAddClass.AllUsersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<allUsers, NeutrianAddClass.AllUsersViewHolder>
                        (
                                allUsers.class,
                                R.layout.all_users_display_layout,
                                NeutrianAddClass.AllUsersViewHolder.class,
                                searchPeopleAndFriends
                        ) {
                    @Override
                    protected void populateViewHolder(NeutrianAddClass.AllUsersViewHolder viewHolder, allUsers model, final int position) {

                        viewHolder.setName(model.getUsername());
                        viewHolder.setStatus(model.getPhone());

                        viewHolder.view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                key=getRef(position).getKey();

                                callNextUploadData();
                            }
                        });

                    }
                };
        recycler_all_users.setAdapter(firebaseRecyclerAdapter);

    }

    private void callNextUploadData() {
            startActivity(new Intent(getApplicationContext(),NeutritianClass.class).putExtra("key",key));
    }
}
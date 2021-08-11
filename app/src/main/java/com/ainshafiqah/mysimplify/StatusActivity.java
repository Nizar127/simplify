package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ainshafiqah.mysimplify.adapter.StatusAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StatusAdapter mStatusAdapter;
    DatabaseReference mbase;
    Button trackNumberID;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        backbtn = findViewById(R.id.backhomebtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatusActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        mbase = FirebaseDatabase.getInstance().getReference("Order");
        trackNumberID = findViewById(R.id.trackID);

        trackNumberID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.tracking.my/");
            }
        });

        recyclerView = findViewById(R.id.recyclerviewStatusorder);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(mbase, OrderData.class)
                .build();

        mStatusAdapter = new StatusAdapter(options);
        recyclerView.setAdapter(mStatusAdapter);
    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
        Toast.makeText(StatusActivity.this, "Please Click Back Here", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStatusAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStatusAdapter.stopListening();
    }
}
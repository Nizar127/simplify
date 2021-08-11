package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ainshafiqah.mysimplify.adapter.OrderDeliverAdapter;
import com.ainshafiqah.mysimplify.adapter.OrderPackAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.content.ContentValues.TAG;

public class OrderPackActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderPackAdapter mordersPackAdapter;
    DatabaseReference mbase;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String userID;
    ImageView therealhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pack);
        userID = fAuth.getCurrentUser().getUid();
        therealhome = findViewById(R.id.thehome);

        therealhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        mbase = FirebaseDatabase.getInstance().getReference("Order");

        Query query = mbase.orderByChild("order_status").equalTo("Packing");
        Log.d(TAG, "Packquery: "+query);

        recyclerView = findViewById(R.id.recyclerviewOrderPack);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(query,OrderData.class)
                .build();

        mordersPackAdapter = new OrderPackAdapter(options);
        recyclerView.setAdapter(mordersPackAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mordersPackAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mordersPackAdapter.stopListening();
    }
}
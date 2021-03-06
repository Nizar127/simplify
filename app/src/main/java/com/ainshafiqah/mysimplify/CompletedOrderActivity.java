package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ainshafiqah.mysimplify.adapter.CompletedOrderAdapter;
import com.ainshafiqah.mysimplify.adapter.OrderDeliverAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CompletedOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CompletedOrderAdapter mordersCompletedAdapter;
    DatabaseReference mbase;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String userID;
    FirebaseDatabase fStart;
    ImageView therealhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order);

        userID = fAuth.getCurrentUser().getUid();

        therealhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        mbase = FirebaseDatabase.getInstance().getReference("Order");

        Query query = mbase.orderByChild("order_status").equalTo("Complete");
        recyclerView = findViewById(R.id.recyclerviewCompleted);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(query,OrderData.class)
                .build();

        mordersCompletedAdapter = new CompletedOrderAdapter(options);
        recyclerView.setAdapter(mordersCompletedAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mordersCompletedAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mordersCompletedAdapter.stopListening();
    }
}
package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ainshafiqah.mysimplify.adapter.OrderPackAdapter;
import com.ainshafiqah.mysimplify.adapter.OrderShipAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class OrderShipActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderShipAdapter mordersShipAdapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ship);

        recyclerView = findViewById(R.id.recyclerviewOrderShipping);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(mbase,OrderData.class)
                .build();

        mordersShipAdapter = new OrderShipAdapter(options);
        recyclerView.setAdapter(mordersShipAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mordersShipAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mordersShipAdapter.stopListening();
    }
}
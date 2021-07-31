package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.ainshafiqah.mysimplify.adapter.OrderAdapter;
import com.ainshafiqah.mysimplify.adapter.OrderDeliverAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderDeliverActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderDeliverAdapter mordersDeliverAdapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_deliver);

        mbase = FirebaseDatabase.getInstance().getReference("Order");
        recyclerView = findViewById(R.id.recyclerviewOrderDeliver);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(mbase,OrderData.class)
                .build();

        mordersDeliverAdapter = new OrderDeliverAdapter(options);
        recyclerView.setAdapter(mordersDeliverAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mordersDeliverAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mordersDeliverAdapter.stopListening();
    }
}
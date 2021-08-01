package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ainshafiqah.mysimplify.adapter.OrderDeliverAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrderDeliverActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderDeliverAdapter mordersDeliverAdapter;
    DatabaseReference mbase;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String userID;
    FirebaseDatabase fStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_deliver);
        userID = fAuth.getCurrentUser().getUid();


        mbase = FirebaseDatabase.getInstance().getReference("Order").child(userID);

        Query query = mbase.orderByChild("order_status").equalTo("Delivered");
        recyclerView = findViewById(R.id.recyclerviewOrderDeliver);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(query,OrderData.class)
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
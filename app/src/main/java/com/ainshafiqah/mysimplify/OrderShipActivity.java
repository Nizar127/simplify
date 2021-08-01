package com.ainshafiqah.mysimplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ainshafiqah.mysimplify.adapter.OrderPackAdapter;
import com.ainshafiqah.mysimplify.adapter.OrderShipAdapter;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.content.ContentValues.TAG;

public class OrderShipActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderShipAdapter mordersShipAdapter;
    DatabaseReference mbase;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ship);

        userID = fAuth.getCurrentUser().getUid();


        //mbase = FirebaseDatabase.getInstance().getReference("Order").child(userID);

        //Query query = mbase.orderByChild("order_status").equalTo("shipping");
        Query query = FirebaseDatabase.getInstance().getReference("Order").child(userID).child("status").orderByChild("order_status").equalTo("shipping");
        Log.d(TAG, "query: "+query);

        recyclerView = findViewById(R.id.recyclerviewOrderShipping);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(query,OrderData.class)
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
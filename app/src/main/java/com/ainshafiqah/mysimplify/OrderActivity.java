package com.ainshafiqah.mysimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ainshafiqah.mysimplify.adapter.OrderAdapter;
import com.ainshafiqah.mysimplify.adapter.OrderAdapterSystem;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrderActivity extends AppCompatActivity {


    BottomNavigationView bottommenu;

    RecyclerView recyclerView;
    OrderAdapter theorderadapter;
    DatabaseReference mbase;
    ImageView imgOrder;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        UserID = fAuth.getCurrentUser().getUid();
        //mbase = FirebaseDatabase.getInstance().getReference("Order");
        Query query = FirebaseDatabase.getInstance().getReference("Order");
        imgOrder = findViewById(R.id.imgOrder);

        recyclerView = findViewById(R.id.recyclerviewSystem);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bottommenu = findViewById(R.id.bottom_navigation);
        bottommenu.setSelectedItemId(R.id.home);

        try{
            imgOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OrderActivity.this, AddOrderActivity.class);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        bottommenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        return true;
                }
                return false;
            }
        });

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(query, OrderData.class)
                .build();

        theorderadapter = new OrderAdapter(options);
        recyclerView.setAdapter(theorderadapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        theorderadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        theorderadapter.stopListening();
    }
}
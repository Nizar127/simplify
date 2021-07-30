package com.ainshafiqah.mysimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class HomeActivity extends AppCompatActivity {

    LinearLayout orderPack, orderShip, orderDeliver, orderCompleted;
    Button btnOrderStatus;
    TextView data1, data2, data3;
    DatabaseReference dbRef;
    BottomNavigationView bottommenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottommenu = findViewById(R.id.bottom_navigation);
        bottommenu.setSelectedItemId(R.id.more);
        orderCompleted = findViewById(R.id.orderCompleted);
        orderDeliver   = findViewById(R.id.orderDeliver);
        orderShip      = findViewById(R.id.orderShip);
        orderPack      = findViewById(R.id.orderPack);
        btnOrderStatus = findViewById(R.id.btnOrderStatus);
        data1          = findViewById(R.id.data1);
        data2          = findViewById(R.id.data2);
        data3          = findViewById(R.id.data3);

        btnOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, StatusActivity.class);
                startActivity(intent);
            }
        });

        orderCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CompletedOrderActivity.class);
                startActivity(intent);
            }
        });

        orderShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrderShipActivity.class);
                startActivity(intent);
            }
        });

        orderPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrderPackActivity.class);
                startActivity(intent);
            }
        });

        orderDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrderDeliverActivity.class);
                startActivity(intent);
            }
        });

        bottommenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}
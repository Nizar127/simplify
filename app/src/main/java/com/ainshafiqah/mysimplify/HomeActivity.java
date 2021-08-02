package com.ainshafiqah.mysimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    LinearLayout orderPack, orderShip, orderDeliver, orderCompleted;
    Button btnOrderStatus;
    TextView data1, data2, data3;
    DatabaseReference dbRef;
    BottomNavigationView bottommenu;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String UserID = fAuth.getCurrentUser().getUid();
    int orderData1, orderData2,orderData3;
    Query thedata1,thedata2,thedata3;
    ImageView imgData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        imgData = findViewById(R.id.imgOrder);
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

        imgData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, theorderActivity.class);
                startActivity(intent);
            }
        });

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
        dbRef = FirebaseDatabase.getInstance().getReference("Order");
        thedata1 = dbRef.child(UserID).orderByChild("order_status").equalTo("Packing");
        thedata1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    orderData1 = (int) snapshot.getChildrenCount();
                    data1.setText(Integer.toString(orderData1));
                }else{
                    Toast.makeText(HomeActivity.this, "The data does not exist", Toast.LENGTH_SHORT).show();
                    data1.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        thedata2 = dbRef.child(UserID).orderByChild("order_status").equalTo("Shipping");
        thedata2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    orderData2 = (int) snapshot.getChildrenCount();
                    data2.setText(Integer.toString(orderData2));

            }else{
                Toast.makeText(HomeActivity.this, "The data does not exist", Toast.LENGTH_SHORT).show();
                data2.setText("0");
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        thedata3 = dbRef.child(UserID).orderByChild("order_status").equalTo("Delivered");
        thedata3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    orderData3 = (int) snapshot.getChildrenCount();
                    data3.setText(Integer.toString(orderData3));
                }else{
                    Toast.makeText(HomeActivity.this, "The data does not exist", Toast.LENGTH_SHORT).show();
                    data3.setText("0");
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
/*        dbRef.child(UserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    orderData1 = (int) snapshot.getChildrenCount();
                }
                data1.setText(Integer.toString(orderData1) + " ");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}
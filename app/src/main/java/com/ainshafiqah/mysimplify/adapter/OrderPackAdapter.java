package com.ainshafiqah.mysimplify.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.mysimplify.OrderActivity;
import com.ainshafiqah.mysimplify.OrderDeliverActivity;
import com.ainshafiqah.mysimplify.OrderShipActivity;
import com.ainshafiqah.mysimplify.R;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrderPackAdapter extends FirebaseRecyclerAdapter<OrderData, OrderPackAdapter.OrderPackViewHolder> {
    String TAG = "";
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderPackAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  OrderPackAdapter.OrderPackViewHolder holder, int position, @NonNull OrderData model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.trackingNum.setText(model.getTrackingNum());
        holder.status.setText(model.getOrder_status());
        holder.statusUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "The dialog start", Toast.LENGTH_SHORT).show();
                //showButton();
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_pack);

                LinearLayout shipping     = bottomSheetDialog.findViewById(R.id.shippingItemDialog);
                LinearLayout delivering     = bottomSheetDialog.findViewById(R.id.deliverItemDialog);
                LinearLayout closeDialog  = bottomSheetDialog.findViewById(R.id.closeDialog);
                LinearLayout deleteDialog = bottomSheetDialog.findViewById(R.id.deleteItemDialog);

                delivering.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key = getRef(position).getKey();
                        Log.d(TAG, "key: " + key);
                        Object deliveredOut = "Delivered";
                        HashMap<String, Object> orderMap = new HashMap<>();
                        //orderMap.put("orderID",userID);
                        orderMap.put("order_status",deliveredOut);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Order");

                        dbref.child(key).updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {


                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(v.getContext(), OrderDeliverActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                            }
                        });
                    }
                });

                shipping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key = getRef(position).getKey();
                        Object shipOut = "shipping";
                        HashMap<String, Object> orderMap = new HashMap<>();
                        //orderMap.put("orderID",userID);
                        orderMap.put("order_status",shipOut);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Order");

                        dbref.child(key).updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                        
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(v.getContext(), OrderShipActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                            }
                        });
                    }
                });

                //delete
                deleteDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key = getRef(position).getKey();
                        Log.d(TAG, "Delete position: "+key);
                        //notifyItemRemoved(key);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Order");
                        dbref.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(v.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(v.getContext(), OrderActivity.class);
                                v.getContext().startActivity(intent);
                            }
                        });
                    }
                });

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog.show();

            }
        });
    }

    @NonNull

    @Override
    public OrderPackAdapter.OrderPackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderpack_ui, parent, false);
        return new OrderPackViewHolder(view);
    }

    class OrderPackViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, trackingNum, status;
        Button statusUpdateBtn;

        public OrderPackViewHolder(@NonNull  View itemView) {
            super(itemView);

            name            = itemView.findViewById(R.id.custNamepacksorder);
            address         = itemView.findViewById(R.id.addresspackorder);
            trackingNum     = itemView.findViewById(R.id.trackingNumpackorder);
            statusUpdateBtn = itemView.findViewById(R.id.statusBtnPacking);
            status          = itemView.findViewById(R.id.statusOrderDetailPack);
        }
    }
}

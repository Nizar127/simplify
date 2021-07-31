package com.ainshafiqah.mysimplify.adapter;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.mysimplify.CompletedOrderActivity;
import com.ainshafiqah.mysimplify.OrderActivity;
import com.ainshafiqah.mysimplify.OrderDeliverActivity;
import com.ainshafiqah.mysimplify.R;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrderDeliverAdapter extends FirebaseRecyclerAdapter<OrderData, OrderDeliverAdapter.OrderDeliverViewHolder> {
    String TAG = "";
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String UserID = fAuth.getCurrentUser().getUid();
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderDeliverAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderDeliverAdapter.OrderDeliverViewHolder holder, int position, @NonNull OrderData model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.trackingNum.setText(model.getTrackingNum());
        holder.statusUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "The dialog start", Toast.LENGTH_SHORT).show();
                //showButton();
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_delivery);

                LinearLayout completed     = bottomSheetDialog.findViewById(R.id.completeItemDialog);
                LinearLayout closeDialog  = bottomSheetDialog.findViewById(R.id.closeDialog);
                LinearLayout deleteDialog = bottomSheetDialog.findViewById(R.id.deleteItemDialog);

                completed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key = getRef(position).getKey();
                        Log.d(TAG, "key: " + key);
                        Object completed = "Complete";
                        HashMap<String, Object> orderMap = new HashMap<>();
                        //orderMap.put("orderID",userID);
                        orderMap.put("order_status",completed);
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Order").child(UserID).child("Order_Completed");
                        dbref.child(key).updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(v.getContext(), CompletedOrderActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                            }
                        });
                    }
                });

                deleteDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence deleteoptions[] = new CharSequence[]{
                                "Yes",
                                "No",
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Are you sure you want to delete this item");
                        builder.setItems(deleteoptions, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
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
                                }if(which==1){
                                    Toast.makeText(v.getContext(),"Cancelled Deleted",Toast.LENGTH_SHORT).show();
                                    bottomSheetDialog.dismiss();
                                }
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
    public OrderDeliverAdapter.OrderDeliverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdeliver_ui, parent, false);
        return new OrderDeliverViewHolder(view);
    }

    class OrderDeliverViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, trackingNum;
        Button statusUpdateBtn;

        public OrderDeliverViewHolder(@NonNull View itemView) {
            super(itemView);

            name            = itemView.findViewById(R.id.custNamedeliversorder);
            address         = itemView.findViewById(R.id.addressdeliverorder);
            trackingNum     = itemView.findViewById(R.id.trackingNumdeliveredorder);
            statusUpdateBtn = itemView.findViewById(R.id.statusBtnDeliver);
        }
    }
}

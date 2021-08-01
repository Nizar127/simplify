package com.ainshafiqah.mysimplify.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.mysimplify.R;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OrderAdapterSystem extends FirebaseRecyclerAdapter<OrderData,OrderAdapterSystem.OrderSystemViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapterSystem(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  OrderAdapterSystem.OrderSystemViewHolder holder, int position, @NonNull OrderData model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.trackingNum.setText(model.getTrackingNum());
        holder.statusUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "The dialog start", Toast.LENGTH_SHORT).show();
                //showButton();
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);

                LinearLayout shipping     = bottomSheetDialog.findViewById(R.id.shippingItemDialog);
                LinearLayout packing      = bottomSheetDialog.findViewById(R.id.packingItemDialog);
                LinearLayout delivering   = bottomSheetDialog.findViewById(R.id.deliverItemDialog);
                LinearLayout completed    = bottomSheetDialog.findViewById(R.id.completeItemDialog);
                LinearLayout closeDialog  = bottomSheetDialog.findViewById(R.id.closeDialog);
                LinearLayout deleteDialog = bottomSheetDialog.findViewById(R.id.deleteItemDialog);

                shipping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                packing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                delivering.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                completed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                deleteDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public OrderAdapterSystem.OrderSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_ui, parent, false);
        return new OrderSystemViewHolder(view);
    }

    class OrderSystemViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, trackingNum;
        Button statusUpdateBtn;

        public OrderSystemViewHolder(@NonNull View itemView) {
            super(itemView);

            name            = itemView.findViewById(R.id.custName);
            address         = itemView.findViewById(R.id.address);
            trackingNum     = itemView.findViewById(R.id.trackingNum);
            statusUpdateBtn = itemView.findViewById(R.id.statusBtn);
        }
    }
}

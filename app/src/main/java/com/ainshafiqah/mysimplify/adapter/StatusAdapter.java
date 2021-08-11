package com.ainshafiqah.mysimplify.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.mysimplify.R;
import com.ainshafiqah.mysimplify.UpdateStatusActivity;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusAdapter extends FirebaseRecyclerAdapter<OrderData, StatusAdapter.StatusViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StatusAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  StatusAdapter.StatusViewHolder holder, int position, @NonNull OrderData model) {
        holder.statusTrackingNum.setText(model.getTrackingNum());
        holder.statusCustName.setText(model.getName());
        holder.statusAdress.setText(model.getAddress());
        holder.statusDetail.setText(model.getOrder_status());



/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateStatusActivity.class);
                intent.putExtra("orderID",model.getOrderID());
                view.getContext().startActivity(intent);
            }
        });*/
    }



    @NonNull
    @Override
    public StatusAdapter.StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_order_ui, parent, false);
        return new StatusAdapter.StatusViewHolder(view);
    }

    class StatusViewHolder extends RecyclerView.ViewHolder{

        TextView statusCustName, statusAdress, statusTrackingNum, statusDetail;


        public StatusViewHolder(@NonNull  View itemView) {
            super(itemView);


            statusCustName     = itemView.findViewById(R.id.custNamestatusorder);
            statusAdress       = itemView.findViewById(R.id.addressstatusorder);
            statusTrackingNum  = itemView.findViewById(R.id.trackingNumstatusorder);
            statusDetail       = itemView.findViewById(R.id.statusOrderDetail);
        }
    }
}

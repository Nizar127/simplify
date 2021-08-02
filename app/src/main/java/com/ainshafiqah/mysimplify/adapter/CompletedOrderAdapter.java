package com.ainshafiqah.mysimplify.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.mysimplify.R;
import com.ainshafiqah.mysimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CompletedOrderAdapter extends FirebaseRecyclerAdapter<OrderData, CompletedOrderAdapter.CompletedViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CompletedOrderAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CompletedOrderAdapter.CompletedViewHolder holder, int position, @NonNull OrderData model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.trackingNum.setText(model.getTrackingNum());
        holder.status.setText(model.getStatus());
    }

    @NonNull

    @Override
    public CompletedOrderAdapter.CompletedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercomplete_ui, parent, false);
        return new CompletedOrderAdapter.CompletedViewHolder(view);
    }

    class CompletedViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, trackingNum, status;

        public CompletedViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.custNameComplete);
            address = itemView.findViewById(R.id.addressComplete);
            trackingNum = itemView.findViewById(R.id.trackingNumComplete);
            status      =itemView.findViewById(R.id.statusDetail);
        }
    }
}

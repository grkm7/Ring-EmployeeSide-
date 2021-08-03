package com.example.employeeside;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.CardViewTutucu> {

    private Context mContext;
    private List<Order> ordersList;

    public OrderAdapter(Context mContext, List<Order> ordersList) {
        this.mContext = mContext;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_orderlist,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {

        Order order = ordersList.get(position);

        holder.textViewRoomNo1.setText(String.valueOf(order.getUser_id()));
        holder.textViewOrder.setText(order.getOrder());
        holder.imageViewDelete1.setImageResource(R.drawable.delete_box);
        holder.textViewNote.setText("Note: "+ order.getUser_note());

        holder.imageViewDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Order");
                String Key = order.getOrder_key().toString();

                Map<String,Object> bilgiler = new HashMap<>();
                bilgiler.put("valid",0);
                myRef.child(Key).updateChildren(bilgiler);
                //myRef.child(Key).removeValue();
                Toast.makeText(mContext,order.getUser_id()+" deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public ImageView imageViewDelete1;
        public TextView textViewRoomNo1;
        public TextView textViewOrder;
        public TextView textViewNote;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);

            imageViewDelete1 = itemView.findViewById(R.id.imageViewDelete1);
            textViewOrder = itemView.findViewById(R.id.textViewOrder);
            textViewRoomNo1 = itemView.findViewById(R.id.textViewRoomNo1);
            textViewNote = itemView.findViewById(R.id.textViewNote);

        }
    }
}

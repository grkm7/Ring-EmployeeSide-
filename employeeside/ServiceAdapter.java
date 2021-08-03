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

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.CardViewTutucu> {

    private Context mContext;
    private List<Services> servicesList;

    public ServiceAdapter(Context mContext, List<Services> servicesList) {
        this.mContext = mContext;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_room,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {
        final Services service = servicesList.get(position);
        holder.textViewRoomNo.setText(String.valueOf(service.getUser_id()));
        holder.textViewTimeSpace.setText(service.getTimeSpace());
        holder.imageViewDelete.setImageResource(R.drawable.delete_box);
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase  database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("RoomService");
                String Key = service.getRoomService_key().toString();
                myRef.child(Key).removeValue();
                Toast.makeText(mContext,service.getUser_id()+" deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public ImageView imageViewDelete;
        public TextView textViewRoomNo;
        public TextView textViewTimeSpace;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);
            imageViewDelete=itemView.findViewById(R.id.imageViewDelete);
            textViewRoomNo=itemView.findViewById(R.id.textViewRoomNo);
            textViewTimeSpace=itemView.findViewById(R.id.textViewTimeSpace);
        }
    }
}

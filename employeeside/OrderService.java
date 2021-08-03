package com.example.employeeside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderService extends AppCompatActivity {

    private Button OrderListButton;
    private Button AddItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_service);


        OrderListButton=findViewById(R.id.OrderListButton);
        AddItemButton=findViewById(R.id.AddItemButton);

        OrderListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderService.this, OrderList.class);
                startActivity(intent);

            }
        });

        AddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderService.this, AddItem.class);

                startActivity(intent);

            }
        });
    }
}
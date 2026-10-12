package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlaceOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        // Handle Place Order button click -> Navigates to GenerateReceipt screen
        Button btnPlaceOrder = findViewById(R.id.btn_place_order);
        if (btnPlaceOrder != null) {
            btnPlaceOrder.setOnClickListener(v -> {
                Toast.makeText(PlaceOrder.this, "Order Placed Successfully! Generating receipt...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlaceOrder.this, GenerateReceipt.class);
                startActivity(intent);
            });
        }
    }
}

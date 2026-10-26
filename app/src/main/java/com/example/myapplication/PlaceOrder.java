package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlaceOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        ImageView ivProduct = findViewById(R.id.product_image);
        TextView tvProductTitle = findViewById(R.id.txt_product_title);

        String productName = getIntent().getStringExtra("PRODUCT_NAME");
        int productImgRes = getIntent().getIntExtra("PRODUCT_IMAGE_RES", -1);

        if (productName != null && !productName.isEmpty() && tvProductTitle != null) {
            tvProductTitle.setText(productName);
        }
        if (productImgRes != -1 && ivProduct != null) {
            ivProduct.setImageResource(productImgRes);
        }

        // Handle Place Order button click -> Navigates to PaymentMethod screen
        Button btnPlaceOrder = findViewById(R.id.btn_place_order);
        if (btnPlaceOrder != null) {
            btnPlaceOrder.setOnClickListener(v -> {
                Toast.makeText(PlaceOrder.this, "Order Placed! Choose Payment Method...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlaceOrder.this, PaymentMethod.class);
                startActivity(intent);
            });
        }
    }
}

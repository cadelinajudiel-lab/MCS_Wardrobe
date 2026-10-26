package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class GenerateReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_receipt);

        Button btnPrintReceipt = findViewById(R.id.btnPrintReceipt);
        MaterialCardView cardQrCode = findViewById(R.id.cardQrCode);
        ImageView ivReceiptLogo = findViewById(R.id.ivReceiptLogo);

        // Handle Print / Download Receipt action -> Returns to Store Tab
        if (btnPrintReceipt != null) {
            btnPrintReceipt.setOnClickListener(v -> {
                Toast.makeText(GenerateReceipt.this, "Receipt Printed! Returning to Store...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GenerateReceipt.this, StoreTab.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        // Handle QR Code click action
        if (cardQrCode != null) {
            cardQrCode.setOnClickListener(v ->
                Toast.makeText(GenerateReceipt.this, "Receipt QR Code Verified: #MCS-2025-0982", Toast.LENGTH_SHORT).show()
            );
        }

        // Handle Logo click action
        if (ivReceiptLogo != null) {
            ivReceiptLogo.setOnClickListener(v ->
                Toast.makeText(GenerateReceipt.this, "Manila Cathedral School Uniform Store", Toast.LENGTH_SHORT).show()
            );
        }
    }
}

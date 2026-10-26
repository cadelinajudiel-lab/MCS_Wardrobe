package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {

    private RadioButton rbGCash, rbMastercard, rbVisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        rbGCash = findViewById(R.id.rbGCash);
        rbMastercard = findViewById(R.id.rbMastercard);
        rbVisa = findViewById(R.id.rbVisa);

        LinearLayout optionGCash = findViewById(R.id.optionGCash);
        LinearLayout optionMastercard = findViewById(R.id.optionMastercard);
        LinearLayout optionVisa = findViewById(R.id.optionVisa);

        Button btnContinue = findViewById(R.id.btnContinue);

        // Click on options
        if (optionGCash != null) {
            optionGCash.setOnClickListener(v -> selectPaymentOption("GCash"));
        }

        if (optionMastercard != null) {
            optionMastercard.setOnClickListener(v -> selectPaymentOption("Mastercard"));
        }

        if (optionVisa != null) {
            optionVisa.setOnClickListener(v -> selectPaymentOption("VISA"));
        }

        if (rbGCash != null) {
            rbGCash.setOnClickListener(v -> selectPaymentOption("GCash"));
        }

        if (rbMastercard != null) {
            rbMastercard.setOnClickListener(v -> selectPaymentOption("Mastercard"));
        }

        if (rbVisa != null) {
            rbVisa.setOnClickListener(v -> selectPaymentOption("VISA"));
        }

        // Continue -> Navigates to GenerateReceipt
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                String selectedMethod = getSelectedPaymentMethod();
                Toast.makeText(PaymentMethod.this, "Payment Processing via " + selectedMethod + "...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentMethod.this, GenerateReceipt.class);
                intent.putExtra("PAYMENT_METHOD", selectedMethod);
                startActivity(intent);
            });
        }
    }

    private void selectPaymentOption(String method) {
        if (rbGCash != null) rbGCash.setChecked("GCash".equals(method));
        if (rbMastercard != null) rbMastercard.setChecked("Mastercard".equals(method));
        if (rbVisa != null) rbVisa.setChecked("VISA".equals(method));
    }

    private String getSelectedPaymentMethod() {
        if (rbMastercard != null && rbMastercard.isChecked()) return "Mastercard";
        if (rbVisa != null && rbVisa.isChecked()) return "VISA";
        return "GCash";
    }
}

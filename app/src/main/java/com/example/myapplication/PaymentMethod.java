package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        RadioGroup rgPaymentMethods = findViewById(R.id.rgPaymentMethods);
        RadioButton rbGCash = findViewById(R.id.rbGCash);
        RadioButton rbMastercard = findViewById(R.id.rbMastercard);
        RadioButton rbVisa = findViewById(R.id.rbVisa);
        Button btnContinue = findViewById(R.id.btnContinue);

        View optionGCash = findViewById(R.id.optionGCash);
        View optionMastercard = findViewById(R.id.optionMastercard);
        View optionVisa = findViewById(R.id.optionVisa);

        if (optionGCash != null && rbGCash != null) {
            optionGCash.setOnClickListener(v -> rbGCash.setChecked(true));
        }
        if (optionMastercard != null && rbMastercard != null) {
            optionMastercard.setOnClickListener(v -> rbMastercard.setChecked(true));
        }
        if (optionVisa != null && rbVisa != null) {
            optionVisa.setOnClickListener(v -> rbVisa.setChecked(true));
        }

        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                String selectedMethod = "GCash";
                int checkedId = rgPaymentMethods != null ? rgPaymentMethods.getCheckedRadioButtonId() : -1;
                if (checkedId == R.id.rbMastercard) {
                    selectedMethod = "Mastercard";
                } else if (checkedId == R.id.rbVisa) {
                    selectedMethod = "VISA";
                } else if (checkedId == R.id.rbGCash) {
                    selectedMethod = "GCash";
                }

                Toast.makeText(PaymentMethod.this, "Payment processed via " + selectedMethod, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentMethod.this, GenerateReceipt.class);
                startActivity(intent);
            });
        }
    }
}

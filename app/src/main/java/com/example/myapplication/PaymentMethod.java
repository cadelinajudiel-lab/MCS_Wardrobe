package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        RadioGroup rgPaymentMethods = findViewById(R.id.rgPaymentMethods);
        Button btnContinue = findViewById(R.id.btnContinue);

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

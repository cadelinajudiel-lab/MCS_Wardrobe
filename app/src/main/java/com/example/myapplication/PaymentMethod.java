package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        RadioButton rbGCash = findViewById(R.id.rbGCash);
        RadioButton rbMastercard = findViewById(R.id.rbMastercard);
        RadioButton rbVisa = findViewById(R.id.rbVisa);
        Button btnContinue = findViewById(R.id.btnContinue);
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                }

                startActivity(intent);
            });
        }
    }
}

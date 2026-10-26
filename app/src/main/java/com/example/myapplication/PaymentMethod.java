package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        LinearLayout optionGCash = findViewById(R.id.optionGCash);
        LinearLayout optionMastercard = findViewById(R.id.optionMastercard);
        LinearLayout optionVisa = findViewById(R.id.optionVisa);

        RadioButton rbGCash = findViewById(R.id.rbGCash);
        RadioButton rbMastercard = findViewById(R.id.rbMastercard);
        RadioButton rbVisa = findViewById(R.id.rbVisa);

        if (optionGCash != null && rbGCash != null && rbMastercard != null && rbVisa != null) {
            optionGCash.setOnClickListener(v -> {
                rbGCash.setChecked(true);
                rbMastercard.setChecked(false);
                rbVisa.setChecked(false);
            });
        }

        if (optionMastercard != null && rbGCash != null && rbMastercard != null && rbVisa != null) {
            optionMastercard.setOnClickListener(v -> {
                rbGCash.setChecked(false);
                rbMastercard.setChecked(true);
                rbVisa.setChecked(false);
            });
        }

        if (optionVisa != null && rbGCash != null && rbMastercard != null && rbVisa != null) {
            optionVisa.setOnClickListener(v -> {
                rbGCash.setChecked(false);
                rbMastercard.setChecked(false);
                rbVisa.setChecked(true);
            });
        }

        Button btnContinue = findViewById(R.id.btnContinue);
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                String selected;
                if (rbMastercard != null && rbMastercard.isChecked()) {
                    selected = "Mastercard";
                } else if (rbVisa != null && rbVisa.isChecked()) {
                    selected = "VISA";
                } else {
                    selected = "GCash";
                }

                Toast.makeText(PaymentMethod.this, "Payment selected: " + selected + ". Thank you!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PaymentMethod.this, ThankYouActivity.class);
                startActivity(intent);
            });
        }
    }
}

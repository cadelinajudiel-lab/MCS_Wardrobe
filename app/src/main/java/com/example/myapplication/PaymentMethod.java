package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        RadioGroup rgPaymentMethods = findViewById(R.id.rgPaymentMethods);
        View optionGCash = findViewById(R.id.optionGCash);
        View optionMastercard = findViewById(R.id.optionMastercard);
        View optionVisa = findViewById(R.id.optionVisa);
        RadioButton rbGCash = findViewById(R.id.rbGCash);
        RadioButton rbMastercard = findViewById(R.id.rbMastercard);
        RadioButton rbVisa = findViewById(R.id.rbVisa);
        Button btnContinue = findViewById(R.id.btnContinue);

        TextView tvCostOfPurchase = findViewById(R.id.tvCostOfPurchase);
        TextView tvTaxFee = findViewById(R.id.tvTaxFee);
        TextView tvTotal = findViewById(R.id.tvTotal);

        ArrayList<String> titles = getIntent().getStringArrayListExtra("product_titles");
        ArrayList<Integer> prices = getIntent().getIntegerArrayListExtra("product_prices");

        int costOfPurchase = 690;
        if (prices != null && !prices.isEmpty()) {
            costOfPurchase = 0;
            for (Integer p : prices) {
                if (p != null) costOfPurchase += p;
            }
        }
        int taxFee = 5;
        int total = costOfPurchase + taxFee;

        if (tvCostOfPurchase != null) tvCostOfPurchase.setText(costOfPurchase + "₱");
        if (tvTaxFee != null) tvTaxFee.setText(taxFee + "₱");
        if (tvTotal != null) tvTotal.setText(total + "₱");

        View.OnClickListener selectionListener = v -> {
            boolean isGCash = (v == optionGCash || v == rbGCash);
            boolean isMastercard = (v == optionMastercard || v == rbMastercard);
            boolean isVisa = (v == optionVisa || v == rbVisa);

            if (rbGCash != null) rbGCash.setChecked(isGCash);
            if (rbMastercard != null) rbMastercard.setChecked(isMastercard);
            if (rbVisa != null) rbVisa.setChecked(isVisa);
        };

        if (optionGCash != null) optionGCash.setOnClickListener(selectionListener);
        if (optionMastercard != null) optionMastercard.setOnClickListener(selectionListener);
        if (optionVisa != null) optionVisa.setOnClickListener(selectionListener);

        if (rbGCash != null) rbGCash.setOnClickListener(selectionListener);
        if (rbMastercard != null) rbMastercard.setOnClickListener(selectionListener);
        if (rbVisa != null) rbVisa.setOnClickListener(selectionListener);

        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                String selectedMethod = "GCash";
                if (rbMastercard != null && rbMastercard.isChecked()) {
                    selectedMethod = "Mastercard";
                } else if (rbVisa != null && rbVisa.isChecked()) {
                    selectedMethod = "VISA";
                } else if (rbGCash != null && rbGCash.isChecked()) {
                    selectedMethod = "GCash";
                }

                Toast.makeText(PaymentMethod.this, "Payment processed via " + selectedMethod, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentMethod.this, GenerateReceipt.class);
                if (titles != null) intent.putStringArrayListExtra("product_titles", titles);
                if (prices != null) intent.putIntegerArrayListExtra("product_prices", prices);
                startActivity(intent);
            });
        }
    }
}

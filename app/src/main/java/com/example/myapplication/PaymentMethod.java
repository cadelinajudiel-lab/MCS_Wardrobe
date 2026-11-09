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
import java.util.Locale;

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

        TextView tvOriginalPrice = findViewById(R.id.tvOriginalPrice);
        TextView tvDiscountSavings = findViewById(R.id.tvDiscountSavings);
        TextView tvCostOfPurchase = findViewById(R.id.tvCostOfPurchase);
        TextView tvTaxFee = findViewById(R.id.tvTaxFee);
        TextView tvTotal = findViewById(R.id.tvTotal);
        View rowOriginalPrice = findViewById(R.id.rowOriginalPrice);
        View rowDiscount = findViewById(R.id.rowDiscount);

        ArrayList<String> titles = getIntent().getStringArrayListExtra("product_titles");
        ArrayList<Integer> prices = getIntent().getIntegerArrayListExtra("product_prices");
        ArrayList<Integer> originalPrices = getIntent().getIntegerArrayListExtra("product_original_prices");

        int costOfPurchase = 690;
        int originalTotal = 690;

        if (prices != null && !prices.isEmpty()) {
            costOfPurchase = 0;
            originalTotal = 0;
            for (int i = 0; i < prices.size(); i++) {
                Integer p = prices.get(i);
                if (p != null) costOfPurchase += p;

                Integer origP = (originalPrices != null && i < originalPrices.size()) ? originalPrices.get(i) : p;
                if (origP != null) {
                    originalTotal += origP;
                } else if (p != null) {
                    originalTotal += p;
                }
            }
        }

        int discountSavings = originalTotal - costOfPurchase;
        int taxFee = 5;
        int total = costOfPurchase + taxFee;

        if (discountSavings > 0) {
            if (rowOriginalPrice != null) rowOriginalPrice.setVisibility(View.VISIBLE);
            if (rowDiscount != null) rowDiscount.setVisibility(View.VISIBLE);
            if (tvOriginalPrice != null) tvOriginalPrice.setText(String.format(Locale.getDefault(), "₱%,d.00", originalTotal));
            if (tvDiscountSavings != null) tvDiscountSavings.setText(String.format(Locale.getDefault(), "-₱%,d.00", discountSavings));
        } else {
            if (rowOriginalPrice != null) rowOriginalPrice.setVisibility(View.GONE);
            if (rowDiscount != null) rowDiscount.setVisibility(View.GONE);
        }

        if (tvCostOfPurchase != null) tvCostOfPurchase.setText(String.format(Locale.getDefault(), "₱%,d.00", costOfPurchase));
        if (tvTaxFee != null) tvTaxFee.setText("₱5.00");
        if (tvTotal != null) tvTotal.setText(String.format(Locale.getDefault(), "₱%,d.00", total));

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

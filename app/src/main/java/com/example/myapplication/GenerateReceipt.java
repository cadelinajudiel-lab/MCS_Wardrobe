package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Locale;

public class GenerateReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_receipt);

        LinearLayout layoutReceiptItems = findViewById(R.id.layout_receipt_items);
        TextView tvReceiptVat = findViewById(R.id.tv_receipt_vat);
        TextView tvReceiptTotal = findViewById(R.id.tv_receipt_total);

        Button btnPrintReceipt = findViewById(R.id.btnPrintReceipt);
        MaterialCardView cardQrCode = findViewById(R.id.cardQrCode);
        ImageView ivReceiptLogo = findViewById(R.id.ivReceiptLogo);

        ArrayList<String> titles = getIntent().getStringArrayListExtra("product_titles");
        ArrayList<Integer> prices = getIntent().getIntegerArrayListExtra("product_prices");

        final ArrayList<String> finalTitles = (titles != null && !titles.isEmpty()) ? titles : new ArrayList<>();
        final ArrayList<Integer> finalPrices = prices != null ? prices : new ArrayList<>();

        if (finalTitles.isEmpty()) {
            finalTitles.add("SHS BLOUSE FEMALE (S)");
            finalPrices.add(650);
        }

        if (layoutReceiptItems != null) {
            layoutReceiptItems.removeAllViews();

            TextView header = new TextView(this);
            header.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            header.setPadding(0, 0, 0, dpToPx(8));
            header.setText(R.string.receipt_item_details_header);
            header.setTextColor(0xFF000000);
            header.setTextSize(16);
            header.setTypeface(null, Typeface.BOLD);
            layoutReceiptItems.addView(header);

            int sumAmount = 0;
            for (int i = 0; i < finalTitles.size(); i++) {
                String title = finalTitles.get(i);
                int price = (i < finalPrices.size() && finalPrices.get(i) != null) ? finalPrices.get(i) : 690;
                sumAmount += price;

                TextView tvItem = new TextView(this);
                tvItem.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                tvItem.setPadding(0, 0, 0, dpToPx(4));
                tvItem.setText(String.format(Locale.getDefault(), "• %s - ₱%d.00", title, price));
                tvItem.setTextColor(0xFF212121);
                tvItem.setTextSize(14);
                tvItem.setTypeface(null, Typeface.BOLD);
                layoutReceiptItems.addView(tvItem);
            }

            final int totalAmount = sumAmount;
            double vat = totalAmount * 0.12;

            if (tvReceiptVat != null) {
                tvReceiptVat.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                tvReceiptVat.setPadding(0, dpToPx(8), 0, dpToPx(4));
                tvReceiptVat.setText(String.format(Locale.getDefault(), "• VAT (12%%): ₱%.2f", vat));
                tvReceiptVat.setTextColor(0xFF212121);
                tvReceiptVat.setTextSize(14);
                tvReceiptVat.setTypeface(null, Typeface.BOLD);
                layoutReceiptItems.addView(tvReceiptVat);
            }

            if (tvReceiptTotal != null) {
                tvReceiptTotal.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                tvReceiptTotal.setPadding(0, dpToPx(4), 0, 0);
                tvReceiptTotal.setText(String.format(Locale.getDefault(), "Total Amount: ₱%.2f", (double) totalAmount));
                tvReceiptTotal.setTextColor(0xFF000000);
                tvReceiptTotal.setTextSize(16);
                tvReceiptTotal.setTypeface(null, Typeface.BOLD);
                layoutReceiptItems.addView(tvReceiptTotal);
            }
        }

        // Handle Print / Download Receipt action -> Opens Thank You screen
        if (btnPrintReceipt != null) {
            btnPrintReceipt.setOnClickListener(v -> {
                Toast.makeText(GenerateReceipt.this, "Receipt Printed Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GenerateReceipt.this, ThankYouActivity.class);
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

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}

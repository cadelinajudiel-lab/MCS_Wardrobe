package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PlaceOrder extends AppCompatActivity {

    private ArrayList<String> finalTitles;
    private ArrayList<Integer> finalImages;
    private ArrayList<String> finalDescs;
    private ArrayList<Integer> finalPrices;
    private ArrayList<Integer> finalOriginalPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        LinearLayout sliderProducts = findViewById(R.id.slider_products);
        TextView tvDesc = findViewById(R.id.txt_body_description);

        ArrayList<String> intentTitles = getIntent().getStringArrayListExtra("product_titles");
        ArrayList<Integer> intentImages = getIntent().getIntegerArrayListExtra("product_images");
        ArrayList<String> intentDescs = getIntent().getStringArrayListExtra("product_descs");
        ArrayList<Integer> intentPrices = getIntent().getIntegerArrayListExtra("product_prices");
        ArrayList<Integer> intentOriginalPrices = getIntent().getIntegerArrayListExtra("product_original_prices");

        if (intentTitles == null || intentTitles.isEmpty()) {
            finalTitles = new ArrayList<>();
            finalImages = new ArrayList<>();
            finalDescs = new ArrayList<>();
            finalPrices = new ArrayList<>();
            finalOriginalPrices = new ArrayList<>();

            String singleTitle = getIntent().getStringExtra("product_title");
            int singleImage = getIntent().getIntExtra("product_image", R.drawable.img);
            String singleDesc = getIntent().getStringExtra("product_desc");

            finalTitles.add(singleTitle != null ? singleTitle : "SHS POLO");
            finalImages.add(singleImage);
            finalDescs.add(singleDesc != null ? singleDesc : getString(R.string.product_blouse_description));
            finalPrices.add(650);
            finalOriginalPrices.add(650);
        } else {
            finalTitles = intentTitles;
            finalImages = intentImages != null ? intentImages : new ArrayList<>();
            finalDescs = intentDescs != null ? intentDescs : new ArrayList<>();
            finalPrices = intentPrices != null ? intentPrices : new ArrayList<>();
            finalOriginalPrices = intentOriginalPrices != null ? intentOriginalPrices : new ArrayList<>();
        }

        if (sliderProducts != null) {
            sliderProducts.removeAllViews();
            for (int i = 0; i < finalTitles.size(); i++) {
                String title = finalTitles.get(i);
                int imageRes = (i < finalImages.size() && finalImages.get(i) != null) ? finalImages.get(i) : R.drawable.img;
                int price = (i < finalPrices.size() && finalPrices.get(i) != null) ? finalPrices.get(i) : 690;
                int originalPrice = (i < finalOriginalPrices.size() && finalOriginalPrices.get(i) != null) ? finalOriginalPrices.get(i) : price;

                // Create CardView for each product
                MaterialCardView card = new MaterialCardView(this);
                LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                        dpToPx(240),
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                cardParams.setMarginEnd(dpToPx(12));
                card.setLayoutParams(cardParams);
                card.setCardBackgroundColor(0xFFFFFFFF);
                card.setRadius(dpToPx(16));
                card.setCardElevation(dpToPx(4));
                card.setStrokeWidth(0);

                LinearLayout cardInner = new LinearLayout(this);
                cardInner.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                ));
                cardInner.setOrientation(LinearLayout.VERTICAL);
                cardInner.setGravity(Gravity.CENTER);
                cardInner.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));

                ImageView ivProd = new ImageView(this);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dpToPx(170)
                );
                ivProd.setLayoutParams(ivParams);
                ivProd.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ivProd.setImageResource(imageRes);

                // Flash animation for each product image
                ivProd.setAlpha(0.1f);
                ivProd.animate().alpha(1.0f).setDuration(500).start();

                TextView tvProdTitle = new TextView(this);
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                tvParams.setMargins(0, dpToPx(12), 0, 0);
                tvProdTitle.setLayoutParams(tvParams);
                tvProdTitle.setGravity(Gravity.CENTER);

                if (originalPrice > price) {
                    SpannableString span = new SpannableString(title + "\n₱" + price + "\n₱" + originalPrice);
                    int startIdx = (title + "\n₱" + price + "\n").length();
                    span.setSpan(new StrikethroughSpan(), startIdx, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    span.setSpan(new ForegroundColorSpan(0xFF888888), startIdx, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvProdTitle.setText(span);
                } else {
                    tvProdTitle.setText(title + "\n₱" + price);
                }
                tvProdTitle.setTextColor(0xFFD32F2F);
                tvProdTitle.setTextSize(13);
                tvProdTitle.setTypeface(null, Typeface.BOLD);

                cardInner.addView(ivProd);
                cardInner.addView(tvProdTitle);
                card.addView(cardInner);
                sliderProducts.addView(card);
            }
        }

        if (!finalDescs.isEmpty() && tvDesc != null) {
            tvDesc.setText(finalDescs.get(0));
        }

        // Handle Place Order button click -> Navigates to Payment Method screen
        Button btnPlaceOrder = findViewById(R.id.btn_place_order);
        if (btnPlaceOrder != null) {
            btnPlaceOrder.setOnClickListener(v -> {
                Toast.makeText(PlaceOrder.this, "Proceeding to Payment Method...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlaceOrder.this, PaymentMethod.class);
                intent.putStringArrayListExtra("product_titles", finalTitles);
                intent.putIntegerArrayListExtra("product_prices", finalPrices);
                intent.putIntegerArrayListExtra("product_original_prices", finalOriginalPrices);
                startActivity(intent);
            });
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}

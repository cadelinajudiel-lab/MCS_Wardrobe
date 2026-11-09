package com.example.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Lao_AddToCartBackground extends AppCompatActivity {

    public static class CartItem {
        public String title;
        public int price;
        public int originalPrice;
        public int imageRes;
        public boolean isSelected = true;

        public CartItem(String title, int price, int originalPrice, int imageRes) {
            this.title = title;
            this.price = price;
            this.originalPrice = originalPrice;
            this.imageRes = imageRes;
        }
    }

    public static ArrayList<CartItem> cartItems = new ArrayList<>();

    static {
        if (cartItems.isEmpty()) {
            cartItems.add(new CartItem("SHS POLO", 650, 650, R.drawable.img));
            cartItems.add(new CartItem("SHS SKIRT", 690, 690, R.drawable.shsskirt));
            cartItems.add(new CartItem("SHS PE SHIRT", 450, 450, R.drawable.peshirt));
            cartItems.add(new CartItem("ACTIVITY SHIRT", 250, 250, R.drawable.activity_shirt_img));
        }
    }

    public static void addCartItem(String title, int price, int originalPrice, int imageRes) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        // Add new item at the TOP (index 0) so newest is on top and old is at bottom
        cartItems.add(0, new CartItem(title, price, originalPrice, imageRes));
    }

    private LinearLayout containerCartItems;
    private TextView txtTotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lao_add_to_cart_background);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        containerCartItems = findViewById(R.id.container_cart_items);
        txtTotalValue = findViewById(R.id.txt_total_value);
        Button btnPurchase = findViewById(R.id.btn_purchase);
        Button btnCancel = findViewById(R.id.btn_cancel);

        populateCartViews();

        if (btnPurchase != null) {
            btnPurchase.setOnClickListener(v -> {
                ArrayList<String> titles = new ArrayList<>();
                ArrayList<Integer> images = new ArrayList<>();
                ArrayList<String> descs = new ArrayList<>();
                ArrayList<Integer> prices = new ArrayList<>();
                ArrayList<Integer> originalPrices = new ArrayList<>();

                for (CartItem item : cartItems) {
                    if (item.isSelected) {
                        titles.add(item.title);
                        images.add(item.imageRes);
                        descs.add("Official Manila Cathedral School uniform item: " + item.title);
                        prices.add(item.price);
                        originalPrices.add(item.originalPrice);
                    }
                }

                if (titles.isEmpty()) {
                    Toast.makeText(this, "Please select at least one item to purchase.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Proceeding to Place Order (" + titles.size() + " items)...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Lao_AddToCartBackground.this, PlaceOrder.class);
                intent.putStringArrayListExtra("product_titles", titles);
                intent.putIntegerArrayListExtra("product_images", images);
                intent.putStringArrayListExtra("product_descs", descs);
                intent.putIntegerArrayListExtra("product_prices", prices);
                intent.putIntegerArrayListExtra("product_original_prices", originalPrices);
                intent.putExtra("product_title", titles.get(0));
                intent.putExtra("product_image", images.get(0));
                intent.putExtra("product_desc", descs.get(0));
                startActivity(intent);
            });
        }

        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> finish());
        }
    }

    private void populateCartViews() {
        if (containerCartItems == null) return;
        containerCartItems.removeAllViews();

        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);

            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));
            row.setBackgroundColor(i % 2 == 0 ? 0xFFE1F5FE : 0xFFFFFFFF);

            CheckBox checkBox = new CheckBox(this);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            checkBox.setChecked(item.isSelected);
            checkBox.setButtonTintList(ColorStateList.valueOf(0xFF2E7D32));
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.isSelected = isChecked;
                updateTotal();
            });

            TextView tvTitle = new TextView(this);
            LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
            );
            tvParams.setMargins(dpToPx(8), 0, 0, 0);
            tvTitle.setLayoutParams(tvParams);
            tvTitle.setText(item.title);
            tvTitle.setTextColor(0xFF000000);
            tvTitle.setTextSize(15);
            tvTitle.setTypeface(null, Typeface.BOLD);

            TextView tvPrice = new TextView(this);
            LinearLayout.LayoutParams priceParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            priceParams.setMargins(0, 0, dpToPx(12), 0);
            tvPrice.setLayoutParams(priceParams);
            if (item.originalPrice > item.price) {
                tvPrice.setText("₱" + item.price + " (Was ₱" + item.originalPrice + ")");
            } else {
                tvPrice.setText("₱" + item.price);
            }
            tvPrice.setTextColor(0xFFD32F2F);
            tvPrice.setTextSize(14);
            tvPrice.setTypeface(null, Typeface.BOLD);

            ImageView btnDelete = new ImageView(this);
            LinearLayout.LayoutParams delParams = new LinearLayout.LayoutParams(
                    dpToPx(36),
                    dpToPx(36)
            );
            btnDelete.setLayoutParams(delParams);
            btnDelete.setImageResource(R.drawable.ic_trash);
            btnDelete.setPadding(dpToPx(6), dpToPx(6), dpToPx(6), dpToPx(6));
            btnDelete.setOnClickListener(v -> {
                cartItems.remove(item);
                populateCartViews();
                updateTotal();
                Toast.makeText(this, item.title + " removed from cart", Toast.LENGTH_SHORT).show();
            });

            row.addView(checkBox);
            row.addView(tvTitle);
            row.addView(tvPrice);
            row.addView(btnDelete);

            containerCartItems.addView(row);

            if (i < cartItems.size() - 1) {
                View divider = new View(this);
                divider.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dpToPx(1)
                ));
                divider.setBackgroundColor(0xFF4CA6FF);
                containerCartItems.addView(divider);
            }
        }

        updateTotal();
    }

    private void updateTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.isSelected) {
                total += item.price;
            }
        }

        if (txtTotalValue != null) {
            txtTotalValue.setText("₱" + total);
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}

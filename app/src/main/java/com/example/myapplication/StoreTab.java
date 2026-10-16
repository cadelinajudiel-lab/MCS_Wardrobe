package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StoreTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_tab);

        // Header and Search Actions
        ImageView btnChat = findViewById(R.id.btnChat);
        ImageView btnFilter = findViewById(R.id.btnFilter);
        TextView tvMore = findViewById(R.id.tvMore);

        // Category Tabs
        TextView tabAll = findViewById(R.id.tabAll);
        TextView tabBlouse = findViewById(R.id.tabBlouse);
        TextView tabSkirts = findViewById(R.id.tabSkirts);

        // Cart Buttons
        Button btnPromoCart1 = findViewById(R.id.btnPromoCart1);
        Button btnPromoCart2 = findViewById(R.id.btnPromoCart2);
        Button btnCartProduct1 = findViewById(R.id.btnCartProduct1);
        Button btnCartProduct2 = findViewById(R.id.btnCartProduct2);

        // Bottom Navigation Bar
        LinearLayout navHome = findViewById(R.id.navHome);
        LinearLayout navPlaceOrder = findViewById(R.id.navPlaceOrder);
        LinearLayout navCart = findViewById(R.id.navCart);
        LinearLayout navProfile = findViewById(R.id.navProfile);

        // Top Actions Listeners
        if (btnChat != null) {
            btnChat.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Opening Messages...", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnFilter != null) {
            btnFilter.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Filters Opened", Toast.LENGTH_SHORT).show()
            );
        }

        if (tvMore != null) {
            tvMore.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Viewing All Featured Products", Toast.LENGTH_SHORT).show()
            );
        }

        // Category Tab Switching
        if (tabAll != null) {
            tabAll.setOnClickListener(v -> {
                tabAll.setBackgroundResource(R.drawable.bg_pill_dark);
                tabAll.setTextColor(0xFFFFFFFF);
                if (tabBlouse != null) {
                    tabBlouse.setBackgroundResource(R.drawable.bg_pill_light);
                    tabBlouse.setTextColor(0xFF333333);
                }
                if (tabSkirts != null) {
                    tabSkirts.setBackgroundResource(R.drawable.bg_pill_light);
                    tabSkirts.setTextColor(0xFF333333);
                }
                Toast.makeText(StoreTab.this, "Showing All Products", Toast.LENGTH_SHORT).show();
            });
        }

        if (tabBlouse != null) {
            tabBlouse.setOnClickListener(v -> {
                tabBlouse.setBackgroundResource(R.drawable.bg_pill_dark);
                tabBlouse.setTextColor(0xFFFFFFFF);
                if (tabAll != null) {
                    tabAll.setBackgroundResource(R.drawable.bg_pill_light);
                    tabAll.setTextColor(0xFF333333);
                }
                if (tabSkirts != null) {
                    tabSkirts.setBackgroundResource(R.drawable.bg_pill_light);
                    tabSkirts.setTextColor(0xFF333333);
                }
                Toast.makeText(StoreTab.this, "Filtered: Blouse Category", Toast.LENGTH_SHORT).show();
            });
        }

        if (tabSkirts != null) {
            tabSkirts.setOnClickListener(v -> {
                tabSkirts.setBackgroundResource(R.drawable.bg_pill_dark);
                tabSkirts.setTextColor(0xFFFFFFFF);
                if (tabAll != null) {
                    tabAll.setBackgroundResource(R.drawable.bg_pill_light);
                    tabAll.setTextColor(0xFF333333);
                }
                if (tabBlouse != null) {
                    tabBlouse.setBackgroundResource(R.drawable.bg_pill_light);
                    tabBlouse.setTextColor(0xFF333333);
                }
                Toast.makeText(StoreTab.this, "Filtered: Skirts Category", Toast.LENGTH_SHORT).show();
            });
        }

        // Add To Cart Listeners
        if (btnPromoCart1 != null) {
            btnPromoCart1.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Added Male SHS Polo Set to Cart!", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnPromoCart2 != null) {
            btnPromoCart2.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Added Female Uniform Set to Cart!", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnCartProduct1 != null) {
            btnCartProduct1.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Added SHS Skirt to Cart!", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnCartProduct2 != null) {
            btnCartProduct2.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Added SHS Polo to Cart!", Toast.LENGTH_SHORT).show()
            );
        }

        // Bottom Navigation Bar Navigation
        if (navHome != null) {
            navHome.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "You are on Home Store Tab", Toast.LENGTH_SHORT).show()
            );
        }

        if (navPlaceOrder != null) {
            navPlaceOrder.setOnClickListener(v -> {
                Intent intent = new Intent(StoreTab.this, PlaceOrder.class);
                startActivity(intent);
            });
        }

        if (navCart != null) {
            navCart.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Shopping Cart Opened", Toast.LENGTH_SHORT).show()
            );
        }

        if (navProfile != null) {
            navProfile.setOnClickListener(v ->
                Toast.makeText(StoreTab.this, "Profile Tab Opened", Toast.LENGTH_SHORT).show()
            );
        }
    }
}

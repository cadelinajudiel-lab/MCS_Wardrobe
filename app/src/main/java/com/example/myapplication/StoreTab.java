package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class StoreTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_tab);

        // Header and Search Actions
        ImageView btnChat = findViewById(R.id.btnChat);
        ImageView btnFilter = findViewById(R.id.btnFilter);
        TextView tvMore = findViewById(R.id.tvMore);
        EditText etSearch = findViewById(R.id.etSearch);
        TextView tvNoProduct = findViewById(R.id.tvNoProduct);

        MaterialCardView cardProduct1 = findViewById(R.id.cardProduct1);
        MaterialCardView cardProduct2 = findViewById(R.id.cardProduct2);
        MaterialCardView cardProduct3 = findViewById(R.id.cardProduct3);
        MaterialCardView cardProduct4 = findViewById(R.id.cardProduct4);
        MaterialCardView cardProduct5 = findViewById(R.id.cardProduct5);

        if (etSearch != null) {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String query = s.toString().toLowerCase().trim();
                    boolean match1 = query.isEmpty() || "skirt".contains(query) || "shs skirt".contains(query);
                    boolean match2 = query.isEmpty() || "polo".contains(query) || "shs polo".contains(query);
                    boolean match3 = query.isEmpty() || "activity shirt".contains(query) || "shirt".contains(query);
                    boolean match4 = query.isEmpty() || "pants".contains(query) || "male pants".contains(query);
                    boolean match5 = query.isEmpty() || "pe shirt".contains(query) || "pe".contains(query);

                    if (cardProduct1 != null) {
                        cardProduct1.setVisibility(match1 ? View.VISIBLE : View.GONE);
                        if (!query.isEmpty() && match1) flashView(cardProduct1);
                    }
                    if (cardProduct2 != null) {
                        cardProduct2.setVisibility(match2 ? View.VISIBLE : View.GONE);
                        if (!query.isEmpty() && match2) flashView(cardProduct2);
                    }
                    if (cardProduct3 != null) {
                        cardProduct3.setVisibility(match3 ? View.VISIBLE : View.GONE);
                        if (!query.isEmpty() && match3) flashView(cardProduct3);
                    }
                    if (cardProduct4 != null) {
                        cardProduct4.setVisibility(match4 ? View.VISIBLE : View.GONE);
                        if (!query.isEmpty() && match4) flashView(cardProduct4);
                    }
                    if (cardProduct5 != null) {
                        cardProduct5.setVisibility(match5 ? View.VISIBLE : View.GONE);
                        if (!query.isEmpty() && match5) flashView(cardProduct5);
                    }

                    boolean anyMatch = match1 || match2 || match3 || match4 || match5;
                    if (tvNoProduct != null) {
                        tvNoProduct.setVisibility(anyMatch ? View.GONE : View.VISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        // Category Tabs
        TextView tabAll = findViewById(R.id.tabAll);
        TextView tabBlouse = findViewById(R.id.tabBlouse);
        TextView tabSkirts = findViewById(R.id.tabSkirts);

        // Cart Buttons
        Button btnPromoCart1 = findViewById(R.id.btnPromoCart1);
        Button btnPromoCart2 = findViewById(R.id.btnPromoCart2);
        Button btnCartProduct1 = findViewById(R.id.btnCartProduct1);
        Button btnCartProduct2 = findViewById(R.id.btnCartProduct2);
        Button btnCartProduct3 = findViewById(R.id.btnCartProduct3);
        Button btnCartProduct4 = findViewById(R.id.btnCartProduct4);
        Button btnCartProduct5 = findViewById(R.id.btnCartProduct5);

        // Bottom Navigation Bar
        TextView navHome = findViewById(R.id.navHome);
        TextView navPlaceOrder = findViewById(R.id.navPlaceOrder);
        TextView navCart = findViewById(R.id.navCart);
        TextView navProfile = findViewById(R.id.navProfile);

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
            btnPromoCart1.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added Male SHS Polo Set to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "MALE SHS POLO SET");
                intent.putExtra("PRODUCT_PRICE", 1380);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.shsmaleunif);
                startActivity(intent);
            });
        }

        if (btnPromoCart2 != null) {
            btnPromoCart2.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added Female Uniform Set to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "FEMALE UNIFORM SET");
                intent.putExtra("PRODUCT_PRICE", 650);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.img);
                startActivity(intent);
            });
        }

        if (btnCartProduct1 != null) {
            btnCartProduct1.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added SHS Skirt to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "SHS SKIRT (XS)");
                intent.putExtra("PRODUCT_PRICE", 690);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.shsskirt);
                startActivity(intent);
            });
        }

        if (btnCartProduct2 != null) {
            btnCartProduct2.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added SHS Polo to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "SHS POLO (S)");
                intent.putExtra("PRODUCT_PRICE", 1380);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.shsmaleunif);
                startActivity(intent);
            });
        }

        if (btnCartProduct3 != null) {
            btnCartProduct3.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added Activity Shirt to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "ACTIVITY SHIRT (M)");
                intent.putExtra("PRODUCT_PRICE", 250);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.activity_shirt_img);
                startActivity(intent);
            });
        }

        if (btnCartProduct4 != null) {
            btnCartProduct4.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added SHS Uniform Male Pants to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "SHS UNIFORM MALE PANTS");
                intent.putExtra("PRODUCT_PRICE", 690);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.shspants);
                startActivity(intent);
            });
        }

        if (btnCartProduct5 != null) {
            btnCartProduct5.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Added SHS PE Shirt to Cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                intent.putExtra("PRODUCT_NAME", "SHS PE SHIRT (M)");
                intent.putExtra("PRODUCT_PRICE", 450);
                intent.putExtra("PRODUCT_IMAGE_RES", R.drawable.peshirt);
                startActivity(intent);
            });
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
            navCart.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Opening Cart...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                startActivity(intent);
            });
        }

        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Toast.makeText(StoreTab.this, "Opening Profile...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreTab.this, ProfileScreenActivity.class);
                startActivity(intent);
            });
        }
    }

    private void flashView(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.2f, 1.0f);
        anim.setDuration(300);
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);
        view.startAnimation(anim);
    }
}

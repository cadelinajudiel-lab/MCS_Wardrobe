package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StoreTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_tab);

        updateUserName();

        TextView tvPricePromo1 = findViewById(R.id.tvPricePromo1);
        if (tvPricePromo1 != null) {
            String text = "₱828.00\n₱1,380.00 -40%";
            SpannableString span = new SpannableString(text);
            int strikeStart = text.indexOf("₱1,380.00");
            int strikeEnd = strikeStart + "₱1,380.00".length();
            int redStart = strikeEnd;

            span.setSpan(new StrikethroughSpan(), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFF888888), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFFD32F2F), redStart, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvPricePromo1.setText(span);
        }

        TextView tvPricePromo2 = findViewById(R.id.tvPricePromo2);
        if (tvPricePromo2 != null) {
            String text = "₱520.00\n₱650.00 -20%";
            SpannableString span = new SpannableString(text);
            int strikeStart = text.indexOf("₱650.00");
            int strikeEnd = strikeStart + "₱650.00".length();
            int redStart = strikeEnd;

            span.setSpan(new StrikethroughSpan(), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFF888888), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFFD32F2F), redStart, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvPricePromo2.setText(span);
        }

        // Search Bar Setup
        EditText etSearch = findViewById(R.id.etSearch);
        View card1 = findViewById(R.id.cardProduct1);
        View card2 = findViewById(R.id.cardProduct2);
        View card3 = findViewById(R.id.cardProduct3);
        View card4 = findViewById(R.id.cardProduct4);
        View card5 = findViewById(R.id.cardProduct5);
        TextView tvNoProduct = findViewById(R.id.tvNoProduct);

        if (etSearch != null) {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String query = s.toString().toLowerCase().trim();
                    boolean match1 = "shs skirt".contains(query) || "skirt".contains(query);
                    boolean match2 = "shs polo".contains(query) || "blouse".contains(query) || "polo".contains(query);
                    boolean match3 = "activity shirt".contains(query) || "activity".contains(query);
                    boolean match4 = "shs male uniform".contains(query) || "pants".contains(query) || "uniform".contains(query);
                    boolean match5 = "shs pe pants".contains(query) || "pe".contains(query);

                    if (card1 != null) {
                        card1.setVisibility(match1 ? View.VISIBLE : View.GONE);
                        if (match1 && !query.isEmpty()) flashView(card1);
                    }
                    if (card2 != null) {
                        card2.setVisibility(match2 ? View.VISIBLE : View.GONE);
                        if (match2 && !query.isEmpty()) flashView(card2);
                    }
                    if (card3 != null) {
                        card3.setVisibility(match3 ? View.VISIBLE : View.GONE);
                        if (match3 && !query.isEmpty()) flashView(card3);
                    }
                    if (card4 != null) {
                        card4.setVisibility(match4 ? View.VISIBLE : View.GONE);
                        if (match4 && !query.isEmpty()) flashView(card4);
                    }
                    if (card5 != null) {
                        card5.setVisibility(match5 ? View.VISIBLE : View.GONE);
                        if (match5 && !query.isEmpty()) flashView(card5);
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
        Button btnCartProduct3 = findViewById(R.id.btnCartProduct3);
        Button btnCartProduct4 = findViewById(R.id.btnCartProduct4);
        Button btnCartProduct5 = findViewById(R.id.btnCartProduct5);

        // Bottom Navigation Bar
        TextView navHome = findViewById(R.id.navHome);
        TextView navPlaceOrder = findViewById(R.id.navPlaceOrder);
        TextView navCart = findViewById(R.id.navCart);
        TextView navProfile = findViewById(R.id.navProfile);
        View headerProfileLayout = findViewById(R.id.headerProfileLayout);

        // Flash & Open Profile Activity Action
        View.OnClickListener profileClickAction = v -> {
            if (v != null) {
                v.animate().alpha(0.2f).setDuration(120).withEndAction(() -> {
                    v.animate().alpha(1.0f).setDuration(120).withEndAction(() -> {
                        Toast.makeText(StoreTab.this, "Opening Profile...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StoreTab.this, ProfileScreenActivity.class);
                        startActivity(intent);
                    });
                });
            } else {
                Intent intent = new Intent(StoreTab.this, ProfileScreenActivity.class);
                startActivity(intent);
            }
        };

        if (headerProfileLayout != null) {
            headerProfileLayout.setOnClickListener(profileClickAction);
        }

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

        // Add To Cart Listeners -> Adds product to cart without leaving Store Tab (Newest at top)
        View.OnClickListener openCartAction = v -> {
            String productName;
            int price;
            int originalPrice;
            int imgRes;

            if (v == btnPromoCart1) {
                productName = "MALE SHS POLO SET";
                price = 828;
                originalPrice = 1380;
                imgRes = R.drawable.shsmaleunif;
            } else if (v == btnPromoCart2) {
                productName = "FEMALE UNIFORM SET";
                price = 520;
                originalPrice = 650;
                imgRes = R.drawable.img;
            } else if (v == btnCartProduct1) {
                productName = "SHS SKIRT";
                price = 690;
                originalPrice = 690;
                imgRes = R.drawable.shsskirt;
            } else if (v == btnCartProduct2) {
                productName = "SHS POLO";
                price = 650;
                originalPrice = 650;
                imgRes = R.drawable.shsmaleunif;
            } else if (v == btnCartProduct3) {
                productName = "ACTIVITY SHIRT";
                price = 250;
                originalPrice = 250;
                imgRes = R.drawable.activity_shirt_img;
            } else if (v == btnCartProduct4) {
                productName = "SHS UNIFORM MALE PANTS";
                price = 690;
                originalPrice = 690;
                imgRes = R.drawable.shsmaleunif;
            } else if (v == btnCartProduct5) {
                productName = "SHS PE SHIRT";
                price = 450;
                originalPrice = 450;
                imgRes = R.drawable.peshirt;
            } else {
                productName = "SHS POLO";
                price = 650;
                originalPrice = 650;
                imgRes = R.drawable.shsmaleunif;
            }

            Lao_AddToCartBackground.addCartItem(productName, price, originalPrice, imgRes);
            Toast.makeText(StoreTab.this, "Added " + productName + " to cart (Top of list)!", Toast.LENGTH_SHORT).show();
        };

        if (btnPromoCart1 != null) btnPromoCart1.setOnClickListener(openCartAction);
        if (btnPromoCart2 != null) btnPromoCart2.setOnClickListener(openCartAction);
        if (btnCartProduct1 != null) btnCartProduct1.setOnClickListener(openCartAction);
        if (btnCartProduct2 != null) btnCartProduct2.setOnClickListener(openCartAction);
        if (btnCartProduct3 != null) btnCartProduct3.setOnClickListener(openCartAction);
        if (btnCartProduct4 != null) btnCartProduct4.setOnClickListener(openCartAction);
        if (btnCartProduct5 != null) btnCartProduct5.setOnClickListener(openCartAction);

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
                Intent intent = new Intent(StoreTab.this, Lao_AddToCartBackground.class);
                startActivity(intent);
            });
        }

        if (navProfile != null) {
            navProfile.setOnClickListener(profileClickAction);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserName();

        TextView tvPricePromo1 = findViewById(R.id.tvPricePromo1);
        if (tvPricePromo1 != null) {
            String text = "₱828.00\n₱1,380.00 -40%";
            SpannableString span = new SpannableString(text);
            int strikeStart = text.indexOf("₱1,380.00");
            int strikeEnd = strikeStart + "₱1,380.00".length();
            int redStart = strikeEnd;

            span.setSpan(new StrikethroughSpan(), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFF888888), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFFD32F2F), redStart, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvPricePromo1.setText(span);
        }

        TextView tvPricePromo2 = findViewById(R.id.tvPricePromo2);
        if (tvPricePromo2 != null) {
            String text = "₱520.00\n₱650.00 -20%";
            SpannableString span = new SpannableString(text);
            int strikeStart = text.indexOf("₱650.00");
            int strikeEnd = strikeStart + "₱650.00".length();
            int redStart = strikeEnd;

            span.setSpan(new StrikethroughSpan(), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFF888888), strikeStart, strikeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFFD32F2F), redStart, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvPricePromo2.setText(span);
        }

        // Search Bar Setup
        EditText etSearch = findViewById(R.id.etSearch);
        View card1 = findViewById(R.id.cardProduct1);
        View card2 = findViewById(R.id.cardProduct2);
        View card3 = findViewById(R.id.cardProduct3);
        View card4 = findViewById(R.id.cardProduct4);
        View card5 = findViewById(R.id.cardProduct5);
        TextView tvNoProduct = findViewById(R.id.tvNoProduct);

        if (etSearch != null) {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String query = s.toString().toLowerCase().trim();
                    boolean match1 = "shs skirt".contains(query) || "skirt".contains(query);
                    boolean match2 = "shs polo".contains(query) || "blouse".contains(query) || "polo".contains(query);
                    boolean match3 = "activity shirt".contains(query) || "activity".contains(query);
                    boolean match4 = "shs male uniform".contains(query) || "pants".contains(query) || "uniform".contains(query);
                    boolean match5 = "shs pe pants".contains(query) || "pe".contains(query);

                    if (card1 != null) {
                        card1.setVisibility(match1 ? View.VISIBLE : View.GONE);
                        if (match1 && !query.isEmpty()) flashView(card1);
                    }
                    if (card2 != null) {
                        card2.setVisibility(match2 ? View.VISIBLE : View.GONE);
                        if (match2 && !query.isEmpty()) flashView(card2);
                    }
                    if (card3 != null) {
                        card3.setVisibility(match3 ? View.VISIBLE : View.GONE);
                        if (match3 && !query.isEmpty()) flashView(card3);
                    }
                    if (card4 != null) {
                        card4.setVisibility(match4 ? View.VISIBLE : View.GONE);
                        if (match4 && !query.isEmpty()) flashView(card4);
                    }
                    if (card5 != null) {
                        card5.setVisibility(match5 ? View.VISIBLE : View.GONE);
                        if (match5 && !query.isEmpty()) flashView(card5);
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
    }

    private void updateUserName() {
        TextView tvUserName = findViewById(R.id.tvUserName);
        if (tvUserName != null) {
            String savedName = getSharedPreferences("UserProfile", MODE_PRIVATE).getString("user_name", null);
            if (savedName != null && !savedName.isEmpty()) {
                tvUserName.setText(savedName);
            }
        }
    }

    private void flashView(View v) {
        if (v != null) {
            v.setAlpha(0.2f);
            v.animate().alpha(1.0f).setDuration(400).start();
        }
    }
}

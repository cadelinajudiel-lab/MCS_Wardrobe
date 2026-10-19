package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.card.MaterialCardView;

public class StoreTabJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build the root RelativeLayout programmatically matching activity_store_tab.xml
        RelativeLayout rootLayout = new RelativeLayout(this);
        rootLayout.setId(android.R.id.content);
        rootLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setBackgroundColor(Color.parseColor("#6EC1F5"));

        // 7. Bottom Navigation Bar (placed at bottom first so ScrollView can layout above it)
        LinearLayout bottomNavigation = new LinearLayout(this);
        bottomNavigation.setId(View.generateViewId());
        RelativeLayout.LayoutParams navParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(60));
        navParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomNavigation.setLayoutParams(navParams);
        bottomNavigation.setOrientation(LinearLayout.HORIZONTAL);
        bottomNavigation.setBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setElevation(dpToPx(8));
        bottomNavigation.setBaselineAligned(false);

        // Bottom Nav Items (using TextView with compound drawables for warning-free performance)
        TextView navHome = createNavItem(R.drawable.ic_home, getString(R.string.nav_home), Color.parseColor("#000000"));
        TextView navPlaceOrder = createNavItem(R.drawable.ic_receipt, getString(R.string.nav_place_order), Color.parseColor("#757575"));
        TextView navCart = createNavItem(R.drawable.ic_cart, getString(R.string.nav_add_to_cart), Color.parseColor("#757575"));
        TextView navProfile = createNavItem(R.drawable.ic_profile, getString(R.string.nav_profile), Color.parseColor("#757575"));

        bottomNavigation.addView(navHome);
        bottomNavigation.addView(navPlaceOrder);
        bottomNavigation.addView(navCart);
        bottomNavigation.addView(navProfile);

        // Scrollable Main Content
        ScrollView scrollView = new ScrollView(this);
        RelativeLayout.LayoutParams scrollParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        scrollParams.addRule(RelativeLayout.ABOVE, bottomNavigation.getId());
        scrollView.setLayoutParams(scrollParams);
        scrollView.setFillViewport(true);

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setLayoutParams(new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(24));

        // 1. Header User Profile & Chat Icon
        LinearLayout headerLayout = new LinearLayout(this);
        LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        headerParams.setMargins(0, 0, 0, dpToPx(16));
        headerLayout.setLayoutParams(headerParams);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        headerLayout.setGravity(Gravity.CENTER_VERTICAL);
        headerLayout.setBaselineAligned(false);

        MaterialCardView avatarCard = new MaterialCardView(this);
        LinearLayout.LayoutParams avatarCardParams = new LinearLayout.LayoutParams(dpToPx(48), dpToPx(48));
        avatarCard.setLayoutParams(avatarCardParams);
        avatarCard.setRadius(dpToPx(24));
        avatarCard.setCardElevation(dpToPx(2));
        avatarCard.setStrokeWidth(0);

        ImageView ivAvatar = new ImageView(this);
        ivAvatar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ivAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivAvatar.setImageResource(R.drawable.circle_white);
        avatarCard.addView(ivAvatar);

        LinearLayout textInfoLayout = new LinearLayout(this);
        LinearLayout.LayoutParams textInfoParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        textInfoParams.setMargins(dpToPx(12), 0, 0, 0);
        textInfoLayout.setLayoutParams(textInfoParams);
        textInfoLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tvWelcome = new TextView(this);
        tvWelcome.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvWelcome.setText(R.string.welcome_back);
        tvWelcome.setTextColor(Color.parseColor("#FFFFFF"));
        tvWelcome.setTextSize(12);
        tvWelcome.setTypeface(null, Typeface.BOLD);

        TextView tvUserName = new TextView(this);
        tvUserName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvUserName.setText(R.string.user_name);
        tvUserName.setTextColor(Color.parseColor("#FFFFFF"));
        tvUserName.setTextSize(18);
        tvUserName.setTypeface(null, Typeface.BOLD);

        textInfoLayout.addView(tvWelcome);
        textInfoLayout.addView(tvUserName);

        ImageView btnChat = new ImageView(this);
        btnChat.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(36), dpToPx(36)));
        btnChat.setImageResource(R.drawable.ic_chat);

        headerLayout.addView(avatarCard);
        headerLayout.addView(textInfoLayout);
        headerLayout.addView(btnChat);

        // 2. Search & Filter Bar
        LinearLayout searchBarLayout = new LinearLayout(this);
        LinearLayout.LayoutParams searchBarContainerParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        searchBarContainerParams.setMargins(0, 0, 0, dpToPx(16));
        searchBarLayout.setLayoutParams(searchBarContainerParams);
        searchBarLayout.setOrientation(LinearLayout.HORIZONTAL);
        searchBarLayout.setGravity(Gravity.CENTER_VERTICAL);
        searchBarLayout.setBaselineAligned(false);

        LinearLayout searchInputWrapper = new LinearLayout(this);
        LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(0, dpToPx(46), 1f);
        searchInputWrapper.setLayoutParams(wrapperParams);
        searchInputWrapper.setBackgroundResource(R.drawable.bg_pill_search);
        searchInputWrapper.setGravity(Gravity.CENTER_VERTICAL);
        searchInputWrapper.setPadding(dpToPx(16), 0, dpToPx(16), 0);

        ImageView ivSearchIcon = new ImageView(this);
        ivSearchIcon.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(20), dpToPx(20)));
        ivSearchIcon.setImageResource(R.drawable.ic_search);

        EditText etSearch = new EditText(this);
        LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        etParams.setMargins(dpToPx(8), 0, 0, 0);
        etSearch.setLayoutParams(etParams);
        etSearch.setBackground(null);
        etSearch.setHint(R.string.search_hint);
        etSearch.setHintTextColor(Color.parseColor("#757575"));
        etSearch.setTextColor(Color.parseColor("#000000"));
        etSearch.setTextSize(13);
        etSearch.setInputType(InputType.TYPE_CLASS_TEXT);

        searchInputWrapper.addView(ivSearchIcon);
        searchInputWrapper.addView(etSearch);

        ImageView btnFilter = new ImageView(this);
        LinearLayout.LayoutParams filterParams = new LinearLayout.LayoutParams(dpToPx(32), dpToPx(32));
        filterParams.setMargins(dpToPx(12), 0, 0, 0);
        btnFilter.setLayoutParams(filterParams);
        btnFilter.setImageResource(R.drawable.ic_filter);

        searchBarLayout.addView(searchInputWrapper);
        searchBarLayout.addView(btnFilter);

        // 3. Category Buttons
        LinearLayout categoryLayout = new LinearLayout(this);
        LinearLayout.LayoutParams catParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        catParams.setMargins(0, 0, 0, dpToPx(20));
        categoryLayout.setLayoutParams(catParams);
        categoryLayout.setOrientation(LinearLayout.HORIZONTAL);
        categoryLayout.setBaselineAligned(false);

        TextView tabAll = createCategoryTab(getString(R.string.tab_all_products), true);
        TextView tabBlouse = createCategoryTab(getString(R.string.tab_blouse), false);
        TextView tabSkirts = createCategoryTab(getString(R.string.tab_skirts), false);

        LinearLayout.LayoutParams tabParamAll = new LinearLayout.LayoutParams(0, dpToPx(40), 1f);
        tabParamAll.setMargins(0, 0, dpToPx(6), 0);
        tabAll.setLayoutParams(tabParamAll);

        LinearLayout.LayoutParams tabParamBlouse = new LinearLayout.LayoutParams(0, dpToPx(40), 1f);
        tabParamBlouse.setMargins(dpToPx(3), 0, dpToPx(3), 0);
        tabBlouse.setLayoutParams(tabParamBlouse);

        LinearLayout.LayoutParams tabParamSkirts = new LinearLayout.LayoutParams(0, dpToPx(40), 1f);
        tabParamSkirts.setMargins(dpToPx(6), 0, 0, 0);
        tabSkirts.setLayoutParams(tabParamSkirts);

        categoryLayout.addView(tabAll);
        categoryLayout.addView(tabBlouse);
        categoryLayout.addView(tabSkirts);

        // 4. Promo Banner Horizontal Scroll Card
        HorizontalScrollView promoScroll = new HorizontalScrollView(this);
        promoScroll.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        promoScroll.setHorizontalScrollBarEnabled(false);
        ViewGroup.MarginLayoutParams promoScrollLp = (ViewGroup.MarginLayoutParams) promoScroll.getLayoutParams();
        promoScrollLp.bottomMargin = dpToPx(20);
        promoScroll.setLayoutParams(promoScrollLp);

        LinearLayout promoContainer = new LinearLayout(this);
        promoContainer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        promoContainer.setOrientation(LinearLayout.HORIZONTAL);

        // Promo Card 1
        MaterialCardView promoCard1 = createPromoCard(R.string.promo_title_1, R.string.promo_discount_1, R.drawable.shsmaleunif, dpToPx(12));
        // Promo Card 2
        MaterialCardView promoCard2 = createPromoCard(R.string.promo_title_2, R.string.promo_discount_2, R.drawable.img, 0);

        promoContainer.addView(promoCard1);
        promoContainer.addView(promoCard2);
        promoScroll.addView(promoContainer);

        // 5. Featured Product Header
        RelativeLayout featuredHeaderLayout = new RelativeLayout(this);
        ViewGroup.MarginLayoutParams featuredHeaderLp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        featuredHeaderLp.bottomMargin = dpToPx(12);
        featuredHeaderLayout.setLayoutParams(featuredHeaderLp);

        TextView tvMore = new TextView(this);
        tvMore.setId(View.generateViewId());
        RelativeLayout.LayoutParams moreLp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        moreLp.addRule(RelativeLayout.ALIGN_PARENT_END);
        tvMore.setLayoutParams(moreLp);
        tvMore.setText(R.string.more_link);
        tvMore.setTextColor(Color.parseColor("#004CB3"));
        tvMore.setTextSize(16);
        tvMore.setTypeface(null, Typeface.BOLD);

        TextView tvFeaturedTitle = new TextView(this);
        RelativeLayout.LayoutParams titleLp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleLp.addRule(RelativeLayout.ALIGN_PARENT_START);
        titleLp.addRule(RelativeLayout.LEFT_OF, tvMore.getId());
        tvFeaturedTitle.setLayoutParams(titleLp);
        tvFeaturedTitle.setText(R.string.featured_product_header);
        tvFeaturedTitle.setTextColor(Color.WHITE);
        tvFeaturedTitle.setTextSize(18);
        tvFeaturedTitle.setTypeface(null, Typeface.BOLD);

        featuredHeaderLayout.addView(tvFeaturedTitle);
        featuredHeaderLayout.addView(tvMore);

        // 6. Featured Products Grid (2 Columns)
        LinearLayout productsGridContainer = new LinearLayout(this);
        productsGridContainer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        productsGridContainer.setOrientation(LinearLayout.VERTICAL);

        LinearLayout productsRow1 = new LinearLayout(this);
        productsRow1.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        productsRow1.setOrientation(LinearLayout.HORIZONTAL);
        productsRow1.setBaselineAligned(false);

        // Product Card 1: SHS SKIRT
        MaterialCardView cardProduct1 = createProductCard(R.drawable.shsskirt, R.string.stock_50, R.string.rating_4_5, R.string.product_shs_skirt);
        // Product Card 2: SHS POLO
        MaterialCardView cardProduct2 = createProductCard(R.drawable.shsmaleunif, R.string.stock_70, R.string.rating_4_7, R.string.product_shs_polo);

        LinearLayout.LayoutParams gridLp1 = new LinearLayout.LayoutParams(0, dpToPx(220), 1f);
        gridLp1.setMargins(0, 0, dpToPx(6), 0);
        cardProduct1.setLayoutParams(gridLp1);

        LinearLayout.LayoutParams gridLp2 = new LinearLayout.LayoutParams(0, dpToPx(220), 1f);
        gridLp2.setMargins(dpToPx(6), 0, 0, 0);
        cardProduct2.setLayoutParams(gridLp2);

        productsRow1.addView(cardProduct1);
        productsRow1.addView(cardProduct2);

        LinearLayout productsRow2 = new LinearLayout(this);
        LinearLayout.LayoutParams row2Params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        row2Params.topMargin = dpToPx(12);
        productsRow2.setLayoutParams(row2Params);
        productsRow2.setOrientation(LinearLayout.HORIZONTAL);
        productsRow2.setBaselineAligned(false);

        // Product Card 3: ACTIVITY SHIRT
        MaterialCardView cardProduct3 = createProductCard(R.drawable.activityshirt, R.string.stock_75, R.string.rating_4_5, R.string.product_activity_shirt);
        LinearLayout.LayoutParams gridLp3 = new LinearLayout.LayoutParams(0, dpToPx(220), 1f);
        gridLp3.setMargins(0, 0, dpToPx(6), 0);
        cardProduct3.setLayoutParams(gridLp3);

        // Product Card 4: SHS UNIFORM MALE PANTS
        MaterialCardView cardProduct4 = createProductCard(R.drawable.shspants, R.string.stock_60, R.string.rating_4_0, R.string.product_shs_male_pants);
        LinearLayout.LayoutParams gridLp4 = new LinearLayout.LayoutParams(0, dpToPx(220), 1f);
        gridLp4.setMargins(dpToPx(6), 0, 0, 0);
        cardProduct4.setLayoutParams(gridLp4);

        productsRow2.addView(cardProduct3);
        productsRow2.addView(cardProduct4);

        LinearLayout productsRow3 = new LinearLayout(this);
        LinearLayout.LayoutParams row3Params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        row3Params.topMargin = dpToPx(12);
        productsRow3.setLayoutParams(row3Params);
        productsRow3.setOrientation(LinearLayout.HORIZONTAL);
        productsRow3.setBaselineAligned(false);

        // Product Card 5: SHS PE PANTS
        MaterialCardView cardProduct5 = createProductCard(R.drawable.peshirt, R.string.stock_50, R.string.rating_4_1, R.string.product_shs_pe_pants);
        LinearLayout.LayoutParams gridLp5 = new LinearLayout.LayoutParams(0, dpToPx(220), 1f);
        gridLp5.setMargins(0, 0, dpToPx(6), 0);
        cardProduct5.setLayoutParams(gridLp5);

        View spacer = new View(this);
        LinearLayout.LayoutParams spacerLp = new LinearLayout.LayoutParams(0, dpToPx(220), 1f);
        spacerLp.setMargins(dpToPx(6), 0, 0, 0);
        spacer.setLayoutParams(spacerLp);

        productsRow3.addView(cardProduct5);
        productsRow3.addView(spacer);

        productsGridContainer.addView(productsRow1);
        productsGridContainer.addView(productsRow2);
        productsGridContainer.addView(productsRow3);

        // Assemble content layout
        contentLayout.addView(headerLayout);
        contentLayout.addView(searchBarLayout);
        contentLayout.addView(categoryLayout);
        contentLayout.addView(promoScroll);
        contentLayout.addView(featuredHeaderLayout);
        contentLayout.addView(productsGridContainer);

        scrollView.addView(contentLayout);

        rootLayout.addView(scrollView);
        rootLayout.addView(bottomNavigation);

        setContentView(rootLayout);

        // Setup Listeners matching StoreTab.java
        btnChat.setOnClickListener(v -> Toast.makeText(this, "Opening Messages...", Toast.LENGTH_SHORT).show());
        btnFilter.setOnClickListener(v -> Toast.makeText(this, "Filters Opened", Toast.LENGTH_SHORT).show());
        tvMore.setOnClickListener(v -> Toast.makeText(this, "Viewing All Featured Products", Toast.LENGTH_SHORT).show());

        tabAll.setOnClickListener(v -> {
            tabAll.setBackgroundResource(R.drawable.bg_pill_dark);
            tabAll.setTextColor(Color.WHITE);
            tabBlouse.setBackgroundResource(R.drawable.bg_pill_light);
            tabBlouse.setTextColor(Color.parseColor("#333333"));
            tabSkirts.setBackgroundResource(R.drawable.bg_pill_light);
            tabSkirts.setTextColor(Color.parseColor("#333333"));
            Toast.makeText(this, "Showing All Products", Toast.LENGTH_SHORT).show();
        });

        tabBlouse.setOnClickListener(v -> {
            tabBlouse.setBackgroundResource(R.drawable.bg_pill_dark);
            tabBlouse.setTextColor(Color.WHITE);
            tabAll.setBackgroundResource(R.drawable.bg_pill_light);
            tabAll.setTextColor(Color.parseColor("#333333"));
            tabSkirts.setBackgroundResource(R.drawable.bg_pill_light);
            tabSkirts.setTextColor(Color.parseColor("#333333"));
            Toast.makeText(this, "Filtered: Blouse Category", Toast.LENGTH_SHORT).show();
        });

        tabSkirts.setOnClickListener(v -> {
            tabSkirts.setBackgroundResource(R.drawable.bg_pill_dark);
            tabSkirts.setTextColor(Color.WHITE);
            tabAll.setBackgroundResource(R.drawable.bg_pill_light);
            tabAll.setTextColor(Color.parseColor("#333333"));
            tabBlouse.setBackgroundResource(R.drawable.bg_pill_light);
            tabBlouse.setTextColor(Color.parseColor("#333333"));
            Toast.makeText(this, "Filtered: Skirts Category", Toast.LENGTH_SHORT).show();
        });

        // Promo Add to Cart buttons
        Button btnPromoCart1 = promoCard1.findViewById(R.id.btnPromoCart1);
        Button btnPromoCart2 = promoCard2.findViewById(R.id.btnPromoCart2);
        if (btnPromoCart1 != null) {
            btnPromoCart1.setOnClickListener(v -> Toast.makeText(this, "Added Male SHS Polo Set to Cart!", Toast.LENGTH_SHORT).show());
        }
        if (btnPromoCart2 != null) {
            btnPromoCart2.setOnClickListener(v -> Toast.makeText(this, "Added Female Uniform Set to Cart!", Toast.LENGTH_SHORT).show());
        }

        // Product Add to Cart buttons
        Button btnCartProduct1 = cardProduct1.findViewById(R.id.btnCartProduct1);
        Button btnCartProduct2 = cardProduct2.findViewById(R.id.btnCartProduct2);
        Button btnCartProduct3 = cardProduct3.findViewById(R.id.btnCartProduct3);
        Button btnCartProduct4 = cardProduct4.findViewById(R.id.btnCartProduct4);
        Button btnCartProduct5 = cardProduct5.findViewById(R.id.btnCartProduct5);
        if (btnCartProduct1 != null) {
            btnCartProduct1.setOnClickListener(v -> Toast.makeText(this, "Added SHS Skirt to Cart!", Toast.LENGTH_SHORT).show());
        }
        if (btnCartProduct2 != null) {
            btnCartProduct2.setOnClickListener(v -> Toast.makeText(this, "Added SHS Polo to Cart!", Toast.LENGTH_SHORT).show());
        }
        if (btnCartProduct3 != null) {
            btnCartProduct3.setOnClickListener(v -> Toast.makeText(this, "Added Activity Shirt to Cart!", Toast.LENGTH_SHORT).show());
        }
        if (btnCartProduct4 != null) {
            btnCartProduct4.setOnClickListener(v -> Toast.makeText(this, "Added SHS Uniform Male Pants to Cart!", Toast.LENGTH_SHORT).show());
        }
        if (btnCartProduct5 != null) {
            btnCartProduct5.setOnClickListener(v -> Toast.makeText(this, "Added SHS PE Shirt to Cart!", Toast.LENGTH_SHORT).show());
        }

        navPlaceOrder.setOnClickListener(v -> startActivity(new Intent(this, PlaceOrder.class)));
        navHome.setOnClickListener(v -> Toast.makeText(this, "You are on Home Store Tab", Toast.LENGTH_SHORT).show());
        navCart.setOnClickListener(v -> Toast.makeText(this, "Shopping Cart Opened", Toast.LENGTH_SHORT).show());
        navProfile.setOnClickListener(v -> Toast.makeText(this, "Profile Tab Opened", Toast.LENGTH_SHORT).show());
    }

    private MaterialCardView createPromoCard(int titleRes, int discountRes, int imageRes, int marginEnd) {
        MaterialCardView card = new MaterialCardView(this);
        ViewGroup.MarginLayoutParams cardLp = new ViewGroup.MarginLayoutParams(dpToPx(280), dpToPx(160));
        cardLp.setMargins(0, 0, marginEnd, 0);
        card.setLayoutParams(cardLp);
        card.setCardBackgroundColor(Color.parseColor("#EAF4FC"));
        card.setRadius(dpToPx(16));
        card.setCardElevation(dpToPx(4));
        card.setStrokeWidth(0);

        RelativeLayout innerLayout = new RelativeLayout(this);
        innerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        innerLayout.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));

        LinearLayout badge = new LinearLayout(this);
        badge.setId(View.generateViewId());
        RelativeLayout.LayoutParams badgeLp = new RelativeLayout.LayoutParams(dpToPx(120), ViewGroup.LayoutParams.WRAP_CONTENT);
        badge.setLayoutParams(badgeLp);
        badge.setBackgroundResource(R.drawable.bg_pill_dark);
        badge.setOrientation(LinearLayout.VERTICAL);
        badge.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
        badge.setBaselineAligned(false);

        TextView tvTitle = new TextView(this);
        tvTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvTitle.setText(titleRes);
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(11);
        tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView tvDiscount = new TextView(this);
        ViewGroup.MarginLayoutParams discLp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        discLp.topMargin = dpToPx(4);
        tvDiscount.setLayoutParams(discLp);
        tvDiscount.setText(discountRes);
        tvDiscount.setTextColor(Color.WHITE);
        tvDiscount.setTextSize(11);
        tvDiscount.setTypeface(null, android.graphics.Typeface.BOLD);

        badge.addView(tvTitle);
        badge.addView(tvDiscount);

        ImageView imgPromo = new ImageView(this);
        RelativeLayout.LayoutParams imgLp = new RelativeLayout.LayoutParams(dpToPx(140), dpToPx(140));
        imgLp.addRule(RelativeLayout.ALIGN_PARENT_END);
        imgLp.addRule(RelativeLayout.CENTER_VERTICAL);
        imgPromo.setLayoutParams(imgLp);
        imgPromo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgPromo.setImageResource(imageRes);

        AppCompatButton btnCart = new AppCompatButton(this);
        if (titleRes == R.string.promo_title_1) {
            btnCart.setId(R.id.btnPromoCart1);
        } else {
            btnCart.setId(R.id.btnPromoCart2);
        }
        RelativeLayout.LayoutParams btnLp = new RelativeLayout.LayoutParams(dpToPx(120), dpToPx(32));
        btnLp.addRule(RelativeLayout.BELOW, badge.getId());
        btnLp.topMargin = dpToPx(16);
        btnCart.setLayoutParams(btnLp);
        btnCart.setBackgroundResource(R.drawable.bg_yellow_button);
        btnCart.setPadding(0, 0, 0, 0);
        btnCart.setText(R.string.btn_add_to_cart);
        btnCart.setTextColor(Color.BLACK);
        btnCart.setTextSize(11);
        btnCart.setTypeface(null, android.graphics.Typeface.BOLD);

        innerLayout.addView(badge);
        innerLayout.addView(imgPromo);
        innerLayout.addView(btnCart);
        card.addView(innerLayout);

        return card;
    }

    private MaterialCardView createProductCard(int imageRes, int stockRes, int ratingRes, int nameRes) {
        MaterialCardView card = new MaterialCardView(this);
        card.setCardBackgroundColor(Color.parseColor("#F5F5F0"));
        card.setRadius(dpToPx(12));
        card.setCardElevation(dpToPx(3));
        card.setStrokeWidth(0);

        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        innerLayout.setOrientation(LinearLayout.VERTICAL);
        innerLayout.setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));
        innerLayout.setBaselineAligned(false);

        ImageView imgProduct = new ImageView(this);
        imgProduct.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(110)));
        imgProduct.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgProduct.setImageResource(imageRes);

        TextView tvStock = new TextView(this);
        LinearLayout.LayoutParams stockLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        stockLp.topMargin = dpToPx(4);
        tvStock.setLayoutParams(stockLp);
        tvStock.setText(stockRes);
        tvStock.setTextColor(Color.parseColor("#D32F2F"));
        tvStock.setTextSize(11);
        tvStock.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView tvRating = new TextView(this);
        tvRating.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvRating.setText(ratingRes);
        tvRating.setTextColor(Color.parseColor("#004CB3"));
        tvRating.setTextSize(11);
        tvRating.setTypeface(null, android.graphics.Typeface.BOLD);

        RelativeLayout bottomRow = new RelativeLayout(this);
        bottomRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        bottomRow.setGravity(Gravity.BOTTOM);

        AppCompatButton btnCart = new AppCompatButton(this);
        if (stockRes == R.string.stock_50) {
            btnCart.setId(R.id.btnCartProduct1);
        } else if (stockRes == R.string.stock_70) {
            btnCart.setId(R.id.btnCartProduct2);
        } else if (stockRes == R.string.stock_75) {
            btnCart.setId(R.id.btnCartProduct3);
        } else {
            btnCart.setId(R.id.btnCartProduct4);
        }
        RelativeLayout.LayoutParams btnLp = new RelativeLayout.LayoutParams(dpToPx(72), dpToPx(28));
        btnLp.addRule(RelativeLayout.ALIGN_PARENT_END);
        btnCart.setLayoutParams(btnLp);
        btnCart.setBackgroundResource(R.drawable.bg_yellow_button);
        btnCart.setPadding(0, 0, 0, 0);
        btnCart.setText(R.string.btn_add_to_cart);
        btnCart.setTextColor(Color.BLACK);
        btnCart.setTextSize(11);
        btnCart.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView tvName = new TextView(this);
        RelativeLayout.LayoutParams nameLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameLp.addRule(RelativeLayout.ALIGN_PARENT_START);
        nameLp.addRule(RelativeLayout.LEFT_OF, btnCart.getId());
        nameLp.addRule(RelativeLayout.CENTER_VERTICAL);
        tvName.setLayoutParams(nameLp);
        tvName.setText(nameRes);
        tvName.setTextColor(Color.BLACK);
        tvName.setTextSize(11);
        tvName.setTypeface(null, android.graphics.Typeface.BOLD);

        bottomRow.addView(tvName);
        bottomRow.addView(btnCart);

        innerLayout.addView(imgProduct);
        innerLayout.addView(tvStock);
        innerLayout.addView(tvRating);
        innerLayout.addView(bottomRow);
        card.addView(innerLayout);

        return card;
    }

    private TextView createNavItem(int iconRes, String title, int textColor) {
        TextView tv = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(title);
        tv.setTextColor(textColor);
        tv.setTextSize(11);
        tv.setTypeface(null, android.graphics.Typeface.BOLD);
        tv.setCompoundDrawablesWithIntrinsicBounds(0, iconRes, 0, 0);
        tv.setCompoundDrawablePadding(dpToPx(2));
        return tv;
    }

    private TextView createCategoryTab(String text, boolean isSelected) {
        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        tv.setTextSize(12);
        tv.setTypeface(null, android.graphics.Typeface.BOLD);
        if (isSelected) {
            tv.setBackgroundResource(R.drawable.bg_pill_dark);
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setBackgroundResource(R.drawable.bg_pill_light);
            tv.setTextColor(Color.parseColor("#333333"));
        }
        return tv;
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}

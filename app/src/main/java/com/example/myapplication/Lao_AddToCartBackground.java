package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Lao_AddToCartBackground extends AppCompatActivity {

    private TextView txtTotalValue;
    private CheckBox cbItem1, cbItem2, cbItem3, cbItem4;
    private LinearLayout layoutItem1, layoutItem2, layoutItem3, layoutItem4;

    private static final int PRICE_BLOUSE = 690;
    private static final int PRICE_SKIRT = 550;
    private static final int PRICE_PE = 510;
    private static final int PRICE_ACTIVITY = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lao_add_to_cart_background);

        txtTotalValue = findViewById(R.id.txt_total_value);

        cbItem1 = findViewById(R.id.cb_item_1);
        cbItem2 = findViewById(R.id.cb_item_2);
        cbItem3 = findViewById(R.id.cb_item_3);
        cbItem4 = findViewById(R.id.cb_item_4);

        layoutItem1 = findViewById(R.id.layout_item_1);
        layoutItem2 = findViewById(R.id.layout_item_2);
        layoutItem3 = findViewById(R.id.layout_item_3);
        layoutItem4 = findViewById(R.id.layout_item_4);

        ImageView btnDelete1 = findViewById(R.id.btn_delete_1);
        ImageView btnDelete2 = findViewById(R.id.btn_delete_2);
        ImageView btnDelete3 = findViewById(R.id.btn_delete_3);
        ImageView btnDelete4 = findViewById(R.id.btn_delete_4);

        Button btnPurchase = findViewById(R.id.btn_purchase);
        Button btnCancel = findViewById(R.id.btn_cancel);

        // Calculate initial total
        calculateTotal();

        // CheckBox Toggle Listeners
        View.OnClickListener checkListener = v -> calculateTotal();
        if (cbItem1 != null) cbItem1.setOnClickListener(checkListener);
        if (cbItem2 != null) cbItem2.setOnClickListener(checkListener);
        if (cbItem3 != null) cbItem3.setOnClickListener(checkListener);
        if (cbItem4 != null) cbItem4.setOnClickListener(checkListener);

        // Delete Item Listeners
        if (btnDelete1 != null) {
            btnDelete1.setOnClickListener(v -> {
                if (layoutItem1 != null) layoutItem1.setVisibility(View.GONE);
                if (cbItem1 != null) cbItem1.setChecked(false);
                calculateTotal();
                Toast.makeText(Lao_AddToCartBackground.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnDelete2 != null) {
            btnDelete2.setOnClickListener(v -> {
                if (layoutItem2 != null) layoutItem2.setVisibility(View.GONE);
                if (cbItem2 != null) cbItem2.setChecked(false);
                calculateTotal();
                Toast.makeText(Lao_AddToCartBackground.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnDelete3 != null) {
            btnDelete3.setOnClickListener(v -> {
                if (layoutItem3 != null) layoutItem3.setVisibility(View.GONE);
                if (cbItem3 != null) cbItem3.setChecked(false);
                calculateTotal();
                Toast.makeText(Lao_AddToCartBackground.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnDelete4 != null) {
            btnDelete4.setOnClickListener(v -> {
                if (layoutItem4 != null) layoutItem4.setVisibility(View.GONE);
                if (cbItem4 != null) cbItem4.setChecked(false);
                calculateTotal();
                Toast.makeText(Lao_AddToCartBackground.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        // Purchase Action -> Navigates to PlaceOrder Activity
        if (btnPurchase != null) {
            btnPurchase.setOnClickListener(v -> {
                Toast.makeText(Lao_AddToCartBackground.this, "Proceeding to Place Order...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Lao_AddToCartBackground.this, PlaceOrder.class);
                startActivity(intent);
            });
        }

        // Cancel Action -> Closes screen and returns to Store Tab
        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> finish());
        }
    }

    private void calculateTotal() {
        int total = 0;
        if (cbItem1 != null && cbItem1.isChecked() && layoutItem1.getVisibility() != View.GONE) {
            total += PRICE_BLOUSE;
        }
        if (cbItem2 != null && cbItem2.isChecked() && layoutItem2.getVisibility() != View.GONE) {
            total += PRICE_SKIRT;
        }
        if (cbItem3 != null && cbItem3.isChecked() && layoutItem3.getVisibility() != View.GONE) {
            total += PRICE_PE;
        }
        if (cbItem4 != null && cbItem4.isChecked() && layoutItem4.getVisibility() != View.GONE) {
            total += PRICE_ACTIVITY;
        }

        if (txtTotalValue != null) {
            txtTotalValue.setText(String.format("₱%,d.00", total));
        }
    }
}

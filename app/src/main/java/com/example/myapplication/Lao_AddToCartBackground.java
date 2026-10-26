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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lao_AddToCartBackground extends AppCompatActivity {

    private int currentTotal = 690;
    private TextView txtTotalValue;
    private LinearLayout layoutItem1, layoutItem2, layoutItem3, layoutItem4;
    private CheckBox cbItem1, cbItem2, cbItem3, cbItem4;
    private String productName = "";
    private int productImageRes = -1;

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

        txtTotalValue = findViewById(R.id.txt_total_value);
        TextView tvItem1 = findViewById(R.id.tv_item_1);
        TextView tvPrice1 = findViewById(R.id.tv_price_1);
        productName = getIntent().getStringExtra("PRODUCT_NAME");
        int productPrice = getIntent().getIntExtra("PRODUCT_PRICE", -1);
        productImageRes = getIntent().getIntExtra("PRODUCT_IMAGE_RES", -1);

        if (productName != null && !productName.isEmpty() && tvItem1 != null) {
            tvItem1.setText(productName);
            if (productPrice > 0) {
                currentTotal = productPrice;
                if (tvPrice1 != null) {
                    tvPrice1.setText("₱" + productPrice);
                }
                updateTotal();
            }
        }

        layoutItem1 = findViewById(R.id.layout_item_1);
        layoutItem2 = findViewById(R.id.layout_item_2);
        layoutItem3 = findViewById(R.id.layout_item_3);
        layoutItem4 = findViewById(R.id.layout_item_4);

        cbItem1 = findViewById(R.id.cb_item_1);
        cbItem2 = findViewById(R.id.cb_item_2);
        cbItem3 = findViewById(R.id.cb_item_3);
        cbItem4 = findViewById(R.id.cb_item_4);

        ImageView btnDelete1 = findViewById(R.id.btn_delete_1);
        ImageView btnDelete2 = findViewById(R.id.btn_delete_2);
        ImageView btnDelete3 = findViewById(R.id.btn_delete_3);
        ImageView btnDelete4 = findViewById(R.id.btn_delete_4);

        Button btnPurchase = findViewById(R.id.btn_purchase);
        Button btnCancel = findViewById(R.id.btn_cancel);

        // Delete item handlers
        if (btnDelete1 != null) {
            btnDelete1.setOnClickListener(v -> {
                if (layoutItem1.getVisibility() != View.GONE) {
                    layoutItem1.setVisibility(View.GONE);
                    currentTotal -= (productPrice > 0 ? productPrice : 650);
                    updateTotal();
                    Toast.makeText(this, "Removed Item", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (btnDelete2 != null) {
            btnDelete2.setOnClickListener(v -> {
                if (layoutItem2.getVisibility() != View.GONE) {
                    layoutItem2.setVisibility(View.GONE);
                    currentTotal -= 690;
                    updateTotal();
                    Toast.makeText(this, "Removed Item", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (btnDelete3 != null) {
            btnDelete3.setOnClickListener(v -> {
                if (layoutItem3.getVisibility() != View.GONE) {
                    layoutItem3.setVisibility(View.GONE);
                    currentTotal -= 450;
                    updateTotal();
                    Toast.makeText(this, "Removed Item", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (btnDelete4 != null) {
            btnDelete4.setOnClickListener(v -> {
                if (layoutItem4.getVisibility() != View.GONE) {
                    layoutItem4.setVisibility(View.GONE);
                    currentTotal -= 250;
                    updateTotal();
                    Toast.makeText(this, "Removed Item", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Checkbox listeners
        if (cbItem1 != null) {
            cbItem1.setOnCheckedChangeListener((buttonView, isChecked) -> recalculateTotal(productPrice > 0 ? productPrice : 650));
        }
        if (cbItem2 != null) {
            cbItem2.setOnCheckedChangeListener((buttonView, isChecked) -> recalculateTotal(productPrice > 0 ? productPrice : 650));
        }
        if (cbItem3 != null) {
            cbItem3.setOnCheckedChangeListener((buttonView, isChecked) -> recalculateTotal(productPrice > 0 ? productPrice : 650));
        }
        if (cbItem4 != null) {
            cbItem4.setOnCheckedChangeListener((buttonView, isChecked) -> recalculateTotal(productPrice > 0 ? productPrice : 650));
        }

        // Purchase button -> Proceed to PlaceOrder
        if (btnPurchase != null) {
            btnPurchase.setOnClickListener(v -> {
                Toast.makeText(this, "Proceeding to Place Order...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Lao_AddToCartBackground.this, PlaceOrder.class);
                intent.putExtra("CART_TOTAL", currentTotal);
                intent.putExtra("PRODUCT_NAME", productName);
                intent.putExtra("PRODUCT_IMAGE_RES", productImageRes);
                startActivity(intent);
            });
        }

        // Cancel button -> Finish activity
        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> {
                Toast.makeText(this, "Action Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }

    private void updateTotal() {
        if (currentTotal < 0) currentTotal = 0;
        txtTotalValue.setText(String.valueOf(currentTotal));
    }

    private void recalculateTotal(int price1) {
        int total = 0;
        if (layoutItem1.getVisibility() == View.VISIBLE && cbItem1.isChecked()) total += price1;
        if (layoutItem2.getVisibility() == View.VISIBLE && cbItem2.isChecked()) total += 690;
        if (layoutItem3.getVisibility() == View.VISIBLE && cbItem3.isChecked()) total += 450;
        if (layoutItem4.getVisibility() == View.VISIBLE && cbItem4.isChecked()) total += 250;
        currentTotal = total;
        updateTotal();
    }
}

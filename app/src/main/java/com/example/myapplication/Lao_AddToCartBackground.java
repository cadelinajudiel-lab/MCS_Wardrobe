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

    private static boolean deleted1 = false;
    private static boolean deleted2 = false;
    private static boolean deleted3 = false;
    private static boolean deleted4 = false;

    private LinearLayout layoutItem1, layoutItem2, layoutItem3, layoutItem4;
    private CheckBox cbItem1, cbItem2, cbItem3, cbItem4;
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

        txtTotalValue = findViewById(R.id.txt_total_value);
        Button btnPurchase = findViewById(R.id.btn_purchase);
        Button btnCancel = findViewById(R.id.btn_cancel);

        // Restore visibility based on static deleted flags
        if (deleted1 && layoutItem1 != null) layoutItem1.setVisibility(View.GONE);
        if (deleted2 && layoutItem2 != null) layoutItem2.setVisibility(View.GONE);
        if (deleted3 && layoutItem3 != null) layoutItem3.setVisibility(View.GONE);
        if (deleted4 && layoutItem4 != null) layoutItem4.setVisibility(View.GONE);

        updateTotal();

        // Checkbox listeners
        if (cbItem1 != null) cbItem1.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotal());
        if (cbItem2 != null) cbItem2.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotal());
        if (cbItem3 != null) cbItem3.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotal());
        if (cbItem4 != null) cbItem4.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotal());

        // Delete button listeners
        if (btnDelete1 != null) {
            btnDelete1.setOnClickListener(v -> {
                deleted1 = true;
                if (layoutItem1 != null) layoutItem1.setVisibility(View.GONE);
                if (cbItem1 != null) cbItem1.setChecked(false);
                updateTotal();
                Toast.makeText(this, "Item 1 removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnDelete2 != null) {
            btnDelete2.setOnClickListener(v -> {
                deleted2 = true;
                if (layoutItem2 != null) layoutItem2.setVisibility(View.GONE);
                if (cbItem2 != null) cbItem2.setChecked(false);
                updateTotal();
                Toast.makeText(this, "Item 2 removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnDelete3 != null) {
            btnDelete3.setOnClickListener(v -> {
                deleted3 = true;
                if (layoutItem3 != null) layoutItem3.setVisibility(View.GONE);
                if (cbItem3 != null) cbItem3.setChecked(false);
                updateTotal();
                Toast.makeText(this, "Item 3 removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnDelete4 != null) {
            btnDelete4.setOnClickListener(v -> {
                deleted4 = true;
                if (layoutItem4 != null) layoutItem4.setVisibility(View.GONE);
                if (cbItem4 != null) cbItem4.setChecked(false);
                updateTotal();
                Toast.makeText(this, "Item 4 removed from cart", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnPurchase != null) {
            btnPurchase.setOnClickListener(v -> {
                Toast.makeText(this, "Proceeding to Place Order...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Lao_AddToCartBackground.this, PlaceOrder.class);
                startActivity(intent);
            });
        }

        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> finish());
        }
    }

    private void updateTotal() {
        int total = 0;
        if (!deleted1 && cbItem1 != null && cbItem1.isChecked()) total += 650;
        if (!deleted2 && cbItem2 != null && cbItem2.isChecked()) total += 690;
        if (!deleted3 && cbItem3 != null && cbItem3.isChecked()) total += 450;
        if (!deleted4 && cbItem4 != null && cbItem4.isChecked()) total += 250;

        if (txtTotalValue != null) {
            txtTotalValue.setText("₱" + total);
        }
    }
}

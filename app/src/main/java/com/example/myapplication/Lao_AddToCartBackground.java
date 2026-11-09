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

import java.util.ArrayList;

public class Lao_AddToCartBackground extends AppCompatActivity {

    public static boolean deleted1 = false;
    public static boolean deleted2 = false;
    public static boolean deleted3 = false;
    public static boolean deleted4 = false;

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
                ArrayList<String> titles = new ArrayList<>();
                ArrayList<Integer> images = new ArrayList<>();
                ArrayList<String> descs = new ArrayList<>();
                ArrayList<Integer> prices = new ArrayList<>();

                if (!deleted1 && cbItem1 != null && cbItem1.isChecked()) {
                    titles.add("SHS BLOUSE FEMALE (S)");
                    images.add(R.drawable.img);
                    descs.add("Stay neat, comfortable, and ready for class with this classic long sleeve school blouse. Made from soft, breathable, and durable fabric.");
                    prices.add(650);
                }
                if (!deleted2 && cbItem2 != null && cbItem2.isChecked()) {
                    titles.add("SHS SKIRT (XS)");
                    images.add(R.drawable.shsskirt);
                    descs.add("Official Manila Cathedral School Senior High School Skirt, made of durable blue fabric tailored for comfortable daily wear.");
                    prices.add(690);
                }
                if (!deleted3 && cbItem3 != null && cbItem3.isChecked()) {
                    titles.add("SHS P. E. (M)");
                    images.add(R.drawable.peshirt);
                    descs.add("Official Manila Cathedral School Physical Education uniform shirt, breathable and lightweight.");
                    prices.add(450);
                }
                if (!deleted4 && cbItem4 != null && cbItem4.isChecked()) {
                    titles.add("ACTIVITY SHIRT (M)");
                    images.add(R.drawable.activity_shirt_img);
                    descs.add("Official Manila Cathedral School Activity Shirt for school events and extracurriculars.");
                    prices.add(250);
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

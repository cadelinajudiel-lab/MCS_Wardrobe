package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileScreenActivity extends AppCompatActivity {

    private TextView tvNameVal, tvBioVal, tvGenderVal, tvBirthdayVal, tvPhoneVal, tvEmailVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_profile);

        ImageView ivBack = findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(v -> finish());
        }

        LinearLayout layoutEditAvatar = findViewById(R.id.layout_edit_avatar);
        if (layoutEditAvatar != null) {
            layoutEditAvatar.setOnClickListener(v ->
                Toast.makeText(this, "Profile picture editing mode opened", Toast.LENGTH_SHORT).show()
            );
        }

        tvNameVal = findViewById(R.id.tv_name_val);
        tvBioVal = findViewById(R.id.tv_bio_val);
        tvGenderVal = findViewById(R.id.tv_gender_val);
        tvBirthdayVal = findViewById(R.id.tv_birthday_val);
        tvPhoneVal = findViewById(R.id.tv_phone_val);
        tvEmailVal = findViewById(R.id.tv_email_val);

        LinearLayout rowName = findViewById(R.id.row_name);
        LinearLayout rowBio = findViewById(R.id.row_bio);
        LinearLayout rowGender = findViewById(R.id.row_gender);
        LinearLayout rowBirthday = findViewById(R.id.row_birthday);
        LinearLayout rowPhone = findViewById(R.id.row_phone);
        LinearLayout rowEmail = findViewById(R.id.row_email);

        if (rowName != null) {
            rowName.setOnClickListener(v -> showEditDialog("Name", tvNameVal));
        }
        if (rowBio != null) {
            rowBio.setOnClickListener(v -> showEditDialog("Bio", tvBioVal));
        }
        if (rowGender != null) {
            rowGender.setOnClickListener(v -> showEditDialog("Gender", tvGenderVal));
        }
        if (rowBirthday != null) {
            rowBirthday.setOnClickListener(v -> showEditDialog("Birthday", tvBirthdayVal));
        }
        if (rowPhone != null) {
            rowPhone.setOnClickListener(v -> showEditDialog("Phone", tvPhoneVal));
        }
        if (rowEmail != null) {
            rowEmail.setOnClickListener(v -> showEmailVerifyDialog());
        }
    }

    private void showEditDialog(String fieldName, TextView targetTextView) {
        EditText input = new EditText(this);
        input.setHint("Enter " + fieldName.toLowerCase());
        input.setPadding(50, 30, 50, 30);
        String currentText = targetTextView.getText().toString();
        if (!currentText.startsWith("Set Now")) {
            input.setText(currentText);
        }

        new AlertDialog.Builder(this)
                .setTitle("Edit " + fieldName)
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String value = input.getText().toString().trim();
                    if (!value.isEmpty()) {
                        targetTextView.setText(value);
                        Toast.makeText(this, fieldName + " updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void showEmailVerifyDialog() {
        EditText input = new EditText(this);
        input.setHint("Enter email address");
        input.setPadding(50, 30, 50, 30);

        new AlertDialog.Builder(this)
                .setTitle("Verify Email")
                .setMessage("Enter your email address to receive verification code:")
                .setView(input)
                .setPositiveButton("Verify", (dialog, which) -> {
                    String email = input.getText().toString().trim();
                    if (!email.isEmpty()) {
                        tvEmailVal.setText(getString(R.string.profile_verified));
                        Toast.makeText(this, "Verification email sent to " + email, Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }
}

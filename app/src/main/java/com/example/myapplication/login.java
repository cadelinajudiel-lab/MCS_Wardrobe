package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private static final String PREFS_NAME = "LoginPrefs";
    private static final String KEY_REMEMBER_ME = "remember_me";
    private static final String KEY_SAVED_EMAIL = "saved_email";
    private static final String KEY_SAVED_PASSWORD = "saved_password";

    private EditText etEmail, etPassword;
    private CheckBox cbRemember;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI Elements
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        TextView tvSignUp = findViewById(R.id.tvSignUp);
        Button btnLogin = findViewById(R.id.btnLogin);
        ImageView ivFacebook = findViewById(R.id.ivFacebook);
        ImageView ivGoogle = findViewById(R.id.ivGoogle);
        ImageView ivApple = findViewById(R.id.ivApple);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load Remembered Credentials if available
        loadRememberedCredentials();

        // Button Listeners
        btnLogin.setOnClickListener(v -> handleLogin());
        tvForgotPassword.setOnClickListener(v -> handleForgotPassword());
        tvSignUp.setOnClickListener(v -> handleSignUp());

        // Social Login Listeners
        ivFacebook.setOnClickListener(v -> handleSocialLogin("Facebook"));
        ivGoogle.setOnClickListener(v -> handleSocialLogin("Google"));
        ivApple.setOnClickListener(v -> handleSocialLogin("Apple"));
    }

    private void loadRememberedCredentials() {
        boolean isRemembered = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
        if (isRemembered) {
            String savedEmail = sharedPreferences.getString(KEY_SAVED_EMAIL, "");
            String savedPassword = sharedPreferences.getString(KEY_SAVED_PASSWORD, "");
            etEmail.setText(savedEmail);
            etPassword.setText(savedPassword);
            cbRemember.setChecked(true);
        }
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email address");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }

        // Save or clear Remember Me state
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (cbRemember.isChecked()) {
            editor.putBoolean(KEY_REMEMBER_ME, true);
            editor.putString(KEY_SAVED_EMAIL, email);
            editor.putString(KEY_SAVED_PASSWORD, password);
        } else {
            editor.clear();
        }
        editor.apply();

        Toast.makeText(this, "Login Successful! Welcome " + email, Toast.LENGTH_SHORT).show();

        // Navigate to StoreTab Activity
        Intent intent = new Intent(login.this, StoreTab.class);
        intent.putExtra("USER_EMAIL", email);
        startActivity(intent);
    }

    private void handleForgotPassword() {
        String currentEmail = etEmail.getText().toString().trim();

        EditText inputEmail = new EditText(this);
        inputEmail.setHint("Enter your email address");
        inputEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        inputEmail.setPadding(50, 30, 50, 30);
        if (!currentEmail.isEmpty()) {
            inputEmail.setText(currentEmail);
        }

        new AlertDialog.Builder(this)
                .setTitle("Forgot Password")
                .setMessage("Enter your email to receive password reset instructions.")
                .setView(inputEmail)
                .setPositiveButton("Reset", (dialog, which) -> {
                    String email = inputEmail.getText().toString().trim();
                    if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(login.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(login.this, "Password reset link sent to " + email, Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void handleSignUp() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 30);

        final EditText etNewEmail = new EditText(this);
        etNewEmail.setHint("Email Address");
        etNewEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        layout.addView(etNewEmail);

        final EditText etNewPassword = new EditText(this);
        etNewPassword.setHint("Password");
        etNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(etNewPassword);

        final EditText etConfirmPassword = new EditText(this);
        etConfirmPassword.setHint("Confirm Password");
        etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(etConfirmPassword);

        new AlertDialog.Builder(this)
                .setTitle("Create New Account")
                .setView(layout)
                .setPositiveButton("Sign Up", (dialog, which) -> {
                    String email = etNewEmail.getText().toString().trim();
                    String pass = etNewPassword.getText().toString().trim();
                    String confirmPass = etConfirmPassword.getText().toString().trim();

                    if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(login.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (pass.isEmpty() || pass.length() < 6) {
                        Toast.makeText(login.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!pass.equals(confirmPass)) {
                        Toast.makeText(login.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    etEmail.setText(email);
                    etPassword.setText(pass);
                    Toast.makeText(login.this, "Account created successfully! Pre-filled credentials.", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void handleSocialLogin(String provider) {
        Toast.makeText(this, "Signing in with " + provider + "...", Toast.LENGTH_SHORT).show();

        // Navigate to StoreTab Activity
        Intent intent = new Intent(login.this, StoreTab.class);
        intent.putExtra("USER_EMAIL", provider.toLowerCase() + "_user@example.com");
        startActivity(intent);
    }
}

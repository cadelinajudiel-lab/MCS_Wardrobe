package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        TextView tvSignUp = findViewById(R.id.tvSignUp);
        Button btnLogin = findViewById(R.id.btnLogin);
        ImageView ivFacebook = findViewById(R.id.ivFacebook);
        ImageView ivGoogle = findViewById(R.id.ivGoogle);
        ImageView ivApple = findViewById(R.id.ivApple);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(login.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(login.this, "Logging in...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, PlaceOrder.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(login.this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        );

        tvSignUp.setOnClickListener(v ->
                Toast.makeText(login.this, "Sign up clicked", Toast.LENGTH_SHORT).show()
        );

        ivFacebook.setOnClickListener(v ->
                Toast.makeText(login.this, "Facebook login clicked", Toast.LENGTH_SHORT).show()
        );

        ivGoogle.setOnClickListener(v ->
                Toast.makeText(login.this, "Google login clicked", Toast.LENGTH_SHORT).show()
        );

        ivApple.setOnClickListener(v ->
                Toast.makeText(login.this, "Apple login clicked", Toast.LENGTH_SHORT).show()
        );
    }
}

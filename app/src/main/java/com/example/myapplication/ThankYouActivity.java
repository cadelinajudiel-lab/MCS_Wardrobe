package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThankYouActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable transitionRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Button btnReceipt = findViewById(R.id.btn_receipt);
        if (btnReceipt != null) {
            btnReceipt.setOnClickListener(v -> {
                handler.removeCallbacks(transitionRunnable);
                openReceipt();
            });
        }

        // Flash Thank You screen for 1.8 seconds before automatically opening Receipt
        transitionRunnable = this::openReceipt;
        handler.postDelayed(transitionRunnable, 1800);
    }

    private void openReceipt() {
        Intent intent = new Intent(ThankYouActivity.this, GenerateReceipt.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(transitionRunnable);
    }
}

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
            btnReceipt.setText(getString(R.string.return_to_store));
            btnReceipt.setOnClickListener(v -> {
                handler.removeCallbacks(transitionRunnable);
                returnToStore();
            });
        }

        // Return to Store automatically after 2.5 seconds
        transitionRunnable = this::returnToStore;
        handler.postDelayed(transitionRunnable, 2500);
    }

    private void returnToStore() {
        Intent intent = new Intent(ThankYouActivity.this, StoreTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(transitionRunnable);
    }
}

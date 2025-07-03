package com.example.solucaologcatbinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.solucaologcatbinding.databinding.ActivityMainBBinding;

public class MainActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // setContentView(R.layout.activity_main_b);
        ActivityMainBBinding binding = ActivityMainBBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        binding.buttonB.setOnClickListener(v -> {
            Log.i("MainActivity", "Button B clicked");
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
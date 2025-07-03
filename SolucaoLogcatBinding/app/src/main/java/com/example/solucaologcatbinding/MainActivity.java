package com.example.solucaologcatbinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.solucaologcatbinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView.setText("Logcat Binding Example");

        Log.v("MainActivity", "Verbose message");
        Log.d("MainActivity", "Debugging information");
        Log.i("MainActivity", "onCreate called");
        Log.w("MainActivity", "Warning message");
        Log.e("MainActivity", "Error message");
        Log.wtf("MainActivity", "What a Terrible Failure");

        binding.button.setOnClickListener(v -> {
            Log.i("MainActivity", "Button clicked");

            Intent intent = new Intent(MainActivity.this, MainActivityB.class);
            intent.putExtra("message", "Hello from MainActivity");
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
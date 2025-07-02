package com.example.solucaosharedprefscompleta;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private boolean switchState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        SwitchCompat switchCompat = findViewById(R.id.switchCompat);

        // Load saved state from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        switchState = sharedPreferences.getBoolean("switchState", false);

        // If switch is on, retrieve the counter value; otherwise reset it
        if (switchState) {
            counter = sharedPreferences.getInt("counter", 0);
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            counter = 0;
            editor.putInt("counter", 0);
            editor.apply();
        }

        // Set initial values
        textView.setText("Counter: " + counter);
        switchCompat.setChecked(switchState);

        // Set up button and switch listeners
        button.setOnClickListener(v -> {
            counter++;
            textView.setText("Counter: " + counter);
            if (switchCompat.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("counter", counter);
                editor.apply();
            }
        });

        // Save switch state
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("switchState", isChecked);
            editor.apply();
        });
    }
}
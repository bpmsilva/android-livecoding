package com.example.solucaotemporizadorcompleta;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.solucaotemporizadorcompleta.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private boolean running = false;
    private int seconds = 0;
    private ActivityMainBinding binding;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (running) {
                seconds++;
                binding.timerTextView.setText(String.valueOf(seconds));
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.play.setOnClickListener(v -> {
            if (!running) {
                running = true;
                handler.post(runnable);
            }
        });

        binding.stop.setOnClickListener(v -> running = false);

        binding.reset.setOnClickListener(v -> {
            running = false;
            seconds = 0;
            binding.timerTextView.setText(String.valueOf(seconds));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
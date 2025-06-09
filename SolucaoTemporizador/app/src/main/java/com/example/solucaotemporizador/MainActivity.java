package com.example.solucaotemporizador;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running = false;
    private TextView timerTextView;
    private Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (running) {
                seconds++;
                timerTextView.setText(String.valueOf(seconds));
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Get all the views
        timerTextView = findViewById(R.id.timerTextView);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(v -> {
            if (!running) {
                running = true;
                handler.post(runnable);
            }
        });

        stopButton.setOnClickListener(v -> running = false);

        resetButton.setOnClickListener(v -> {
            running = false;
            seconds = 0;
            timerTextView.setText(String.valueOf(seconds));
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
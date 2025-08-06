package com.example.explicacaotemporizador;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean running = false;
    private int seconds = 0;
    private TextView timerTextView;
    private Handler handler = new Handler();

    private Runnable timerRunnable = new Runnable() {
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

        timerTextView = findViewById(R.id.timer);

        // Buttons
        Button startButton = findViewById(R.id.start_button);
        Button stopButton  = findViewById(R.id.stop_button);
        Button resetButton = findViewById(R.id.reset_button);

        timerTextView.setText("0");

        /* // Wrong way to simulate a timer
        startButton.setOnClickListener(v -> {
            for (int i = 0; i < 10; i++) {
                // wrong way to simulate a timer
                sleep(1000); // Sleep for 1 second
                seconds++;
                timerTextView.setText(String.valueOf(seconds));
            }
        }); */

        // Right way to simulate a timer
        startButton.setOnClickListener(v -> {
            if (!running) {
                running = true;
                handler.postDelayed(timerRunnable, 1000);
            }
        });

        // Stop the timer
        stopButton.setOnClickListener(v -> {
            running = false;
        });

        // Reset the timer
        resetButton.setOnClickListener(v -> {
            running = false;
            seconds = 0;
            timerTextView.setText("0");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Callback class to handle the timer updates
    private class OnTimerUpdate implements Runnable {
        @Override
        public void run() {
            if (running) {
                seconds++;
                timerTextView.setText(String.valueOf(seconds));
                handler.postDelayed(this, 1000); // Schedule the next update
            }
        }
    }
}
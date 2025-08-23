package com.example.viewprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ProgressBar horizontalProgressBar, circularProgressBar;
    private Button button;
    private Handler handler = new Handler();

    int progress  = 0;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (progress <= 100) {
                horizontalProgressBar.setProgress(progress);
                progress += 10;
                handler.postDelayed(this, 1000);
            } else {
                horizontalProgressBar.setProgress(0);
                progress = 0;
                circularProgressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize ProgressBars
        horizontalProgressBar = findViewById(R.id.progress_bar_horizontal);
        circularProgressBar = findViewById(R.id.progress_bar_circular);
        button = findViewById(R.id.button);

        circularProgressBar.setVisibility(ProgressBar.INVISIBLE);

        button.setOnClickListener(v -> {
            progress = 0;
            horizontalProgressBar.setProgress(0);
            handler.postDelayed(runnable, 1000);
            circularProgressBar.setVisibility(ProgressBar.VISIBLE);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
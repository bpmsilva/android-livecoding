package com.example.solucaofuncoesanonimas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Using a lambda expression
        Button anonymousClassButton = findViewById(R.id.anonymous_class_button);
        Button lambdaFunctionButton = findViewById(R.id.lambda_class_button);
        Button customClassButton = findViewById(R.id.custom_class_button);
        Button activityClassButton = findViewById(R.id.activity_class_button);

        // Using an anonymous class
        anonymousClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        "Anonymous class clicked!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        lambdaFunctionButton.setOnClickListener(v -> {
            Toast.makeText(this, "Anonymous function clicked!", Toast.LENGTH_SHORT).show();
        });

        // Using a custom listener class
        customClassButton.setOnClickListener(new MyCustomListener());

        // Using the activity class as a listener
        activityClassButton.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_class_button) {
            Toast.makeText(MainActivity.this, "Activity button clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyCustomListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Custom class button clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
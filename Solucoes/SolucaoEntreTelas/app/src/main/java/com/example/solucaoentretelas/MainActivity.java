package com.example.solucaoentretelas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Button button_a = findViewById(R.id.button_a);
        Button button_b = findViewById(R.id.button_b);

        button_a.setOnClickListener(v -> {
            Intent intent_a = new Intent(MainActivity.this, ActivityA.class);
            startActivity(intent_a);
        });

        button_b.setOnClickListener(v -> {
            Intent intent_b = new Intent(MainActivity.this, ActivityB.class);
            intent_b.putExtra("mensagem", "Olá, mundo!");
            startActivity(intent_b);
        });

    }
}
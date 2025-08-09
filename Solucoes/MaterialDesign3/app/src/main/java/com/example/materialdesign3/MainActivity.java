package com.example.materialdesign3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button newMaterial3Button = findViewById(R.id.button_new_material3);
        newMaterial3Button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewMaterial3Activity.class);
            startActivity(intent);
        });

        Button noMaterialButton = findViewById(R.id.button_no_material);
        noMaterialButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NoMaterialActivity.class);
            startActivity(intent);
        });

        Button completeExampleButton = findViewById(R.id.button_complete_example);
        completeExampleButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CompleteExampleActivity.class);
            startActivity(intent);
        });

        Button newNoMaterialButton = findViewById(R.id.button_new_no_material);
        newNoMaterialButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewNoMaterialActivity.class);
            startActivity(intent);
        });

    }
}
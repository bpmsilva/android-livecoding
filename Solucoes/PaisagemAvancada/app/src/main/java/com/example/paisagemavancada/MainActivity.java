package com.example.paisagemavancada;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int rotationCount = 0;  // contador de rotações

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ligar a variável Java ao componente XML
        textView = findViewById(R.id.text_view);

        int current = getResources().getConfiguration().orientation;

        if (savedInstanceState != null) {
            rotationCount = savedInstanceState.getInt("rotationCount", 0);
            // conta só transições que CHEGAM em paisagem
            if (current == Configuration.ORIENTATION_LANDSCAPE ) {
                rotationCount++;
            }
        }

        textView.setText((current == Configuration.ORIENTATION_LANDSCAPE ? "Paisagem" : "Retrato")
                + " (" + rotationCount + " mudanças)");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rotationCount", rotationCount);
    }
}
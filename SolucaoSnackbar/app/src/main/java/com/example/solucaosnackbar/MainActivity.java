package com.example.solucaosnackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button snackbarButton;
    private boolean isPrimaryColor = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        snackbarButton = findViewById(R.id.snackbar_button);
        snackbarButton.setOnClickListener(v -> showSnackbar());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void showSnackbar() {
        Snackbar.make(findViewById(R.id.main), "Mudar a cor do Botão", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", v1 -> {
                    // Ação quando o usuário clicar em "OK"
                    Toast.makeText(this, "Ação confirmada", Toast.LENGTH_SHORT).show();
                    if (isPrimaryColor) {
                        snackbarButton.setBackgroundColor(ContextCompat.getColor(this, R.color.danger_red));
                    } else {
                        snackbarButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    }
                    isPrimaryColor = !isPrimaryColor; // Alterna a cor
                }).show();
    }
}

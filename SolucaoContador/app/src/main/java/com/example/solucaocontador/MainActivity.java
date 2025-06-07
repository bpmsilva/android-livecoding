package com.example.solucaocontador;

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

    private int contador = 0;

    // As contantes para SharedPreferences são declaras como static final,
    // pois não devem ser alteradas durante a execução do aplicativo e pertencem à classe.
    private static final String PREFS_NAME = "contador_prefs";
    private static final String KEY_CONTADOR = "contador";

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

        // Inicialize os componentes do aplicativo aqui
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        SwitchCompat switchCompat = findViewById(R.id.save_switch);

        // SharedPreferences para salvar o contador
        // MODE_PRIVATE é uma constante importada em android.content.Context
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        contador = prefs.getInt(KEY_CONTADOR, 0); // 0 é o valor padrão

        textView.setText(String.valueOf(contador));

        button.setOnClickListener(view -> {
            // tratar o clique do botão
            contador++;
            textView.setText(String.valueOf(contador));

            // Salva o novo valor do contador
            if (switchCompat.isChecked()) {
                // Se o switch estiver ativado, salva o contador
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(KEY_CONTADOR, contador);
                editor.apply(); // Salva de forma assíncrona
            }
        });
    }
}
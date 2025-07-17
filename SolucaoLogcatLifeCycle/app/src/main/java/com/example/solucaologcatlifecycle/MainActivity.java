package com.example.solucaologcatlifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CICLO_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate chamado");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart chamado");
        // Ideal para: ações que tornam a Activity visível, mas ainda não interativa.
        // Ex: iniciar animações leves, preparar recursos visuais.
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume chamado");
        // Ideal para: retomar interações com o usuário.
        // Ex: iniciar câmera, sensores, ouvir localização, etc.
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause chamado");
        // Ideal para: pausar ações que não devem continuar com a Activity em segundo plano.
        // Ex: pausar vídeo, desligar sensores, salvar dados temporários.
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop chamado");
        // Ideal para: liberar recursos que não são mais visíveis.
        // Ex: encerrar conexões de rede, fechar banco de dados.
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart chamado");
        // Ideal para: reconfigurar o que foi liberado no onStop, antes de voltar ao onStart.
        // Ex: recarregar dados ou atualizar interface.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy chamado");
        // Ideal para: liberar todos os recursos antes da Activity ser destruída.
        // Ex: fechar arquivos, desconectar serviços, limpar memória.
    }
}
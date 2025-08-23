package com.example.solucaoprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Barras de progresso: uma indeterminada (tipo spinner) e uma determinada (barra com valor)
    private ProgressBar progressIndeterminate, progressDeterminate;

    // Botão que inicia o carregamento
    private Button startButton;

    // Handler permite agendar e executar tarefas em uma thread específica
    private Handler handler = new Handler();

    // Valor atual do progresso da barra determinada
    private int progresso = 0;

    // Variável para guardar um Runnable criado dinamicamente (v2)
    private Runnable loadingRunnable;

    // Runnable pré-definido (v1), que simula o carregamento em etapas
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progresso += 10;
            progressDeterminate.setProgress(progresso);

            if (progresso < 100) {
                // Agenda novamente essa mesma tarefa para continuar o progresso após 1 segundo
                handler.postDelayed(this, 1000);
            } else {
                // Quando o progresso chega a 100, oculta a barra indeterminada e exibe mensagem
                // A barra indeterminada ocupa espaço mas não é visível
                progressIndeterminate.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(MainActivity.this, "Concluído!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencia os componentes visuais do layout
        progressIndeterminate = findViewById(R.id.progress_indeterminate);
        progressDeterminate = findViewById(R.id.progress_determinate);
        startButton = findViewById(R.id.start_button);

        // Inicialmente, a barra indeterminada está invisível (não aparece mas ocupa espaço)
        progressIndeterminate.setVisibility(ProgressBar.INVISIBLE);

        // Ao clicar no botão, inicia o carregamento usando a versão 2 (com lambda)
        startButton.setOnClickListener(v -> iniciarCarregamentov2());
    }

    // Versão 1: usa um Runnable definido fora do método
    private void iniciarCarregamentov1() {
        progresso = 0;
        progressDeterminate.setProgress(0);
        progressIndeterminate.setVisibility(ProgressBar.VISIBLE);
        handler.postDelayed(runnable, 1000);
    }

    // Versão 2: cria um novo Runnable com lambda dentro do próprio método
    private void iniciarCarregamentov2() {
        progresso = 0;
        progressDeterminate.setProgress(0);
        progressIndeterminate.setVisibility(ProgressBar.VISIBLE);

        // Define a tarefa que incrementa o progresso e agenda a si mesma
        loadingRunnable = () -> {
            progresso += 10;
            progressDeterminate.setProgress(progresso);

            if (progresso < 100) {
                handler.postDelayed(loadingRunnable, 1000);
            } else {
                // A barra ocupa espaço mas não é visível
                progressIndeterminate.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(MainActivity.this, "Concluído!", Toast.LENGTH_SHORT).show();
            }
        };

        // Inicia a execução da tarefa após 1 segundo
        handler.postDelayed(loadingRunnable, 1000);
    }
}

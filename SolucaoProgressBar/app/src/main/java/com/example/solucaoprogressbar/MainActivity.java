package com.example.solucaoprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressIndeterminate, progressDeterminate;
    private Button startButton;

    // Enviar e processar mensagens ou tarefas (Runnables)
    // em uma Thread específica, geralmente a thread principal (UI thread).
    private Handler handler = new Handler();
    private int progresso = 0;

    // Define uma tarefa a ser executada em paralelo ou agendada para o futuro.
    private Runnable loadingRunnable;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progresso += 10;
            progressDeterminate.setProgress(progresso);

            if (progresso < 100) {
                handler.postDelayed(this, 1000); // chama novamente após 300ms
            } else {
                progressIndeterminate.setVisibility(ProgressBar.GONE);
                Toast.makeText(MainActivity.this, "Concluído!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressIndeterminate = findViewById(R.id.progress_indeterminate);
        progressDeterminate = findViewById(R.id.progress_determinate);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(v -> iniciarCarregamentov2());
    }

    private void iniciarCarregamentov1() {
        progresso = 0;
        progressDeterminate.setProgress(0);
        progressIndeterminate.setVisibility(ProgressBar.VISIBLE);
        handler.postDelayed(runnable, 1000);
    }

    private void iniciarCarregamentov2() {
        progresso = 0;
        progressDeterminate.setProgress(0);
        progressIndeterminate.setVisibility(ProgressBar.VISIBLE);

        loadingRunnable = () -> {
            progresso += 10;
            progressDeterminate.setProgress(progresso);

            if (progresso < 100) {
                handler.postDelayed(loadingRunnable, 1000); // chama novamente após 300ms
            } else {
                progressIndeterminate.setVisibility(ProgressBar.GONE);
                Toast.makeText(MainActivity.this, "Concluído!", Toast.LENGTH_SHORT).show();
            }
        };

        handler.postDelayed(loadingRunnable, 1000);
    }
}
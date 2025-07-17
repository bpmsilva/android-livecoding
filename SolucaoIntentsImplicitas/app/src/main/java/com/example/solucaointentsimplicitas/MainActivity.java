package com.example.solucaointentsimplicitas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnAbrirNavegador;
    Button btnAbrirTelefone;
    Button btnLigarPorTelefone;
    Button btnAbrirMapas;
    Button btnEnviarEmail;
    Button btnCompartilharTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializando os botões
        btnAbrirNavegador = findViewById(R.id.abrir_navegador);
        btnAbrirTelefone = findViewById(R.id.abrir_telefone);
        btnLigarPorTelefone = findViewById(R.id.ligacao_por_telefone);
        btnAbrirMapas = findViewById(R.id.abrir_mapas);
        btnEnviarEmail = findViewById(R.id.enviar_email);
        btnCompartilharTexto = findViewById(R.id.compartilhar_texto);

        // Configurando os listeners dos botões
        btnAbrirNavegador.setOnClickListener(v -> abrirNavegador());
        btnAbrirTelefone.setOnClickListener(v -> abrirTelefone());
        btnLigarPorTelefone.setOnClickListener(v -> ligarPorTelefone());
        btnAbrirMapas.setOnClickListener(v -> abrirMapas());
        btnEnviarEmail.setOnClickListener(v -> enviarEmail());
        btnCompartilharTexto.setOnClickListener(v -> compartilharTexto());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void abrirNavegador() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com"));
        startActivity(intent);
    }

    private void abrirTelefone() {
        // Ligar diretamente para um número de telefone
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:123456789"));
        startActivity(intent);
    }

    private void ligarPorTelefone() {
        // Ligar para um número de telefone
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:123456789"));
        startActivity(intent);
    }


    private void abrirMapas() {
        // Abrir o app de Mapas em uma localização
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=IFBA Salvador");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps"); // Garante abrir no Google Maps
        startActivity(mapIntent);
    }
    private void enviarEmail() {
        // Enviar e-mail
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"bernardo@ifba.edu.br" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Assunto do Email");
        intent.putExtra(Intent.EXTRA_TEXT, "Olá, tudo bem?");
        startActivity(intent);
    }

    private void compartilharTexto() {
        // Compartilhar texto com outros apps
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Esse é um texto para compartilhar.");
        startActivity(Intent.createChooser(intent, "Compartilhar via"));
    }
}
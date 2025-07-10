package com.example.advancedtodolist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRemover;
    private EditText editTextAdicionar;
    private Button adicionaTarefa;
    private Button removeTarefa;
    private ListView listaTarefas;
    private ArrayList<String> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the UI components
        editTextAdicionar = findViewById(R.id.editTextAdicionar);
        editTextRemover = findViewById(R.id.editTextRemover);
        adicionaTarefa = findViewById(R.id.adicionaTarefa);
        removeTarefa = findViewById(R.id.removeTarefa);
        listaTarefas = findViewById(R.id.listaTarefas);

        // Initialize the ArrayList and set up the adapter
        tarefas = new ArrayList<>();

        // Add some initial tasks
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);

        // Set the adapter to the ListView
        listaTarefas.setAdapter(adapter);

        // Set up the listener for the "Adicionar" button
        adicionaTarefa.setOnClickListener(v -> {
            String tarefa = editTextAdicionar.getText().toString();
            if (!tarefa.isEmpty()) {
                tarefas.add(tarefa);
                adapter.notifyDataSetChanged();
                editTextAdicionar.setText("");
            }
        });

        // Set up the listener for the "Remover" button
        removeTarefa.setOnClickListener(v -> {
            String tarefa = editTextRemover.getText().toString();
            int index = Integer.parseInt(tarefa) - 1; // Convert to zero-based index
            if (index >= 0 && index < tarefas.size()) {
                tarefas.remove(index);
                adapter.notifyDataSetChanged();
                editTextRemover.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
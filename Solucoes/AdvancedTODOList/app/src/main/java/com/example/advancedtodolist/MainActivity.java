package com.example.advancedtodolist;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.advancedtodolist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<String> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Bind the views using View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the ArrayList and set up the adapter
        tarefas = new ArrayList<>();

        // Add some initial tasks
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);

        // Set the adapter to the ListView
        binding.listaTarefas.setAdapter(adapter);

        // Set up the listener for the "Adicionar" button
        binding.adicionaTarefa.setOnClickListener(v -> {
            String tarefa = binding.editTextAdicionar.getText().toString();
            if (!tarefa.isEmpty()) {
                tarefas.add(tarefa);
                adapter.notifyDataSetChanged();
                binding.editTextAdicionar.setText("");
            }
        });

        // Set up the listener for the "Remover" button
        binding.removeTarefa.setOnClickListener(v -> {
            String tarefa = binding.editTextRemover.getText().toString();
            int index = Integer.parseInt(tarefa) - 1; // Convert to zero-based index
            if (index >= 0 && index < tarefas.size()) {
                tarefas.remove(index);
                adapter.notifyDataSetChanged();
                binding.editTextRemover.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
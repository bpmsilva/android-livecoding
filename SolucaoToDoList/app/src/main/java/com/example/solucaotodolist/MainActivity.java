package com.example.solucaotodolist;

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

        // Initialize UI components
        Button addButton = findViewById(R.id.addButton);
        EditText todoEditText = findViewById(R.id.todoEditText);
        ListView todoListView = findViewById(R.id.todoListView);

        // Lista dinâmica para armazenar as tarefas digitadas.
        ArrayList<String> taskList = new ArrayList<>();

        // Adaptador que exibe cada tarefa como uma linha de texto simples.
        ArrayAdapter<String> taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        // Conecta o adaptador ao ListView para exibir as tarefas.
        todoListView.setAdapter(taskAdapter);

        // Define o que acontece ao clicar no botão: adiciona a tarefa à lista.
        addButton.setOnClickListener(view -> {
            String task = todoEditText.getText().toString().trim();
            if (!task.isEmpty()) {
                taskList.add(task);
                taskAdapter.notifyDataSetChanged();
                todoEditText.setText("");
            }
        });
    }
}
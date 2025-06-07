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

        // Cria uma lista que armazenará as tarefas digitadas pelo usuário.
        // ArrayList é uma lista dinâmica que permite adicionar e remover itens.
        ArrayList<String> taskList = new ArrayList<>();

        // Cria um adaptador que conecta a lista de tarefas (taskList) ao ListView (todoListView).
        // ArrayAdapter é uma implementação simples de Adapter que converte cada item da lista em uma linha do ListView.
        // Estamos usando um layout padrão do Android (simple_list_item_1) para mostrar cada tarefa como uma linha de texto.
        ArrayAdapter<String> taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        // Define o adaptador (taskAdapter) no ListView para que a lista de tarefas seja exibida na interface.
        todoListView.setAdapter(taskAdapter);

        // Set up the button click listener to add tasks
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
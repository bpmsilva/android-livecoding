package com.example.solucaolistviewduasstrings;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Map<String, String>> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find views by their IDs
        ListView booksListView = findViewById(R.id.booksListView);
        EditText authorEditText = findViewById(R.id.authorEditText);
        EditText titleEditText = findViewById(R.id.titleEditText);
        Button addButton = findViewById(R.id.addButton);

        // Add dummy data to the items list
        addDummyData();

        // Create a SimpleAdapter to bind the data to the ListView
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                items,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "author"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        // Set the adapter to the ListView
        booksListView.setAdapter(adapter);

        // Add an OnClickListener to the addButton to add new items
        addButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();

            if (!title.isEmpty() && !author.isEmpty()) {
                Map<String, String> newItem = new HashMap<>();
                newItem.put("title", title);
                newItem.put("author", author);
                items.add(newItem);
                adapter.notifyDataSetChanged();
                titleEditText.setText("");
                authorEditText.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addDummyData() {
        // Create a list of books with title and author
        Map<String, String> item1 = new HashMap<>();
        item1.put("title", "Dom Casmurro");
        item1.put("author", "Machado de Assis");

        Map<String, String> item2 = new HashMap<>();
        item2.put("title", "Memórias Póstumas de Brás Cubas");
        item2.put("author", "Machado de Assis");

        items.add(item1);
        items.add(item2);
    }
}
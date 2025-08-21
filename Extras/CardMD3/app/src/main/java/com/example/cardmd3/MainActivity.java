package com.example.cardmd3;

import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageButton btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.op1) {
                    // ação opção 1
                    Toast.makeText(this, "Opção 1 selecionada", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.op2) {
                    // ação opção 2
                    Toast.makeText(this, "Opção 2 selecionada", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.op3) {
                    // ação opção 3
                    Toast.makeText(this, "Opção 3 selecionada", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }
}
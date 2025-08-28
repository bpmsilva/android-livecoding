package com.example.solucaoanimacaosimples;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button fadeButton, moveButton, rotateButton;
    private ImageView image;
    private boolean isFaded = false;
    private boolean isMoved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fadeButton = findViewById(R.id.fade_button);
        moveButton = findViewById(R.id.move_button);
        rotateButton = findViewById(R.id.rotate_button);
        image = findViewById(R.id.image);

        // Fade in/out usando View.animate()
        fadeButton.setOnClickListener(v -> {
            if (isFaded) {
                fadeButton.animate().alpha(1f).setDuration(500);
            } else {
                fadeButton.animate().alpha(0.2f).setDuration(500);
            }
            isFaded = !isFaded;
        });

        // Mover botão na horizontal usando View.animate()
        moveButton.setOnClickListener(v -> {
            if (isMoved) {
                moveButton.animate().translationX(0f).setDuration(500);
            } else {
                moveButton.animate().translationX(200f).setDuration(500);
            }
            isMoved = !isMoved;
        });

        // Girar imagem com ObjectAnimator
        rotateButton.setOnClickListener(v -> {
                ObjectAnimator anim = ObjectAnimator.ofFloat(image, View.ROTATION, 0f, 360f);
            anim.setDuration(1000);
            anim.start();
        });
    }
}

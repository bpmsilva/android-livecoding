package com.example.solucaoimagepicker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare the Button for the DatePickerDialog
    private Button datePickerButton;
    // Declare the ImageView to display the selected image
    private ImageView selectedImage;

    // Declare the ActivityResultLauncher for picking images
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the ImageView to display the selected image
        selectedImage = findViewById(R.id.main_image_view);

        // Initialize the ActivityResultLauncher for picking images
        // imagePickerLauncher = registerForActivityResult(
        //         new ActivityResultContracts.StartActivityForResult(),
        //         result -> onImagePicked(result) // Handle the result of the image picking.
        //                                         // It could be implemented in another way.
        // );

        // Inicia um launcher que ainda não está conectado a um ImagePicker
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ImagePickerCallback()
        );

        selectedImage.setOnClickListener(v -> {
            // Create an intent to pick an image from the gallery
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);

            // Acima tivemos um intent implítico. Se fossemos fazer um Intent explícito para outra activity, faríamos assim:
            /*
            Intent explicitIntent = new Intent(MainActivity.this, AnotherActivity.class);
            startActivity(explicitIntent);
            */
        });


        /*
         * A parte do código abaixo é responsável por exibir um DatePickerDialog quando o botão é clicado.
         */
        /*
        datePickerButton.setOnClickListener(view -> {
            // Pega a data atual para definir o DatePickerDialog
            // Isto é feito para que o DatePickerDialog abra com a data atual selecionada.
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH); // Obs: o mês começa em 0 (janeiro = 0, dezembro = 11)
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Cria o DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this, // Passa o contexto da Activity
                    // Assim como em onImagePicked, aqui lidamos com o resultado do DatePickerDialog.
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        // Atualiza o texto com a data escolhida
                        String data = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day
            );

            datePickerDialog.show();
        }); */

        // Semelhante a onImagePicked, aqui lidamos com o resultado do DatePickerDialog.
        datePickerButton = findViewById(R.id.date_picker_button);
        datePickerButton.setOnClickListener(view -> {
            // Pega a data atual para definir o DatePickerDialog
            // Isto é feito para que o DatePickerDialog abra com a data atual selecionada.
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH); // Obs: o mês começa em 0 (janeiro = 0, dezembro = 11)
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Cria o DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this, // Passa o contexto da Activity
                    // Assim como em onImagePicked, aqui lidamos com o resultado do DatePickerDialog.
                    new DatePickerCallback(),
                    year, month, day // Passa a data atual para o DatePickerDialog
            );

            datePickerDialog.show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void onImagePicked(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                Uri selectedImageUri = data.getData();
                selectedImage.setImageURI(selectedImageUri);
            }
        }
    }

    private class ImagePickerCallback implements ActivityResultCallback<ActivityResult> {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Uri selectedImageUri = data.getData();
                    selectedImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private class DatePickerCallback implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Handle the date selected by the user
            String data = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
        }
    }

}
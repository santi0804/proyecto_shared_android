package com.example.proyecto_shared;

import android.os.Bundle;import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MiArchivoPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos de la interfaz
        EditText etNombre = findViewById(R.id.etNombre);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        TextView tvResultado = findViewById(R.id.tvResultado);

        // Obtener una instancia de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Leer y mostrar datos guardados al iniciar
        String nombreGuardado = sharedPreferences.getString("nombreUsuario", "No disponible");
        tvResultado.setText("Nombre guardado: " + nombreGuardado);

        // Configurar el botón "Guardar"
        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            if (!nombre.isEmpty()) {
                // Guardar el nombre
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombreUsuario", nombre);
                editor.apply();

                // Actualizar el TextView
                tvResultado.setText("Nombre guardado: " + nombre);
                Toast.makeText(MainActivity.this, "Nombre guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Por favor, ingresa un nombre", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón "Eliminar"
        btnEliminar.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("nombreUsuario"); // Eliminar el dato "nombreUsuario"
            editor.apply();

            tvResultado.setText("Nombre guardado: No disponible");
            Toast.makeText(MainActivity.this, "Nombre eliminado", Toast.LENGTH_SHORT).show();
        });
    }
}

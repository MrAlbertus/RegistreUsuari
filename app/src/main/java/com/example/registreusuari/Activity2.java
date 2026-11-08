package com.example.registreusuari;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recollim les dades que ens envia la MainActivity
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String apellidos = intent.getStringExtra("apellidos");
        int edad = intent.getIntExtra("edad", 0);
        boolean matriculado = intent.getBooleanExtra("matriculado", false);

        TextView tvNombre = findViewById(R.id.tvNombre);
        TextView tvApellidos = findViewById(R.id.tvApellidos);
        TextView tvEdad = findViewById(R.id.tvEdad);
        TextView tvMatriculado = findViewById(R.id.tvMatriculado);
        CheckBox cbPolitica = findViewById(R.id.cbPolitica);
        Button btnSalir = findViewById(R.id.btnSalir);

        // Mostrem les dades als TextView
        tvNombre.setText("Nombre: " + nombre);
        tvApellidos.setText("Apellidos: " + apellidos);
        tvEdad.setText("Edad: " + edad);
        if (matriculado) {
            tvMatriculado.setText("Matriculado: Sí");
        } else {
            tvMatriculado.setText("Matriculado: No");
        }

        // Al fer clic a sortir "Salir" tornem a la MainActivity amb les dades
        btnSalir.setOnClickListener(v -> {
            Intent respuesta = new Intent();
            respuesta.putExtra("acepta", cbPolitica.isChecked());
            setResult(RESULT_OK, respuesta);
            finish();
        });
    }
}
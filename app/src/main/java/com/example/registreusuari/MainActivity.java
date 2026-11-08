package com.example.registreusuari;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etApellidos = findViewById(R.id.etApellidos);
        EditText etEdad = findViewById(R.id.etEdad);
        RadioButton rbSi = findViewById(R.id.rbSi);
        Button btnMostrar = findViewById(R.id.btnMostrar);

        // Al fer clic en "Mostrar Info" obrim l'Activity2 amb les dades guardades
        btnMostrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("apellidos", etApellidos.getText().toString());
            intent.putExtra("edad", Integer.parseInt(etEdad.getText().toString()));
            intent.putExtra("matriculado", rbSi.isChecked());
            startActivityForResult(intent, 1);
        });
    }

    // executa al tornar de la activity2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            boolean acepta = data.getBooleanExtra("acepta", false);
            TextView tvPolitica = findViewById(R.id.tvPolitica);
            if (acepta) {
                tvPolitica.setText("Acepta la política de privacidad");
            } else {
                tvPolitica.setText("No acepta la política de privacidad");
            }
        }
    }
}
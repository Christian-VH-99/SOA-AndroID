package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.autenticacion.Modelo.ModeloVacuna;
import com.example.autenticacion.Presentador.PresentadorCargarVacuna;
import com.example.autenticacion.R;

public class VistaCargarVacuna extends AppCompatActivity implements View.OnClickListener{

    private PresentadorCargarVacuna prestdrCargarVacuna;
    private EditText etFecha, etFirma;
    private RadioButton radioButton1, radioButton2, radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cargar_vacuna);

        etFecha = findViewById(R.id.editTextFecha);
        etFirma = findViewById(R.id.editTextFirma);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        Bundle datosDeSesion = getIntent().getExtras();

        Button btnRegistrar = findViewById(R.id.btnRegistrarVacuna);
        btnRegistrar.setOnClickListener(this);

        prestdrCargarVacuna = new PresentadorCargarVacuna(this, datosDeSesion);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnRegistrarVacuna){

            String Fecha = etFecha.getText().toString().trim();
            String Firma = etFirma.getText().toString().trim();
            String Tipo_de_vacuna = "";

            if(radioButton1.isChecked()){
                Tipo_de_vacuna = "Sputnik V";
            }

            if(radioButton2.isChecked()){
                Tipo_de_vacuna = "Sinopharm";
            }

            if(radioButton3.isChecked()){
                Tipo_de_vacuna = "AstraZeneca";
            }



            ModeloVacuna vacuna = new ModeloVacuna(0,Tipo_de_vacuna, Fecha, Firma, "");

            prestdrCargarVacuna.cargarVacuna(vacuna);

        }
    }
}
package com.example.autenticacion.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autenticacion.Modelo.ModeloVacuna;
import com.example.autenticacion.Presentador.PresentadorCargarVacuna;
import com.example.autenticacion.R;


public class VistaCargarVacuna extends AppCompatActivity implements View.OnClickListener{

    private PresentadorCargarVacuna prestdrCargarVacuna;
    private EditText etFecha, etFirma;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private String seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cargar_vacunas);

        etFecha = findViewById(R.id.editTextFecha);
        etFirma = findViewById(R.id.editTextFirma);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        Bundle datosDeSesion = getIntent().getExtras();

        prestdrCargarVacuna = new PresentadorCargarVacuna(this, datosDeSesion);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnVolver){

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

            ModeloVacuna vacuna = new ModeloVacuna(0,Tipo_de_vacuna, Fecha,0,Firma, "");

            prestdrCargarVacuna.cargarVacuna(vacuna);

        }
    }
}

package com.example.autenticacion.Vista;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autenticacion.Modelo.ModeloVacuna;
import com.example.autenticacion.Presentador.PresentadorConsultarVacunas;

import com.example.autenticacion.R;

import java.util.List;

public class VistaConsultarVacunas extends AppCompatActivity implements View.OnClickListener {

    private PresentadorConsultarVacunas prestdrConsultarVacunas;
    private TextView txtIDVacuna_Fila_1;
    private TextView txtTipo_Fila_1;
    private TextView txtFecha_Fila_1;
    private TextView txtDosis_Fila_1;
    private TextView txtFirma_Fila_1;

    private TextView txtIDVacuna_Fila_2;
    private TextView txtTipo_Fila_2;
    private TextView txtFecha_Fila_2;
    private TextView txtDosis_Fila_2;
    private TextView txtFirma_Fila_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_consultar_vacunas);

        Bundle datosDeSesion =getIntent().getExtras();

        prestdrConsultarVacunas = new PresentadorConsultarVacunas(this, datosDeSesion);

        List<ModeloVacuna> vacunas = prestdrConsultarVacunas.consultarVacunas();

        txtIDVacuna_Fila_1 = findViewById(R.id.txtIDVacuna_Fila_1);
        txtTipo_Fila_1 = findViewById(R.id.txtTipo_Fila_1);
        txtFecha_Fila_1 = findViewById(R.id.txtFecha_Fila_1);
        txtDosis_Fila_1 = findViewById(R.id.txtDosis_Fila_1);
        txtFirma_Fila_1 = findViewById(R.id.txtFirma_Fila_1);

        txtIDVacuna_Fila_1.setText(vacunas.get(1).getId_vacuna());
        txtTipo_Fila_1.setText(vacunas.get(1).getTipo_de_vacuna());
        txtFecha_Fila_1.setText(vacunas.get(1).getFecha_de_vacuna());
        txtDosis_Fila_1.setText(vacunas.get(1).getDosis());
        txtFirma_Fila_1.setText(vacunas.get(1).getFirma());

        txtIDVacuna_Fila_2 = findViewById(R.id.txtIDVacuna_Fila_2);
        txtTipo_Fila_2 = findViewById(R.id.txtTipo_Fila_2);
        txtFecha_Fila_2 = findViewById(R.id.txtFecha_Fila_2);
        txtDosis_Fila_2 = findViewById(R.id.txtDosis_Fila_2);
        txtFirma_Fila_2 = findViewById(R.id.txtFirma_Fila_2);

        txtIDVacuna_Fila_2.setText(vacunas.get(2).getId_vacuna());
        txtTipo_Fila_2.setText(vacunas.get(2).getTipo_de_vacuna());
        txtFecha_Fila_2.setText(vacunas.get(2).getFecha_de_vacuna());
        txtDosis_Fila_2.setText(vacunas.get(2).getDosis());
        txtFirma_Fila_2.setText(vacunas.get(2).getFirma());

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnVolver){

            prestdrConsultarVacunas.volver();

        }
    }
}

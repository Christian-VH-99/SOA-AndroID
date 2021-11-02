package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autenticacion.Modelo.ListAdapter;
import com.example.autenticacion.Modelo.ModeloVacuna;
import com.example.autenticacion.Presentador.PresentadorConsultarVacunas;
import com.example.autenticacion.R;

import java.util.ArrayList;
import java.util.List;

public class VistaConsultarVacunas extends AppCompatActivity implements View.OnClickListener {

    private PresentadorConsultarVacunas prestdrConsultarVacunas;
    private ListView listView;
    ListAdapter adaptador;
    private List<ModeloVacuna> vacunas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_consultar_vacunas);

        Bundle datosDeSesion =getIntent().getExtras();

        prestdrConsultarVacunas = new PresentadorConsultarVacunas(this, datosDeSesion);
        prestdrConsultarVacunas.consultarVacunas();

        vacunas = prestdrConsultarVacunas.getVacunas();

        listView = findViewById(R.id.list_vacunas);

        if(!vacunas.isEmpty()){
            adaptador = new ListAdapter(this,R.layout.item_row,vacunas);
            listView.setAdapter(adaptador);
        }else
            Toast.makeText(this, "No hay vacunas registradas", Toast.LENGTH_SHORT).show();


        Button btnVolver = findViewById(R.id.btnVolverVacunas);
        btnVolver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnVolverVacunas){

            prestdrConsultarVacunas.volver();

        }
    }
}
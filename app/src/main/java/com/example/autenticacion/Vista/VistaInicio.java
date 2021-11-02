package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Presentador.PresentadorInicio;
import com.example.autenticacion.R;

public class VistaInicio extends AppCompatActivity implements View.OnClickListener{

    private PresentadorInicio presntdorInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_inicio);

        Bundle datosDeSesion =getIntent().getExtras();

        presntdorInicio = new PresentadorInicio(this,datosDeSesion);
        presntdorInicio.mostrarNivelBateria();

        Button btnCargar = findViewById(R.id.btnCargarVacuna);
        btnCargar.setOnClickListener(this);

        Button btnConsultar = findViewById(R.id.btnConsultarVacunas);
        btnConsultar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnCargarVacuna){

            presntdorInicio.cargarVacuna();
        }

        if(v.getId()== R.id.btnConsultarVacunas){

            presntdorInicio.consultarVacunas();
        }
    }
}
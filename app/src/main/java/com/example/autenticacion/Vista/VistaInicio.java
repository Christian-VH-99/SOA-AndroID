package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Modelo.Login.ModeloUsuarioLogin;
import com.example.autenticacion.Presentador.PresentadorInicio;
import com.example.autenticacion.R;

public class VistaInicio extends AppCompatActivity implements View.OnClickListener{

    private PresentadorInicio presntdorInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_inicio);

        Bundle datosDeSeSion =getIntent().getExtras();

        presntdorInicio = new PresentadorInicio(this,datosDeSeSion);
        presntdorInicio.mostrarNivelBateria();

    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnCargarVacuna){

            presntdorInicio.cargarVacuna();
        }

        if(v.getId()== R.id.btnConsultarVacunas){

            presntdorInicio.cargarVacuna();
        }
    }

}
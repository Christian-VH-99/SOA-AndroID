package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Presentador.PresentadorInicio;
import com.example.autenticacion.R;

public class VistaInicio extends AppCompatActivity {

    private PresentadorInicio presntdorInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_inicio);

        Bundle tokensEnviados =getIntent().getExtras();

        presntdorInicio = new PresentadorInicio(this,tokensEnviados);
        presntdorInicio.mostrarNivelBateria();



    }
}
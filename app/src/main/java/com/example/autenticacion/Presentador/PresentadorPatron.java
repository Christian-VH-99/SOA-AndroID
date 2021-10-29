package com.example.autenticacion.Presentador;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.autenticacion.MainActivity;
import com.example.autenticacion.Vista.VistaInicio;
import com.example.autenticacion.Vista.VistaLogin;
import com.example.autenticacion.Vista.VistaRegistro;

public class PresentadorPatron {

    private Context contexto;
    private String patron;

    public PresentadorPatron (Context contexto, String patron){
        this.contexto = contexto;
        this.patron = patron;
    }

    public void validarPatron (String patornIngresado){
        if(patron.equals(patornIngresado)){
            Toast.makeText(contexto, "Patron correctro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(contexto, VistaLogin.class);
            contexto.startActivity(intent);
        }else{
            Toast.makeText(contexto, "Patron incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}

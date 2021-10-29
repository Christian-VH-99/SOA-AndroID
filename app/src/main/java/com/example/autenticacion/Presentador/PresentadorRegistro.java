package com.example.autenticacion.Presentador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.widget.Toast;

import com.example.autenticacion.API.ClienteApi;
import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Modelo.Registro.ModeloRespuestaRegistrar;
import com.example.autenticacion.Vista.VistaInicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentadorRegistro {

    private Context contexto;
    private ClienteApi clienteApi;

    public PresentadorRegistro(Context contexto) {
        this.contexto = contexto;
    }

    public void registrarUsuario(ModeloUsuario usuario) {
        if(!confirmarConexion()){
            Toast.makeText(contexto, "Error en la conexion", Toast.LENGTH_SHORT).show();
            return;
        }

        clienteApi = ClienteApi.getInstance();
        String mensaje = usuario.datosCorrectos();

        //Toast.makeText(contexto, "toy", Toast.LENGTH_SHORT).show();

        if(mensaje.equals("Datos Correctos")){

            clienteApi.Registrar(usuario, new Callback<ModeloRespuestaRegistrar>() {

                @Override
                public void onResponse(Call<ModeloRespuestaRegistrar> call, Response<ModeloRespuestaRegistrar> response) {

                    if(response.isSuccessful()){
                       Toast.makeText(contexto, "Usuario registrado - " + response.body().getToken(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(contexto, VistaInicio.class);
                        contexto.startActivity(intent);
                    }else
                        Toast.makeText(contexto, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ModeloRespuestaRegistrar> call, Throwable t) {
                    Toast.makeText(contexto, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                }
            });
        }else
            Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
        
    }

    private boolean confirmarConexion() {
        ConnectivityManager cm =
                (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}

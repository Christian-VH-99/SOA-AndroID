package com.example.autenticacion.Presentador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.autenticacion.API.ClienteApi;
import com.example.autenticacion.Modelo.Evento.ModeloEvento;
import com.example.autenticacion.Modelo.Evento.ModeloRespuestaEvento;
import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Modelo.Login.ModeloUsuarioLogin;
import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Vista.VistaInicio;
import com.example.autenticacion.Vista.VistaLogin;
import com.example.autenticacion.Vista.VistaRegistro;
import com.google.type.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentadorLogin {

    private Context contexto;
    private ClienteApi clienteApi;

    public PresentadorLogin(Context contexto) {
        this.contexto = contexto;
    }

    public void login(ModeloUsuarioLogin usuario){

        if(!confirmarConexion()){
            Toast.makeText(contexto, "Error en la conexion", Toast.LENGTH_SHORT).show();
            return;
        }

        clienteApi = ClienteApi.getInstance();
        String mensaje = usuario.datosLoginCorrectos();

        //Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();

        if(mensaje.equals("Datos Correctos")){

            clienteApi.Login(usuario, new Callback<ModeloRespuestaLogin>() {

                @Override
                public void onResponse(Call<ModeloRespuestaLogin> call, Response<ModeloRespuestaLogin> response) {

                    if(response.isSuccessful()){

                        Toast.makeText(contexto, "Sesión Iniciada " + response.body().getToken(), Toast.LENGTH_SHORT).show();

                        registrarLogin(response.body().getToken());

                        Intent intent = new Intent(contexto, VistaInicio.class);
                        contexto.startActivity(intent);
                    }else{
                        Toast.makeText(contexto, "No se pudo iniciar sesión - los datos son incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ModeloRespuestaLogin> call, Throwable t) {
                    Toast.makeText(contexto, "El usuario no existe", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private boolean confirmarConexion() {
        ConnectivityManager cm =
                (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    public void registrarse() {
        Intent intent = new Intent(contexto, VistaRegistro.class);
        contexto.startActivity(intent);
    }

    public  void registrarLogin(String token){

        ModeloEvento evento = new ModeloEvento("TEST","Login", "Se inicio sesión");
        clienteApi.RegistrarEvento(token, evento, new Callback<ModeloRespuestaEvento>() {
            @Override
            public void onResponse(Call<ModeloRespuestaEvento> call, Response<ModeloRespuestaEvento> response) {
                Toast.makeText(contexto, "Usuario logeado registrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModeloRespuestaEvento> call, Throwable t) {
                Toast.makeText(contexto, "No se pudo registrar el evento login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.autenticacion.API;

import com.example.autenticacion.Modelo.Evento.ModeloEvento;
import com.example.autenticacion.Modelo.Evento.ModeloRespuestaEvento;
import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Modelo.Login.ModeloUsuarioLogin;
import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Modelo.Registro.ModeloRespuestaRegistrar;
import com.example.autenticacion.Modelo.Token.ModeloRespuestaToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteApi {

    private static InterfazApi servicio;
    private static ClienteApi clienteApi;
    private static Retrofit retrofit = null;

    private ClienteApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://so-unlam.net.ar/api/api/")
                .build();
        servicio = retrofit.create(InterfazApi.class);
    }

    public static ClienteApi getInstance() {
        if (clienteApi == null) {
            clienteApi = new ClienteApi();
        }
        return clienteApi;
    }

    public void Registrar(ModeloUsuario usuario, Callback<ModeloRespuestaRegistrar> callback)
    {
        Call<ModeloRespuestaRegistrar> usuarioCall = servicio.registrar(usuario);
        usuarioCall.enqueue(callback);
    }

    public void Login(ModeloUsuarioLogin usuario, Callback<ModeloRespuestaLogin> callback)
    {
        Call<ModeloRespuestaLogin> usuarioCall = servicio.login(usuario);
        usuarioCall.enqueue(callback);
    }

    public void RegistrarEvento(String token, ModeloEvento event, Callback<ModeloRespuestaEvento> callback)
    {
        Call<ModeloRespuestaEvento> usuarioCall = servicio.registrarEvento("Bearer "+token, event);
        usuarioCall.enqueue(callback);
    }

    public void Refresh(String token_refresh, Callback<ModeloRespuestaToken> callback)
    {
        Call<ModeloRespuestaToken> usuarioCall = servicio.refresh("Bearer "+token_refresh);
        usuarioCall.enqueue(callback);
    }

}

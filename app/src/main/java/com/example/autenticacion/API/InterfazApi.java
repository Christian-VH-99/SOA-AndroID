package com.example.autenticacion.API;

import com.example.autenticacion.Modelo.Evento.ModeloEvento;
import com.example.autenticacion.Modelo.Evento.ModeloRespuestaEvento;
import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Modelo.Login.ModeloUsuarioLogin;
import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Modelo.Registro.ModeloRespuestaRegistrar;
import com.example.autenticacion.Modelo.Token.ModeloRespuestaToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfazApi {

    @Headers({
            "content-type: application/json"
    })

    @POST("register")
    Call<ModeloRespuestaRegistrar> registrar(@Body ModeloUsuario usuario);

    @POST("login")
    Call<ModeloRespuestaLogin> login(@Body ModeloUsuarioLogin usuario);

    //@POST("refresh")
    //Call<ModeloRespuestaToken> refresh(@Body ModeloUsuario usuario);

    @POST("event")
    Call<ModeloRespuestaEvento> registrarEvento(@Header("Authorization") String token, @Body ModeloEvento event);

}

package com.example.autenticacion.Presentador;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.aware.PublishConfig;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.autenticacion.API.ClienteApi;
import com.example.autenticacion.Modelo.Token.ModeloRespuestaToken;
import com.example.autenticacion.Modelo.Token.ModeloTokens;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentadorInicio {

    private Context contexto;
    private ModeloTokens tokens;
    private Handler handler = new Handler();
    private final int TIEMPO = 20000;
    private ClienteApi clienteApi;

    public PresentadorInicio(Context contexto, Bundle tokensEnviados)
    {
        this.contexto = contexto;
        if(tokensEnviados!=null){
            this.tokens = (ModeloTokens) tokensEnviados.getSerializable("tokens");
        }
        clienteApi = ClienteApi.getInstance();
        actualizarToken();

    }

    public void mostrarNivelBateria(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent estadoBateria = contexto.registerReceiver(null, ifilter);

        int nivel = estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int escala = estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float bateria = nivel * 100 / (float)escala;

        Toast.makeText(contexto, "Bateria al " + bateria + "%", Toast.LENGTH_SHORT).show();

    }

    public void actualizarToken () {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,TIEMPO);
                obtenerNuevoToken();
            }
        },TIEMPO);
    }

    public void obtenerNuevoToken()
    {
        /*
        if(!confirmarConexion()){
            Toast.makeText(contexto, "Error en la conexion", Toast.LENGTH_SHORT).show();
            return;
        }

        clienteApi.Refresh(tokens.getToken_Refresh(), new Callback<ModeloRespuestaToken>() {
            @Override
            public void onResponse(Call<ModeloRespuestaToken> call, Response<ModeloRespuestaToken> response) {
                if(response.isSuccessful()){
                    tokens.setToken(response.body().getToken());
                    tokens.setToken_Refresh(response.body().getToken_refresh());
                    Toast.makeText(contexto, tokens.getToken(), Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(contexto, "No se pudo actualizar el token", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ModeloRespuestaToken> call, Throwable t) {
                Toast.makeText(contexto, "Error al actualizar token", Toast.LENGTH_SHORT).show();
            }
        });*/
        Toast.makeText(contexto, "tamos", Toast.LENGTH_SHORT).show();
    }

    private boolean confirmarConexion() {
        ConnectivityManager cm =
                (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }
}

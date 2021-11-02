package com.example.autenticacion.Presentador;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorEvent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.autenticacion.API.ClienteApi;
import com.example.autenticacion.Modelo.Evento.ModeloEvento;
import com.example.autenticacion.Modelo.Evento.ModeloRespuestaEvento;
import com.example.autenticacion.Modelo.Token.ModeloDatosSesion;
import com.example.autenticacion.Modelo.Token.ModeloRespuestaToken;
import com.example.autenticacion.Vista.VistaCargarVacuna;
import com.example.autenticacion.Vista.VistaConsultarVacunas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentadorInicio {

    private Context contexto;
    private ModeloDatosSesion datosSesion;
    private Handler handler = new Handler();
    private final int TIEMPO = 870000;
    private ClienteApi clienteApi;
    private String email;

    public ModeloDatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(ModeloDatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

    public PresentadorInicio(Context contexto, Bundle datosDeSesion)
    {
        this.contexto = contexto;
        if(datosDeSesion!=null){
            this.datosSesion = (ModeloDatosSesion) datosDeSesion.getSerializable("tokens");
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

        if(!confirmarConexion()){
            Toast.makeText(contexto, "Error en la conexion", Toast.LENGTH_SHORT).show();
            return;
        }

        clienteApi.Refresh(datosSesion.getToken_Refresh(), new Callback<ModeloRespuestaToken>() {
            @Override
            public void onResponse(Call<ModeloRespuestaToken> call, Response<ModeloRespuestaToken> response) {
                if(response.isSuccessful()){
                    datosSesion.setToken(response.body().getToken());
                    datosSesion.setToken_Refresh(response.body().getToken_refresh());
                    //Toast.makeText(contexto, datosSesion.getToken(), Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(contexto, "No se pudo actualizar el token", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ModeloRespuestaToken> call, Throwable t) {
                Toast.makeText(contexto, "Error al actualizar token", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean confirmarConexion() {
        ConnectivityManager cm =
                (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    public void cargarVacuna(){
        Intent intent = new Intent(contexto, VistaCargarVacuna.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("tokens", datosSesion);
        intent.putExtras(bundle);
        contexto.startActivity(intent);
    }

    public void consultarVacunas(){
        Intent intent = new Intent(contexto, VistaConsultarVacunas.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("tokens", datosSesion);
        intent.putExtras(bundle);
        contexto.startActivity(intent);
    }

    public void avisoTemp(float value) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
        alerta.setMessage("la temprea tura es de " + value +"°C puede ser riesgoso quedarse en este lugar");
        alerta.setTitle("Alerta: Temperatura alta");
        AlertDialog dialog = alerta.create();
        dialog.show();
    }

    public void registrarShake() {

        ModeloEvento evento = new ModeloEvento("TEST", "Shake",
                "Se realizo un shake para poder visualizar las vacunas");
        clienteApi.RegistrarEvento(datosSesion.getToken(), evento, new Callback<ModeloRespuestaEvento>() {
            @Override
            public void onResponse(Call<ModeloRespuestaEvento> call, Response<ModeloRespuestaEvento> response) {
                if(response.isSuccessful())
                    Toast.makeText(contexto, "Se registro un shake", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(contexto, "No se pudo registrar el shake", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModeloRespuestaEvento> call, Throwable t) {
                Toast.makeText(contexto, "Falla al registrar un shake", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void registrarTempAlta() {

        ModeloEvento evento = new ModeloEvento("TEST", "Temperatura",
                "Se detecto una temperatura mayor a los 35°C");
        clienteApi.RegistrarEvento(datosSesion.getToken(), evento, new Callback<ModeloRespuestaEvento>() {
            @Override
            public void onResponse(Call<ModeloRespuestaEvento> call, Response<ModeloRespuestaEvento> response) {
                if(response.isSuccessful())
                    Toast.makeText(contexto, "Se registro una temperatura mayor a 35°C", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(contexto, "No se pudo registrar el evento Temperatura", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModeloRespuestaEvento> call, Throwable t) {
                Toast.makeText(contexto, "Falla al registrar un evento Temperatura", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

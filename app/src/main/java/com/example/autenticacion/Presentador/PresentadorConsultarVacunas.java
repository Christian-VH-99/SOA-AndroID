package com.example.autenticacion.Presentador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.autenticacion.API.ClienteApi;
import com.example.autenticacion.BaseDeDatos.AdminSQLiteOpenHelper;
import com.example.autenticacion.Modelo.ModeloVacuna;
import com.example.autenticacion.Modelo.Token.ModeloDatosSesion;
import com.example.autenticacion.Modelo.Token.ModeloRespuestaToken;
import com.example.autenticacion.Vista.VistaInicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentadorConsultarVacunas {


    private Context contexto;
    private ModeloDatosSesion datosSesion;
    private Handler handler = new Handler();
    private final int TIEMPO = 870000;
    private ClienteApi clienteApi;

    private List<ModeloVacuna> vacunas;

    public PresentadorConsultarVacunas(Context contexto, Bundle datosDeSesion) {
        this.contexto = contexto;
        if (datosDeSesion != null) {
            this.datosSesion = (ModeloDatosSesion) datosDeSesion.getSerializable("tokens");

        }
        clienteApi = ClienteApi.getInstance();
        actualizarToken();



    }

    private void actualizarToken() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, TIEMPO);
                obtenerNuevoToken();
            }
        }, TIEMPO);
    }

    public void obtenerNuevoToken() {

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
                    //Toast.makeText(contexto, tdatosSesionokens.getToken(), Toast.LENGTH_SHORT).show();

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
                (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    public void consultarVacunas() {
        AdminSQLiteOpenHelper BaseDeDatos = AdminSQLiteOpenHelper.getInstance(contexto);
        this.vacunas = BaseDeDatos.consultarVacunas(this.datosSesion.getEmail());
    }

    public void volver() {

        Intent intent = new Intent(contexto, VistaInicio.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("tokens", datosSesion);
        intent.putExtras(bundle);
        contexto.startActivity(intent);
    }

    public List<ModeloVacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<ModeloVacuna> vacunas) {
        this.vacunas = vacunas;
    }
}

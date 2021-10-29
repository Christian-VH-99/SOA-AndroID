package com.example.autenticacion.Presentador;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class PresentadorInicio {

    private Context contexto;
    Timer timer = new Timer();
    final Handler handler = new Handler();

    public PresentadorInicio(Context contexto) {
        this.contexto = contexto;
    }

    public void mostrarNivelBateria(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent estadoBateria = contexto.registerReceiver(null, ifilter);

        int nivel = estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int escala = estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float bateria = nivel * 100 / (float)escala;

        Toast.makeText(contexto, "Bateria al " + bateria + "%", Toast.LENGTH_SHORT).show();

    }

    public void actualizarToken (){
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                AsyncTask obtenerToken = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        return null;
                    }
                };
                obtenerToken.execute();
            }
        };
        timer.schedule(tarea,0,84000);
    }
}

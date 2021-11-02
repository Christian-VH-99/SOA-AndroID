package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autenticacion.Modelo.Login.ModeloRespuestaLogin;
import com.example.autenticacion.Presentador.PresentadorInicio;
import com.example.autenticacion.R;

public class VistaInicio extends AppCompatActivity implements View.OnClickListener, SensorEventListener{

    private PresentadorInicio presntdorInicio;
    private SensorManager sensorManager;
    //private Sensor acelerometro;


    int whip = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_inicio);

        Bundle datosDeSesion =getIntent().getExtras();

        presntdorInicio = new PresentadorInicio(this,datosDeSesion);
        presntdorInicio.mostrarNivelBateria();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        TextView tvUsuario = findViewById(R.id.textViewUusario);
        tvUsuario.setText(presntdorInicio.getDatosSesion().getEmail());


        Button btnCargar = findViewById(R.id.btnCargarVacuna);
        btnCargar.setOnClickListener(this);

        Button btnConsultar = findViewById(R.id.btnConsultarVacunas);
        btnConsultar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnCargarVacuna){

            presntdorInicio.cargarVacuna();
        }

        if(v.getId()== R.id.btnConsultarVacunas){

            presntdorInicio.consultarVacunas();
        }
    }

    protected void inicializarSensores(){
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void pararSensores(){
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        inicializarSensores();
    }

    @Override
    protected void onRestart() {
        inicializarSensores();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        pararSensores();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        pararSensores();
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //como los sensores pueden lanzar un hilo que pasa por aca me sincronizo
        synchronized (this){
            if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
                if((event.values[0]>25) || (event.values[1]>25) || (event.values[2]>25) ){
                    presntdorInicio.registrarShake();
                    presntdorInicio.consultarVacunas();
                }
            }else if(event.sensor.getType()== Sensor.TYPE_AMBIENT_TEMPERATURE){
                if(event.values[0] > 35){
                    presntdorInicio.registrarTempAlta();
                    presntdorInicio.avisoTemp(event.values[0]);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
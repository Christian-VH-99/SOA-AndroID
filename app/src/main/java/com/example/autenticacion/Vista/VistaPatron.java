package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.autenticacion.Presentador.PresentadorPatron;
import com.example.autenticacion.R;

import java.util.List;

public class VistaPatron extends AppCompatActivity {

    private PresentadorPatron presentadorPatron;
    private String patronIngresado;
    private String patron = "01258";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_patron);

        presentadorPatron = new PresentadorPatron(this,patron);

        PatternLockView patronBloqueo = (PatternLockView) findViewById(R.id.patron);
        patronBloqueo.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                patronIngresado = PatternLockUtils.patternToString(patronBloqueo, pattern);
                //Toast.makeText(VistaPatron.this, patronIngresado, Toast.LENGTH_SHORT).show();
                presentadorPatron.validarPatron(patronIngresado);

            }

            @Override
            public void onCleared() {

            }
        });

    }
}
package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.autenticacion.Modelo.Login.ModeloUsuarioLogin;
import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Presentador.PresentadorLogin;
import com.example.autenticacion.R;

public class VistaLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;
    private PresentadorLogin presentadorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_login);

        presentadorLogin = new PresentadorLogin(this);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        Button btnRegistrarse = findViewById(R.id.btnRegistrarLogin);
        btnRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnLogin){

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            ModeloUsuarioLogin modeloUsuario = new ModeloUsuarioLogin(email, password);

            presentadorLogin.login(modeloUsuario);

        }else if(v.getId()==R.id.btnRegistrarLogin)
            presentadorLogin.registrarse();

    }
}
package com.example.autenticacion.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Presentador.PresentadorRegistro;
import com.example.autenticacion.R;
//import com.google.firebase.inappmessaging.model.Button;

public class VistaRegistro extends AppCompatActivity implements View.OnClickListener {

    private EditText etNombre, etApellido, etDni, etEmail, etPassword, etComision, etGrupo;
    private PresentadorRegistro presentadorRegistro;
    private ModeloUsuario modeloUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_registro);

        presentadorRegistro = new PresentadorRegistro(this);

        etNombre = findViewById(R.id.editTextName);
        etApellido = findViewById(R.id.editTextLastname);
        etDni = findViewById(R.id.editTextDni);
        etEmail = findViewById(R.id.editTextTextEmail);
        etPassword = findViewById(R.id.editTextTextPassword);
        etComision = findViewById(R.id.editTextTextComision);
        etGrupo = findViewById(R.id.editTextGrupo);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnRegistrar){

            String name = etNombre.getText().toString().trim();
            String lastname = etApellido.getText().toString().trim();
            String dni = etDni.getText().toString().trim();
            int dni_int = 0;

            if( !dni.isEmpty() ) {
                dni_int = Integer.parseInt(dni);
            }

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String commission = etComision.getText().toString().trim();
            int commission_int = 0;

            if ( !commission.isEmpty() ) {
                commission_int = Integer.parseInt(commission);
            }

            String group = etGrupo.getText().toString().trim();
            int grupo_int = 0;

            if ( ! group.isEmpty() ) {
                grupo_int = Integer.parseInt(group);
            }

            modeloUsuario = new ModeloUsuario("PROD",name, lastname, email,
                    password, dni_int, commission_int, grupo_int);


            presentadorRegistro.registrarUsuario(modeloUsuario);

        }
    }
}
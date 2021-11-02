package com.example.autenticacion;

import android.content.Context;
import android.os.AsyncTask;

import com.example.autenticacion.BaseDeDatos.AdminSQLiteOpenHelper;
import com.example.autenticacion.Modelo.ModeloVacuna;
import com.example.autenticacion.Modelo.Token.ModeloDatosSesion;

import java.util.List;

public class TareaBackground  extends AsyncTask <String, Integer,  String> {

    private Context contexto;
    private List<ModeloVacuna> vacunas;
    private ModeloDatosSesion datosSesion;

    public TareaBackground(Context contexto, ModeloDatosSesion datosSesion) {
        this.contexto = contexto;
        this.datosSesion = datosSesion;
    }

    @Override
    protected String doInBackground(String... strings) {
        AdminSQLiteOpenHelper BaseDeDatos = AdminSQLiteOpenHelper.getInstance(contexto);
        vacunas = BaseDeDatos.consultarVacunas(this.datosSesion.getEmail());
        return "oks";
    }

    public List<ModeloVacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<ModeloVacuna> vacunas) {
        this.vacunas = vacunas;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}

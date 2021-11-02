package com.example.autenticacion.Modelo;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.autenticacion.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ModeloVacuna> {

    private List<ModeloVacuna> vacunas;
    private Context contexto;
    private int resourseLayout;



    public ListAdapter(@NonNull Context context, int resource, List<ModeloVacuna> objects) {
        super(context, resource, objects);
        this.vacunas = objects;
        this.contexto = context;
        this.resourseLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if(vista == null){
            vista = LayoutInflater.from(contexto).inflate(resourseLayout,null);
        }

        ModeloVacuna vacuna = vacunas.get(position);

        TextView nombreVacuna = vista.findViewById(R.id.textViewVacuna);
        nombreVacuna.setText(vacuna.getTipo_de_vacuna());
        TextView fecha = vista.findViewById(R.id.textViewFecha);
        fecha.setText(vacuna.getFecha_de_vacuna());
        TextView nombreVacunador = vista.findViewById(R.id.textViewVacunador);
        nombreVacunador.setText(vacuna.getFirma());



        return vista;
    }
}

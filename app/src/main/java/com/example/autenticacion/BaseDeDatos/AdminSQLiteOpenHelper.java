package com.example.autenticacion.BaseDeDatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.autenticacion.Modelo.ModeloUsuario;
import com.example.autenticacion.Modelo.ModeloVacuna;

import java.util.ArrayList;
import java.util.List;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //Macros
    private static final int REGISTRO_YA_EXISTE = 0;
    private static final int ERROR_DE_OPERACION = 1;
    private static final int OPERACION_REALIZADA = 2;


    //Info de la Base
    private static AdminSQLiteOpenHelper sInstance;
    private static final String DATABASE_NAME = "BaseAppCovid";
    private static final int DATABASE_VERSION = 1;

    // Nombre de tablas
    private static final String TABLA_USUARIOS = "Usuarios";
    private static final String TABLA_VACUNAS = "Vacunas";

    // Campos Tabla Usuarios
    private static final String PRIMARY_KEY_MAIL = "mail";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDO = "apellido";
    private static final String DNI = "dni";
    private static final String NRO_DE_CIUDADANO = "numero_de_ciudadano";

    // Campos Tabla Vacunas
    private static final String PRIMARY_KEY_ID_VACUNA = "id_vacuna";
    private static final String TIPO_DE_VACUNA = "tipo_de_vacuna";
    private static final String FECHA_DE_VACUNA = "fecha_de_vacuna";
    private static final String DOSIS = "dosis";
    private static final String FIRMA = "firma";
    private static final String FK_KEY_MAIL = "mail";


    private AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos){

        String CREAR_TABLA_USUARIOS = "CREATE TABLE " + TABLA_USUARIOS +
                "(" +
                PRIMARY_KEY_MAIL + " TEXT PRIMARY KEY," + // Define la primary key
                NOMBRE + " TEXT," +
                APELLIDO + " TEXT," +
                DNI + " INTEGER," +
                NRO_DE_CIUDADANO + " TEXT" +
                ")";

        String CREAR_TABLA_VACUNAS = "CREATE TABLE " + TABLA_VACUNAS +
                "(" +
                PRIMARY_KEY_ID_VACUNA + " INTEGER PRIMARY KEY," +
                TIPO_DE_VACUNA + " TEXT," +
                FECHA_DE_VACUNA + " TEXT," +
                DOSIS + " INTEGER," +
                FIRMA + " TEXT," +
                FK_KEY_MAIL + " TEXT " +
                ")";

        BaseDeDatos.execSQL(CREAR_TABLA_USUARIOS);
        BaseDeDatos.execSQL(CREAR_TABLA_VACUNAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il){

    }

    @Override
    public void onConfigure(SQLiteDatabase BaseDeDatos) {
        super.onConfigure(BaseDeDatos);
        BaseDeDatos.setForeignKeyConstraintsEnabled(true);
    }

    public static synchronized AdminSQLiteOpenHelper getInstance(Context contexto) {
        if (sInstance == null) {
            sInstance = new AdminSQLiteOpenHelper(contexto.getApplicationContext());
        }
        return sInstance;
    }

    // Insertar un registro Usuario
    public void insertarUsuario(ModeloUsuario usuario) {

        // Crea y abre la base de datos en modo escritura
        SQLiteDatabase BaseDeDatos = getWritableDatabase();

        //Envolvemos la operacion en una transacción. Esto ayuda con el rendimiento y asegura
        //la consistencia de la base de datos.
        BaseDeDatos.beginTransaction();
        try {

            // Verificamos si el usuario existe en la base.
            String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                    PRIMARY_KEY_MAIL, TABLA_USUARIOS, PRIMARY_KEY_MAIL);
            Cursor cursor = BaseDeDatos.rawQuery(usersSelectQuery, new String[]{String.valueOf(usuario.getEmail())});

            if (cursor.moveToFirst()) {

                Log.d("BaseDeDatos", "Elusuario ya esta registrado");
            }

            ContentValues values = new ContentValues();
            values.put(PRIMARY_KEY_MAIL, usuario.getEmail());
            values.put(NOMBRE, usuario.getName());
            values.put(APELLIDO, usuario.getLastname());
            values.put(DNI, usuario.getDni());
            values.put(NRO_DE_CIUDADANO, "");   //Se carga cuando se cargue una Vacuna.

            BaseDeDatos.insertOrThrow(TABLA_USUARIOS, null, values);
            BaseDeDatos.setTransactionSuccessful();
            Log.d("BaseDeDatos", "Se registro el usuario en la base");
        } catch (Exception e) {
            Log.d("BaseDeDatos", "Error al intentar registrar el usuario");
        } finally {
            BaseDeDatos.endTransaction();
        }
    }

    // Insertar un registro Vacuna
    public void insertarVacuna(ModeloVacuna vacuna){

        SQLiteDatabase baseDeDatos = getWritableDatabase();

        baseDeDatos.beginTransaction();
        try {

            ContentValues values = new ContentValues();

            values.put(PRIMARY_KEY_ID_VACUNA, vacuna.getId_vacuna());
            values.put(TIPO_DE_VACUNA, vacuna.getTipo_de_vacuna());
            values.put(FECHA_DE_VACUNA, vacuna.getFecha_de_vacuna());
            values.put(DOSIS, vacuna.getDosis());
            values.put(FIRMA, vacuna.getFirma());
            values.put(FK_KEY_MAIL, vacuna.getMail()); //se debe cargar en el objeto antes de enviarlo

            baseDeDatos.insertOrThrow(TABLA_VACUNAS, null, values);
            baseDeDatos.setTransactionSuccessful();
            Log.d("BaseDeDatos", "Se registro la vacuna en la base");
        } catch (Exception e) {
            Log.d("BaseDeDatos", "Error al intentar registrar la vacuna");
        } finally {
            baseDeDatos.endTransaction();

        }

    }

    // Consultar Vacunas
    @SuppressLint("Range")
    public List<ModeloVacuna> consultarVacunas(String email) {
        List<ModeloVacuna> vacunas = new ArrayList<>();

        //Modificar la consulta con un where para filtar por el mail de usuario.

        // SELECT * FROM Vacunas
        // LEFT OUTER JOIN Usuarios
        // ON Vacunas.FK_KEY_MAIL = Usuarios.PRIMARY_KEY_MAIL
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                        TABLA_VACUNAS,
                        TABLA_USUARIOS,
                        TABLA_VACUNAS, FK_KEY_MAIL,
                        TABLA_USUARIOS, PRIMARY_KEY_MAIL);

        // obtenemos acceso a la bae en modo lectura
        SQLiteDatabase BaseDeDatos = getReadableDatabase();
        Cursor cursor = BaseDeDatos.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    //Creo un objeto vacio para cargar los valores recuperados de la tabla
                    ModeloVacuna vacuna = new ModeloVacuna();
                    vacuna.setDosis(cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY_ID_VACUNA)));
                    vacuna.setTipo_de_vacuna(cursor.getString(cursor.getColumnIndex(TIPO_DE_VACUNA)));
                    vacuna.setFecha_de_vacuna(cursor.getString(cursor.getColumnIndex(FECHA_DE_VACUNA)));
                    vacuna.setDosis(cursor.getInt(cursor.getColumnIndex(DOSIS)));
                    vacuna.setTipo_de_vacuna(cursor.getString(cursor.getColumnIndex(FIRMA)));
                    vacunas.add(vacuna);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("BaseDeDatos", "Error al intentar recuperar las vacunas");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return vacunas;
    }

}

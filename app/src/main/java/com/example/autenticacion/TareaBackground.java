package com.example.autenticacion;

import android.os.AsyncTask;

public class TareaBackground  extends AsyncTask <String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {
       while(true){
           try {
               Thread.sleep(840000);
               // aca deberia pedir el token.
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

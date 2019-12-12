package com.example.alumno.parcial2;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by alumno on 28/11/2019.
 */

public class MiHilo extends Thread {
    String b;
    Uri.Builder params;
    Handler handler;

public MiHilo(Uri.Builder params, Handler handler){

    this.params = params;
    this.handler  = handler;
}


    @Override
    public void run() {
    HttpManager manager = new HttpManager();
        try {
            String respuesta = manager.PasarDatos(this.params);
            JSONObject jso = new JSONObject(respuesta);
            String r = jso.getString("type");
            Message msg = new Message();
            if((r.equals("User")) || (r.equals("Admin"))){

                msg.obj = "Ok";
                handler.sendMessage(msg);
            }
            else{
                msg.obj = "notOk";
                handler.sendMessage(msg);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

}

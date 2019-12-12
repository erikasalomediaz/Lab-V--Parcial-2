package com.example.alumno.parcial2;

import android.net.Uri;
import android.os.Bundle;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alumno on 28/11/2019.
 */

public class HttpManager {

    public String PasarDatos(Uri.Builder post) throws
            IOException {
        HttpURLConnection con = null;
        try{
        String result;
        URL url = new URL("http://192.168.0.22:3000/login");
         con = (HttpURLConnection) url.openConnection();
        con.setReadTimeout(10000 /* milliseconds */);
        con.setConnectTimeout(15000 /* milliseconds */);
        con.setRequestMethod("POST");
        con.setDoInput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = post.build().getEncodedQuery();
        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


        writer.write(body);
        writer.flush();
        writer.close();

        int response = con.getResponseCode();
        if (response == 200) {
            InputStream is = con.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            is.close();
            return new String(baos.toByteArray());


        }
       return null;
    }


    public String getBytesDataByPOST(Uri.Builder postParams) throws
            IOException
    {

        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL("http://192.168.0.22:3000/login");
            // url = new URL("http://www.lslutnfra.com/alumnos/practicas/postEcho.php");
            conn = (HttpURLConnection)
                    url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        String body = postParams.build().getEncodedQuery(); // transforma el uri.builder en un string
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new
                OutputStreamWriter(os,"UTF-8"));
        writer.write(body);
        writer.flush();
        writer.close();
        os.close();
        int response = conn.getResponseCode();
        if(response==200) {
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            is.close();
            return new String(baos.toByteArray());


        }

        else
            throw new IOException();
    }
}
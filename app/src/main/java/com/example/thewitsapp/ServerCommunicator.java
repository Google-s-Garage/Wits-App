package com.example.thewitsapp;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


public abstract class ServerCommunicator extends AsyncTask<String, String, String> {

    private final  String address;
    private final ContentValues params;

    protected  ServerCommunicator(String address, ContentValues params) {
        this.address = address;
        this.params = params;
    }

//    @Override
//    protected abstract void onPreExecute();  //while loading

    @Override
    protected String doInBackground(String... strings) { //while enstablishing connection....

        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(2000);

            if(params.size()>0){
                Uri.Builder builder = new Uri.Builder();

                for(String s : params.keySet()) builder.appendQueryParameter(s,params.getAsString(s));
                String query = builder.build().getEncodedQuery();
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, Charset.forName("UTF-8")));
                assert query != null;
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = br.readLine();
            br.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    @Override
    protected abstract void onPostExecute(String output); //after connection what happens.....
}

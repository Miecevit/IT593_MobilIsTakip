package com.it593.dev.mobilistakip;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MessageHelper {

    private static Message getMessageFromJSONObject(JSONObject jsonObject) {
        Message message = new Message();




        try {
            message.setMesajText(jsonObject.getString("MessageContent"));
        }
        catch (JSONException e1) {
            e1.printStackTrace();
        }


        try {
            message.setGonderici(jsonObject.getString("SenderId"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }





        try {
            message.setZaman(jsonObject.getString("Time"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }



    public static URL getURL_getAllMessages() {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("get_all_messages");

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static List<Message> getAllMessages() {

        List<Message> listItems = new ArrayList<Message>();

        try {
            URL url = getURL_getAllMessages();
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("GetAllMessagesResult");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = (JSONObject)jsonArray.get(i);
                Message message = getMessageFromJSONObject(jObject);
                listItems.add(message);
            }

            reader.close();

        }
        catch(Exception e){

        }

        return listItems;
    }




}

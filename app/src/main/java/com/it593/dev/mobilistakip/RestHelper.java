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

public class RestHelper {

    public static URL getURL_checkUser (String Email, String password) {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("CheckUser")
                    .appendQueryParameter("email", Email)
                    .appendQueryParameter("password", password);

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static User checkUserMethod(String userName, String password) {

        User user = null;

        try {
            URL url = getURL_checkUser(userName, password);
            System.out.println("checkuser!");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            if (line != null && !line.isEmpty() && !line.equals("null")) {
                JSONObject jsonResponse = new JSONObject(line);
                user = getUserFromJSONObject(jsonResponse);
            }

            reader.close();
        }
        catch(Exception e){
            //String msg = e.getMessage();
        }

        return user;
    }

    private static User getUserFromJSONObject(JSONObject jsonObject) {
        User user = new User();




        try {
            user.setEmail(jsonObject.getString("email"));
        }
        catch (JSONException e1) {
            e1.printStackTrace();
        }


        try {
            user.setId(jsonObject.getInt("idUser"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            user.setFirstName(jsonObject.getString("first_name"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user.setFirstName(jsonObject.getString("last_name"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            user.setPassword(jsonObject.getString("password"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user.setJob(jsonObject.getString("job"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static URL getURL_getAllUsers() {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("GetAllUsers");

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static List<User> getAllActiveUsers() {

        List<User> listItems = new ArrayList<User>();

        try {
            URL url = getURL_getAllUsers();
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("GetAllUsers");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = (JSONObject)jsonArray.get(i);
                User user = getUserFromJSONObject(jObject);
                listItems.add(user);
            }

            reader.close();

        }
        catch(Exception e){

        }

        return listItems;
    }

    public static User getUserMethod(int userId) {

        User item = null;

        try {
            URL url = getURL_getUser(userId);
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONObject jsonObject = jsonResponse.getJSONObject("GetUserMethodResult");
            item = getUserFromJSONObject(jsonObject);
            reader.close();

        }
        catch(Exception e){
            //String msg = e.getMessage();
        }

        return item;
    }

    public static URL getURL_getUser (int userId) {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service")
                    .appendPath("GetUserDetail")
                    .appendQueryParameter("idUser", String.valueOf(userId));

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }
}

package com.it593.dev.mobilistakip;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

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



public class TaskHelper {
    // task e göre düzenlenecek.
    private static User getTaskFromJSONObject(JSONObject jsonObject) {
        Task task = new Task();

        try {
            user.setFirstName(jsonObject.getString("first_name"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user.setLastName(jsonObject.getString("last_name"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user.setEmail(jsonObject.getString("email"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user.setId(jsonObject.getInt("idUser"));
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

        try {
            user.setPassword(jsonObject.getString("password"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static List<Task> getAllTasks() {

        List<Task> listItems = new ArrayList<Task>();

        try {
            URL url = getURL_getAllTasks();
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("getAllTasksResult");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = (JSONObject)jsonArray.get(i);
                Task task = getTaskFromJSONObject(jObject);
                listItems.add(task);
            }

            reader.close();

        }
        catch(Exception e){

        }

        return listItems;
    }

    public static URL getURL_getAllTasks() {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("get_all_tasks");

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static Task getTaskDetail(int taskId) {

        Task item = null;

        try {
            URL url = getURL_getTaskDetail(taskId);
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONObject jsonObject = jsonResponse.getJSONObject("getTaskDetailResult");
            item = getTaskFromJSONObject(jsonObject);
            reader.close();

        }
        catch(Exception e){
            //String msg = e.getMessage();
        }

        return item;
    }

    public static URL getURL_getTaskDetail (int taskId) {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("get_task_detail")
                    .appendQueryParameter("idTask", String.valueOf(taskId));

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

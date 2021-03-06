package com.it593.dev.mobilistakip;

import android.icu.text.SimpleDateFormat;
import android.net.Uri;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TaskHelper {

    private static Task getTaskFromJSONObject(JSONObject jsonObject) {
        Task task = new Task();

        try {
            task.setidTask(jsonObject.getInt("idTask"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setName(jsonObject.getString("name"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setShort_description(jsonObject.getString("short_description"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setLong_description(jsonObject.getString("long_description"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }



        try {

            String dateStr = jsonObject.getString("created_date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);


            task.setCreated_date(date);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            String dateStr = jsonObject.getString("modified_date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);


            task.setModified_date(date);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            task.setLocation(jsonObject.getString("location"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            task.setTask_statue(jsonObject.getString("task_statue"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setLocation(jsonObject.getString("location"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            String dateStr = jsonObject.getString("start_date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);


            task.setStart_date(date);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            String dateStr = jsonObject.getString("end_date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);


            task.setEnd_date(date);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            task.setHandled_by(jsonObject.getInt("handled_by"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setCreated_by(jsonObject.getInt("created_by"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setModified_by(jsonObject.getInt("modified_by"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setLink(jsonObject.getString("link"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setTask_type(jsonObject.getString("task_type"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            task.setPhoto(jsonObject.getString("photo"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }








        return null;
    }




    // GetAllTasks
    public static List<Task> getAllTasks() {

        List<Task> listItems = new ArrayList<Task>();

        try {
            URL url = getURL_getAllTasks();
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("GetAllTasksResult");

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

    // GetTaskDetail
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

    public static URL getURL_getTaskDetail(int taskId) {
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

    // GetTaskByStatue
    public static Task getTaskByStatue(int statue) {

        Task item = null;

        try {
            URL url = getURL_getTaskByStatue(statue);
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONObject jsonObject = jsonResponse.getJSONObject("getTaskByStatueResult");
            item = getTaskFromJSONObject(jsonObject);
            reader.close();

        }
        catch(Exception e){
            //String msg = e.getMessage();
        }

        return item;
    }

    public static URL getURL_getTaskByStatue(int statue) {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("get_task_by_statue")
                    .appendQueryParameter("statue", String.valueOf(statue));

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    // GetTaskByType
    public static Task getTaskByType(int type) {

        Task item = null;

        try {
            URL url = getURL_getTaskByType(type);
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONObject jsonObject = jsonResponse.getJSONObject("getTaskByTypeResult");
            item = getTaskFromJSONObject(jsonObject);
            reader.close();

        }
        catch(Exception e){
            //String msg = e.getMessage();
        }

        return item;
    }

    public static URL getURL_getTaskByType(int type) {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("get_task_by_type")
                    .appendQueryParameter("type", String.valueOf(type));

            String urlStr = builder.build().toString();
            URL url = new URL(urlStr);
            return url;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    // Ecevit buraya bir bakabilir misin, User da dönmeli dönmemeli sorunsalı yaşadım
    // GetTaskByTypeAndUser
    public static Task getTaskByTypeAndUser(int type, int userId) {

        Task item = null;

        try {
            URL url = getURL_getTaskByTypeAndUser(type, userId);
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = reader.readLine();

            JSONObject jsonResponse = new JSONObject(line);
            JSONObject jsonObject = jsonResponse.getJSONObject("getTaskByTypeAndUserResult");
            item = getTaskFromJSONObject(jsonObject);
            reader.close();

        }
        catch(Exception e){
            //String msg = e.getMessage();
        }

        return item;
    }

    public static URL getURL_getTaskByTypeAndUser(int type, int userId) {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("it592.idegis.com.tr")
                    .appendPath("IT592Service.svc")
                    .appendPath("get_task_by_type_and_user")
                    .appendQueryParameter("type", String.valueOf(type))
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

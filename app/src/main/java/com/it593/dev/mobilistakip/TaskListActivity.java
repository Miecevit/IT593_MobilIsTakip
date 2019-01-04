package com.it593.dev.mobilistakip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {


    private List<Task> allTasks;
    private TaskListAdapter adapter;
    private ListView listViewTasks;

    private void LoadTasks() {
        adapter = new TaskListAdapter(this, allTasks);
        listViewTasks.setAdapter(adapter);
    }

    public class getTasksTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            allTasks = TaskHelper.getAllTasks();
            if(allTasks != null)
                System.out.println(allTasks);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (allTasks != null) {
                LoadTasks();
            }
            else
                System.out.println("Can't get TaskS!!");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void result) {
            super.onCancelled(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        listViewTasks.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedTaskId = Integer.parseInt(((TextView)view.findViewById(R.id.txtTask_Id)).getText().toString());
                openTaskDetails(selectedTaskId);
            }
        });

        new getTasksTask().execute();
    }

    private void openTaskDetails(int selectedTaskId) {
        Intent intent = new Intent(getApplicationContext(), TaskDetailsActivity.class);
        intent.putExtra("SELECTED_TaskID", selectedTaskId);
        startActivity(intent);
    }

}

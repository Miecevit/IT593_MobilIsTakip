package com.it593.dev.mobilistakip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private int TaskId;
    private Task selectedTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_detail);

        Intent intent = getIntent();
        TaskId = intent.getIntExtra("SELECTED_TaskID", -1);
        new LoadUserDetails().execute();
    }

    class LoadUserDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TaskDetailsActivity.this);
            progressDialog.setMessage("Görev Bilgileri yükleniyor ...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            TextView txtTaskDetail_Name = (TextView)findViewById(R.id.txtTaskDetail_Name);
            TextView txtTaskDetail_LongDesc = (TextView)findViewById(R.id.txtTaskDetail_LongDesc);
            TextView txtTaskDetail_Start = (TextView)findViewById(R.id.txtTaskDetail_Start);
            TextView txtTaskDetail_End = (TextView)findViewById(R.id.txtTaskDetail_End);
            TextView txtTaskDetail_Status = (TextView)findViewById(R.id.txtTaskDetail_Status);
            TextView txtTaskDetail_Type = (TextView)findViewById(R.id.txtTaskDetail_Type);

            txtTaskDetail_Name.setText(selectedTask.getName());
            txtTaskDetail_LongDesc.setText(selectedTask.getLong_description());
            txtTaskDetail_Start.setText(selectedTask.getStart_date().toString());
            txtTaskDetail_End.setText(selectedTask.getEnd_date().toString());
            txtTaskDetail_Status.setText(selectedTask.getTask_statue());
            txtTaskDetail_Type.setText(selectedTask.getTask_type());

        }

        @Override
        protected Void doInBackground(Void... params) {
            selectedTask = TaskHelper.getTaskDetail(TaskId);
            return null;
        }
    }
}

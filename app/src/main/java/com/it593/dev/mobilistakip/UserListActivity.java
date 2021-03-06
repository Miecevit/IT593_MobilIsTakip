package com.it593.dev.mobilistakip;

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

public class UserListActivity extends AppCompatActivity {


    private List<User> allUsers;
    private UserListAdapter adapter;
    private ListView listViewUsers;

    private void LoadUsers() {
        adapter = new UserListAdapter(this, allUsers);
        listViewUsers.setAdapter(adapter);
    }

    public class getUsersTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            allUsers = UserHelper.getAllActiveUsers();
            if(allUsers != null)
            System.out.println(allUsers);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (allUsers != null) {
                LoadUsers();
            }
            else
                System.out.println("Can't get USERS!!");
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
        setContentView(R.layout.activity_user_list);

        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        listViewUsers.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedUserId = Integer.parseInt(((TextView)view.findViewById(R.id.txtUser_Id)).getText().toString());
                openUserDetails(selectedUserId);
            }
        });

        new getUsersTask().execute();
    }

    private void openUserDetails(int selectedUserId) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra("SELECTED_USERID", selectedUserId);
        startActivity(intent);
    }

}

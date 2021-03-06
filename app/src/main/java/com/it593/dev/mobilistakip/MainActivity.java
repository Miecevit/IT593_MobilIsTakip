package com.it593.dev.mobilistakip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button userbtn;
    private Button taskbtn;
    private Button mapbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userbtn = (Button) findViewById(R.id.userBtn);

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);


            }
        });

        taskbtn = (Button) findViewById(R.id.taskBtn);

        taskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                startActivity(intent);


            }
        });

        mapbtn = (Button) findViewById(R.id.mapBtn);

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);


            }
        });





    }
}

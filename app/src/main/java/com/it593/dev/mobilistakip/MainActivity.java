package com.it593.dev.mobilistakip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button userbtn;

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





    }
}

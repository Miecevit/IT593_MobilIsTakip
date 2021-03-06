package com.it593.dev.mobilistakip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText edtUserName, edtPassword;
    private String username;
    private String password;
    User user = new User();
    private List<User> allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






        btnLogin = (Button) findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(this);

        edtPassword = (EditText) findViewById(R.id.passwordTextfield);
        edtUserName = (EditText) findViewById(R.id.usernameTextfield);
    }


    @Override
    public void onClick(View v) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {




               toMain();
               /* System.out.println(edtUserName.getText().toString());
                System.out.println(edtPassword.getText().toString());

                user = UserHelper.checkUserMethod(edtUserName.getText().toString(), edtPassword.getText().toString());

                allUsers = UserHelper.getAllActiveUsers();
                System.out.println(user);
                System.out.println(allUsers);



                if (user != null) {
                    toMain();
                }*/
            }
        });
    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

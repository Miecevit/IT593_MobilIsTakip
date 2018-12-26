package com.it593.dev.mobilistakip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private int userId;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Intent intent = getIntent();
        userId = intent.getIntExtra("SELECTED_USERID", -1);
        new LoadUserDetails().execute();
    }

    class LoadUserDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(UserDetailActivity.this);
            progressDialog.setMessage("Kullanıcı bilgileri yükleniyor ...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            TextView txtUserDetail_FirstName = (TextView)findViewById(R.id.txtUserDetail_Name);
            TextView txtUserDetail_LastName = (TextView)findViewById(R.id.txtUserDetail_LastName);
            TextView txtUserDetail_Eposta = (TextView)findViewById(R.id.txtUserDetail_Eposta);
            TextView txtUserDetail_Auth = (TextView)findViewById(R.id.txtUserDetail_Auth);

            txtUserDetail_FirstName.setText(selectedUser.getFirstName());
            txtUserDetail_LastName.setText(selectedUser.getLastName());
            txtUserDetail_Auth.setText(selectedUser.getAuthType() == User.AUTHTYPE.Yonetici ? "Yönetici" : "Kullanıcı");
            txtUserDetail_Eposta.setText(selectedUser.getEmail());
        }

        @Override
        protected Void doInBackground(Void... params) {
            selectedUser = RestHelper.getUserMethod(userId);
            return null;
        }
    }
}

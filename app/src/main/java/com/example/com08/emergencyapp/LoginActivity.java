package com.example.com08.emergencyapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by com08 (07/11/2016).
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    class Authenticate extends AsyncTask<Void, Void, String>
    {
        String usr, pass;
        Authenticate(String username, String pass)
        {
            this.usr = username;
            this.pass = pass;
        }

        @Override
        protected String doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}

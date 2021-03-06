package com.example.com08.emergencyapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.com08.emergencyapp.customthings.ConstantStuff;
import com.example.com08.emergencyapp.customthings.UploadFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by com08 (07/11/2016).
 */

public class LoginActivity extends AppCompatActivity {

    private EditText edUsername, edPass;
    ProgressDialog progressDialog;
    SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = this.getSharedPreferences("emergency", MODE_APPEND);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPass = (EditText) findViewById(R.id.edPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new Authenticate(edUsername.getText().toString(),
                            edPass.getText().toString()).execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!preferences.getBoolean("isLogin", false)) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
    }

    private void showAlert(String message) {

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(message)
//                .setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.warning))
                .setPositiveButton(
                        android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                .create();

        alertDialog.setCancelable(false);
        alertDialog.show();

        View btnTest = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
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
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.loging_in));
            progressDialog.setIndeterminateDrawable(getDrawable(R.drawable.test_animated));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            try
            {
                return authentication(ConstantStuff.URL_LOGIN);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        private String authentication(String requestURL) {
            String result = "";
            String charset = "UTF-8";

            try {
                UploadFile multipart = new UploadFile(requestURL, charset);

                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");

                multipart.addFormField("USERNAME", usr);
                multipart.addFormField("PASS", pass);

                List<String> response = multipart.finish();

                System.out.println("SERVER REPLIED:");

                for (String line : response) {
                    System.out.println(line);
                    result = line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            if(s.equals("OK"))
            {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogin", true);
                editor.putString("username", usr);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                showAlert(getResources().getString(R.string.alert_wrong_input));
            }
        }
    }
}

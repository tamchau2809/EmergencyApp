package com.example.com08.emergencyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.com08.emergencyapp.customthings.ConstantStuff;
import com.example.com08.emergencyapp.customthings.UploadFile;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    ImageButton btnSubmit;
    ProgressDialog progressDialog;
    CheckBox cbBlcatActiv, cbCmtNotif, cbCmtActiv;
    CheckBox cbEmail, cbSMS, cbTel;
    CheckBox cbBlcat1, cbBlcat2, cbSME;
    LinearLayout lnrSituation, lnrStation, lnrReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        initListener();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SubmitNoti(cbBlcatActiv.getText().toString(),
                        cbCmtNotif.getText().toString(),
                        cbCmtActiv.getText().toString()).execute();
            }
        });

        if(!preferences.getBoolean("isLogin", false))
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void initListener()
    {
        cbBlcatActiv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbCmtActiv.isChecked() || cbCmtNotif.isChecked())
                {
                    cbEmail.setEnabled(true);
                    cbSMS.setEnabled(true);
                    cbTel.setEnabled(true);
                    if(cbEmail.isChecked() || cbSMS.isChecked() || cbTel.isChecked())
                    {
                        cbBlcat1.setEnabled(true);
                        cbBlcat2.setEnabled(true);
                        cbSME.setEnabled(true);
                        if(cbBlcat1.isChecked() || cbBlcat2.isChecked() || cbSME.isChecked())
                        {
                            btnSubmit.setEnabled(true);
                        }
                    }
                }
                else
                {
                    cbEmail.setEnabled(false);
                    cbSMS.setEnabled(false);
                    cbTel.setEnabled(false);
                    cbBlcat1.setEnabled(false);
                    cbBlcat2.setEnabled(false);
                    cbSME.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
            }
        });
        cbCmtNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbBlcatActiv.isChecked() || cbCmtActiv.isChecked())
                {
                    cbEmail.setEnabled(true);
                    cbSMS.setEnabled(true);
                    cbTel.setEnabled(true);
                    if(cbEmail.isChecked() || cbSMS.isChecked() || cbTel.isChecked())
                    {
                        cbBlcat1.setEnabled(true);
                        cbBlcat2.setEnabled(true);
                        cbSME.setEnabled(true);
                        if(cbBlcat1.isChecked() || cbBlcat2.isChecked() || cbSME.isChecked())
                        {
                            btnSubmit.setEnabled(true);
                        }
                    }
                }
                else
                {
                    cbEmail.setEnabled(false);
                    cbSMS.setEnabled(false);
                    cbTel.setEnabled(false);
                    cbBlcat1.setEnabled(false);
                    cbBlcat2.setEnabled(false);
                    cbSME.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
            }
        });
        cbCmtActiv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbBlcatActiv.isChecked() || cbCmtNotif.isChecked())
                {
                    cbEmail.setEnabled(true);
                    cbSMS.setEnabled(true);
                    cbTel.setEnabled(true);
                    if(cbEmail.isChecked() || cbSMS.isChecked() || cbTel.isChecked())
                    {
                        cbBlcat1.setEnabled(true);
                        cbBlcat2.setEnabled(true);
                        cbSME.setEnabled(true);
                        if(cbBlcat1.isChecked() || cbBlcat2.isChecked() || cbSME.isChecked())
                        {
                            btnSubmit.setEnabled(true);
                        }
                    }
                }
                else
                {
                    cbEmail.setEnabled(false);
                    cbSMS.setEnabled(false);
                    cbTel.setEnabled(false);
                    cbBlcat1.setEnabled(false);
                    cbBlcat2.setEnabled(false);
                    cbSME.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
            }
        });

        cbEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbSMS.isChecked() || cbTel.isChecked())
                {
                    cbBlcat1.setEnabled(true);
                    cbBlcat2.setEnabled(true);
                    cbSME.setEnabled(true);
                    if(cbBlcat1.isChecked() || cbBlcat2.isChecked() || cbSME.isChecked())
                    {
                        btnSubmit.setEnabled(true);
                    }
                }
                else
                {
                    cbBlcat1.setEnabled(false);
                    cbBlcat2.setEnabled(false);
                    cbSME.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
            }
        });

        cbSMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbEmail.isChecked() || cbTel.isChecked())
                {
                    cbBlcat1.setEnabled(true);
                    cbBlcat2.setEnabled(true);
                    cbSME.setEnabled(true);
                    if(cbBlcat1.isChecked() || cbBlcat2.isChecked() || cbSME.isChecked())
                    {
                        btnSubmit.setEnabled(true);
                    }
                }
                else
                {
                    cbBlcat1.setEnabled(false);
                    cbBlcat2.setEnabled(false);
                    cbSME.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
            }
        });

        cbTel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbSMS.isChecked() || cbEmail.isChecked())
                {
                    cbBlcat1.setEnabled(true);
                    cbBlcat2.setEnabled(true);
                    cbSME.setEnabled(true);
                    if(cbBlcat1.isChecked() || cbBlcat2.isChecked() || cbSME.isChecked())
                    {
                        btnSubmit.setEnabled(true);
                    }
                }
                else
                {
                    cbBlcat1.setEnabled(false);
                    cbBlcat2.setEnabled(false);
                    cbSME.setEnabled(false);
                    btnSubmit.setEnabled(false);
                }
            }
        });

        cbBlcat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbBlcat2.isChecked() || cbSME.isChecked())
                {
                    btnSubmit.setEnabled(true);
                }
                else
                {
                    btnSubmit.setEnabled(false);
                }
            }
        });

        cbBlcat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbBlcat1.isChecked() || cbSME.isChecked())
                {
                    btnSubmit.setEnabled(true);
                }
                else
                {
                    btnSubmit.setEnabled(false);
                }
            }
        });

        cbSME.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked || cbBlcat2.isChecked() || cbBlcat1.isChecked())
                {
                    btnSubmit.setEnabled(true);
                }
                else
                {
                    btnSubmit.setEnabled(false);
                }
            }
        });
    }

    private void initWidget()
    {
        preferences = this.getSharedPreferences("emergency", MODE_APPEND);
        cbBlcatActiv = (CheckBox)findViewById(R.id.cbBlcatActiv);
        cbCmtNotif = (CheckBox)findViewById(R.id.cbCmtNotif);
        cbCmtActiv = (CheckBox)findViewById(R.id.cbCmtActiv);
        cbEmail = (CheckBox)findViewById(R.id.cbEmail);
        cbSMS = (CheckBox)findViewById(R.id.cbSMS);
        cbTel = (CheckBox)findViewById(R.id.cbTel);
        cbBlcat1 = (CheckBox)findViewById(R.id.cbBlcat1);
        cbBlcat2 = (CheckBox)findViewById(R.id.cbBlcat2);
        cbSME = (CheckBox)findViewById(R.id.cbSME);
        btnSubmit = (ImageButton)findViewById(R.id.btnSubmit);
        lnrSituation = (LinearLayout)findViewById(R.id.lnrSituation);
        lnrStation = (LinearLayout)findViewById(R.id.lnrStation);
        lnrReceiver = (LinearLayout)findViewById(R.id.lnrReceiver);

        cbEmail.setEnabled(false);
        cbSMS.setEnabled(false);
        cbTel.setEnabled(false);
        cbBlcat1.setEnabled(false);
        cbBlcat2.setEnabled(false);
        cbSME.setEnabled(false);
        btnSubmit.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(preferences.getBoolean("isLogin", false)) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
    }

//    @Override
//    protected void onStop() {
//        new Logout().execute();
//        super.onStop();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            new Logout().execute();
        }

        return super.onOptionsItemSelected(item);
    }

    private class SubmitNoti extends AsyncTask<Void, Void, String>
    {
        String value1, value2, value3;

        SubmitNoti(String v1, String v2, String v3)
        {
            this.value1 = v1;
            this.value2 = v2;
            this.value3 = v3;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.plz_wait));
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminateDrawable(getDrawable(R.drawable.test_animated));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String URL_SUBMIT = "http://192.168.1.12/chauvu/emergency/test.php?checkLogin=true";
            try
            {
                return uploadData(URL_SUBMIT);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        private String uploadData(String url)
        {
            String result = "";
            String charset = "UTF-8";
            try
            {
                UploadFile multipart = new UploadFile(url, charset);

                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");

                multipart.addFormField("USERNAME", value1);
                multipart.addFormField("PASS", value2);
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
        }
    }

    private class Logout extends AsyncTask<Void, Void, Void>
    {
        String username;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.gbye));
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminateDrawable(getDrawable(R.drawable.test_animated));
            progressDialog.show();
            username = preferences.getString("username", "");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try
            {
                logout(ConstantStuff.URL_LOGOUT);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        private void logout(String requestURL) {
            String charset = "UTF-8";

            try {
                UploadFile multipart = new UploadFile(requestURL, charset);

                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");

                multipart.addFormField("USERNAME", username);

                List<String> response = multipart.finish();

                System.out.println("SERVER REPLIED:");
                for (String line : response) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            preferences.edit().clear().apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}

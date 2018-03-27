package com.example.android.mydoctor;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Queue;

/**
 * Created by DELL on 9/10/2017.
 */

public class ActivityLogin extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("usersession",this.MODE_PRIVATE);
        final CheckInternetConnection checkInternetConnection = new CheckInternetConnection(this);

        final EditText etEmail = (EditText)findViewById(R.id.etEmail);
        final EditText etPassword = (EditText)findViewById(R.id.etPassword);
        final Button bLogin = (Button)findViewById(R.id.bLogin);
        final TextView tvForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        final TextView tvRegister = (TextView)findViewById(R.id.tvRegister);
        final ProgressBar circularProgressBar = (ProgressBar)findViewById(R.id.loginProgressBar);
        circularProgressBar.setVisibility(View.INVISIBLE);

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,ActivityForgotPassword.class);
                startActivity(intent);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerPage = new Intent(ActivityLogin.this,ActivityRegister.class);
                ActivityLogin.this.startActivity(registerPage);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularProgressBar.setVisibility(View.VISIBLE);
                if (checkInternetConnection.isConnected()) {
                    final String email = etEmail.getText().toString();
                    final String password = etPassword.getText().toString();

                    if (email.matches("") || password.matches("")) {
                        AlertMessage("Email and Password cannot be blank","ERROR");
                        circularProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else{
                        Response.Listener<String> listener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        String name = jsonResponse.getString("name");
                                        String email = jsonResponse.getString("email");
                                        String mobilenumber = jsonResponse.getString("mobilenumber");

                                        sharedPreferences.edit().putString("email", email)
                                                .putString("name", name)
                                                .putString("mobilenumber", mobilenumber).commit();

                                        Intent intent = new Intent(ActivityLogin.this, ActivityUserArea.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("email", email);
                                        intent.putExtra("mobilenumber", mobilenumber);
                                        ActivityLogin.this.startActivity(intent);
                                        finish();
                                    } else {
                                        AlertMessage("Check username and password", "ERROR");
                                        circularProgressBar.setVisibility(View.INVISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        LoginRequest loginRequest = new LoginRequest(email, password, listener);
                        RequestQueue queue = Volley.newRequestQueue(ActivityLogin.this);
                        queue.add(loginRequest);
                    }
                }else {
                    checkInternetConnection.generateAlert();
                    circularProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void AlertMessage(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLogin.this);
        builder.setMessage(message)
                .setNegativeButton("Retry",null)
                .setTitle(title)
                .create()
                .show();
    }
}

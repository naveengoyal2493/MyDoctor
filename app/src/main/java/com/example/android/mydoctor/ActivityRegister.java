package com.example.android.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 9/12/2017.
 */

public class ActivityRegister extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final CheckInternetConnection checkInternetConnection = new CheckInternetConnection(this);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etMobileNumber = (EditText)findViewById(R.id.etMobileNumber);
        final EditText etPassword = (EditText)findViewById(R.id.etPassword);
        final EditText etEmail = (EditText)findViewById(R.id.etEmail);
        final EditText etConfirmPass = (EditText)findViewById(R.id.etConfirmPass);
        final ProgressBar registerProgressBar = (ProgressBar) findViewById(R.id.registerProgressBar);
        registerProgressBar.setVisibility(View.INVISIBLE);

        final Button bRegister = (Button)findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProgressBar.setVisibility(View.VISIBLE);
                if (checkInternetConnection.isConnected()) {
                    final String name = etName.getText().toString();
                    final String mobilenumber = etMobileNumber.getText().toString();
                    final String email = etEmail.getText().toString();
                    final String password = etPassword.getText().toString();
                    final String confirmpassword = etConfirmPass.getText().toString();

                    if (name.matches("") || mobilenumber.matches("") || email.matches("") || password.matches("") || confirmpassword.matches(""))
                    {
                        alertMessage("Fields cannot be blank","Ok");
                        registerProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else if(mobilenumber.length()!=10)
                    {
                        alertMessage("Please enter a valid mobile number","Ok");
                        registerProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else if(!validateEmailAddress(email))
                    {
                        alertMessage("Please enter valid email address", "Ok");
                        registerProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else if(password.length()<8)
                    {
                        alertMessage("Password should not be less then 8 letters", "Ok");
                        registerProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else if(!password.matches(confirmpassword))
                    {
                        alertMessage("Passwords do not match", "Ok");
                        registerProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                                        ActivityRegister.this.startActivity(intent);
                                        finish();
                                    } else {
                                        alertMessage("Register Failed", "Retry");
                                        registerProgressBar.setVisibility(View.INVISIBLE);

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        RegisterRequest registerRequest = new RegisterRequest(name, mobilenumber, email, password, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(ActivityRegister.this);
                        queue.add(registerRequest);
                    }
                }
                else
                {
                    checkInternetConnection.generateAlert();
                    registerProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
    private Boolean validateEmailAddress(String email){
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +"\\@" +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +"(" +"\\." +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")+";
        Matcher matcher = Pattern.compile(validemail).matcher(email);

        if(matcher.matches()){
            return true;
        }
        else
        {
            return false;
        }
    }

    public void alertMessage(String message, String buttonMessage){
        AlertDialog.Builder nullInformation = new AlertDialog.Builder(ActivityRegister.this);
        nullInformation.setMessage(message)
                .setTitle("Error")
                .setNegativeButton(buttonMessage,null)
                .create()
                .show();
    }
}

package com.example.android.mydoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityUserArea extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        CheckInternetConnection checkInternetConnection = new CheckInternetConnection(this);
        boolean isconnected = checkInternetConnection.isConnected();
        final SharedPreferences sharedPreferences = this.getSharedPreferences("usersession", this.MODE_PRIVATE);

        if (isconnected) {
            final TextView tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
            final EditText etName = (EditText) findViewById(R.id.etName);
            final EditText etEmail = (EditText) findViewById(R.id.etEmail);
            final EditText etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
            final Button bLogout = (Button) findViewById(R.id.bLogout);
            final TextView tv_HomePage = (TextView)findViewById(R.id.tv_HomePage);

            Intent intent = getIntent();
            String name = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");
            String mobilenumber = intent.getStringExtra("mobilenumber");

            String message = "Welcome " + name;
            tvWelcomeMessage.setText(message);
            etName.setText(name);
            etEmail.setText(email);
            etMobileNumber.setText(mobilenumber);

            bLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences.edit().clear().commit();
                    Intent intent = new Intent(ActivityUserArea.this, ActivityLogin.class);
                    ActivityUserArea.this.startActivity(intent);
                    finish();
                }
            });

            tv_HomePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityUserArea.this, ActivityHomePage.class);
                    startActivity(intent);
                }
            });

        }
        else{

        }
    }
}

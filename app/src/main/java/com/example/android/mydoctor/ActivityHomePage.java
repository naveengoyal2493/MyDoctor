package com.example.android.mydoctor;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityHomePage extends AppCompatActivity {
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ImageView img_AppointDoctor = (ImageView)findViewById(R.id.img_AppointDoctor);
        TextView tv_welcomemsg = (TextView)findViewById(R.id.tv_welcomemsg);

         mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         mToggle = new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
         mToolbar = (Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(mToolbar);

        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_AppointDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appointDoctorIntent = new Intent(ActivityHomePage.this,ActivityAppointDoctor.class);
                startActivity(appointDoctorIntent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {

            switch (item.getItemId()){
                case R.id.nav_account:
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityHomePage.this);
                builder.setMessage("Working")
                        .setNegativeButton("Retry",null)
                        .setTitle("Working")
                        .create()
                        .show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void AlertMessage(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityHomePage.this);
        builder.setMessage(message)
                .setNegativeButton("Retry",null)
                .setTitle(title)
                .create()
                .show();
    }
}

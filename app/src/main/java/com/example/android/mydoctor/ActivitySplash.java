package com.example.android.mydoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by DELL on 9/17/2017.
 */

public class ActivitySplash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("usersession",this.MODE_PRIVATE);

        ImageView imageView = (ImageView)findViewById(R.id.iMyDocLogo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        imageView.startAnimation(animation);

        final Intent intent = new Intent(this, ActivityLogin.class);

        Thread timer = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                    if(sharedPreferences.getString("email","")!="")
                    {
                        Intent intent = new Intent(ActivitySplash.this,ActivityUserArea.class);
                        intent.putExtra("email",sharedPreferences.getString("email",""));
                        intent.putExtra("name",sharedPreferences.getString("name",""));
                        intent.putExtra("mobilenumber",sharedPreferences.getString("mobilenumber",""));
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(ActivitySplash.this,ActivityLogin.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        timer.start();

    }
}

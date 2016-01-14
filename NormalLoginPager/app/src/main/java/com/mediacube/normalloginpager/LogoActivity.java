package com.mediacube.normalloginpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2015/12/30.
 */
public class LogoActivity extends Activity{

    private ProgressBar progressBar;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logo);

        progressBar = (ProgressBar) findViewById(R.id.pb_bar);
        backButton = (Button)findViewById(R.id.btn_back);
       new Thread(){
           @Override
           public void run() {
               try {
                   Thread.sleep(3000);
                   Intent intent = new Intent(LogoActivity.this,WelcomeActivity.class);
                   startActivity(intent);

                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }.start();



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

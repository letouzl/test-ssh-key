package com.mediacube.normalloginpager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private SharedPreferences sp;
    private EditText userName;
    private EditText password;
    private CheckBox rememberPwd;
    private CheckBox autoLogin;
    private Button btn_login;
    private ImageButton  btn_Quit;

    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        setContentView(R.layout.activity_login);
        //获得实例对象
        initView();
        sp =this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        if(sp != null) {
            //判断记住密码选框的状态
            if (sp.getBoolean("IS_REMEBER_PWD", false)== true) {

                userName.setText(sp.getString("USERNAME", ""));
                password.setText(sp.getString("PASSWORD", ""));
                //设置默认是记录密码状态
                rememberPwd.setChecked(true);
            }
                //判断自动登录选框的状态
            if (sp.getBoolean("IS_AUTO_LOGIN", false) == true) {
                    //设置默认的自动登陆状态
                    autoLogin.setChecked(true);
                    createDialog();
                  new Thread(){
                     @Override
                     public void run() {
                         try {
                             Thread.sleep(3000);

                             if(mProgressDialog.isShowing()){
                                 mProgressDialog.dismiss();
                             }
                             //跳转界面
                           Intent intent1 = new Intent(LoginActivity.this, LogoActivity.class);
                          //Intent intent1 = new Intent(LoginActivity.this, Show.class);
                             startActivity(intent1);
                             finish();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 }.start();

            }

        }

        //监听记住密码选框事件
        rememberPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rememberPwd.isChecked()==false){

                    autoLogin.setChecked(false);
                }

//                if(rememberPwd.isChecked()){
//
//                    sp.edit().putBoolean("IS_REMEBER_PWD",true).commit();
//                }else{
//
//                    sp.edit().putBoolean("IS_REMEBER_PWD",false).commit();
//                }
            }
        });

        //监听自动登陆选框事件
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                     rememberPwd.setChecked(true);

//                if (autoLogin.isChecked()) {
//
//                    sp.edit().putBoolean("IS_AUTO_LOGIN", true).commit();
//
//                } else {
//
//                    sp.edit().putBoolean("IS_AUTO_LOGIN", false).commit();
//                }
            }

        });

        //自动登陆监听事件，
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userNameStr = userName.getText().toString();
                String passwordStr = password.getText().toString();

                if(!userNameStr.equals("") && !passwordStr.equals("")){
                    if(autoLogin.isChecked()){
                        sp.edit().putBoolean("IS_REMEBER_PWD",true)
                                .putBoolean("IS_AUTO_LOGIN",true).putString("USERNAME",userNameStr)
                                .putString("PASSWORD",passwordStr).commit();

                    }else{
                         if(rememberPwd.isChecked()){
                             sp.edit().putBoolean("IS_REMEBER_PWD",true)
                                     .putBoolean("IS_AUTO_LOGIN",false).putString("USERNAME",userNameStr)
                                     .putString("PASSWORD",passwordStr).commit();

                        }else{
                             sp.edit().putBoolean("IS_REMEBER_PWD",false)
                                     .putBoolean("IS_AUTO_LOGIN",false).putString("USERNAME",userNameStr)
                                     .putString("PASSWORD",passwordStr).commit();
                        }
                    }

                  Intent intent = new Intent(LoginActivity.this, LogoActivity.class);
                    //Intent intent = new Intent(LoginActivity.this, Show.class);
                    startActivity(intent);

                }else{

                    Toast.makeText(LoginActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                }

//                if (userNameStr.equals("liu") && passwordStr.equals("123")) {
//
//                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//
//                    //登录成功和记住密码框为选中状态才保存用户信息
//                    if (rememberPwd.isChecked()) {
//                        //记住用户名、密码、
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putString("USER_NAME", userNameStr);
//                        editor.putString("PASSWORD", passwordStr);
//                        editor.commit();
//                    }
//                    //跳转界面
//                    Intent intent = new Intent(LoginActivity.this, LogoActivity.class);
//                    LoginActivity.this.startActivity(intent);
//                    //finish();
//
//                } else {
//
//                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
//                }

            }
        });

        btn_Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void createDialog(){

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("正在验证中");
        mProgressDialog.setMessage("正在登陆请稍后......");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

    }

    public void initView(){

        userName = (EditText)findViewById(R.id.et_zh);
        password = (EditText)findViewById(R.id.et_mima);
        rememberPwd = (CheckBox)findViewById(R.id.cb_rememberpwd);
        autoLogin = (CheckBox)findViewById(R.id.cb_auto_login);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_Quit = (ImageButton)findViewById(R.id.ima_btn);

    }
}

package com.appstone.assignmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mEtUsername;
    private EditText mEtPassword;
    private CheckBox mChRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtUsername = findViewById(R.id.et_mail);
        mEtPassword = findViewById(R.id.et_password);
        mChRememberMe = findViewById(R.id.chk_remember);
        Button mBtnLogin = findViewById(R.id.btn_login);
        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED",MODE_PRIVATE);
        final SharedPreferences.Editor eDitor = prefManager.edit();
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();
                boolean isRememberMe = mChRememberMe.isChecked();
                if(isRememberMe){
                    eDitor.putBoolean("ISREMEMBERME",isRememberMe);
                }
                eDitor.putString("USERNAME",username);
                eDitor.putString("PASSWORD",password);
                eDitor.apply();
                moveToViewScreen();
            }
        });
        boolean isLreadyLoggedIn = prefManager.getBoolean("ISREMEMBERME",false);
        if(isLreadyLoggedIn){
            moveToViewScreen();
        }
    }
    private void moveToViewScreen(){
        Toast.makeText(LoginActivity.this, "You Have Successfully Logged in", Toast.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this,ViewActivity.class));
        finish();
    }
    }

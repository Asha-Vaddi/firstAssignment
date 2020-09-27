package com.appstone.assignmentactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ViewActivity extends AppCompatActivity {
private TextView mTvUsername;
private SharedPreferences.Editor eDitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mTvUsername = findViewById(R.id.tv_username);
        Button mBtnLogout = findViewById(R.id.btn_logout);
        Button mBtnAlert = findViewById(R.id.btn_alert);
        Button mbtnShowSnackBar = findViewById(R.id.btn_snackbar);
        Button mbtnEdit = findViewById(R.id.btn_edit);
        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED",MODE_PRIVATE);
        eDitor = prefManager.edit();
        String username = prefManager.getString("USERNAME","");
        mTvUsername.setText(username);

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eDitor.putString("USERNAME","");
                eDitor.putString("PASSWORD","");
                eDitor.putBoolean("ISREMEMBERME",false);
                eDitor.apply();
                Toast.makeText(ViewActivity.this,"You Have Successfully Logged Out",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ViewActivity.this,LoginActivity.class));
                finish();
            }
        });
        mBtnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ViewActivity.this).setTitle("Alert").setMessage("Are you sure you want to Logout").setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNeutralButton("MAYBE LATER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
        mbtnShowSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Showing Snackbar",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        });
        mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamedata = mTvUsername.getText().toString();
                Intent data= new Intent(ViewActivity.this,EditActivity.class);
                data.putExtra("USERNAME",usernamedata);
                data.putExtra("ISALREADYLOGGEDIN",true);
                startActivityForResult(data,1000);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                String editedUsername = data.getExtras().getString("EDITED USERNAME");
                mTvUsername.setText(editedUsername);
                eDitor.putString("USERNAME",editedUsername);
                eDitor.apply();
            }else{
                Toast.makeText(ViewActivity.this, "User Cancelled Operation", Toast.LENGTH_LONG).show();
            }
        }
    }
}
package com.appstone.assignmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final EditText mEtUsernameData = findViewById(R.id.et_user_name_data);
        Button mbtnupdate = findViewById(R.id.btn_update);
        Button mbtncancel = findViewById(R.id.btn_cancel);
        Bundle intentdata = getIntent().getExtras();
        if(intentdata != null){
            String username = intentdata.getString("USERNAME");
            Boolean isLoggedIn = intentdata.getBoolean("ISALREADYLOGGEDIN");
            mEtUsernameData.setText(username);
        }
        mbtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(AppCompatActivity.RESULT_CANCELED);
                finish();
            }
        });
        mbtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedUsedName = mEtUsernameData.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("EDITED USERNAME",editedUsedName);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
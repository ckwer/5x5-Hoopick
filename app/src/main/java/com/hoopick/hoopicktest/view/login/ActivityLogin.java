package com.hoopick.hoopicktest.view.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.view.login.ActivityLogin;
import com.hoopick.hoopicktest.view.main.ActivityMain;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by junhyeok on 2017. 9. 8..
 */

public class ActivityLogin extends AppCompatActivity {

    private Button btnLinkToRegisterScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        btnLinkToRegisterScreen = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        btnLinkToRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(ActivityLogin.this,RegisterActivity.class);
                lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ActivityLogin.this.startActivity(lIntent);
            }
        });
    }

}

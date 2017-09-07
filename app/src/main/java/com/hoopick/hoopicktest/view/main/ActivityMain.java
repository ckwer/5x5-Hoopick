package com.hoopick.hoopicktest.view.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.view.lineup.ActivityLineup;
import com.hoopick.hoopicktest.view.settings.ActivitySettings;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.LineUpBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lIntent = new Intent(ActivityMain.this, ActivityLineup.class);
                lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ActivityMain.this.startActivity(lIntent);
            }
        });


        findViewById(R.id.SettingsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lIntent = new Intent(ActivityMain.this, ActivitySettings.class);
                ActivityMain.this.startActivity(lIntent);
            }
        });


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getSupportActionBar().hide();

    }


    @Override
    protected void onResume() {
        super.onResume();

        //
        //

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = pInfo.versionCode;
        String versionName = pInfo.versionName;
        //
        //

        TextView lTextVersion = (TextView) findViewById(R.id.textVersion);
        lTextVersion.setText(String.format("Version : %s", versionName));

    }


}


package com.hoopick.hoopicktest.view.playball;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.control.action.HpActionLineup;
import com.hoopick.hoopicktest.control.action.HpActionShot;
import com.hoopick.hoopicktest.data.util.HpGenUtil;
import com.hoopick.hoopicktest.view.lineup.ActivityLineup;

import java.lang.reflect.Field;

import static android.R.color.black;

/**
 * Created by junhyeok on 2017. 9. 12..
 */

public class ActivityScore extends AppCompatActivity {
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private final int REQUEST_CODE_START_ACTIVITY = 1;
    private NumberPicker mHomePicker;
    private NumberPicker mAwayPicker;
    private TextView mCancelText;
    private TextView mSaveText;
    private int mHomeScore;
    private int mAwayScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mHomePicker = (NumberPicker) findViewById(R.id.home_picker);
        mAwayPicker = (NumberPicker) findViewById(R.id.away_picker);
        mCancelText = (TextView) findViewById(R.id.Cancel_text);
        mSaveText = (TextView)findViewById(R.id.Save_text);

        mHomePicker.setMinValue(0);
        mHomePicker.setMaxValue(200);
        mHomePicker.setWrapSelectorWheel(false);

        mAwayPicker.setMinValue(0);
        mAwayPicker.setMaxValue(200);
        mAwayPicker.setWrapSelectorWheel(false);


        //ActivityPlayball scoreButton 값 받아오기
        Intent intent = getIntent();
        int no = intent.getIntExtra("home_picker",0);
        int no1 = intent.getIntExtra("away_picker",0);
        mHomePicker.setValue(no);
        mAwayPicker.setValue(no1);


//        mSaveText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityScore.this,ActivityPlayBall.class);
//
//                int home = mHomePicker.getValue();
//                int away = mAwayPicker.getValue();
//
//                //ActivityPlayball ScoreButton에 값 넘겨주기
//                    intent.putExtra("home_score",String.valueOf(home));
//                    intent.putExtra("away_score",String.valueOf(away));
//
//                startActivity(intent);
//
//            }
//        });
//
//
//        mCancelText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(ActivityScore.this,ActivityPlayBall.class);
//
//                int home = mHomePicker.getValue();
//                int away = mAwayPicker.getValue();
//                //ActivityPlayball ScoreButton에 값 넘겨주기
//                intent.putExtra("home_score",String.valueOf(home));
//               intent.putExtra("away_score",String.valueOf(away));
//                startActivity(intent);
//                ActivityScore.this.finish();
//
//            }
//        });

    }
}

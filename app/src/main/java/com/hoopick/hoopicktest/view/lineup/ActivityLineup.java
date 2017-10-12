package com.hoopick.hoopicktest.view.lineup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.control.action.HpActionLineup;
import com.hoopick.hoopicktest.data.util.HpGenUtil;
import com.hoopick.hoopicktest.view.main.ActivityMain;
import com.hoopick.hoopicktest.view.playball.ActivityPlayBall;
import static android.R.attr.action;

/**
 * Created by junhyeok on 2017. 8. 14..
 */

public class ActivityLineup extends AppCompatActivity {

    private final int REQUEST_CODE_START_ACTIVITY = 1;
    private AlertDialog mAlertDialog = null;
    private TextView mTextHomeTeam = null;
    private TextView mTextAwayTeam = null;
    private TextView mTextHomePlayerName1 = null;
    private TextView mTextHomePlayerName2 = null;
    private TextView mTextHomePlayerName3 = null;
    private TextView mTextHomePlayerName4 = null;
    private TextView mTextHomePlayerName5 = null;
    private TextView mTextHomePlayerNumber1 = null;
    private TextView mTextHomePlayerNumber2 = null;
    private TextView mTextHomePlayerNumber3 = null;
    private TextView mTextHomePlayerNumber4 = null;
    private TextView mTextHomePlayerNumber5 = null;
    private TextView mTextAwayPlayerName1 = null;
    private TextView mTextAwayPlayerName2 = null;
    private TextView mTextAwayPlayerName3 = null;
    private TextView mTextAwayPlayerName4 = null;
    private TextView mTextAwayPlayerName5 = null;
    private TextView mTextAwayPlayerNumber1 = null;
    private TextView mTextAwayPlayerNumber2 = null;
    private TextView mTextAwayPlayerNumber3 = null;
    private TextView mTextAwayPlayerNumber4 = null;
    private TextView mTextAwayPlayerNumber5 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lineup");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTextHomeTeam = (TextView)findViewById(R.id.textHomeTeam);
        mTextAwayTeam = (TextView)findViewById(R.id.textAwayTeam);

        mTextHomePlayerName1 = ((TextView)findViewById(R.id.textHomePlayerName1));
        mTextHomePlayerName2 = ((TextView)findViewById(R.id.textHomePlayerName2));
        mTextHomePlayerName3 = ((TextView)findViewById(R.id.textHomePlayerName3));
        mTextHomePlayerName4 = ((TextView)findViewById(R.id.textHomePlayerName4));
        mTextHomePlayerName5 = ((TextView)findViewById(R.id.textHomePlayerName5));

        mTextHomePlayerNumber1 = ((TextView)findViewById(R.id.textHomePlayerNumber1));
        mTextHomePlayerNumber2 = ((TextView)findViewById(R.id.textHomePlayerNumber2));
        mTextHomePlayerNumber3 = ((TextView)findViewById(R.id.textHomePlayerNumber3));
        mTextHomePlayerNumber4 = ((TextView)findViewById(R.id.textHomePlayerNumber4));
        mTextHomePlayerNumber5 = ((TextView)findViewById(R.id.textHomePlayerNumber5));

        mTextAwayPlayerName1 = ((TextView)findViewById(R.id.textAwayPlayerName1));
        mTextAwayPlayerName2 = ((TextView)findViewById(R.id.textAwayPlayerName2));
        mTextAwayPlayerName3 = ((TextView)findViewById(R.id.textAwayPlayerName3));
        mTextAwayPlayerName4 = ((TextView)findViewById(R.id.textAwayPlayerName4));
        mTextAwayPlayerName5 = ((TextView)findViewById(R.id.textAwayPlayerName5));

        mTextAwayPlayerNumber1 = ((TextView)findViewById(R.id.textAwayPlayerNumber1));
        mTextAwayPlayerNumber2 = ((TextView)findViewById(R.id.textAwayPlayerNumber2));
        mTextAwayPlayerNumber3 = ((TextView)findViewById(R.id.textAwayPlayerNumber3));
        mTextAwayPlayerNumber4 = ((TextView)findViewById(R.id.textAwayPlayerNumber4));
        mTextAwayPlayerNumber5 = ((TextView)findViewById(R.id.textAwayPlayerNumber5));

        findViewById(R.id.buttonDummy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mTextHomeTeam.getText().toString().trim().equalsIgnoreCase("")){
                    mTextHomeTeam.setText("한국");
                }
                if(mTextAwayTeam.getText().toString().trim().equalsIgnoreCase("")){
                    mTextAwayTeam.setText("미국");
                }

                if (mTextHomePlayerNumber1.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerNumber1.setText("11");
                }

                if (mTextHomePlayerName1.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerName1.setText("서장훈");
                }

                if (mTextHomePlayerNumber2.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerNumber2.setText("12");
                }

                if (mTextHomePlayerName2.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerName2.setText("문경은");
                }

                if (mTextHomePlayerNumber3.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerNumber3.setText("13");
                }

                if (mTextHomePlayerName3.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerName3.setText("이상민");
                }

                if (mTextHomePlayerNumber4.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerNumber4.setText("14");
                }

                if (mTextHomePlayerName4.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerName4.setText("최희암");
                }

                if (mTextHomePlayerNumber5.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerNumber5.setText("99");
                }

                if (mTextHomePlayerName5.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextHomePlayerName5.setText("최불암");
                }

                if (mTextAwayPlayerNumber1.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerNumber1.setText("21");
                }

                if (mTextAwayPlayerName1.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerName1.setText("현주엽");
                }

                if (mTextAwayPlayerNumber2.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerNumber2.setText("22");
                }

                if (mTextAwayPlayerName2.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerName2.setText("전희철");
                }

                if (mTextAwayPlayerNumber3.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerNumber3.setText("23");
                }

                if (mTextAwayPlayerName3.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerName3.setText("양희승");
                }

                if (mTextAwayPlayerNumber4.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerNumber4.setText("24");
                }

                if (mTextAwayPlayerName4.getText().toString().trim().equalsIgnoreCase("")) {
                    mTextAwayPlayerName4.setText("김연아");
                }

                if (mTextAwayPlayerNumber5.getText().toString().trim().equalsIgnoreCase("")){
                    mTextAwayPlayerNumber5.setText("55");
                }

                if (mTextAwayPlayerName5.getText().toString().trim().equalsIgnoreCase("")){
                    mTextAwayPlayerName5.setText("김승현");
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle("Hoopick")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface alertDialog, int arg1) {

                        Intent lIntent = new Intent(ActivityLineup.this, ActivityMain.class);
                        lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        ActivityLineup.this.startActivityForResult(lIntent, REQUEST_CODE_START_ACTIVITY);

                        ActivityLineup.this.finish();

                    }
                }).create();

        mAlertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lineup, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                // Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;

            case R.id.action_jumpball:
                jumpBall();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == REQUEST_CODE_START_ACTIVITY) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user came back from the Enable Location Activity
                // Dismiss the Dialog
                if ((null != mAlertDialog) && mAlertDialog.isShowing()) {
                    mAlertDialog.dismiss();
                }
            }
        }

    }

    private boolean validateJumpBall() {

        TextView[] lArrayEdit = {mTextHomeTeam, mTextAwayTeam
                , mTextHomePlayerNumber1, mTextHomePlayerName1
                , mTextHomePlayerNumber2, mTextHomePlayerName2
                , mTextHomePlayerNumber3, mTextHomePlayerName3
                , mTextHomePlayerNumber4, mTextHomePlayerName4
                , mTextHomePlayerNumber5, mTextHomePlayerName5
                , mTextAwayPlayerNumber1, mTextAwayPlayerName1
                , mTextAwayPlayerNumber2, mTextAwayPlayerName2
                , mTextAwayPlayerNumber3, mTextAwayPlayerName3
                , mTextAwayPlayerNumber4, mTextAwayPlayerName4
                , mTextAwayPlayerNumber5, mTextAwayPlayerName5
        };

        for (TextView nText : lArrayEdit) {
            if (nText.getText().toString().trim().equalsIgnoreCase("")) {
                nText.requestFocus();
                return false;
            }
        }

        return true;
    }


    public void onClickBackground(View view) {

        EditText editDummy = (EditText)findViewById(R.id.editDummy);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editDummy.getWindowToken(), 0);

        findViewById(R.id.layoutLineupBox).requestFocus();
    }



    private void jumpBall() {

        if (false == validateJumpBall() ) {
            return;
        }

        try {

            String lGameTimeMin = "10";
            String lShotTimeSec = "24";

            new HpActionLineup(ActivityLineup.this
                    , HpGenUtil.genGameId()
                    , "2016-2017"
                    , mTextHomeTeam.getText().toString()
                    , mTextAwayTeam.getText().toString()
                    , mTextHomePlayerNumber1.getText().toString()
                    , mTextHomePlayerName1.getText().toString()
                    , mTextHomePlayerNumber2.getText().toString()
                    , mTextHomePlayerName2.getText().toString()
                    , mTextHomePlayerNumber3.getText().toString()
                    , mTextHomePlayerName3.getText().toString()
                    , mTextHomePlayerNumber4.getText().toString()
                    , mTextHomePlayerName4.getText().toString()
                    , mTextHomePlayerNumber5.getText().toString()
                    , mTextHomePlayerName5.getText().toString()
                    , mTextAwayPlayerNumber1.getText().toString()
                    , mTextAwayPlayerName1.getText().toString()
                    , mTextAwayPlayerNumber2.getText().toString()
                    , mTextAwayPlayerName2.getText().toString()
                    , mTextAwayPlayerNumber3.getText().toString()
                    , mTextAwayPlayerName3.getText().toString()
                    , mTextAwayPlayerNumber4.getText().toString()
                    , mTextAwayPlayerName4.getText().toString()
                    , mTextAwayPlayerNumber5.getText().toString()
                    , mTextAwayPlayerName5.getText().toString()
                    , lGameTimeMin
                    , lShotTimeSec
            ).execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Intent lIntent = new Intent(ActivityLineup.this, ActivityPlayBall.class);
        lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ActivityLineup.this.startActivityForResult(lIntent, REQUEST_CODE_START_ACTIVITY);

        ActivityLineup.this.finish();

//        Intent tIntent = new Intent(ActivityLineup.this, ActivityTest.class);
//        tIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        ActivityLineup.this.startActivityForResult(lIntent, REQUEST_CODE_START_ACTIVITY);
//
//        ActivityLineup.this.finish();

    }

}
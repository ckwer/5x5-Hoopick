package com.hoopick.hoopicktest.view.playball;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hoopick.hoopicktest.R;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class DialogFreeThrow extends Dialog implements android.view.View.OnClickListener {

    public static final int FREE_THROW_MADE = 1;
    public static final int FREE_THROW_MISS = 2;

    public Activity c;
    public Dialog d;

    private OnClickFreethrowListner mOnClickFreeThrowListner = null;

    private String mTitle = "";
    private String mDesc = "";

    public DialogFreeThrow(Context context, String aTitle, String aDesc) {
        super(context);

        mTitle = aTitle;
        mDesc = aDesc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_free_throw);

        findViewById(R.id.button_free_throw_made).setOnClickListener(this);
        findViewById(R.id.button_free_throw_miss).setOnClickListener(this);

        ((TextView)findViewById(R.id.text_title)).setText(mTitle);
        ((TextView)findViewById(R.id.text_desc)).setText(mDesc);

    }
    public void setOnClickFreethrowListner(OnClickFreethrowListner aOnClickShotListner) {
        mOnClickFreeThrowListner = aOnClickShotListner;
    }

    public static abstract class OnClickFreethrowListner {
        abstract void OnClickShot(int aMenuItem);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_free_throw_made: {
                mOnClickFreeThrowListner.OnClickShot(FREE_THROW_MADE);
                dismiss();
                break;
            }

            case R.id.button_free_throw_miss: {
                mOnClickFreeThrowListner.OnClickShot(FREE_THROW_MISS);
                dismiss();
                break;
            }


        }

    }
}

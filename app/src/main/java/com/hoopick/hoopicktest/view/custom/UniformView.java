package com.hoopick.hoopicktest.view.custom;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by pro on 2016-10-24.
 */
public class UniformView extends View {

    private TextView mTextPlayerName = null;
    private TextView mTextPlayerNumber = null;

    public UniformView(Context context) {
        super (context);
    }

    public TextView getmTextPlayerName() {
        return mTextPlayerName;
    }

    public void setmTextPlayerName(TextView mTextPlayerName) {
        this.mTextPlayerName = mTextPlayerName;
    }

    public TextView getmTextPlayerNumber() {
        return mTextPlayerNumber;
    }

    public void setmTextPlayerNumber(TextView mTextPlayerNumber) {
        this.mTextPlayerNumber = mTextPlayerNumber;
    }
}

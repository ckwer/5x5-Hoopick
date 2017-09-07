package com.hoopick.hoopicktest.view.playball;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.hoopick.hoopicktest.R;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class DialogWhistle extends Dialog {

    public static final int MENU_MAIN_FOUL = 10;
    public static final int MENU_MAIN_SUBSTITUTION = 20;
    // public static final int MENU_MAIN_VIOLATION = 30;
    public static final int MENU_SUB_VIOLATION_SHOT_CLOCK = 31;
    public static final int MENU_SUB_VIOLATION_TRAVELING = 32;
    public static final int MENU_SUB_VIOLATION_DOUBLE_DRIBBLE = 33;
    public static final int MENU_SUB_VIOLATION_HELD_BALL = 34;
    public static final int MENU_SUB_VIOLATION_5S_POST_UP = 35;
    public static final int MENU_MAIN_OUT_OF_BOUND = 40;
    public static final int MENU_MAIN_CHECK =50;
    public static final int MENU_MAIN_END_QUATER =60;

    public DialogWhistle(Context context) {
        super(context);
    }
    private OnClickWhistleListner mOnClickWhistleListner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_whistle);

        findViewById(R.id.button_main_foul).setOnClickListener(mOnButtonClick);

        findViewById(R.id.button_main_substitution).setOnClickListener(mOnButtonClick);

        findViewById(R.id.button_main_violation).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_sub_violation_shock_clock).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_sub_violation_traveling).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_sub_violation_double_dribble).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_sub_violation_held_ball).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_sub_violation_5s_post_up).setOnClickListener(mOnButtonClick);

        findViewById(R.id.button_main_out_of_bound).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_main_check).setOnClickListener(mOnButtonClick);
        findViewById(R.id.button_main_end_quater).setOnClickListener(mOnButtonClick);

        hideAllSubMenu();

    }

    private void hideAllSubMenu() {
        findViewById(R.id.layout_sub_violation).setVisibility(View.GONE);
    }

    private View.OnClickListener mOnButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            hideAllSubMenu();

            switch (v.getId()) {

                case R.id.button_main_foul:
                    mOnClickWhistleListner.OnClickWhistle(MENU_MAIN_FOUL);
                    break;

                case R.id.button_main_substitution:
                    mOnClickWhistleListner.OnClickWhistle(MENU_MAIN_SUBSTITUTION);
                    break;

                case R.id.button_main_violation:
                    if (findViewById(R.id.layout_sub_violation).getVisibility() == View.VISIBLE) {
                        findViewById(R.id.layout_sub_violation).setVisibility(View.GONE);
                    }
                    else {
                        findViewById(R.id.layout_sub_violation).setVisibility(View.VISIBLE);
                    }
                    break;

                case R.id.button_sub_violation_shock_clock:
                    mOnClickWhistleListner.OnClickWhistle(MENU_SUB_VIOLATION_SHOT_CLOCK);
                    break;

                case R.id.button_sub_violation_traveling:
                    mOnClickWhistleListner.OnClickWhistle(MENU_SUB_VIOLATION_TRAVELING);
                    break;

                case R.id.button_sub_violation_double_dribble:
                    mOnClickWhistleListner.OnClickWhistle(MENU_SUB_VIOLATION_DOUBLE_DRIBBLE);
                    break;

                case R.id.button_sub_violation_held_ball:
                    mOnClickWhistleListner.OnClickWhistle(MENU_SUB_VIOLATION_HELD_BALL);
                    break;

                case R.id.button_sub_violation_5s_post_up:
                    mOnClickWhistleListner.OnClickWhistle(MENU_SUB_VIOLATION_5S_POST_UP);
                    break;


                case R.id.button_main_out_of_bound:
                    mOnClickWhistleListner.OnClickWhistle(MENU_MAIN_OUT_OF_BOUND);
                    break;

                case R.id.button_main_check:
                    mOnClickWhistleListner.OnClickWhistle(MENU_MAIN_CHECK);
                    break;

                case R.id.button_main_end_quater:
                    mOnClickWhistleListner.OnClickWhistle(MENU_MAIN_END_QUATER);
                    break;

                default:
                    break;

            }

        }

    };

    public DialogWhistle setOnClickWhistleListner(OnClickWhistleListner aOnClickListner) {
        mOnClickWhistleListner = aOnClickListner;
        return this;
    }

    public static abstract class OnClickWhistleListner {

        abstract void OnClickWhistle(int aMenuItem);
    }

}
package com.hoopick.hoopicktest.view.playball;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class DialogSelectPlayer extends Dialog  {

    private static final int[][] lSelectIds = {
            {
                    R.id.layout_home_uniform1
                    , R.id.layout_home_uniform2
                    , R.id.layout_home_uniform3
                    , R.id.layout_home_uniform4
                    , R.id.layout_home_uniform5
            },
            {
                    R.id.layout_away_uniform1
                    , R.id.layout_away_uniform2
                    , R.id.layout_away_uniform3
                    , R.id.layout_away_uniform4
                    , R.id.layout_away_uniform5
            }
    };

    private String mTitle = "";
    private String mDesc = "";
    private int mHideTeam = -1;
    private TextView[] lTextPlayerNumber = new TextView[10];
    private TextView[] lTextPlayerName = new TextView[10];
    private OnClickPlayerListner mOnClickListner = null;

    public DialogSelectPlayer(Context context, String aTitle, String aDesc) {
        super(context);
        mTitle = aTitle;
        mDesc = aDesc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_player);

        initLayout();

    }

    private void initLayout() {

        ((TextView)findViewById(R.id.text_title)).setText(mTitle);
        ((TextView)findViewById(R.id.text_desc)).setText(mDesc);

        //
        for (int n=0; n<lSelectIds.length; n++) {
            for (int k=0; k<lSelectIds[n].length; k++) {

                HpPlayer lPlayer = HpGameManager.get().findPlayerBySlot(n, k);
                if (null == lPlayer) {
                    continue;
                }

                View lViewUniform = findViewById(lSelectIds[n][k]);
                if (null == lViewUniform) {
                    continue;
                }

                lViewUniform.setVisibility(View.VISIBLE);
                lViewUniform.setOnClickListener(mOnClickUniform);

                // team
                lViewUniform.setTag(new Integer(n));

                int lIndex = n * 4 + k;

                lTextPlayerNumber[lIndex] = (TextView) lViewUniform.findViewById(R.id.text_player_number);
                lTextPlayerNumber[lIndex].setText(lPlayer.getNumber());

                lTextPlayerName[lIndex] = (TextView) lViewUniform.findViewById(R.id.text_player_name);
                lTextPlayerName[lIndex].setText(lPlayer.getName());

            }
        }


        if (mHideTeam >= 0) {

            for (int k=0; k<lSelectIds[mHideTeam].length; k++) {

                View lViewUniform = findViewById(lSelectIds[mHideTeam][k]);
                if (null == lViewUniform) {
                    continue;
                }

                lViewUniform.setVisibility(View.INVISIBLE);

            }

        }

    }

    public void hideHomeTeam() {
        hideTeam(1);
    }

    public void hideAwayTeam() {
        hideTeam(0);
    }

    public void hideTeam(int aTeam) {
        mHideTeam = aTeam;
    }

    public DialogSelectPlayer setOnClickPlayerListner(OnClickPlayerListner aOnClickListner) {
        mOnClickListner = aOnClickListner;
        return this;
    }

    public static abstract class OnClickPlayerListner {

        abstract void OnClickPlayer(int team, int player);
    }


    private View.OnClickListener mOnClickUniform = new View.OnClickListener() {

        @Override
        public void onClick(View aView) {

            if (null == mOnClickListner) {
                return;
            }

            for (int n=0; n<lSelectIds.length; n++) {
                for (int k = 0; k < lSelectIds[n].length; k++) {

                    if (aView.getId() == lSelectIds[n][k]) {
                        mOnClickListner.OnClickPlayer(n, k);
                        break;
                    }

                }
            }

            DialogSelectPlayer.this.dismiss();
        }
    };


}

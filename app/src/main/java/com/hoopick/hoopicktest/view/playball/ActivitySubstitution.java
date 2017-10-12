package com.hoopick.hoopicktest.view.playball;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.control.action.HpActionSubstitution;
import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;


/**
 * Created by pro on 2016-10-23.
 */
public class ActivitySubstitution extends AppCompatActivity {

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

    private TextView[] lTextPlayerNumber = new TextView[10];
    private TextView[] lTextPlayerName = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Substitution");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        initLayout();

    }

    private void initLayout() {

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

                // team
                lViewUniform.setTag(new Integer(n));

                //views to drag,
                //set touch listeners
                lViewUniform.setOnTouchListener(new ChoiceTouchListener());
                //set drag listeners
                lViewUniform.setOnDragListener(new ChoiceDragListener());

                int lIndex = n * 4 + k;

                lTextPlayerNumber[lIndex] = (TextView) lViewUniform.findViewById(R.id.text_player_number);
                lTextPlayerNumber[lIndex].setText(lPlayer.getNumber());

                lTextPlayerName[lIndex] = (TextView) lViewUniform.findViewById(R.id.text_player_name);
                lTextPlayerName[lIndex].setText(lPlayer.getName());

            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_substitution, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_cancel:
                onBackPressed();
                break;

            case R.id.action_ok:
                applaySubstitution();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


    private void applaySubstitution() {

        // check difference

        boolean lIsDiff = false;

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

                TextView lTextPlayerNumber = (TextView) lViewUniform.findViewById(R.id.text_player_number);
                if (false == lTextPlayerNumber.getText().toString().equalsIgnoreCase(lPlayer.getNumber())) {
                    lIsDiff = true;
                    break;
                }

                TextView lTextPlayerName = (TextView) lViewUniform.findViewById(R.id.text_player_name);
                if (false == lTextPlayerName.getText().toString().equalsIgnoreCase(lPlayer.getName())) {
                    lIsDiff = true;
                    break;
                }
            }
        }


        // if diff, change player
        if (lIsDiff) {

            try {

                new HpActionSubstitution(ActivitySubstitution.this,
                        lTextPlayerName[0].getText().toString(),
                        lTextPlayerNumber[0].getText().toString(),
                        lTextPlayerName[1].getText().toString(),
                        lTextPlayerNumber[1].getText().toString(),
                        lTextPlayerName[2].getText().toString(),
                        lTextPlayerNumber[2].getText().toString(),
                        lTextPlayerName[3].getText().toString(),
                        lTextPlayerNumber[3].getText().toString(),
                        lTextPlayerName[4].getText().toString(),
                        lTextPlayerNumber[4].getText().toString(),
                        lTextPlayerName[5].getText().toString(),
                        lTextPlayerNumber[5].getText().toString(),
                        lTextPlayerName[6].getText().toString(),
                        lTextPlayerNumber[6].getText().toString(),
                        lTextPlayerName[7].getText().toString(),
                        lTextPlayerNumber[7].getText().toString(),
                        lTextPlayerName[8].getText().toString(),
                        lTextPlayerNumber[8].getText().toString(),
                        lTextPlayerName[9].getText().toString(),
                        lTextPlayerNumber[9].getText().toString(),
                        lTextPlayerName[10].getText().toString(),
                        lTextPlayerNumber[10].getText().toString(),
                        lTextPlayerName[11].getText().toString(),
                        lTextPlayerNumber[11].getText().toString()


                ).execute();

            }
            catch (Exception e) {
                e.printStackTrace();;
            }

        }

        this.finish();

    }


    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.setBackgroundColor(0xFFFFBBFF);

                //start dragging the item touched
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                }
                else {
                    view.startDrag(data, shadowBuilder, view, 0);
                }

                return true;
            }
            else {
                return false;
            }

        }

    }


    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            //handle the dragged view being dropped over a drop view
            View lFromView = (View) event.getLocalState();
            TextView lFromTextPlayerNumber = (TextView) lFromView.findViewById(R.id.text_player_number);
            TextView lFromTextPlayerName = (TextView) lFromView.findViewById(R.id.text_player_name);

            //handle the dragged view being dropped over a target view
            View lTargetUniform = v;
            TextView lTargetTextPlayerNumber = (TextView) lTargetUniform.findViewById(R.id.text_player_number);
            TextView lTargetTextPlayerName = (TextView) lTargetUniform.findViewById(R.id.text_player_name);

            String lTargetPlayerNumber = lTargetTextPlayerNumber.getText().toString();
            String lTargetPlayerName = lTargetTextPlayerName.getText().toString();

            // 자기 자신이면 불가
            if (lFromView.equals(lTargetUniform)) {
                return false;
            }

            // 다른 팀이면 불가
            if (false == lFromView.getTag().toString().equalsIgnoreCase(lTargetUniform.getTag().toString())) {
                return false;
            }

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    // lTargetUniform.setBackgroundColor(0xAAAA00FF);
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    lTargetUniform.setBackgroundColor(Color.MAGENTA);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    lTargetUniform.setBackgroundColor(Color.TRANSPARENT);
                    break;

                case DragEvent.ACTION_DROP:

                    lFromView.setBackgroundColor(Color.TRANSPARENT);
                    lTargetUniform.setBackgroundColor(Color.TRANSPARENT);

                    lTargetTextPlayerNumber.setText(lFromTextPlayerNumber.getText().toString());
                    lTargetTextPlayerName.setText(lFromTextPlayerName.getText().toString());

                    lFromTextPlayerNumber.setText(lTargetPlayerNumber);
                    lFromTextPlayerName.setText(lTargetPlayerName);

                    /*
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);

                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;

                    //view being dragged and dropped
                    TextView dropped = (TextView) view;

                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText());

                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    */

                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;

                default:
                    break;
            }

            return true;
        }
    }

}

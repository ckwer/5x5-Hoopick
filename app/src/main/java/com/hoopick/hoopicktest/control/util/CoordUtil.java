package com.hoopick.hoopicktest.control.util;

/**
 * Created by pro on 2016-10-21.
 */
public class CoordUtil {

    /**
     * 좌표를 가지고 1점 또는 2점을 리턴한다. (3:3에서는 1점, 2점)
     * 농구장의 크기는 반코트
     * 가로 크기 12000, 세로 크기 10000로 기준
     * @param aPosX x/12000 (ex:6000 = 50%)
     * @param aPosY y/10000 (ex: 100 = 1%)
     * @return
     */
    public static int pointScoreInHalf(int aCenterX, int aCenterY, int a3PtX, int a3PtY, int aPosX, int aPosY) {

        int lPointScore = 3;

        if (aPosY < 10) {

            if ( (aPosY > 8) && (aPosY < 42) ) {
                lPointScore = 2;
            }

        }
        else {

            int lGoalX = aCenterX;
            int lGoalY = aCenterY;
            int lCenterXDelta = 5;

            int l3PtX = a3PtX;
            int l3PtY = a3PtY;

            // 골대 지점
            int lDistance2Pt = getDistance(lGoalX, lGoalY, l3PtX, l3PtY);

            if (aPosY < 25 ) {

                int lCenterLeftX = lGoalX + lCenterXDelta;
                int lCenterLeftY = lGoalY;

                // 슛한 지점
                int lDistanceShot = getDistance(lCenterLeftX, lCenterLeftY, aPosX, aPosY);

                if (lDistanceShot < lDistance2Pt) {
                    lPointScore = 2;
                }

//                Log.i("Hoopick", String.format("onTouch (%04d, %04d) %d Ponit, Distance %04d, 3Pt : %04d"
//                        , aPosX, aPosY, lPointScore, lDistanceShot, lDistance2Pt));
            }
            else {

                int lCenterRightX = lGoalX - lCenterXDelta;
                int lCenterRightY = lGoalY;

                // 슛한 지점
                int lDistanceShot = getDistance(lCenterRightX, lCenterRightY, aPosX, aPosY);

                if (lDistanceShot < lDistance2Pt) {
                    lPointScore = 2;
                }

//                Log.i("Hoopick", String.format("onTouch (%04d, %04d) %d Ponit, Distance %04d, 3Pt : %04d"
//                        , aPosX, aPosY, lPointScore, lDistanceShot, lDistance2Pt));
            }

        }

        return lPointScore;
    }

    /**
     * 두 점 사이의 거리를 구한다.
     * @param aPosX1
     * @param aPosY1
     * @param aPosX2
     * @param aPosY2
     * @return
     */
    public static int getDistance(int aPosX1, int aPosY1, int aPosX2, int aPosY2) {
        return (int) Math.sqrt( Math.pow((aPosX1 - aPosX2), 2) + Math.pow((aPosY1 - aPosY2), 2) );
    }


}

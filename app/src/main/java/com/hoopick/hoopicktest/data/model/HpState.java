package com.hoopick.hoopicktest.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by pro on 2016-09-20.
 */
@JsonPropertyOrder({"gameId"
        , "season"
        , "gameDate"
        , "currentTime"
        , "teamHome"
        , "teamAway"
        , "playerHomeNumber1"
        , "playerHomeName1"
        , "playerHomeNumber2"
        , "playerHomeName2"
        , "playerHomeNumber3"
        , "playerHomeName3"
        , "playerHomeNumber4"
        , "playerHomeName4"
        , "playerHomeNumber5"
        , "playerHomeName5"
        , "playerAwayNumber1"
        , "playerAwayName1"
        , "playerAwayNumber2"
        , "playerAwayName2"
        , "playerAwayNumber3"
        , "playerAwayName3"
        , "playerAwayNumber4"
        , "playerAwayName4"
        , "playerAwayNumber5"
        , "playerAwayName5"
        , "playId"
        , "remainingTime"
        , "shotclock"
        , "elapsedTime"
        , "elapsedTimeSec"
        , "playLength"
        , "playLengthSec"
        , "scoreHome"
        , "scoreAway"
        , "foulsAway"
        , "foulsHome"
        , "eventType"
        , "moreDetailOfEvent"
        , "eventDetail"
        , "eventDesc"
        , "teamNameExecutedEvent"
        , "playerExecutedEvent"
        , "teamGrabbedTheBallAfterEvent"
        , "teamAssist"
        , "JumpBallHome"
        , "JumpBallAway"
        , "teamBlockedAShot"
        , "teamCheckIn"
        , "teamCheckOut"
        , "freeThrowsOrder"
        , "teamFoul"
        , "teamWhistleGrabbed"
        , "freeThrowsCount"
        , "pointsScoredWithinEvent"
        , "shotMadeOrMissed"
        , "teamSteal"
        , "shotDistance"
        , "shotAxisX"
        , "shotAxisY"
})
public class HpState extends Object implements Cloneable {

    // EventType
    public static final String EVENT_TYPE_LINEUP = "lineup";
    public static final String EVENT_TYPE_START_OF_PERIOD = "start of period";
    public static final String EVENT_TYPE_PASS = "pass";
    // public static final String EVENT_TYPE_MISS = "miss"; -> shotMadeOrMissed : miss
    public static final String EVENT_TYPE_REBOUND = "rebound";
    public static final String EVENT_TYPE_SHOT = "shot";
    public static final String EVENT_TYPE_FOUL = "foul";
    public static final String EVENT_TYPE_FREE_THROW = "free throw";
    public static final String EVENT_TYPE_TURNOVER = "turnover";
    public static final String EVENT_TYPE_TIMEOUT = "timeout";
    public static final String EVENT_TYPE_WHISTLE = "whistle";
    public static final String EVENT_TYPE_OUT_OF_BOUND = "out of bound";
    public static final String EVENT_TYPE_SET = "set";
    public static final String EVENT_TYPE_SUBSTITUTION = "substitution";
    public static final String EVENT_TYPE_END_OF_PERIOD = "end of period";
    public static final String EVENT_TYPE_UNKNOWN = "unknown";


    // EventDetail
    public static final String EVENT_DETAIL_START_OF_PERIOD = "start of period";
    public static final String EVENT_DETAIL_JUMP_BALL = "jump ball";
    public static final String EVENT_DETAIL_JUMP_SHOT = "Jump Shot";
    public static final String EVENT_DETAIL_S_FOUL = "s.foul";
    public static final String EVENT_DETAIL_FREE_THROW_1OF2 = "Free Throw 1 of 2";
    public static final String EVENT_DETAIL_FREE_THROW_2OF2 = "Free Throw 2 of 2";
    public static final String EVENT_DETAIL_UNKNOWN = "unknown";
    public static final String EVENT_DETAIL_TEAM_REBOUND = "team rebound";
    public static final String EVENT_DETAIL_REBOUND_OFFENSIVE = "offensive rebound";
    public static final String EVENT_DETAIL_REBOUND_DEFENSIVE = "defensive rebound";
    public static final String EVENT_DETAIL_REBOUND_FREE_THROW = "freethrow rebound";
    public static final String EVENT_DETAIL_FOUL_OFFENSIVE = "offensive foul";
    public static final String EVENT_DETAIL_FOUL_DEFENSIVE = "defensive foul";
    public static final String EVENT_DETAIL_P_FOUL = "p.foul";
    public static final String EVENT_DETAIL_LAYUP = "Layup";
    public static final String EVENT_DETAIL_SUB = "sub";
    public static final String EVENT_DETAIL_TIMEOUT_REGULAR = " timeout: regular";
    public static final String EVENT_DETAIL_OUT_OF_BOUNDS_LOST_BALL_TURNOVER = "out of bounds lost ball turnover";
    public static final String EVENT_DETAIL_DUNK = "Dunk";
    public static final String EVENT_DETAIL_L_B_FOUL = "l.b.foul";
    public static final String EVENT_DETAIL_TIMEOUT_OFFICIAL = "timeout: official";
    public static final String EVENT_DETAIL_BAD_PASS = "bad pass";
    public static final String EVENT_DETAIL_VIOLATION_KICKED_BALL = "violation:kicked ball";
    public static final String EVENT_DETAIL_TRAVELING = "traveling";
    public static final String EVENT_DETAIL_END_OF_PERIOD = "end of period";
    public static final String EVENT_DETAIL_FOUL = "foul";
    public static final String EVENT_DETAIL_FREE_THROW_TECHNICAL = "Free Throw Technical";



    // ---------------------------------------------------------------------------------------------------------
    // property
    private String currentTime = "";    // YYYYMMDDHHMMSS (20160920142233)
    private String gameId = ""; // HpGameManager id# : 0041500404
    private String season = ""; // Season that this data sets belongs to : 2016 Playoff
    private String gameDate = ""; // HpGameManager date : 2016-06-10
    private String teamHome = "";   // HomeTeam Name
    private String teamAway = "";   // AwayTeam Name
    private String playerHomeNumber1 = "";
    private String playerHomeName1 = "";
    private String playerHomeNumber2 = "";
    private String playerHomeName2 = "";
    private String playerHomeNumber3 = "";
    private String playerHomeName3 = "";
    private String playerHomeNumber4 = "";
    private String playerHomeName4 = "";
    private String playerHomeNumber5 = "";
    private String playerHomeName5 = "";
    private String playerAwayNumber1 = "";
    private String playerAwayName1 = "";
    private String playerAwayNumber2 = "";
    private String playerAwayName2 = "";
    private String playerAwayNumber3 = "";
    private String playerAwayName3 = "";
    private String playerAwayNumber4 = "";
    private String playerAwayName4 = "";
    private String playerAwayNumber5 = "";
    private String playerAwayName5 = "";
    private String period = ""; // Period of the game that the the event described in that row actually occurred : 1
    private String scoreAway = "";
    private String scoreHome = "";
    private String foulsAway = "";
    private String foulsHome = "";
    private String remainingTime = "";  // Remaining time in the period : 0:12:00
    private String shotclock ="";
    private String elapsedTime = "";    // Time passed since the period has started : 0:00:19
    @JsonIgnore private String elapsedTimeSec = "0";
    private String playLength = ""; // Duration of the event described in that row : 0:00:19
    @JsonIgnore private String playLengthSec = "0"; // Time passed since the the period has started : 00:00:11
    private String playId = "0"; // All events in a game has an id number : 0
    private String teamNameExecutedEvent = "";  // The team that has executed the described event : GSW
    private String eventType = "";  // Various types of events (fouls, freethrows, turnovers etc.) : start of period
    private String JumpBallAway = ""; // Player of away team who is participating in a jumpball
    private String JumpBallHome = ""; // Player of home team who is participating in a jumpball
    private String teamBlockedAShot = ""; // team who blocked a shot
    private String teamCheckIn = "";   // team who checks in the game
    private String teamCheckOut = ""; // team who checks out the game
    private String freeThrowsOrder = ""; // Freethrows in an order (first, second, third...) : 1
    private String teamFoul = ""; // Player who has drawn a foul
    private String teamWhistleGrabbed = ""; // team who grabbed the ball when whistle blowed
    private String freeThrowsCount = "";    // How many free throws are going to be shoot : 2
    private String teamExecutedEvent = ""; // Player who executed this event
    private String pointsScoredWithinEvent = "";    // Points scored within the event : 1
    private String teamGrabbedTheBallAfterEvent = ""; // team who grabbed the ball after an event
    private String moreDetailOfEvent = "";  // More details on how the event happened
    private String shotMadeOrMissed = "";   // Shot made or missed
    private String teamSteal = "";    // Player who steals the ball
    private String eventDetail = "";  // Type of the event : Jump Shot
    private String shotDistance = "";   // Shot distance (in terms of FEET)
    private String shotAxisX = "";  // X axis value of that shot
    private String shotAxisY = "";  // Y axis value of that shot
    private String eventDesc = "";  // Description of event : Jump Ball Thompson vs. Bogut: Tip to Curry

    public HpState clone() throws CloneNotSupportedException {
        HpState lState = (HpState) super.clone();

        return lState;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }

    public String getPlayerAwayName1() {
        return playerAwayName1;
    }

    public void setPlayerAwayName1(String playerAwayName1) {
        this.playerAwayName1 = playerAwayName1;
    }

    public String getPlayerAwayName2() {
        return playerAwayName2;
    }

    public void setPlayerAwayName2(String playerAwayName2) {
        this.playerAwayName2 = playerAwayName2;
    }

    public String getPlayerAwayName3() {
        return playerAwayName3;
    }

    public void setPlayerAwayName3(String playerAwayName3) {
        this.playerAwayName3 = playerAwayName3;
    }

    public String getPlayerAwayName4() {
        return playerAwayName4;
    }

    public void setPlayerAwayName4(String playerAwayName4) {
        this.playerAwayName4 = playerAwayName4;
    }

    public String getPlayerAwayName5() {
        return playerAwayName5;
    }

    public void setPlayerAwayName5(String playerAwayName5) {
        this.playerAwayName5 = playerAwayName5;
    }

    public String getPlayerHomeName1() {
        return playerHomeName1;
    }

    public void setPlayerHomeName1(String playerHomeName1) {
        this.playerHomeName1 = playerHomeName1;
    }

    public String getPlayerHomeName2() {
        return playerHomeName2;
    }

    public void setPlayerHomeName2(String playerHomeName2) {
        this.playerHomeName2 = playerHomeName2;
    }

    public String getPlayerHomeName3() {
        return playerHomeName3;
    }

    public void setPlayerHomeName3(String playerHomeName3) {
        this.playerHomeName3 = playerHomeName3;
    }

    public String getPlayerHomeName4() {
        return playerHomeName4;
    }

    public void setPlayerHomeName4(String playerHomeName4) {
        this.playerHomeName4 = playerHomeName4;
    }

    public String getPlayerHomeName5() {
        return playerHomeName5;
    }

    public void setPlayerHomeName5(String playerHomeName5) {
        this.playerHomeName5 = playerHomeName5;
    }


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(String scoreAway) {
        this.scoreAway = scoreAway;
    }

    public String getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(String scoreHome) {
        this.scoreHome = scoreHome;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getShotclock(){return shotclock;}

    public void setShotclock(String shotclock){this.shotclock = shotclock;}

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getPlayLength() {
        return playLength;
    }

    public void setPlayLength(String playLength) {
        this.playLength = playLength;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getTeamNameExecutedEvent() {
        return teamNameExecutedEvent;
    }

    public void setTeamNameExecutedEvent(String teamNameExecutedEvent) {
        this.teamNameExecutedEvent = teamNameExecutedEvent;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    public String getJumpBallAway() {
        return JumpBallAway;
    }

    public void setJumpBallAway(String JumpBallAway) {
        this.JumpBallAway = JumpBallAway;
    }

    public String getJumpBallHome() {
        return JumpBallHome;
    }

    public void setJumpBallHome(String JumpBallHome) {
        this.JumpBallHome = JumpBallHome;
    }

    public String getteamBlockedAShot() {
        return teamBlockedAShot;
    }

    public void setteamBlockedAShot(String teamBlockedAShot) {
        this.teamBlockedAShot = teamBlockedAShot;
    }

    public String getteamCheckIn() {
        return teamCheckIn;
    }

    public void setteamCheckIn(String teamCheckIn) {
        this.teamCheckIn = teamCheckIn;
    }

    public String getteamCheckOut() {
        return teamCheckOut;
    }

    public void setteamCheckOut(String teamCheckOut) {
        this.teamCheckOut = teamCheckOut;
    }

    public String getFreeThrowsOrder() {
        return freeThrowsOrder;
    }

    public void setFreeThrowsOrder(String freeThrowsOrder) {
        this.freeThrowsOrder = freeThrowsOrder;
    }

    public String getteamFoul() {
        return teamFoul;
    }

    public void setteamFoul(String teamFoul) {
        this.teamFoul = teamFoul;
    }

    public String getFreeThrowsCount() {
        return freeThrowsCount;
    }

    public void setFreeThrowsCount(String freeThrowsCount) {
        this.freeThrowsCount = freeThrowsCount;
    }

    public String getteamExecutedEvent() {
        return teamExecutedEvent;
    }

    public void setteamExecutedEvent(String teamExecutedEvent) {
        this.teamExecutedEvent = teamExecutedEvent;
    }

    public String getPointsScoredWithinEvent() {
        return pointsScoredWithinEvent;
    }

    public void setPointsScoredWithinEvent(String pointsScoredWithinEvent) {
        this.pointsScoredWithinEvent = pointsScoredWithinEvent;
    }

    public String getteamGrabbedTheBallAfterEvent() {
        return teamGrabbedTheBallAfterEvent;
    }

    public void setPlayerGrabbedTheBallAfterEvent(String teamGrabbedTheBallAfterEvent) {
        this.teamGrabbedTheBallAfterEvent = teamGrabbedTheBallAfterEvent;
    }

    public String getMoreDetailOfEvent() {
        return moreDetailOfEvent;
    }

    public void setMoreDetailOfEvent(String moreDetailOfEvent) {
        this.moreDetailOfEvent = moreDetailOfEvent;
    }

    public String getShotMadeOrMissed() {
        return shotMadeOrMissed;
    }

    public void setShotMadeOrMissed(String shotMadeOrMissed) {
        this.shotMadeOrMissed = shotMadeOrMissed;
    }

    public String getteamSteal() {
        return teamSteal;
    }

    public void setteamSteal(String teamSteal) {
        this.teamSteal = teamSteal;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    public String getShotDistance() {
        return shotDistance;
    }

    public void setShotDistance(String shotDistance) {
        this.shotDistance = shotDistance;
    }

    public String getShotAxisX() {
        return shotAxisX;
    }

    public void setShotAxisX(String shotAxisX) {
        this.shotAxisX = shotAxisX;
    }

    public String getShotAxisY() {
        return shotAxisY;
    }

    public void setShotAxisY(String shotAxisY) {
        this.shotAxisY = shotAxisY;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getPlayLengthSec() {
        return playLengthSec;
    }

    public void setPlayLengthSec(String playLengthSec) {
        this.playLengthSec = playLengthSec;
    }

    public String getElapsedTimeSec() {
        return elapsedTimeSec;
    }

    public void setElapsedTimeSec(String elapsedTimeSec) {
        this.elapsedTimeSec = elapsedTimeSec;
    }

    public String getPlayerHomeNumber1() {
        return playerHomeNumber1;
    }

    public void setPlayerHomeNumber1(String playerHomeNumber1) {
        this.playerHomeNumber1 = playerHomeNumber1;
    }

    public String getPlayerHomeNumber2() {
        return playerHomeNumber2;
    }

    public void setPlayerHomeNumber2(String playerHomeNumber2) {
        this.playerHomeNumber2 = playerHomeNumber2;
    }

    public String getPlayerHomeNumber3() {
        return playerHomeNumber3;
    }

    public void setPlayerHomeNumber3(String playerHomeNumber3) {
        this.playerHomeNumber3 = playerHomeNumber3;
    }

    public String getPlayerHomeNumber4() {
        return playerHomeNumber4;
    }

    public void setPlayerHomeNumber4(String playerHomeNumber4) {
        this.playerHomeNumber4 = playerHomeNumber4;
    }

    public String getPlayerHomeNumber5() {
        return playerHomeNumber5;
    }

    public void setPlayerHomeNumber5(String playerHomeNumber5) {
        this.playerHomeNumber5 = playerHomeNumber5;
    }

    public String getPlayerAwayNumber1() {
        return playerAwayNumber1;
    }

    public void setPlayerAwayNumber1(String playerAwayNumber1) {
        this.playerAwayNumber1 = playerAwayNumber1;
    }

    public String getPlayerAwayNumber2() {
        return playerAwayNumber2;
    }

    public void setPlayerAwayNumber2(String playerAwayNumber2) {
        this.playerAwayNumber2 = playerAwayNumber2;
    }

    public String getPlayerAwayNumber3() {
        return playerAwayNumber3;
    }

    public void setPlayerAwayNumber3(String playerAwayNumber3) {
        this.playerAwayNumber3 = playerAwayNumber3;
    }

    public String getPlayerAwayNumber4() {
        return playerAwayNumber4;
    }

    public void setPlayerAwayNumber4(String playerAwayNumber4) {
        this.playerAwayNumber4 = playerAwayNumber4;
    }

    public String getPlayerAwayNumber5() {
        return playerAwayNumber5;
    }

    public void setPlayerAwayNumber5(String playerAwayNumber5) {
        this.playerAwayNumber5 = playerAwayNumber5;
    }


    public String getFoulsAway() {
        return foulsAway;
    }

    public void setFoulsAway(String foulsAway) {
        this.foulsAway = foulsAway;
    }

    public String getFoulsHome() {
        return foulsHome;
    }

    public void setFoulsHome(String foulsHome) {
        this.foulsHome = foulsHome;
    }

    public String getteamWhistleGrabbed() {return teamWhistleGrabbed;}

    public void setteamWhistleGrabbed(String teamWhistleGrabbed){ this.teamWhistleGrabbed = teamWhistleGrabbed;}
}

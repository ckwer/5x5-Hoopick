package com.hoopick.hoopicktest.data.model;

import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by pro on 2016-11-29.
 */
public class HpStateBluetooth {

    private String teamHome = "";   // HomeTeam Name
    private String teamAway = "";   // AwayTeam Name
    private String scoreAway = "";
    private String scoreHome = "";
    private String foulsAway = "";
    private String foulsHome = "";
    private String remainingTime = "";  // Remaining time in the period : 0:12:00


    public HpStateBluetooth () {
    }

    public HpStateBluetooth(HpState aState) {
        setTeamHome(aState.getTeamHome());
        setTeamAway(aState.getTeamAway());

        setScoreHome(aState.getScoreHome());
        setScoreAway(aState.getScoreAway());
        setFoulsHome(aState.getFoulsHome());
        setFoulsAway(aState.getFoulsAway());

        setRemainingTime(aState.getRemainingTime());
    }

    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
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

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
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

    public String getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }
}

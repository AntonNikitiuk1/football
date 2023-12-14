package com.example.football;

public class Result {
    private String homeTeam;
    private String awayTeam;
    private int homeGoals;
    private int awayGoals;
    private String date;

    public Result(String homeTeam, String awayTeam, int homeGoals, int awayGoals, String date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.date = date;
    }

    public String getDetails() {
        return homeTeam + " - " + homeGoals + " : " + awayGoals + " - " + awayTeam + " - " + date;
    }
}

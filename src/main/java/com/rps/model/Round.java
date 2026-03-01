package com.rps.model;
import java.sql.Timestamp;
public class Round {
    private int id, matchId;
    private String playerMove, computerMove, result;
    private Timestamp playedAt;
    public int getId() { return id; }
    public void setId(int id) { this.id=id; }
    public int getMatchId() { return matchId; }
    public void setMatchId(int m) { this.matchId=m; }
    public String getPlayerMove() { return playerMove; }
    public void setPlayerMove(String m) { this.playerMove=m; }
    public String getComputerMove() { return computerMove; }
    public void setComputerMove(String m) { this.computerMove=m; }
    public String getResult() { return result; }
    public void setResult(String r) { this.result=r; }
    public Timestamp getPlayedAt() { return playedAt; }
    public void setPlayedAt(Timestamp t) { this.playedAt=t; }
}
package com.rps.model;
import java.sql.Timestamp;
public class Match {
    private int id, userId;
    private Timestamp playedAt;
    public int getId() { return id; }
    public void setId(int id) { this.id=id; }
    public int getUserId() { return userId; }
    public void setUserId(int u) { this.userId=u; }
    public Timestamp getPlayedAt() { return playedAt; }
    public void setPlayedAt(Timestamp t) { this.playedAt=t; }
}
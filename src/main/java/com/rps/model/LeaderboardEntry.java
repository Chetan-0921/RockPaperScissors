package com.rps.model;
public class LeaderboardEntry {
    private int rank, totalRounds, wins, losses, ties;
    private String username;
    private double winPct;
    public int getRank() { return rank; }
    public void setRank(int r) { this.rank=r; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username=u; }
    public int getTotalRounds() { return totalRounds; }
    public void setTotalRounds(int t) { this.totalRounds=t; }
    public int getWins() { return wins; }
    public void setWins(int w) { this.wins=w; }
    public int getLosses() { return losses; }
    public void setLosses(int l) { this.losses=l; }
    public int getTies() { return ties; }
    public void setTies(int t) { this.ties=t; }
    public double getWinPct() { return winPct; }
    public void setWinPct(double p) { this.winPct=p; }
}
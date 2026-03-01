package com.rps.dao;
import com.rps.model.LeaderboardEntry;
import com.rps.util.DBConnection;
import java.sql.*;
import java.util.*;

public class LeaderboardDAO {
    public List<LeaderboardEntry> getTop20() throws Exception {
        String sql="SELECT * FROM leaderboard LIMIT 20";
        Connection con=null; Statement st=null; ResultSet rs=null;
        List<LeaderboardEntry> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection(); st=con.createStatement(); rs=st.executeQuery(sql);
            int rank=1;
            while(rs.next()){
                LeaderboardEntry e=new LeaderboardEntry();
                e.setRank(rank++); e.setUsername(rs.getString("username"));
                e.setTotalRounds(rs.getInt("total_rounds")); e.setWins(rs.getInt("wins"));
                e.setLosses(rs.getInt("losses")); e.setTies(rs.getInt("ties"));
                e.setWinPct(rs.getDouble("win_pct")); list.add(e);
            } return list;
        } finally { DBConnection.close(rs,st,con); }
    }
}
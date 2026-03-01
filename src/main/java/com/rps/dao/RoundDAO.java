package com.rps.dao;
import com.rps.model.Round;
import com.rps.util.DBConnection;
import java.sql.*;
import java.util.*;

public class RoundDAO {
    public void saveRound(Round round) throws Exception {
        String sql="INSERT INTO rounds(match_id,player_move,computer_move,result) VALUES(?,?,?,?)";
        Connection con=null; PreparedStatement ps=null;
        try {
            con=DBConnection.getConnection(); ps=con.prepareStatement(sql);
            ps.setInt(1,round.getMatchId()); ps.setString(2,round.getPlayerMove());
            ps.setString(3,round.getComputerMove()); ps.setString(4,round.getResult());
            ps.executeUpdate();
        } finally { DBConnection.close(ps,con); }
    }
    public List<Round> getRoundsByMatch(int matchId) throws Exception {
        String sql="SELECT * FROM rounds WHERE match_id=? ORDER BY played_at ASC";
        Connection con=null; PreparedStatement ps=null; ResultSet rs=null;
        List<Round> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection(); ps=con.prepareStatement(sql);
            ps.setInt(1,matchId); rs=ps.executeQuery();
            while(rs.next()){
                Round r=new Round(); r.setId(rs.getInt("id")); r.setMatchId(rs.getInt("match_id"));
                r.setPlayerMove(rs.getString("player_move")); r.setComputerMove(rs.getString("computer_move"));
                r.setResult(rs.getString("result")); r.setPlayedAt(rs.getTimestamp("played_at"));
                list.add(r);
            } return list;
        } finally { DBConnection.close(rs,ps,con); }
    }
    public List<Round> getAllRoundsByUser(int userId) throws Exception {
        String sql="SELECT r.* FROM rounds r JOIN matches m ON r.match_id=m.id WHERE m.user_id=?";
        Connection con=null; PreparedStatement ps=null; ResultSet rs=null;
        List<Round> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection(); ps=con.prepareStatement(sql);
            ps.setInt(1,userId); rs=ps.executeQuery();
            while(rs.next()){
                Round r=new Round(); r.setId(rs.getInt("id")); r.setMatchId(rs.getInt("match_id"));
                r.setPlayerMove(rs.getString("player_move")); r.setComputerMove(rs.getString("computer_move"));
                r.setResult(rs.getString("result")); r.setPlayedAt(rs.getTimestamp("played_at"));
                list.add(r);
            } return list;
        } finally { DBConnection.close(rs,ps,con); }
    }
}
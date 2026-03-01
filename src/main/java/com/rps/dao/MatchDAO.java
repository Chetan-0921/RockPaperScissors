package com.rps.dao;
import com.rps.model.Match;
import com.rps.util.DBConnection;
import java.sql.*;
import java.util.*;

public class MatchDAO {
    public int createMatch(int userId) throws Exception {
        String sql="INSERT INTO matches(user_id) VALUES(?)";
        Connection con=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            con=DBConnection.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,userId); ps.executeUpdate();
            rs=ps.getGeneratedKeys(); if(rs.next()) return rs.getInt(1);
            throw new Exception("Match creation failed");
        } finally { DBConnection.close(rs,ps,con); }
    }
    public List<Match> getMatchesByUser(int userId) throws Exception {
        String sql="SELECT * FROM matches WHERE user_id=? ORDER BY played_at DESC";
        Connection con=null; PreparedStatement ps=null; ResultSet rs=null;
        List<Match> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection(); ps=con.prepareStatement(sql);
            ps.setInt(1,userId); rs=ps.executeQuery();
            while(rs.next()){
                Match m=new Match(); m.setId(rs.getInt("id"));
                m.setUserId(rs.getInt("user_id")); m.setPlayedAt(rs.getTimestamp("played_at"));
                list.add(m);
            } return list;
        } finally { DBConnection.close(rs,ps,con); }
    }
}
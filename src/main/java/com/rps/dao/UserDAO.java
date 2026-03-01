package com.rps.dao;
import com.rps.model.User;
import com.rps.util.*;
import java.sql.*;

public class UserDAO {
    public boolean register(User user) throws Exception {
        String sql = "INSERT INTO users (username,password,email) VALUES(?,?,?)";
        Connection con=null; PreparedStatement ps=null;
        try {
            con=DBConnection.getConnection(); ps=con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,PasswordUtil.hash(user.getPassword()));
            ps.setString(3,user.getEmail());
            ps.executeUpdate(); return true;
        } catch(SQLIntegrityConstraintViolationException e){ return false; }
        finally { DBConnection.close(ps,con); }
    }
    public User login(String username, String password) throws Exception {
        String sql="SELECT id,username,email FROM users WHERE username=? AND password=?";
        Connection con=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            con=DBConnection.getConnection(); ps=con.prepareStatement(sql);
            ps.setString(1,username); ps.setString(2,PasswordUtil.hash(password));
            rs=ps.executeQuery();
            if(rs.next()) return new User(rs.getInt("id"),rs.getString("username"),rs.getString("email"));
            return null;
        } finally { DBConnection.close(rs,ps,con); }
    }
}
package com.rps.servlet;
import com.rps.dao.LeaderboardDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("user")==null){
            resp.sendRedirect(req.getContextPath()+"/login"); return;
        }
        try {
            req.setAttribute("entries",new LeaderboardDAO().getTop20());
            req.getRequestDispatcher("/WEB-INF/views/leaderboard.jsp").forward(req,resp);
        } catch(Exception e){ throw new ServletException(e); }
    }
}
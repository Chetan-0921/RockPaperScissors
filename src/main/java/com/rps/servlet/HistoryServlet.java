package com.rps.servlet;
import com.rps.dao.*;
import com.rps.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("user")==null){
            resp.sendRedirect(req.getContextPath()+"/login"); return;
        }
        User user=(User)session.getAttribute("user");
        try {
            List<Match> matches=new MatchDAO().getMatchesByUser(user.getId());
            req.setAttribute("matches",matches);
            String mid=req.getParameter("matchId");
            if(mid!=null){
                List<Round> rounds=new RoundDAO().getRoundsByMatch(Integer.parseInt(mid));
                req.setAttribute("rounds",rounds); req.setAttribute("matchId",Integer.parseInt(mid));
            }
            req.getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req,resp);
        } catch(Exception e){ throw new ServletException(e); }
    }
}
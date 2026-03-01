package com.rps.servlet;
import com.rps.dao.RoundDAO;
import com.rps.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("user")==null){
            resp.sendRedirect(req.getContextPath()+"/login"); return;
        }
        User user=(User)session.getAttribute("user");
        try {
            List<Round> rounds=new RoundDAO().getAllRoundsByUser(user.getId());
            int wins=0,losses=0,ties=0;
            Map<String,Integer> moves=new LinkedHashMap<>();
            moves.put("Rock",0); moves.put("Paper",0); moves.put("Scissors",0);
            for(Round r:rounds){
                if("Win".equals(r.getResult())) wins++;
                else if("Loss".equals(r.getResult())) losses++; else ties++;
                moves.merge(r.getPlayerMove(),1,Integer::sum);
            }
            int total=rounds.size();
            req.setAttribute("totalRounds",total); req.setAttribute("totalWins",wins);
            req.setAttribute("totalLosses",losses); req.setAttribute("totalTies",ties);
            req.setAttribute("winPct",total>0?String.format("%.1f",wins*100.0/total):"0.0");
            req.setAttribute("moveCounts",moves);
            req.getRequestDispatcher("/WEB-INF/views/stats.jsp").forward(req,resp);
        } catch(Exception e){ throw new ServletException(e); }
    }
}
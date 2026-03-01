package com.rps.servlet;
import com.rps.dao.*;
import com.rps.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static final String[] MOVES={"Rock","Paper","Scissors"};
    private final Random rng=new Random();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("user")==null){
            resp.sendRedirect(req.getContextPath()+"/login"); return;
        }
        try {
            User user=(User)session.getAttribute("user");
            int matchId=new MatchDAO().createMatch(user.getId());
            session.setAttribute("matchId",matchId);
            session.setAttribute("mWins",0); session.setAttribute("mLosses",0); session.setAttribute("mTies",0);
        } catch(Exception e){ throw new ServletException(e); }
        req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("user")==null){
            resp.sendRedirect(req.getContextPath()+"/login"); return;
        }
        String playerMove=req.getParameter("move");
        String computerMove=MOVES[rng.nextInt(3)];
        String result=decide(playerMove,computerMove);

        int wins=(int)session.getAttribute("mWins");
        int losses=(int)session.getAttribute("mLosses");
        int ties=(int)session.getAttribute("mTies");
        if("Win".equals(result)) wins++; else if("Loss".equals(result)) losses++; else ties++;
        session.setAttribute("mWins",wins); session.setAttribute("mLosses",losses); session.setAttribute("mTies",ties);

        try {
            Round round=new Round();
            round.setMatchId((int)session.getAttribute("matchId"));
            round.setPlayerMove(playerMove); round.setComputerMove(computerMove); round.setResult(result);
            new RoundDAO().saveRound(round);
        } catch(Exception e){ throw new ServletException(e); }

        req.setAttribute("playerMove",playerMove); req.setAttribute("computerMove",computerMove);
        req.setAttribute("result",result); req.setAttribute("mWins",wins);
        req.setAttribute("mLosses",losses); req.setAttribute("mTies",ties);
        req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req,resp);
    }

    private String decide(String p, String c){
        if(p.equals(c)) return "Tie";
        if(("Rock".equals(p)&&"Scissors".equals(c))||("Paper".equals(p)&&"Rock".equals(c))||("Scissors".equals(p)&&"Paper".equals(c))) return "Win";
        return "Loss";
    }
}
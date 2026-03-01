package com.rps.servlet;
import com.rps.dao.UserDAO;
import com.rps.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        try {
            User user=new UserDAO().login(username,password);
            if(user!=null){
                req.getSession().setAttribute("user",user);
                resp.sendRedirect(req.getContextPath()+"/game");
            } else {
                req.setAttribute("error","Invalid username or password.");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            }
        } catch(Exception e){ throw new ServletException(e); }
    }
}
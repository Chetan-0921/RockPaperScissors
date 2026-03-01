package com.rps.servlet;
import com.rps.dao.UserDAO;
import com.rps.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username=req.getParameter("username").trim();
        String email=req.getParameter("email").trim();
        String password=req.getParameter("password");
        String confirm=req.getParameter("confirm");
        if(!password.equals(confirm)){
            req.setAttribute("error","Passwords do not match.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp); return;
        }
        User user=new User(); user.setUsername(username); user.setEmail(email); user.setPassword(password);
        try {
            boolean ok=new UserDAO().register(user);
            if(ok){
                req.setAttribute("success","Account created! Please log in.");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            } else {
                req.setAttribute("error","Username or email already taken.");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp);
            }
        } catch(Exception e){ throw new ServletException(e); }
    }
}
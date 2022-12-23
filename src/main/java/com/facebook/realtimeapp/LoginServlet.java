package com.facebook.realtimeapp;

import com.facebook.dao.UserDao;
import com.facebook.dao.dto.impl.UserDaoImpl;
import com.facebook.util.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private  UserDao userDao= new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
        //response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username= request.getParameter("username");
        String password= request.getParameter("password");

        User validatedUser= userDao.authenticateUser(username,password);
        HttpSession ses = request.getSession();

        if(validatedUser.getId()>0){
            //VALIDATE USER
            ses.setAttribute("userID",validatedUser.getId());
            ses.setAttribute("fullName",validatedUser.getName());
            ses.setAttribute("email",validatedUser.getEmail());
            ses.setAttribute("isLoggedIn",1);

           ses.setAttribute("errorMessage",null);

            response.sendRedirect("/index.jsp");
            System.out.println(validatedUser.getId()+" Email "+validatedUser.getEmail());

        }
        else{
            ses.setAttribute("errorMessage","Wrong username or password");
            response.sendRedirect("/login.jsp");
        }

    }
}

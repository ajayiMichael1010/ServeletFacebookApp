package com.facebook.realtimeapp;
import com.facebook.dao.UserDao;
import com.facebook.dao.dto.impl.UserDaoImpl;
import com.facebook.util.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static UserDao userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signup.js");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String username= request.getParameter("username");
        String password = request.getParameter("password");
        User user= userDao.saveUser(name,email,gender,username,password, LocalDateTime.now());
        request.getRequestDispatcher("/login.jsp").forward(request,response);

        //response.sendRedirect("/login.jsp");

        System.out.println("Full Name"+ request.getParameter("name"));
    }
}

package com.as.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.as.dao2.UserDao;
import com.as.dao2.UserDaoImp;
import com.as.service.*;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
        description = "This will handle login",
        urlPatterns = { "/login" },
        initParams = {
                @WebInitParam(name = "key", value = "mykey")
        })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginService loginService =new LoginServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        UserDao usd = new UserDaoImp();
        if(usd.getUserDetails(username,password)){
            HttpSession session = request.getSession(); //get session else create new
//			request.getSession(false); //get session else give null
            session.setAttribute("username",  username);
            session.setAttribute("logintime", new Date());
            session.setAttribute("ipaddress", request.getRemoteAddr());

            request.setAttribute("username", username);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("DataFetcher");
            requestDispatcher.forward(request, response);
        }
//        if(loginService.checkLogin(username, password))
//        {
//            HttpSession session = request.getSession(); //get session else create new
////			request.getSession(false); //get session else give null
//            session.setAttribute("username",  username);
//            session.setAttribute("logintime", new Date());
//            session.setAttribute("ipaddress", request.getRemoteAddr());
//
//            request.setAttribute("username", username);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("DataFetcher");
//            requestDispatcher.forward(request, response);
//        }
        else {
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//			requestDispatcher.forward(request, response);
            response.sendRedirect("index.jsp");
        }
    }

}

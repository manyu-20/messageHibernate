package com.as.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.as.dao.MessagesReadDaoImpl;
import com.as.dao2.MessagesDao;
import com.as.dao2.MessagesDaoImp;
//import com.as.jdbc.DbConnection;
import com.as.service.CalculateLimit;


@WebServlet("/DataFetcher")
public class DataFetcher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MessagesReadDaoImpl messagesReadDao = new MessagesReadDaoImpl();
    private CalculateLimit calculateLimitObj = new CalculateLimit();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int limitInbox = 1;
        int limitOutbox = 1;
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        if(session == null || session.getAttribute("username") == null)
        {
            //forward response.redirect
            response.sendRedirect("index.jsp");
            return;
        }
        //get data from db for username

//        if(request.getParameter("pgNoInbox") == null) {
//            request.setAttribute("pgNoInbox", 1);
//            limitInbox = calculateLimitObj.calculateLimit((int) request.getAttribute("pgNoInbox"));
//        } else {
//            limitInbox = calculateLimitObj.calculateLimit(Integer.parseInt(request.getParameter("pgNoInbox")));
//        }
//
//        if(request.getParameter("pgNoOutbox") == null) {
//            request.setAttribute("pgNoOutbox", 1);
//            limitOutbox = calculateLimitObj.calculateLimit((int) request.getAttribute("pgNoOutbox"));
//        } else {
//            limitOutbox = calculateLimitObj.calculateLimit(Integer.parseInt(request.getParameter("pgNoOutbox")));
//        }
//
//        request.setAttribute("pgNoInbox", (limitInbox / 5) + 1);
//        request.setAttribute("pgNoOutbox", (limitOutbox / 5) + 1);

        session.setAttribute("username", username);
        System.out.println("initiating and calling msdimp");
        MessagesDao msd = new MessagesDaoImp();
        if(request.getParameter("pgNoInbox") == null || request.getParameter("pgNoOutbox") == null){
            System.out.println("first time ?");
            request.setAttribute("pgNoInbox", 1);
            request.setAttribute("pgNoOutbox", 1);
            request.setAttribute("inbox", msd.InboxMessages(username,1));
            request.setAttribute("outbox", msd.OutboxMessages(username,1));
        }
        else{
            System.out.println("2nd timE?");
            limitInbox = Integer.parseInt(request.getParameter("pgNoInbox"));
            limitOutbox = Integer.parseInt(request.getParameter("pgNoOutbox"));
            System.out.println("limit inbox -> " + limitInbox);
            System.out.println("limit outbox -> " + limitOutbox);
            request.setAttribute("inbox", msd.InboxMessages(username,limitInbox));
            request.setAttribute("outbox", msd.OutboxMessages(username,limitOutbox));
            request.setAttribute("pgNoInbox", (limitInbox));
            request.setAttribute("pgNoOutbox", (limitOutbox));
        }



//        DbConnection.closeConnection();
        System.out.println("printing messages!");
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

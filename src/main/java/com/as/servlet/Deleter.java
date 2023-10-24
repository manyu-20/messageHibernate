package com.as.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.as.dao.MessagesDeleteDaoImpl;
import com.as.dao2.MessagesDao;
import com.as.dao2.MessagesDaoImp;
//import com.as.jdbc.DbConnection;

/**
 * Servlet implementation class Deleter
 */
@WebServlet("/Deleter")
public class Deleter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MessagesDeleteDaoImpl messagesDeleteDao = new MessagesDeleteDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int pk = Integer.parseInt(request.getParameter("id"));
        if(session == null || session.getAttribute("username") == null)
        {
            //forward response.redirect
            response.sendRedirect("index.jsp");
            return;
        }
        MessagesDao msd = new MessagesDaoImp();
        msd.deleteMessage(pk);
//        DbConnection.closeConnection();

        RequestDispatcher rd = request.getRequestDispatcher("DataFetcher");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

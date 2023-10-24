package com.as.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.as.dao.MessagesWriteDaoImpl;
import com.as.dao2.MessagesDao;
import com.as.dao2.MessagesDaoImp;
import com.as.hiber.Messages;
import com.as.model.MessagesOld;

/**
 * Servlet implementation class DataPost
 */
@WebServlet("/DataPost")
public class DataPost extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MessagesWriteDaoImpl messagesWriteDao = new MessagesWriteDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        if(session == null || session.getAttribute("username") == null)
        {
            //forward response.redirect
            response.sendRedirect("index.jsp");
            return;
        }
        //get data from db for username
        MessagesDao msd = new MessagesDaoImp();

        java.util.Date date = new Date();
        Messages message = new Messages(username, request.getParameter("touser"), request.getParameter("message"), new java.sql.Timestamp(date.getTime()));
//        messagesWriteDao.insert(message);
        msd.writeMessages(message);

        RequestDispatcher rd = request.getRequestDispatcher("DataFetcher");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

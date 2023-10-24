<%--
  Created by IntelliJ IDEA.
  User: abhimanyusaini
  Date: 22/10/23
  Time: 3:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.as.model.MessagesOld"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="com.as.hiber.Messages" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Messages</title>
    <style type="text/css">
        tr,th,td {
            border: 1px solid;
            border-collapse: collapse;
            padding : 8px;
            text-align: center;
        }

        table{
            border: 1px solid;
            border-collapse: collapse;
            padding : 8px;
            text-align: center;
            width: 100%;
        }

        .headingMessage{
            text-align : center;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .row{
            display: grid;
            grid-template-columns: repeat(6, 1fr);
        }

        .item {
            grid-column: 3 / 5;

        }

        input{
            margin-top : 10px;
            width: 100%;
        }

        .submitButton{
            padding : 8px;
            width: 30%;
            margin-left : 140px;
            margin-top : 40px;
        }

        .myGrid{
            display: grid;
            grid-template-columns: repeat(12, 1fr);
        }
        .boxItem{
            grid-column: 4 / span 6;
        }
        .navHead{
            display: flex;
            justify-content : space-between;
        }

        .container2{
            padding : 20px;
            display: flex;
            justify-content: space-around;
        }
        button{
            padding: 8px;
        }

    </style>
</head>
<body>
<%String user = "" + session.getAttribute("username");
%>
<div class="navHead">
    <h1>Welcome to Messaging System :) <%= user %></h1>
    <p><a href="logout"><button>Logout</button></a></p>
</div>



<div class="row">
    <div class="item">
        <form action="DataPost" method="post">
            <h3>Recipient Name</h3>
            <input type="text" name="touser" /> <br>
            <h3>Enter Message</h3>
            <input type="text" name="message" /> <br>
            <input class="submitButton" type="submit" value="Send Message"/>
        </form>
    </div>
</div>


<div class="headingMessage">
    <h2>Outbox</h2>
</div>
<div class="myGrid">
    <div class="boxItem">
        <table>

            <tr>
                <th>Sr No.</th>
                <th>Receiver</th>
                <th>Message</th>
                <th>Time</th>
            </tr>
            <% List<Messages> std = (List<Messages>) request.getAttribute("outbox");
                int srNO = 1;
                int sizeOutboxList = (std != null) ? std.size() : 0; // Add a null check here
                if (std != null) {
                    for (Messages msg : std) { %>
            <%-- Arranging data in tabular form --%>
            <tr>
                <td><%= srNO %></td>
                <td><%= msg.getToUser() %></td>
                <td><%= msg.getMessage() %></td>
                <td><%= msg.getLogAsString() %></td>
                <td> <a href="Deleter?id=<%= msg.getPk() %>"> <button>Delete</button> </a> </td>
            </tr>
            <% srNO++;
            }
            } %>






        </table>
        <%  int pgNoOutbox = (int)request.getAttribute("pgNoOutbox");
            int noChangeInbox = (int)request.getAttribute("pgNoInbox");
            int next = pgNoOutbox + 1;
            int prev = pgNoOutbox - 1;
        %>
        <div class="container2">
            <% if(prev != 0){ %>
            <a href="DataFetcher?pgNoOutbox=<%=prev%>&pgNoInbox=<%=noChangeInbox%>"> <button>Prev</button> </a>
            <% } %>
            <% if(sizeOutboxList == 5){ %>
            <a href="DataFetcher?pgNoOutbox=<%=next%>&pgNoInbox=<%=noChangeInbox%>"> <button>Next</button> </a>
            <% } %>


        </div>
    </div>
</div>

<br>
<br>




<div class="headingMessage">
    <h2>Inbox</h2>
</div>
<div class="myGrid">
    <div class="boxItem">
        <table>

            <tr>
                <th>Sr No.</th>
                <th>Sender</th>
                <th>Message</th>
                <th>Time</th>
            </tr>
            <% List<Messages> inboxList = (List<Messages>) request.getAttribute("inbox");
                int srNoInbox = 1;
                int sizeInboxList = (inboxList != null) ? inboxList.size() : 0; // Add a null check here
                if (inboxList != null) {
                    for (Messages msg : inboxList) { %>
            <%-- Arranging data in tabular form --%>
            <tr>
                <td><%= srNoInbox %></td>
                <td><%= msg.getFromUser() %></td>
                <td><%= msg.getMessage() %></td>
                <td><%= msg.getLogAsString() %></td>
                <td> <a href="Deleter?id=<%= msg.getPk() %>"> <button>Delete</button> </a> </td>
            </tr>
            <% srNoInbox++;
            }
            } %>

        </table>
        <%int pgNoInbox = (int)request.getAttribute("pgNoInbox");
            int noChangeOutbox = (int)request.getAttribute("pgNoOutbox");
            int nextI = pgNoInbox + 1;
            int prevI = pgNoInbox - 1;
        %>
        <div class="container2">
            <% if(prevI != 0){ %>
            <a href="DataFetcher?pgNoInbox=<%=prevI%>&pgNoOutbox=<%=noChangeOutbox%>"> <button>Prev</button> </a>
            <% } %>
            <% if(sizeInboxList == 5){ %>
            <a href="DataFetcher?pgNoInbox=<%=nextI%>&pgNoOutbox=<%=noChangeOutbox%>"> <button>Next</button> </a>
            <% } %>


        </div>
    </div>
</div>

<br>
<br>

<div class="headingMessage">
</div>


</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Messages Project</title>
    <style>
        .mygrid{
            display: grid;
            grid-template-columns: repeat(12, 1fr);
        }
        .item{
            grid-column: 5 / 9 ;
        }
        input{
            padding: 5px;
            margin : 20px;
        }
        .myForm{

        }
        .headingMessage{
            text-align : center;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        form{
            border: 1px solid #2d2d2d;
        }
        .inputBox{
            width : 86%;
        }

    </style>
</head>
<body>

<div class="headingMessage">
    <h1>Login Service</h1>
</div>

<div class="mygrid">
    <div class="item">

        <p>
            <% if (request.getParameter("msg") != null)
            %>/*
            out.println("Please Login to Continue");
            */

        </p>
        <form action="login" method="post">
            <input class="inputBox" type="text" name="username" /> <br>
            <input class="inputBox" type="password" name="password" /> <br>
            <input type="submit" value="Login"/>
        </form>
    </div>
</div>

</body>
</html>
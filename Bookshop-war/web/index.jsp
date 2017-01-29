<%-- 
    Document   : index
    Created on : 20.11.2016, 23:01:55
    Author     : irina
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>BookShop Web Application</title>
    </head>
    <body>
        <h1>Welcome to BookShop!</h1>
        <a href="addBookToShop.jsp">Add new book </a>
        
        <!-- <a href="viewCart.jsp">View cart </a> -->
        <!-- Форма для запроса страницы books.jsp -->
        <form method="get" action="books.jsp">
            <p><input type ="submit" name ="enterButton"
                      value ="Click here to enter store" /></p>
        </form>
    </body>
</html>

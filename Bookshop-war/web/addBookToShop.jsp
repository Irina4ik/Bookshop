<%-- 
    Document   : add
    Created on : 20.11.2016, 22:33:49
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
        <h1>Add new record to the book shop</h1>
        <form action="doAddBookToShop.jsp" method="POST">
            <p>Author: <input type="text" name="author" value="" /></p>
            <p>Title: <input type="text" name="title" value="" /></p>
            <p>Price: <input type="text" name="price" value="" /></p>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>

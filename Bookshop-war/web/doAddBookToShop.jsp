<%-- 
    Document   : doAddBookToShop
    Created on : 20.11.2016, 22:35:27
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
        <%@page import="javax.naming.*, bookshop.*"%>
        <%@page errorPage="error.jsp"%>
        <%!
            BookShopSessionBeanRemote ejbRef;
        %>
        
        <%
            InitialContext ic = new InitialContext();
            ejbRef = (BookShopSessionBeanRemote) ic.lookup("bookshop.BookShopSessionBeanRemote");
            
            ejbRef.addBookToShop(request.getParameter("author"),
                                 request.getParameter("title"), 
                                 Double.valueOf(request.getParameter("price")));
        %>
        <h1>Record successfully added!</h1>
        <a href="index.jsp">Return</a>
    </body>
</html>

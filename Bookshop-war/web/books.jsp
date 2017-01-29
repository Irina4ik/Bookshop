<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "https://www.w3.org/TR/2002/REC-xhtml1-20020801/DTD/xhtml1-strict.dtd">

<%-- http://w3c.org/TR/xhtml1/DTD/xthml1-strict.dtd 
    Document   : books
    Created on : 15.11.2016, 23:13:04
    Author     : irina
--%>
<%-- param JSP-page --%>
<%@
    page language = "java"
    import = "javax.naming.*, bookshop.*, java.util.*"
    session="true"
%>
<html xmlns = "http://www.w3.org/1999/xhtml">
    
    <head>
        <title>Book List</title>
    </head>
        
    <body>
        <%@page errorPage="error.jsp"%>
        <h1>Available Books</h1>
        <p>Click a link to view book information</p>
        
        <p>
            
            <%-- begin JSP to create list book --%>
        
        <%!
            BookShopSessionBeanRemote ejbRef;
        %>
        
        <%
            InitialContext ic = new InitialContext();
            ejbRef = (BookShopSessionBeanRemote) ic.lookup("bookshop.BookShopSessionBeanRemote");
            
            List book = ejbRef.getAllBooks();
            Book currentBook;
            
            // save titles
            session.setAttribute("book", book);
                
            // get iterator for List
            Iterator iterator = book.iterator();
                
            // get BookBean and
            // create link
            while (iterator.hasNext()) {
                currentBook = (Book) iterator.next();
            %> <%-- end JSP for insert XHTML --%>
               <%-- and expr JSP --%>
               
               <%-- link info book --%>
               <span>
                   <a href=
                      "displayBook?title=<%= currentBook.getTitle() %>">
                       
                       <%= currentBook.getAuthor() + ", " +
                           currentBook.getTitle() + ", " +
                           currentBook.getPrice() + "$" %>
                   </a>
               </span><br />
               
               <% // continue JSP
                   
                } // end while

               %> <%-- end JSP --%>
        </p>
        <p><a href="viewCart.jsp">View cart </a></p>
    </body>
</html>
<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://w3c.org/TR/xhtml1/DTD/xthml1-strict.dtd">
<%-- 
    Document   : viewCart
    Created on : 15.11.2016, 23:45:59
    Author     : irina
--%>

<%-- param JSP --%>
<%@page language = "java" session="true"%>
<%@page import="bookshop.*"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>

<html xmlns = "http://www.w3c.org/1999/xhtml">

    <head>
        <title>Shopping Cart</title>
        
    </head>
    
    <body>
         <h1>Shopping Cart</h1>
        
        <%-- begin JSP for view cart --%>
        <%
            Map cart = (Map) session.getAttribute("cart");
            double total = 0;
            
            if(cart == null || cart.size() == 0)
                out.println("<p>Shopping cart is currently empty.</p>");
            else {
                // create variable for view cart
                Set cartItems = cart.keySet();
                Iterator iterator = cartItems.iterator();
                
                Book book;
                CartItem cartItem;
                
                int quantity;
                double price, subtotal; //
                %> <%-- end JSP for output literal XHTML --%>
                
                <table>
                    <thead><tr>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr></thead>
                    <% // continue JSP
                        
                        while(iterator.hasNext()) {
                            
                            cartItem = (CartItem) cart.get(iterator.next());
                            book = cartItem.getBook();
                            quantity = cartItem.getQuantity();
                            price = book.getPrice();
                            subtotal = quantity * price;
                            total += subtotal;
                            
                    
                    %> <%-- end JSP for output XHTML-code and expr JSP in while --%>
                    
                    <%-- display string table with title book, quantity, price, subtotal --%>
                    <tr>
                        <td><%= book.getTitle() %></td>
                        
                        <td>
                            <form action="UpdateCart" method="post">
                                <input type="text" size="2"
                                       name ="<%=book.getTitle()%>"
                                       value="<%=quantity%>" />
                                <input type = "submit" value="Update" />
                            </form>
                        </td>
                        
                        <td>
                            <%=
                                new DecimalFormat("0.00").format(price)
                            %>
                        </td>
                        
                        <td class ="bold right">
                            <%=
                                new DecimalFormat("0.00").format(subtotal)
                                
                            %>
                        </td>


                        <td align = "center">
                            <form action="RemoveFromCart" method="post">
                                <input name ="title" type="hidden"
                                       value="<%=book.getTitle()%>" />
                                <input type = "submit" value="Remove" />
                            </form>
                        </td>
                    </tr>
                    
                    <% //continue JSP
                        } //end while
                    %> <%-- end JSP for literal XHTML and display string table with total cost --%>
                    <tr>
                        <td colspan="4" class="bold right">Total:
                            <%= new DecimalFormat("0.00").format(total)%>
                        </td>
                    </tr>
                </table>
                <% // continue JSP
                    
                    // wrire current cost in attribute session
                    session.setAttribute("total", new Double(total));
            } // end else
                %> <%-- end JSP --%>
                
                <!-- link books.jsp and continue buy -->
                <p class = "bold green">
                    <a href ="index.jsp">Start page</a>
                </p>                
                
                <!-- form for calculate total cost -->
                <form method="get" action="books.jsp"
                    <p><input type="submit" value="Continue Shopping" /></p>
                </form>
    </body>
</html>
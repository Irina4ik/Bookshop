/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Сервлет для добавления книги в магазинную тележку. 
package bookshop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author irina
 */
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            RequestDispatcher dispatcher;
            
            // если сеанс не существует, переадресвция к странице index.html
            if (session == null) {
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            
            // если сеанс существует, получить объект HashMap для тележки и книгу для добавления
            Map cart = (Map) session.getAttribute("cart");
            Book book = (Book) session.getAttribute("bookToAdd");
            
            // если тележка не существует, создать её
            if (cart == null) {
                cart = new HashMap();
                
                // задание атрибута сеанса cart
                session.setAttribute("cart", cart);
            }
            
            // определение, имеется ли книга в тележке
            CartItem cartItem = (CartItem) cart.get(book.getTitle());
            
            // Если книга уже имеется в тележке, обновить её количество.
            // В противном случае, создать элемент для помещения в тележку.
            if (cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            else
            cart.put(book.getTitle(), new CartItem(book, 1));
            
            // отправка страницы viewCart.jsp пользователю
            dispatcher = request.getRequestDispatcher("/viewCart.jsp");
            dispatcher.forward(request, response);
    }


}

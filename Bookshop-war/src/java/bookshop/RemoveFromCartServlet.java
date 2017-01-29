/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Сервлет для удаления книги из магазинной тележки. 
package bookshop;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * @author irina
 */
@WebServlet(name = "RemoveFromCart", urlPatterns = {"/RemoveFromCart"})
public class RemoveFromCartServlet extends HttpServlet {

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
        try {
            // получение объекта DocumentBuilderFactory
            // для создания синтаксического анализатора XML DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // получение объекта DocumentBuilder для построения дерева DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // создание нового объекта Document (пустое дерево DOM)
            Document document = builder.newDocument();
            
            //удаление товара из магазинной тележки
           
            String title = request.getParameter("title");
            
            HttpSession session = request.getSession();
            
            Map cart = (Map) session.getAttribute("cart");
            
            if(cart!= null)
                cart.remove(title);
            
            response.sendRedirect("viewCart.jsp");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RemoveFromCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
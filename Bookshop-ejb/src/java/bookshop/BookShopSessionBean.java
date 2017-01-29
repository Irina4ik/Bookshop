/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
/**
 *
 * @author irina
 */
@Stateless
public class BookShopSessionBean implements BookShopSessionBeanRemote, BookShopSessionBeanLocal {

    @PersistenceContext(unitName = "Bookshop-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
        
    private Connection connection;
    private PreparedStatement titlesQuery;
   
    public BookShopSessionBean() //throws NamingException
    {
        //попытка соединения с базой данных и создание операторов SQL
        try {
            InitialContext ic = new InitialContext(); 
            DataSource source =
                    (DataSource) ic.lookup("jdbc/bookshop");
            
            connection = source.getConnection();
            
            titlesQuery = connection.prepareStatement("SELECT primarykey, author, title, price FROM book ORDER BY title");
        }
        
        //обработка исключений при установке базы данных
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        // обработка исключений при нахождении источника данных
        catch (NamingException namingException) {
                namingException.printStackTrace();
        }
    }

    @Override
    public List getAllBooks() {
            List titlesList = new ArrayList();

            // получение списка книг
            try {
                ResultSet results = titlesQuery.executeQuery();
            
            // получение строки данных
            while (results.next()) {
                Book book = new Book();
                
                book.setTitle(results.getString("title"));
                book.setAuthor(results.getString("author"));;
                book.setPrice(results.getDouble("price"));
        
                titlesList.add(book);
            }
        }
        
        // обработка исключений при запросе базы данных
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        //возврат списка названий
        finally {
            return titlesList;
        }
    }
    
    // закрытие операторов и завершение соединения с базой данных
    protected void finalize() {
        // попытка закрыть соединение с базой данных
        try {
            connection.close();
        }
        
        //обработка исключения SQLException при операции закрытия
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    @Override
    public void addToCart(Book book, Map cart) {
        //AddToCartServlet
    }

    @Override
    public void viewCart() {
        //viewCart.jsp
    }

    @Override
    public void removeFromCart(Book book) {
        //RemoveFromCartServlet
    }
    

    @Override
    public void addBookToShop(String author, String title, double price) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setPrice(price);
        em.persist(book);
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}

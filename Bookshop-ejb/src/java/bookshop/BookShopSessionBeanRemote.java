/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author irina
 */
@Remote
public interface BookShopSessionBeanRemote {

    List getAllBooks();

    void addToCart(Book book, Map cart);

    void viewCart();

    void removeFromCart(Book book);
    
    void addBookToShop(String author, String title, double price);
}

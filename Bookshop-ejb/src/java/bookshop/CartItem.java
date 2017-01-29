/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author irina
 */
@Entity
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Book book;
    private int quantity;
    
    public CartItem() {
    }
    
    // инициализация объекта CartItemBean
    public CartItem(Book bookToAdd, int number) {
        book = bookToAdd;
        quantity = number;
    }
    
    // получение книги (это свойство только для чтения)
    public Book getBook() {
        return book;
    }
    
    // задание количества
    public void setQuantity(int number) {
        quantity = number;
    }
    
    // получение количества
    public int getQuantity() {
        return quantity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartItem)) {
            return false;
        }
        CartItem other = (CartItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bookshop.CartItem[ id=" + id + " ]";
    }
    
}

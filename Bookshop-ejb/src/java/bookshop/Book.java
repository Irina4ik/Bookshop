/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author irina
 */
@Entity
@Table(name = "BOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findByPrimarykey", query = "SELECT b FROM Book b WHERE b.primarykey = :primarykey")
    , @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
    , @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
    , @NamedQuery(name = "Book.findByPrice", query = "SELECT b FROM Book b WHERE b.price = :price")
    , @NamedQuery(name = "Book.prepareStatement", query = "SELECT b FROM Book b ORDER BY b.title")
})

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIMARYKEY")
    @GeneratedValue
    private Integer primarykey;
    @Size(max = 50)
    @Column(name = "AUTHOR")
    private String author;
    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;

    public Book() {
    }

    public Book(Integer primarykey) {
        this.primarykey = primarykey;
    }

    public Integer getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(Integer primarykey) {
        this.primarykey = primarykey;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (primarykey != null ? primarykey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.primarykey == null && other.primarykey != null) || (this.primarykey != null && !this.primarykey.equals(other.primarykey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bookshop.Book[ primarykey=" + primarykey + " ]";
    }
    
        // получение XML-представления книги
    public Element getXML(Document document) {
        
        // создание корневого элемента product для товара
        Element product = document.createElement("product");
        
        // создание элемента, добавление его как дочернего элемента для product
        Element temp = document.createElement("author");
        temp.appendChild(document.createTextNode(getAuthor().toString()));
        product.appendChild(temp);
        
        // создание элемента title, добавление его как дочернего элемента для product
        temp = document.createElement("title");
        temp.appendChild(document.createTextNode(getTitle()));
        product.appendChild(temp);
        
        // создание объекта форматирования денежных единиц для долл. США
        NumberFormat priceFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        
        // создание элемента price, добавление его как дочернего элемента для product
        temp = document.createElement("price");
        temp.appendChild(document.createTextNode(priceFormatter.format(getPrice())));
        product.appendChild(temp);

        // возврат элемента product
        return product;
    }
}

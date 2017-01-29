package bookshop;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Сервлет для возврата клиенту информации об одной книге.
// Сервлет выдает XML-код, который преобразуется с помощью XSL
// для формирования XHTML-страницы, отображаемой клиентом.

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author irina
 */
@WebServlet(name = "displayBook", urlPatterns = {"/displayBook"})
public class BookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // диспетчер RequestDispatcher для переадресации клиента
        // на основную страницу, если сеанс не существует, или ни одна из книг не выбрана
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        
        // если сеанс не существует, переадресация к index.html
        if (session == null)
            dispatcher.forward(request, response);
        
        // получение книг из объекта сеанса
        List titles = (List) session.getAttribute("book");
        
        // нахождение компонента BookBean для выбранной книги
        Iterator iterator = titles.iterator();
        Book book = null;
        
        String title = request.getParameter("title");
        
        while(iterator.hasNext()) {
            book = (Book) iterator.next();
            if (title.equals(book.getTitle())) {
                
                // сохранение книги в атрибуте сеанса
                session.setAttribute("bookToAdd", book);
                break; // primarykey-код для текущей книги
            }
        }
        
        // если книги нет в списке, переадресация к index.jsp
        if(book == null)
            dispatcher.forward(request, response);
        
        // получение XML-кода и преобразование его для просмотра клиентом
        try {
            // получение объекта DocumentBuilderFactory
            // для создания синтаксического анализатора XML DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // получение объекта DocumentBuilder для построения дерева DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // создание нового объекта Document (пустое дерево DOM)
            Document messageDocument = builder.newDocument();
            
            // получение XML из объекта BookBean и добавление в объект Document
            Element bookElement = book.getXML(messageDocument);
            messageDocument.appendChild(bookElement);
            
            // получение объекта PrintWriter для записи данных клиенту
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            // открытие потока ввода InputStream для XSL-документа
            InputStream xslStream = getServletContext().getResourceAsStream("/book.xsl");
            
            // преобразование XML-документа с использованием XSLT
            transform(messageDocument, xslStream, out);
            
            // очистка и закрытие объекта PrintWriter
            out.flush();
            out.close();
        }
        
        // перехват исключений от синтаксического анализатора XML
        catch (ParserConfigurationException pcException) {
            pcException.printStackTrace();
        }
        //
        /*catch(TransformerException transformerException) {
            transformerException.printStackTrace(System.err);
        } */  
    }

    // преобразование XML-документа с использованием потока ввода
    // XSLT InputStream и запись полученного документа в объект PrintWriter
    private void transform(Document document, InputStream xslStream, PrintWriter output) /*throws TransformerConfigurationException, TransformerException*/ {
        try {
            // создание объекта DOMSource для исходного XML-документа
            Source xmlSource = new DOMSource(document);
            
            //создание объекта StreamSource для XSLT-документа
            Source xslSource = new StreamSource(xslStream);
            
            // создание объекта StreamResult для результата трансформации
            Result result = new StreamResult(output);
            
            // создание объекта TransformerFactory для получения объекта Transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            
            // создание объекта Transformer для выполнения XSLT-трансформации
            Transformer transformer = transformerFactory.newTransformer(xslSource);
            
            // выполнение трансформации и доставка содержимого клиенту
            transformer.transform(xmlSource, result);
        }
        
        // обработка исключения при трансформации XML-документа
        catch(TransformerException transformerException) {
            transformerException.printStackTrace(System.err);
        }
    }

}
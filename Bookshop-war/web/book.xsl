<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : book.xsl
    Created on : 15 ноября 2016 г., 22:38
    Author     : irina
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" omit-xml-declaration="no"
    indent="yes" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
    doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"/>

    <!-- Преобразует XML в XHTML
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <!-- задание корня XML-документа, на который ссылается эта таблица стилей -->
    <xsl:template match="product">
        <html xmlns = "http://www.w3.org/1999/xhtml">
            
            <head>
                
                <!-- получение названия книги от JSP-страницы для помещения его в заголовок -->
                <title><xsl:value-of select = "title"/></title>
                <!-- <link rel = "stylesheet" href = "styles.css" type = "text/css" /> -->
            </head>
            
            <body>
                <h1><xsl:value-of select = "title" /></h1>
                
                <table>

                    <tr>
                        
                        <!-- создание ячеек таблицы  -->
                        <td>Author: </td>
                        
                        <td><xsl:value-of select = "author"/></td>
                    </tr>
                    <tr>     
                        <!-- создание ячеек таблицы -->
                        <td>Price:</td>
                        
                        <td>
                            <xsl:value-of select = "price" /> 
                        </td>
                    </tr>
                    
                    <tr>

                        
                        <!-- создание кнопки добавления книги в тележку -->
                        <td>
                            <form method = "post" action = "addToCart"><p>
                                <input type = "submit" value = "Add to Cart" />
                            </p></form>
                        </td>
                        

                        <!-- создание кнопки для просмотра содержимого тележки -->
                        <td>
                            <form method = "get" action = "viewCart.jsp"><p>
                                <input type = "submit" value = "View Cart" />
                            </p></form>
                        </td>     
                    </tr>
                </table>
                
            </body>
            
        </html>
        
    </xsl:template>

</xsl:stylesheet>

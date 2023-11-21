/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.biblioteca.Libro;
import com.mycompany.biblioteca.Lista;
import com.mycompany.biblioteca.Serializacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SvVerPrestamo", urlPatterns = {"/SvVerPrestamo"})
public class SvVerPrestamo extends HttpServlet {
Lista listaPrestamo = new Lista ();
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
       
             String titulo = request.getParameter("titulo").trim();
                 // si se va a ver un libro de los disponibles 
                    try {
            listaPrestamo = Serializacion.leerPrestamo(request.getServletContext());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Libro libro = listaPrestamo.buscarLibro(titulo, request);

            
            if (libro != null) {
            // Genera la respuesta HTML con los detalles del perro
            String libroHtml = "<h2>Titulo: " + libro.getTitulo() + "</h2>" +
                               "<p>Autor: " + libro.getAutor() + "</p>" +
                               "<p>Año: " + libro.getAnio() + "</p>" +
                               "<img src='imgLibros/" + libro.getPortada()+ "' alt='" + libro.getTitulo() + "' width='100%'/>";
            response.setContentType("text/html");
            response.getWriter().write(libroHtml);
            

        } else {
            // Maneja el caso en el que no se encuentra el libro
            response.setContentType("text/plain");
            response.getWriter().write("Libro no encontrado");
            
        }
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
         
             
     
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

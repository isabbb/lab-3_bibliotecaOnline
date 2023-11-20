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
@WebServlet(name = "SvPrestamo", urlPatterns = {"/SvPrestamo"})
public class SvPrestamo extends HttpServlet {
     Lista listaPrestamo = new Lista();
     Lista tablaPrestamo = new Lista();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try {
             listaPrestamo = Serializacion.leerArchivo(request. getServletContext());
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         String libroPre = request.getParameter("tiPres");
         
         try {
             Libro libroPrestamo = listaPrestamo.buscarLibro(libroPre, request);
             listaPrestamo.agregar(libroPrestamo);
         
            Serializacion.escribirPrestamo(listaPrestamo, request. getServletContext());
         
       
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
    
         response.sendRedirect("Login.jsp");
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

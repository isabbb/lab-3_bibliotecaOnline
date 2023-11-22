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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author A,S,I,C
 */
@WebServlet(name = "SvPrestamo", urlPatterns = {"/SvPrestamo"})
public class SvPrestamo extends HttpServlet {
     Lista listaPrestamo = new Lista();
       

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
          ServletContext context =getServletContext();
          
         try {
             
             //Se lee el archivo donde estan los libros disponibles
             listaPrestamo = Serializacion.leerArchivo(context);
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         //Se obtiene el parametro para saber que libro es
         String libroPre = request.getParameter("tiPres");
             Libro libroPrestamo = null;
         try {
             
             //buscamos el libro con el parametro obtenido 
             libroPrestamo = listaPrestamo.buscarLibro(libroPre, request);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         if (libroPrestamo != null){
             //se crea una nueva lista para guardar el libro prestado
             Lista nuevaLista = new Lista ();
              try {
                  
                  //leemos para mantener persistencia 
                  nuevaLista = Serializacion.leerPrestamo(context);
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              //Agregamos el libro a la lista
             nuevaLista.agregar(libroPrestamo);
             
             //Agregamos el libro a el archivo de prestamos
            Serializacion.escribirPrestamo(nuevaLista, context);
            
            
            //eliminamos el libro del archivo disponibles
            String eliminar =request.getParameter("tiPres");
              listaPrestamo.eliminarLibro(eliminar);
               Serializacion.escribirArchivo(listaPrestamo, context);
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

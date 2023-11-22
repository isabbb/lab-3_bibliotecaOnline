/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.biblioteca.Lista;
import com.mycompany.biblioteca.Serializacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author C,I,S,A
 */
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet {
    
  Lista listaLibros = new Lista();
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
   
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           ServletContext context =getServletContext();
        
      try {
          listaLibros = Serializacion.leerArchivo(context);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(SvEliminar.class.getName()).log(Level.SEVERE, null, ex);
          
          System.out.println("no esta funcionando");
      }
      
      String titulo = request.getParameter("inputEliminar");
      
        System.out.println(titulo);
      
      listaLibros.eliminarLibro(titulo);
      
      Serializacion.escribirArchivo(listaLibros, context);
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
    }

}

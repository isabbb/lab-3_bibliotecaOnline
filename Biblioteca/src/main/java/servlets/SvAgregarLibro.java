/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.biblioteca.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import com.mycompany.biblioteca.Lista;
import com.mycompany.biblioteca.Serializacion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SvAgregarLibro", urlPatterns = {"/SvAgregarLibro"})
public class SvAgregarLibro extends HttpServlet {
    
    Lista listaLibros = new Lista();

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           ServletContext context = getServletContext();
        
        try {
            listaLibros = Serializacion.leerArchivo(context);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String fecha = request.getParameter("fecha");

       
     
                   
        //obtener la parte de la foto
        Part fotoPart = request.getPart("foto");
        String fileName = fotoPart.getSubmittedFileName();
        //directorio donde se almacena el archivo
        String uploadD = context.getRealPath("/imgLibros");
     
        String filePath = uploadD + File.separator + fileName;
        try ( InputStream input = fotoPart.getInputStream(); 
               OutputStream output = new FileOutputStream (filePath)){
            
            
             byte[] buffer = new byte[1024];
             int length; 
             
             while ((length = input.read(buffer))>8){
                 output.write(buffer, 0, length);
             }
        }
        
        
        Libro libro = new Libro(titulo, autor, fileName, fecha);
        
        listaLibros.agregar(libro);
        Serializacion.escribirArchivo(listaLibros, context);
        
        response.sendRedirect("Login.jsp");
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

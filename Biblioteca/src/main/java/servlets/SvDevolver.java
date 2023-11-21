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
 * @author ADMIN
 */
@WebServlet(name = "SvDevolver", urlPatterns = {"/SvDevolver"})
public class SvDevolver extends HttpServlet {

    Lista listaDevolver = new Lista();
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context =getServletContext();

try {
    // Se lee la lista de préstamos
    Lista listaPrestamo = Serializacion.leerPrestamo(context);

    // Se obtiene el parámetro para saber qué libro se va a devolver
    String libroDevuelto = request.getParameter("devolver");

    if (listaPrestamo != null) {
        // Se busca el libro a devolver en la lista de préstamos
        Libro libroADevolver = listaPrestamo.buscarLibro(libroDevuelto, request);

        if (libroADevolver != null) {
            // Se elimina el libro de la lista de préstamos
            listaPrestamo.eliminarLibro(libroDevuelto);

            // Se vuelve a leer la lista original de libros disponibles
            Lista listaDisponible = Serializacion.leerArchivo(context);

            if (listaDisponible == null) {
                listaDisponible = new Lista();
            }

            // Se agrega el libro devuelto a la lista original de libros disponibles
            listaDisponible.agregar(libroADevolver);

            // Se escribe la lista actualizada de libros disponibles
            Serializacion.escribirArchivo(listaDisponible, context);

            // Se escribe la lista actualizada de préstamos
            Serializacion.escribirPrestamo(listaPrestamo, context);
        }
    }
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

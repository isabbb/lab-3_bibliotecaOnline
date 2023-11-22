/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.biblioteca.MetodosU;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author A,S,I,C
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Aquí vienen los datos por doPost
        // Manda las variables pero no las muestra por motivos de seguridad
        String cedula = request.getParameter("cedula");
        String contrasenia = request.getParameter("contrasenia");

        // Verificar las credenciales del usuario y obtener el nombre de usuario autenticado
        String nombreUsuarioAutenticado = MetodosU.loginUsuario(cedula, contrasenia, getServletContext());

        if (nombreUsuarioAutenticado != null) {
            // Las credenciales son válidas, puedes redireccionar al usuario a la página deseada
            request.getSession().setAttribute("usuario", nombreUsuarioAutenticado);
            response.sendRedirect("Login.jsp");
        } else {
            // Las credenciales no son válidas, redirecciona a "index.jsp" con un parámetro de alerta
            response.sendRedirect("index.jsp?alert=error");
        }
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

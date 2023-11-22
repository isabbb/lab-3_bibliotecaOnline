    /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
    */
    package com.mycompany.biblioteca;

    import static com.mycompany.biblioteca.Serializacion.leerArchivo;
    import java.io.IOException;
    import java.io.Serializable;
    import javax.servlet.ServletContext;
    import javax.servlet.http.HttpServletRequest;
    import static jdk.jpackage.internal.Arguments.CLIOptions.context;

    /**
    * 
    * Clase que representa una lista de libros.
    * Esta lista está implementada como una lista doblemente enlazada.
    * @author C,J,I,S
    */
    public class Lista implements Serializable{

    private Nodo iNodo, fNodo;
        
    /**
     * Constructor de la clase Lista. Inicializa los nodos de inicio y fin como nulos.
     */

    public Lista (){
    iNodo = null;
    fNodo = null;
    }
        
    /**
     * Verifica si la lista contiene elementos.
     * 
     * @return true si la lista no está vacía, false si está vacía.
     */

    public boolean verificar (){
    return iNodo !=null;
    }

    /**
     * Método para agregar un libro a la lista.
     * 
     * @param libro Libro a agregar a la lista.
     */
   public void agregar(Libro libro) {
    Nodo nuevoNodo = new Nodo(libro, null, null);
    if (iNodo == null) {
        // si la lista está vacía, el nuevo nodo es tanto el inicio como el final de la lista
        iNodo = nuevoNodo;
        fNodo = nuevoNodo;
    } else {
        // si la lista no está vacía, agregamos el nuevo nodo al final de la lista
        nuevoNodo.setAnterior(fNodo);
        fNodo.setSiguiente(nuevoNodo);
        fNodo = nuevoNodo;
    }
}

/**
     * Genera una tabla HTML que representa la lista de libros.
     * 
     * @return Una cadena de texto que contiene el código HTML de la tabla.
     */
        
    public String generarTabla (){
        StringBuilder tablaHTML = new StringBuilder();
        Nodo actual = iNodo; 

        if (actual != null) {
        //Se recorre la lista
        while (actual != null) {
        Libro libro = actual.libro;
        tablaHTML.append("<tr>");
        tablaHTML.append("<td>").append(libro.getTitulo()).append("</td>");
        tablaHTML.append("<td>").append(libro.getAutor()).append("</td>");
        tablaHTML.append("<td>").append(libro.getAnio()).append("</td>");
        tablaHTML.append("<td>").append(libro.getPortada()).append("</td>");

        tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
        tablaHTML.append("<form action=\"SvPrestamo\" method=\"GET\" ><input type=\"text\" name=\"tiPres\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-cart-shopping\"></i></button></form>");
        tablaHTML.append ("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></  td>");
   


        tablaHTML.append("</tr>");
        actual = actual.siguiente;
        }
    }

        tablaHTML.append("</table>");  // Cierra la tabla

        return tablaHTML.toString();//Se manda la tabla creada
    }
    /**
     * Busca un libro por título y autor en la lista.
     * 
     * @param titulo Título del libro a buscar.
     * @param request Objeto HttpServletRequest necesario para la búsqueda.
     * @return El libro encontrado o null si no se encuentra.
     * @throws IOException           
     * @throws ClassNotFoundException 
     */
    public Libro buscarLibro (String titulo,HttpServletRequest request) throws IOException, ClassNotFoundException{

        Nodo actual = iNodo;
            while (actual !=null ){
            Libro libro = actual.libro;

            if (libro.getTitulo().equalsIgnoreCase(titulo)){

                return libro;

            }
             actual = actual.siguiente;
    }
    return null;
    }
        
     /**
     * Verifica si un libro existe en la lista.
     * 
     * @param terminoBusqueda Término de búsqueda para el libro.
     * @param request   Objeto HttpServletRequest necesario para la búsqueda.
     * @return true si el libro existe, false si no existe.
     * @throws IOException            
     * @throws ClassNotFoundException 
     */
        
    public boolean libroExiste (String terminoBusqueda, HttpServletRequest request) throws IOException, ClassNotFoundException{
        
     
        Nodo actual = iNodo;
            while (actual !=null ){
            Libro libro = actual.libro;

            if (libro.getTitulo().equalsIgnoreCase(terminoBusqueda) || libro.getAutor().equalsIgnoreCase(terminoBusqueda)){

                return true;

            }
             actual = actual.siguiente;
    }
  
        return false;
    }
        
      /**
     * Genera una tabla HTML que representa los resultados de búsqueda.
     * 
     * @param terminoBusqueda Término de búsqueda para filtrar los resultados.
     * @param request         Objeto HttpServletRequest necesario para la búsqueda.
     * @return Una cadena de texto que contiene el código HTML de la tabla de búsqueda.
     */
        
    public String tablaBusqueda (String terminoBusqueda, HttpServletRequest request){
        StringBuilder tablaHTML = new StringBuilder();
        Nodo actual = iNodo;

        while (actual !=null){
        if (terminoBusqueda.equalsIgnoreCase(actual.libro.getAutor()) || terminoBusqueda.equalsIgnoreCase(actual.libro.getTitulo())){

        Libro libro = actual.libro;
        tablaHTML.append("<tr>");
        tablaHTML.append("<td>").append(libro.getTitulo()).append("</td>");
        tablaHTML.append("<td>").append(libro.getAutor()).append("</td>");
        tablaHTML.append("<td>").append(libro.getAnio()).append("</td>");
        tablaHTML.append("<td>").append(libro.getPortada()).append("</td>");

       tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
        tablaHTML.append("<form action=\"SvPrestamo\" method=\"GET\" ><input type=\"text\" name=\"tiPres\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-cart-shopping\"></i></button></form>");
        tablaHTML.append ("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></  td>");
        }
        actual = actual.siguiente;
        }
        tablaHTML.append("</table>");  // Cierra la tabla

        return tablaHTML.toString(); //Se devuelve la tabla


    }
    
    public void eliminarLibro (String titulo){
   
    if (iNodo != null) {
        Nodo actual = iNodo;
        Nodo anterior = null;

        while (actual != null) {
            if (actual.libro.getTitulo().equals(titulo)) {
                
                if (actual == iNodo) {//si el libro es igual a primer nodo
                    
                    //pasa a ser el siguiente nodo
                    iNodo = iNodo.siguiente;
                    
                    // si el nodo siguiente no es igual a nulo
                    if (iNodo != null) {
                        //nodo anterior pasa a ser nulo
                        iNodo.anterior = null;
                    }
                    return; // Sale del método después de eliminar el primer nodo
                    
                } else if (actual == fNodo) {// si es igual al anterior nodo 
                    //pasa a ser el anterior nodo 
                    fNodo = fNodo.anterior;

                    //si el nodo anterior no es nulo 
                    if (fNodo != null) {
                        //El nodo siguiente es igual a nulo 
                        fNodo.siguiente = null;
                    }
                    return; // Sale del método después de eliminar el último nodo
                    
                } else {
                    // si el libro a eliminar no esta ni al principio nio al final 

                    //el nodo anterior pasa a apuntar al siguiente del que quiere eliminar saltandose el actual 
                    anterior.siguiente = actual.siguiente;

                    // el nodo siguiente pasa a apuntar al aterior del libro eliminado saltandose el actual 
                    actual.siguiente.anterior = anterior;
                    
                    return; // Sale del método después de eliminar un nodo en el medio de la lista
                }
            }
            //para seguir recorriendo la lista 
            anterior = actual;
            actual = actual.siguiente;
        }
    }
}


public String tablaPrestamo (){
        StringBuilder tablaPrestamo = new StringBuilder();
        Nodo actual = iNodo; 
    
      // Si hay nodos en la lista
        if (actual != null) {
        //Se recorre la lista
        while (actual != null) {
        Libro libro = actual.libro;
        tablaPrestamo.append("<tr>");
        tablaPrestamo.append("<td>").append(libro.getTitulo()).append("</td>");
        tablaPrestamo.append("<td>").append(libro.getAutor()).append("</td>");
        tablaPrestamo.append("<td>").append(libro.getAnio()).append("</td>");
        tablaPrestamo.append("<td>").append(libro.getPortada()).append("</td>");
            
        // Acción de ver detalles y devolver el libro
        tablaPrestamo.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
        tablaPrestamo.append ("<form action=\"SvDevolver\" method=\"GET\" ><input type=\"text\" name=\"devolver\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-retweet\"></i></button></form></  td>");

        tablaPrestamo.append("</tr>");
        actual = actual.siguiente;
        }
    }

        tablaPrestamo.append("</table>");  // Cierra la tabla

        return tablaPrestamo.toString();//Se manda la tabla creada
    }
     public String tablaBusquedaPrestamo (String terminoBusqueda, HttpServletRequest request){
        StringBuilder tablaPrestamo = new StringBuilder();
        Nodo actual = iNodo;

        while (actual !=null){
        if (terminoBusqueda.equalsIgnoreCase(actual.libro.getAutor()) || terminoBusqueda.equalsIgnoreCase(actual.libro.getTitulo())){

       
        Libro libro = actual.libro;
        tablaPrestamo.append("<tr>");
        tablaPrestamo.append("<td>").append(libro.getTitulo()).append("</td>");
        tablaPrestamo.append("<td>").append(libro.getAutor()).append("</td>");
        tablaPrestamo.append("<td>").append(libro.getAnio()).append("</td>");
        tablaPrestamo.append("<td>").append(libro.getPortada()).append("</td>");

         tablaPrestamo.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
      tablaPrestamo.append ("<form action=\"SvDevolver\" method=\"GET\" ><input type=\"text\" name=\"devolver\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-retweet\"></i></button></form></  td>");

        tablaPrestamo.append("</tr>");
       
        
    }
         actual = actual.siguiente;
        }
          tablaPrestamo.append("</table>");  // Cierra la tabla
          return tablaPrestamo.toString(); //Se devuelve la tabla
     }
     
     
     
     
    }
    
    
    
    

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
    * @author ***
    */
    public class Lista implements Serializable{

    private Nodo iNodo, fNodo;


    public Lista (){
    iNodo = null;
    fNodo = null;
    }


    public boolean verificar (){
    return iNodo !=null;
    }

    /**
     * método para agregar libro 
     * @param libro 
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
        tablaHTML.append("<form action=\"SvPrestamo\" method=\"GET\" ><input type=\"text\" name=\"tia\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-pen-clip\"></i></button></form>");
        tablaHTML.append ("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\""+libro.getTitulo()+"\"hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></  td>");
   


        tablaHTML.append("</tr>");
        actual = actual.siguiente;
        }
    }

        tablaHTML.append("</table>");  // Cierra la tabla

        return tablaHTML.toString();//Se manda la tabla creada
    }
    
    public Libro buscarLibro (String titulo,HttpServletRequest request) throws IOException, ClassNotFoundException{

        Nodo actual = iNodo;

        System.out.println(titulo);


            while (actual !=null ){
            Libro libro = actual.libro;

            if (libro.getTitulo().equalsIgnoreCase(titulo)){

                return libro;

            }
             actual = actual.siguiente;
    }
    return null;

    }
    
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
        tablaHTML.append("<a href=\"SvPrestamo?idLibro=\""+libro.getTitulo()+"\" type=\"button\" class=\"btn btn-outline-success\"  data-nombre=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-pen-clip\"></i></a>");
        tablaHTML.append("<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminarModal\" data-eliminar=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>");

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
                if (actual == iNodo) {
                    iNodo = iNodo.siguiente;
                    if (iNodo != null) {
                        iNodo.anterior = null;
                    }
                    return; // Sale del método después de eliminar el primer nodo
                } else if (actual == fNodo) {
                    fNodo = fNodo.anterior;
                    if (fNodo != null) {
                        fNodo.siguiente = null;
                    }
                    return; // Sale del método después de eliminar el último nodo
                } else {
                    anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = anterior;
                    return; // Sale del método después de eliminar un nodo en el medio de la lista
                }
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }
}

    
    }
    
//    public String tablaPrestamo (HttpServletRequest request){
//          StringBuilder tablaPrestamo = new StringBuilder();
//        Nodo actual = iNodo;
//              
//    while (actual !=null){
////    if (terminoBusqueda.equalsIgnoreCase(actual.libro.getAutor()) || terminoBusqueda.equalsIgnoreCase(actual.libro.getTitulo())){
////
////    Libro libro = actual.libro;
////    tablaPrestamo.append("<tr>");
////    tablaPrestamo.append("<td>").append(libro.getTitulo()).append("</td>");
////    tablaPrestamo.append("<td>").append(libro.getAutor()).append("</td>");
////    tablaPrestamo.append("<td>").append(libro.getAnio()).append("</td>");
////    tablaPrestamo.append("<td>").append(libro.getPortada()).append("</td>");
////
////    tablaPrestamo.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
////    tablaPrestamo.append("<a href=\"SvPrestamo?idLibro=\""+libro.getTitulo()+"\" type=\"button\" class=\"btn btn-outline-success\"  data-nombre=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-pen-clip\"></i></a>");
////    tablaPrestamo.append("<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminarModal\" data-eliminar=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>");
////
////    }
////    actual = actual.siguiente;
////    }
////    tablaPrestamo.append("</table>");  // Cierra la tabla
//
//    return tablaPrestamo.toString(); //Se devuelve la tabla
//
//
//    }
//    
//    
    
    
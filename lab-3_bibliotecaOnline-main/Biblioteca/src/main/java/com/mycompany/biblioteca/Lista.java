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

    public void agregar(Libro libro) {
    Nodo nuevoNodo = new Nodo(libro);

    if (iNodo == null) {
    // If the list is empty, set both iNodo and fNodo to the new node
    iNodo = fNodo = nuevoNodo;
    } else {
    // If the list is not empty, add the new node at the end and update fNodo
    fNodo.siguiente = nuevoNodo;
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
    tablaHTML.append("<form action=\"SvPrestamo\" method=\"GET\" ><button type=\"submit\" class=\"btn btn-outline-success\" data-nombre=\"" + libro.getTitulo() + "\"> <input type=\"text\" name=\"tia\" value=\""+libro.getTitulo()+"\"hidden><i class=\"fa-solid fa-pen-clip\"></i></form>");
    tablaHTML.append("<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminarModal\" data-tituloEliminar=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>");



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
        
      
                    if (iNodo != null){
                    Nodo  anterior = iNodo;
                    Nodo actual = iNodo.siguiente;  
                        
                     while (actual !=null){
                            
                       if (actual.libro.getTitulo().equalsIgnoreCase(titulo)){
                                    
                                // si actual es el primero nodo
                                if (anterior == iNodo){
                                     iNodo.siguiente = actual.siguiente;
                                    
                                     if (actual.siguiente != null){
                                         actual.siguiente.anterior = iNodo;
                                     }
                                     else {
                                         //actual no es el ultimo nodo
                                          anterior.siguiente = actual.siguiente;
                                          
                                          if (actual.siguiente != null){
                                              actual.siguiente.anterior = anterior;
                                          }
                                          
                                     }
                                     
                                     actual.siguiente = null;
                                     actual.anterior = null;
                                        return;
                                }
                                
                                 anterior = actual;
                                   actual = actual.siguiente;
                                   return; 
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
    
    }
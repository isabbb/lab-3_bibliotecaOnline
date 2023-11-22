        /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.biblioteca;


    import static com.mycompany.biblioteca.Serializacion.leerArchivo;
    import java.io.EOFException;
    import java.io.File;
        import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
    import javax.servlet.ServletContext;
    import javax.servlet.http.HttpServletRequest;

    /**
    * Clase utilizada para la serialización y deserialización de objetos Lista.
    * Proporciona métodos para escribir y leer información de libros y préstamos en archivos.
    * También incluye funciones para listar libros y préstamos basados en términos de búsqueda.
    * 
    * @author C,J,I,S
    */
    public class Serializacion {
            
    /**
     * Escribe la información de la Lista de libros en un archivo serializado.
     * 
     * @param lLibros Lista de libros a serializar.
     * @param context Contexto del servlet para obtener la ruta del archivo.
     * @throws FileNotFoundException, Si no se encuentra el archivo.
     * @throws IOException, Si ocurre un error de entrada/salida durante la escritura.
     */
            
        public static void escribirArchivo(Lista lLibros, ServletContext context) throws FileNotFoundException, IOException {

            String rutaRelativa = "/data/librosAgregados.ser";
            String rutaAbsoluta = context.getRealPath(rutaRelativa);
            File archivo = new File(rutaAbsoluta);

                try (FileOutputStream fos = new FileOutputStream(archivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {



                oos.writeObject(lLibros);
            } catch (IOException e) {
                System.out.println("Error al escribir");
            }
        }
            
       /**
     * Lee la información de la Lista de libros desde un archivo serializado.
     * 
     * @param context Contexto del servlet para obtener la ruta del archivo.
     * @return Lista de libros leída desde el archivo.
     * @throws IOException, Si ocurre un error de entrada/salida durante la lectura.
     * @throws ClassNotFoundException, Si la clase no se encuentra durante la deserialización.
     */
            
       public static Lista leerArchivo(ServletContext context) throws IOException, ClassNotFoundException {
           
        Lista lLibros = new Lista();
        
        String rutaRelativa = "/data/librosAgregados.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        if (archivo.exists() && archivo.isFile()) {
            try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis)) {

                lLibros = (Lista) ois.readObject();
            } catch (EOFException e) {
                System.out.println("El archivo está vacío");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo"); 
            }
        } else {
            System.out.println("El archivo no existe");
        }

        return lLibros;
    }
     /**
     * Escribe la información de la Lista de préstamos en un archivo serializado.
     * 
     * @param lLibros Lista de préstamos a serializar.
     * @param context Contexto del servlet para obtener la ruta del archivo.
     * @throws FileNotFoundException, Si no se encuentra el archivo.
     * @throws IOException, Si ocurre un error de entrada/salida durante la escritura.
     */  
       
  public static void escribirPrestamo(Lista lLibros, ServletContext context) throws FileNotFoundException, IOException {

    String rutaRelativa = "/data/librosPrestados.ser";
    String rutaAbsoluta = context.getRealPath(rutaRelativa);

    try (FileOutputStream fos = new FileOutputStream(new File(rutaAbsoluta));
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

        oos.writeObject(lLibros);
    } catch (IOException e) {
        System.out.println("Error al escribir");
    }
}
            
/**
     * Lee la información de la Lista de préstamos desde un archivo serializado.
     * 
     * @param context Contexto del servlet para obtener la ruta del archivo.
     * @return Lista de préstamos leída desde el archivo.
     * @throws IOException, Si ocurre un error de entrada/salida durante la lectura.
     * @throws ClassNotFoundException, Si la clase no se encuentra durante la deserialización.
     */
            
public static Lista leerPrestamo(ServletContext context) throws IOException, ClassNotFoundException {
    Lista lLibros = new Lista();
        
        String rutaRelativa = "/data/librosPrestados.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        if (archivo.exists() && archivo.isFile()) {
            try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis)) {

                lLibros = (Lista) ois.readObject();
            } catch (EOFException e) {
                System.out.println("El archivo está vacío");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo"); 
            }
        } else {
            System.out.println("El archivo no existe");
        }

        return lLibros;
}
            /**
           * Lista libros según un término de búsqueda y devuelve una representación en forma de tabla HTML.
           * 
           * @param terminoBusqueda,Término de búsqueda para filtrar los resultados.
           * @param context ,Contexto del servlet para obtener la información del archivo.
           * @param request, Objeto HttpServletRequest necesario para la búsqueda.
           * @return Una cadena de texto que contiene el código HTML de la tabla de libros.
           * @throws IOException, Si ocurre un error de entrada/salida durante la lectura.
           * @throws ClassNotFoundException, Si la clase no se encuentra durante la deserialización.
           */
         public static String listarLibros (String terminoBusqueda,ServletContext context, HttpServletRequest request) throws IOException, ClassNotFoundException{
           //Llenamos la lista con la informacion del archivo
           Lista listaLibro = leerArchivo(context);
           //En caso de estar vacia se crea una
            if (listaLibro == null) {
                 listaLibro = new Lista();
            }
           String tabla="";//Variable que contiene la tabla

           if (terminoBusqueda==null){
               tabla=listaLibro.generarTabla();
           }else if (!terminoBusqueda.isEmpty() ){
               tabla = listaLibro.tablaBusqueda(terminoBusqueda, request);
           }
               

               return tabla;
    }
         
         
            // Método para listar todos los préstamos almacenados en un archivo
    public static String listarPrestamos(String terminoBusqueda, ServletContext context, HttpServletRequest request) throws IOException, ClassNotFoundException {

        // Leemos el archivo que contiene la información de los préstamos y la almacenamos en una lista llamada "listaLibro"
        Lista listaLibro = leerPrestamo(context);

        // En caso de que la lista esté vacía, creamos una nueva lista
        if (listaLibro == null) {
            listaLibro = new Lista();
        }

        String tabla = ""; // Variable que almacenará la tabla que se mostrará en la interfaz web

        // Si no se ha ingresado ningún término de búsqueda, se mostrará la tabla con todos los préstamos almacenados
        if (terminoBusqueda == null) {
            tabla = listaLibro.tablaPrestamo();
        }
        // Si se ha ingresado un término de búsqueda, se mostrará la tabla con los préstamos que coincidan con ese término
        else if (!terminoBusqueda.isEmpty()) {
            tabla = listaLibro.tablaBusquedaPrestamo(terminoBusqueda, request);
        }

        return tabla; // Retornamos la tabla que se mostrará en la interfaz web
    }





        
}

             
             
         
    

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletContext;

/**
 *
 * @author ***
 */
public class Serializacion {
    
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
}

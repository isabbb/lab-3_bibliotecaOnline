/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca;


/**
 *
 * @author ADMIN
 */
public class Nodo {
    public Libro libro;
    Nodo siguiente, anterior;
    
    public Nodo ( Libro libro){
        this.libro=libro;
        siguiente = null;
        anterior = null;
    }
    
}

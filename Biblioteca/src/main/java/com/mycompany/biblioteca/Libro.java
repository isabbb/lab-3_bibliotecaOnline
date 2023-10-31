/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca;

/**
 *
 * @author Lenovo
 */
public class Libro {
   private String Titulo;
   private String Autor;
   private String Portada;
   private String Anio;
    public Libro siguiente;
    public Libro anterior;

    public Libro() {
    }

    public Libro(String Titulo, String Autor, String Portada, String Anio) {
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.Portada = Portada;
        this.Anio = Anio;
        this.siguiente = null;
        this.anterior = null;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public String getPortada() {
        return Portada;
    }

    public String getAnio() {
        return Anio;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public void setPortada(String Portada) {
        this.Portada = Portada;
    }

    public void setAnio(String Anio) {
        this.Anio = Anio;
    }
    public Libro getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Libro siguiente) {
        this.siguiente = siguiente;
    }

    public Libro getAnterior() {
        return anterior;
    }

    public void setAnterior(Libro anterior) {
        this.anterior = anterior;
    }
}
   


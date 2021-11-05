/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.semillero.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geo-1
 */
public class ConsultarComicTamanoNombreDTO extends ResultadoDTO{
    /**
    * Lista que contiene los nombres que tienen igual o menor numero de caracteres que la longitud ingresada
    */
    public List<String>   nombresIgualLongitud;
    /**
    * lista que contiene los nombres que tienen mayor numero de caracteres que la longitud ingresada
    */
    
    public List<String>   nombresDistintaLongitud;

    


	private static final long serialVersionUID = 1L;
       
        /**
         * constructor de la clase
         */
        public ConsultarComicTamanoNombreDTO(){
            //constructor vacio
        }
       
    /**
    * metodo que nos retorna una lista que contiene los numeros con la propiedad nombrada al principio
     */
  
  
        public List<String> getNombresIgualLongitud() {
        return this.nombresIgualLongitud;
    }
    /**
    * metodo que nos sirve para agregar el nombre de un comic que cumpla la propiedad en la lista respectiva
     */
    public void addNombresIgualLongitud(String nombre) {
        this.nombresIgualLongitud.add(nombre);
    }
    /**
    * metodo que nos retorna una lista que contiene los numeros con la propiedad nombrada al principio
     */
        public List<String> getNombresDistintaLongitud() {
        return this.nombresDistintaLongitud;
    }
    /**
    * metodo que nos sirve para agregar el nombre de un comic que cumpla la propiedad en la lista respectiva
     */
    public void addNombresDitintaLongitud(String nombre) {
        this.nombresDistintaLongitud.add(nombre);
    }
     
}

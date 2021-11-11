/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.semillero.dto;

/**
 *DTO que contiene la informacion para el metodo comparComic
 * @author Geovani Andres Ladino O
 */
public class ComprarComicDTO {
    /**
     * variable que contiene el id del comic que se quiere comprar
     */
    private Long id;
    /**
     * variable que contiene la cantidad de comics que se quieren comprar
     */
    private int cantidadComprar;
    /**
     * Metodo que retorna el id del comic que se va a comprar
     * @return id 
     */
    public Long getId() {
        return id;
    }

   /**
    * Metodo que retorna la cantidad de comics que se pretenden comprar
    * @return cantidadComprar 
    */
    public int getCantidad() {
        return cantidadComprar;
    }

   

    public ComprarComicDTO() {
        //construnctor vacio
    }
    
}

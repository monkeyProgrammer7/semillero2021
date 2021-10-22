/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.hbt.semillero.enums;

/**
 * <b>Descripción:<b> Clase que determina el tipo de estado de los comics(Antivo, Inactivo)
 * 
 * @author Geovani Andres Ladino Orrego
 * @version
 */
public enum EstadoEnum {
    ACTIVO("enum.estado.activo"),
    INACTIVO("enum.estado.inactivo");
    
    /**
     * variable que nos permite almacenar el mensaje de los estados
     */
    private String codigoMensaje;
    /**
     * Constructor de la clase Estado enum
     * @param codigoMensaje  mensaje de los estados
     */
     EstadoEnum(String codigoMensaje){
        this.codigoMensaje=codigoMensaje;
    }
     /**
      * Metodo que nos permite cambiar el mensaje de los estados
      * @param codigoMensaje 
      */
    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }
     
     
}

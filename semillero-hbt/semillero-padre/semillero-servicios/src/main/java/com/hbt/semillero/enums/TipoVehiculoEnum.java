/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.hbt.semillero.enums;

/**
 *
 * @author geo-1
 */
public enum TipoVehiculoEnum {
    TERRESTRE("Terrestre",1),
    ACUATICO("Acuatico",2),
    AEREO("aereo",3);
    
    private String tipo;
    private int identificador;
    TipoVehiculoEnum(String tipo, int identificador){
        this.identificador=identificador;
        this.tipo=tipo;
    }
    
    public String getTipo(){
        return tipo;
    }
    /**
     * 
     * @return 
     */

    public int getIdentificador() {
        return identificador;
    }
    
}

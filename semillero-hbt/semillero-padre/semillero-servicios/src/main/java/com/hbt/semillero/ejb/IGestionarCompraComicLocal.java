/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hbt.semillero.ejb;


import com.hbt.semillero.dto.ComprarComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import javax.ejb.Local;

/**
 * Interfaz que contiene los metodos abstractos que se conectan con el api rest y se implementan en el gestionar comic bean
 * @author geovani Andres Ladino O.
 */
@Local
public interface IGestionarCompraComicLocal {
     /**
     * Metodo abstracto que sera implementado en el bean
     * @param comprarComicDTO DTO que contiene los campo necesarios para itentar o realizar la compra
     * @return DTO con la informacion de la compra
     * @throws java.lang.Exception
     *
     */
    public ResultadoDTO comprarComic(ComprarComicDTO comprarComicDTO) throws Exception;
}

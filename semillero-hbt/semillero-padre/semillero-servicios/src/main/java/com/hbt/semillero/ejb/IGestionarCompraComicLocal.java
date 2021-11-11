/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hbt.semillero.ejb;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ComprarComicDTO;
import javax.ejb.Local;

/**
 * Interfaz que contiene los metodos abstractos que se conectan con el api rest y se implementan en el gestionar comic bean
 * @author geovani Andres Ladino O.
 */
@Local
public interface IGestionarCompraComicLocal {
    public ComicDTO comprarComic(ComprarComicDTO comprarComicDTO);
}

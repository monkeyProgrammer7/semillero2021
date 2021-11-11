/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.semillero.rest;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.ejb.IGestionarCompraComicLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que contiene las direcciones de los servicios que se van a ejecutar y que se conecta con el front
 * @author Geovani Andres Ladino O
 */
@Path("/gestionarComic")
public class GestionarCompraComicRest {
    /**
     * Variable que contiene a la interfaz para poder utilizar los metodos declarados en ella
     */
    @EJB
    private IGestionarCompraComicLocal gestionarCompraComicLocal;
    
      @POST
        @Path("comprarComic")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public ComicDTO editarComic(ComicDTO comicDTO){
         
         return this.gestionarCompraComicLocal.comprarComic(comicDTO);
        }
}

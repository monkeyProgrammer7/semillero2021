/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.semillero.rest;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ComprarComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarCompraComicLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
        
    /**
     * metodo que llama al metodo comprarComic de la interfaz IGestionarCompraComicLocal 
     * @param comprarComicDTO
     * @return resultado de la consulta
     */
      @POST
        @Path("/comprarComic")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public ResultadoDTO editarComic(ComprarComicDTO comprarComicDTO){
            ComicDTO comicDTOResult = new ComicDTO();
         try{
             return this.gestionarCompraComicLocal.comprarComic(comprarComicDTO);
         }catch(Exception e){
             comicDTOResult.setExitoso(false);
            comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
         }
         return comicDTOResult;
        }
}

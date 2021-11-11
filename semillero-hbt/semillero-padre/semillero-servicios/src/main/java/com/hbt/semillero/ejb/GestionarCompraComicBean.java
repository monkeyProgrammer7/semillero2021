/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.semillero.ejb;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ComprarComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase donde se implementan los servicios necesarios para comprar un comic
 *
 * @author Geovani Andres Ladino O.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComicLocal {

    /**
     * variable que contiene los metodos de persistencia de informacion
     */
    @PersistenceContext
    public EntityManager em;

    /**
     * Metodo que valida si la compra de un comic es o no posible y modifica
     * informacion del comic en caso de que se haga la compra.
     *
     * @param comprarComicDTO DTO que contiene los campo necesarios para itentar o realizar la compra
     * @return DTO con la informacion de la compra
     * @throws java.lang.Exception
     *
     */

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResultadoDTO comprarComic(ComprarComicDTO comprarComicDTO) throws Exception {
        Long idComic = comprarComicDTO.getId();
        Comic comic = null;
        ResultadoDTO resultadoCompra = new ResultadoDTO();
        try {
            comic = this.consultarComicPorId(idComic);

            if (comic.getEstadoEnum() == EstadoEnum.INACTIVO) { //revisa el estado del comic que se quiere comprar
                throw new Exception("El comic seleccionado no cuenta con stock en bodega");
            }
            if (comprarComicDTO.getCantidad() <= comic.getCantidad()) {

                Long cantidad = comic.getCantidad() - comprarComicDTO.getCantidad();
                comic.setCantidad(idComic);
                if (cantidad == 0) {
                    comic.setEstadoEnum(EstadoEnum.INACTIVO);
                }
                comic.setFechaVenta(LocalDate.now());
                resultadoCompra.setExitoso(true);
                resultadoCompra.setMensajeEjecucion("compra exitosa");
            } else {
                throw new Exception("La cantidad existente del comic es:  numeroComics, y supera la ingresada");
            }

        } catch (Exception e) {
            resultadoCompra.setExitoso(false);
            resultadoCompra.setMensajeEjecucion("no se completo la comprar error tecnico");
        }
        return resultadoCompra;
    }

    /**
     *Metodo que obtiene el comic dependiendo del id que le ingresen
     * @param idComic 
     * @return retorna una entidad comic que corresponde al id que le ingresamos como parametro
     */
    private Comic consultarComicPorId(Long idComic) {
        String consultaUnComic = " SELECT c FROM Comic c WHERE c.id = :idComic ";
        Query queryComic = em.createQuery(consultaUnComic);
        queryComic.setParameter("idComic", idComic);
        Comic comic = (Comic) queryComic.getSingleResult();
        return comic;
    }

    /**
     *
     * Metodo encargado de transformar un comic a un comicDTO
     *
     * @param comic
     * @return
     */
    private ComicDTO convertirComicToComicDTO(Comic comic) {
        ComicDTO comicDTO = new ComicDTO();
        comicDTO.setId(comic.getId());
        comicDTO.setNombre(comic.getNombre());
        comicDTO.setEditorial(comic.getEditorial());
        comicDTO.setTematicaEnum(comic.getTematicaEnum());
        comicDTO.setColeccion(comic.getColeccion());
        comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
        comicDTO.setPrecio(comic.getPrecio());
        comicDTO.setAutores(comic.getAutores());
        comicDTO.setColor(comic.getColor());
        comicDTO.setFechaVenta(comic.getFechaVenta());
        comicDTO.setEstadoEnum(comic.getEstadoEnum());
        comicDTO.setCantidad(comic.getCantidad());
        return comicDTO;
    }

    /**
     *
     * Metodo encargado de transformar un comicDTO a un comic
     *
     * @param comic
     * @return
     */
    private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
        Comic comic = new Comic();
        comic.setId(comicDTO.getId());
        comic.setNombre(comicDTO.getNombre());
        comic.setEditorial(comicDTO.getEditorial());
        comic.setTematicaEnum(comicDTO.getTematicaEnum());
        comic.setColeccion(comicDTO.getColeccion());
        comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
        comic.setPrecio(comicDTO.getPrecio());
        comic.setAutores(comicDTO.getAutores());
        comic.setColor(comicDTO.getColor());
        comic.setFechaVenta(comicDTO.getFechaVenta());
        comic.setEstadoEnum(comicDTO.getEstadoEnum());
        comic.setCantidad(comicDTO.getCantidad());
        return comic;
    }

}

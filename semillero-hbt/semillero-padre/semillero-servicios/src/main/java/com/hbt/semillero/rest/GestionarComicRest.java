package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaEditorialComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanoNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import org.json.JSONObject;


@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;

	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}
	
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.crearComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	} 
        
        @GET
        @Path("/consultaEditorialComic")
        @Produces(MediaType.APPLICATION_JSON)
        public ConsultaEditorialComicDTO consultaEditorialComic(@QueryParam("idComic") Long idComic){
            return this.gestionarComicLocal.consultarEditorialComic(idComic);
        }
        /**
         * Consulta que envia un parametro ingresado para saber que nombres son mayores y menores a las listas
         * @param lengthComic tamaño de caracteres
         * @return Json que contiene un objeto que tiene 2 listas una con los nombres de menor tamaño que el parametro
         */
        @GET
        @Path("/consultaTamanoNombreComic")
        @Produces(MediaType.APPLICATION_JSON)
        public ConsultarComicTamanoNombreDTO  consultarComicTamanoNombreDTO(@QueryParam("lengthComic") Short lengthComic){
            return this.gestionarComicLocal.consultarComicTamanoNombreDTO(lengthComic);
        } 
        
        @DELETE
        @Path("/eliminarComic")
        @Produces(MediaType.APPLICATION_JSON)
        public ResultadoDTO eliminarComic(Long idComic){
            //ComicDTO comic = new ComicDTO();
//            comic.setMensajeEjecucion("si entre al api");
//            comic.setExitoso(true);
//            return comic;
            return this.gestionarComicLocal.eliminarComic(idComic);
        }
        
        @PUT
        @Path("editarComic")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public ComicDTO editarComic(ComicDTO comicDTO){
         
         return this.gestionarComicLocal.editarComic(comicDTO);
        }
        
        @GET
        @Path("/consultarComics")
        @Produces(MediaType.APPLICATION_JSON)
        public List<ComicDTO>  consultarComics(){
            return this.gestionarComicLocal.consultarComics();
        } 
        /**
         * Api que contiene la ruta del servicio que llamara al metodo de la interfaz de consultar un comic
         * @param idComic
         * @return 
         */
        @GET
        @Path("/consultarComic")
        @Produces(MediaType.APPLICATION_JSON)
        public ComicDTO consultarComic(@QueryParam("idComic") Long idComic){ 
         
            
            return this.gestionarComicLocal.consultarComic(idComic);
        } 
	//PUT: sirve para indicar que sera una funcion que pretende modificar
}

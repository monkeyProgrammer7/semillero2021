package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaEditorialComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanoNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	@PersistenceContext
	public EntityManager em;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
						+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");	
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return consultaNombrePrecioDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {
		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		
		ComicDTO comicDTOResult = null;
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
                
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente");
		return comicDTOResult;
	}
	/**
         *Metodo que hace una consulta para saber que nombres son iguales o menores y mayores al parametro lengthComic y nos retorna un DTO 
         * @param lengthComic este parametro nos indica un tamaño de cadena
         * @return consultarComicTamanoNombreDTO DTO que contiene 2 listas que nos muestran los nombres que son iguales o menosres al parametro y las que son mayores estan en otra lista
	 */
        @Override
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ConsultarComicTamanoNombreDTO consultarComicTamanoNombreDTO(Short lengthComic) {
        List<String> nombresComic;
        String consulta ="SELECT c.nombre  "
			+ " FROM Comic c  ";
        ConsultarComicTamanoNombreDTO consultarComicTamanoNombreDTO = new ConsultarComicTamanoNombreDTO();
        try{
           Query consultaNativa= em.createQuery(consulta);
           nombresComic =(List<String>) consultaNativa.getResultList();
            for (int i = 0; i < nombresComic.size(); i++) {
                if (nombresComic.get(i).length()<= lengthComic) {
                    consultarComicTamanoNombreDTO.getNombresIgualLongitud().add(nombresComic.get(i));
                }else{
                    consultarComicTamanoNombreDTO.getNombresDistintaLongitud().add(nombresComic.get(i));
                            
                            }
                            
            }
           
           consultarComicTamanoNombreDTO.setExitoso(true);
           consultarComicTamanoNombreDTO.setMensajeEjecucion("Comics procesados exitosamente");
       }catch(Exception e){
        consultarComicTamanoNombreDTO.setExitoso(false);
        consultarComicTamanoNombreDTO.setMensajeEjecucion("no se ejecuto correctamente la consulta, error tecnico");
        }
       return consultarComicTamanoNombreDTO;
    }
    /**
     * 
     */

     /**
      * 
      * @param idComic
      * @return 
      */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConsultaEditorialComicDTO consultarEditorialComic(Long idComic) {
       String consulta ="SELECT new com.hbt.semillero.dto.ConsultaEditorialComicDTO(c.editorial)  "
						+ " FROM Comic c WHERE c.id = :idComic";
       ConsultaEditorialComicDTO consultaEditorialComicDTO = new ConsultaEditorialComicDTO();
       try{
           Query consultaNativa= em.createQuery(consulta);
           consultaNativa.setParameter("idComic", idComic);
           consultaEditorialComicDTO =(ConsultaEditorialComicDTO) consultaNativa.getSingleResult();
           consultaEditorialComicDTO.setExitoso(true);
           consultaEditorialComicDTO.setMensajeEjecucion("Se realizo correctamente la consulta");
       }catch(Exception e){
        consultaEditorialComicDTO.setExitoso(false);
        consultaEditorialComicDTO.setMensajeEjecucion("no se ejecuto correctamente la consulta, error tecnico");
        }
       return consultaEditorialComicDTO;
    }
    /**
     * Servicio para eliminar un comic ingresando su id
     * @param idComic id del comic que vamos a eliminar
     * @return un resultado DTO que contiene los mensajes de exitoso
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ResultadoDTO eliminarComic(Long idComic) {

        if(idComic==null){
        }
       
        
        ResultadoDTO eliminarComicDTO = new ResultadoDTO();
        Comic comicEliminar ;
        
        try{
           
            comicEliminar =this.consultarComicPorId(idComic);
            em.remove(comicEliminar);
            eliminarComicDTO.setExitoso(true);
           eliminarComicDTO.setMensajeEjecucion("Se realizo correctamente la eliminación del comic "+ comicEliminar.getNombre());
        }catch(Exception e){
            eliminarComicDTO.setExitoso(false);
            eliminarComicDTO.setMensajeEjecucion("no se ejecuto correctamente la consulta, error tecnico");
        }
        return eliminarComicDTO;
    }
    
    private Comic consultarComicPorId(Long idComic) {
		String consultaUnComic = " SELECT c FROM Comic c WHERE c.id = :idComic ";
		Query queryComic = em.createQuery(consultaUnComic);
		queryComic.setParameter("idComic", idComic);
		Comic comic = (Comic) queryComic.getSingleResult();
		return comic;
	}
        
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ComicDTO editarComic(ComicDTO comicDTO) {
       
        ComicDTO comicResult = new ComicDTO();
      Long idComic= comicDTO.getId();
        try{
              
        Comic comicReemplazar = this.consultarComicPorId(idComic);
        comicReemplazar.setAutores("su mama");
        //Comic comicReemplazo = this.convertirComicDTOToComic(comicDTO);
        em.merge(comicReemplazar);
        //comicResult = this.convertirComicToComicDTO(comicReemplazo);
        comicResult.setExitoso(true);
        comicResult.setMensajeEjecucion("se reemplazo correctamente el comic ");
        
        }catch(Exception e){
            comicResult.setExitoso(true);
        comicResult.setMensajeEjecucion("se reemplazo correctamente el comic ");
        }
        return comicResult;
    }


	

	
        /**
         * 
         * @return 
         */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {
		String consulta = "SELECT cm FROM Comic cm";
	
		Query queryFindAllComic = em.createQuery(consulta);
		@SuppressWarnings("unchecked")
		List<ComicDTO> listaComics = (List<ComicDTO> )queryFindAllComic.getResultList();
		

		return listaComics;
		
	}
         @Override
         @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ComicDTO consultarComic(Long idComic) {
        ComicDTO  resultado = new ComicDTO();
      
        
        try{
         Comic comic= this.consultarComicPorId(idComic);
       resultado = this.convertirComicToComicDTO(comic);
       resultado.setMensajeEjecucion("se consulto correctamente");
       resultado.setExitoso(true);
        }catch(Exception e){
        resultado.setMensajeEjecucion("error tecnico");
       resultado.setExitoso(false);
        }
      
       return resultado;
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

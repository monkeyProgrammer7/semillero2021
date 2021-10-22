/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semillero.pruebasUnitarias;

import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import java.util.logging.Level;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <b>Descripción:<b> Clase que contiene los test unitarios
 * 
 * @author Geovani Andres Ladino Orrego
 * @version
 */
public class CreacionComicsTest {
    /**
     * Arraylist que contendra los comics inactivos filtrados
     */
    ArrayList<Comic> comicsInactivos;
    /**
     * Constante que contendra el log de la clase AritmeticaTest
     */
    private final static Logger log = Logger.getLogger(CreacionComicsTest.class);
    /**
     * Lista dinamica que contendra los nombres de los comics
     */
    ArrayList<String> nombreComics;
    /**
     * Lista dinamica que contendra los comics
     */
    ArrayList<Comic> comics;
/**
 * metodo que contiene las preparaciones necesarias para el test
 */
    @BeforeTest
    public void inicializar() {
        comics = new ArrayList<Comic>();
        
        Comic comic;
        int random;
        BasicConfigurator.configure(); // Inicializa el logger con una configuracion basica
        log.info("Haciendo Preparaciones para la prueba unitaria");
        nombreComics = new ArrayList<String>();
        //creamos 10 posibles nombres de comics
        nombreComics.add("Tiburon alce");
        nombreComics.add("Hombre oso cerdo");
        nombreComics.add("champivaca");
        nombreComics.add("gato perro");
        nombreComics.add("el hahas");
        nombreComics.add("Dora la usurpadora");
        nombreComics.add("el bulto");
        nombreComics.add("vaca toro");
        nombreComics.add("Marta");
        nombreComics.add("Dulcinea");

        //generamos numeros random enteros del 0 al 9
        for (int i = 0; i < nombreComics.size(); i++) {
            random = (int) (Math.random() * nombreComics.size());
               //Hacemos que si un número random se repite el iterador del ciclo se devuelva y generamos un nuevo numero random
            if (nombreComics.get(random) == "NO") {

                i--;
                continue;
            }
            //asignamos de manera aleatoria el nombre y el estado de los comics
            if (random % 2 == 0) {
                comic = new Comic(nombreComics.get(random), EstadoEnum.ACTIVO);
                comics.add(comic);
                nombreComics.set(random, "NO");
            } else {
                comic = new Comic(nombreComics.get(random), EstadoEnum.INACTIVO);
                comics.add(comic);
                nombreComics.set(random, "NO");
            }

        }
        for (int i = 0; i < nombreComics.size(); i++) {
            log.info("comic " + i + " creado: " + comics.get(i).getNombre() + " " + comics.get(i).getEstadoEnum());
        }
        log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
    }
    /**
     * Metodo que filtra comics con un valor de EstadoEnum especifico
     * @param estado El tipo de enum especifico
     * @return Lista con solo objetos que tienen como valor el tipo enum especifco
     */
    private ArrayList<Comic> filtrarObjetosEnum(EstadoEnum estado) {
        ArrayList<Comic> comicsFiltrados = new ArrayList<Comic>();
        for (int i = 0; i < comics.size(); i++) {
            if (comics.get(i).getEstadoEnum() == estado) {
                comicsFiltrados.add(comics.get(i));
            }
        }
        return comicsFiltrados;

    }
    /**
     * Metodo que entrega un array de comics que tienen el valor de enum ACTIVE
     * @return lista de comics Activos
     */

    private ArrayList<Comic> filtrarComicsActivos() {
        ArrayList<Comic> comicsActivosFiltrados = filtrarObjetosEnum(EstadoEnum.ACTIVO);
        return comicsActivosFiltrados;

    }
     /**
     * Metodo que entrega un array de comics que tienen el valor de enum INACTIVE
     * @return lista de comics inactivos
     */
    private ArrayList<Comic> filtrarComicsInactivos() throws Exception{
        ArrayList<Comic> comicsInactivosFiltrados = filtrarObjetosEnum(EstadoEnum.INACTIVO);
        
        return comicsInactivosFiltrados;

    }
        /**
         * Prueba unitaria para saber si el filtrado de comics con el estado activo es correcto
         */
    @Test
    public void validarFiltradoComicsActivos() {
        log.info("Inicia ejecucion del metodo validarFiltradoComicsActivos()");
        ArrayList<Comic> comicsActivos = filtrarComicsActivos();

        for (int i = 0; i < comicsActivos.size(); i++) {
            try {

                Assert.assertEquals(comicsActivos.get(i).getEstadoEnum(), EstadoEnum.ACTIVO);
                System.out.println("comics activos : " + comicsActivos.get(i).getNombre() + " " + comicsActivos.get(i).getEstadoEnum());
            } catch (Exception e) {
                Assert.assertEquals(e.getMessage(), "Ha fallado el filtro de comics activos");
            }

        }
    }
    /**
     * Test para capturar la exception arrojada por el metodo que filtra los comics inactivos1
     */
       @Test
    public void validarFiltradoComicsInactivos()  {
        
        log.info("Inicia ejecucion del metodo validarFiltradoComicsInactivos()");
        String nombresInactivos ="";
        
        try {
            comicsInactivos = filtrarComicsInactivos();
           
            throw new Exception("Se ha detectado que de " + comics.size() + " comics se encontraron que " + (comics.size()-comicsInactivos.size()) + " se encuentran activos y " + comicsInactivos.size() + " inactivos. Los comics inactivos son: "+"\n");
            
        } catch (Exception ex) {
            for (int i = 0; i < comicsInactivos.size(); i++) {
                nombresInactivos.concat(i+" "+comicsInactivos.get(i).getNombre()+"\n");
            }
            System.out.println(ex.getMessage()+nombresInactivos);
        }

       
          

    }

}

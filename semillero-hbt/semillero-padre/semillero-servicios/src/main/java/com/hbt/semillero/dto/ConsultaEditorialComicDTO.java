/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.semillero.dto;

/**
 *
 * @author geo-1
 */
public class ConsultaEditorialComicDTO extends ResultadoDTO{
         /**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
        /**
         * 
         */
        private String editorial;
        /**
         * 
         */
        public ConsultaEditorialComicDTO(){
            //constructor vacio
        }
        /**
         * 
         * @param editorial 
         */
         public ConsultaEditorialComicDTO(String editorial){
            this.editorial= editorial;
        }
         /**
          * 
          * @return 
          */
         public String getEditorial() {
            return editorial;
        }
        /**
         * 
         * @param editorial 
         */
        public void setEditorial(String editorial) {
            this.editorial = editorial;
        }
         
}

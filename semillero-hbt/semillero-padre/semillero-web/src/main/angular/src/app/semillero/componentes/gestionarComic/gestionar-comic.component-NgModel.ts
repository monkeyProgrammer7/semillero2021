import { Component, OnInit } from '@angular/core';
import { ComicDTO } from '../../dto/comic-dto';
/**
 * @description componente encargado de gestionar la logica para crear consultar acualizar y eliminar un comic
 * @author Geovani Ladino
 */
@Component({
  selector: 'gestionar-comic',
  templateUrl: './gestionar-comic.component.html',
  
})

export class GestionarComicComponent implements OnInit {
  public comicDTO : ComicDTO;
  public listaComics: Array <ComicDTO>;
  public comicDTOInfo: ComicDTO;
  public mostrarItem: boolean;
  constructor() { }

  ngOnInit() {
    this.comicDTO=new ComicDTO();
    this.listaComics= new Array<ComicDTO>();
  }
  public crearComic():void{
    
    this.listaComics.push(this.comicDTO);
    this.comicDTO=new ComicDTO();
  }

  public imprimirInfoComic(posicion : number) : void {
    this.mostrarItem = true;
    this.comicDTOInfo = this.listaComics[posicion];
  }

  public cerrar() : void {
    this.mostrarItem = false;
  }
}

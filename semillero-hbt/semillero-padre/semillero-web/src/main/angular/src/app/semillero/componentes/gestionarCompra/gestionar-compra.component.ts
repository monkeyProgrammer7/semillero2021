import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ComicDTO } from '../../dto/comic-dto';
import { ComprarComicDTO } from '../../dto/comprar-comic-dto';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';

@Component({
  selector: 'gestionar-compra',
  templateUrl: './gestionar-compra.component.html',
  
})
export class GestionarCompraComponent implements OnInit {
  public comicDTO : ComicDTO;
  public comprarComicDTO : ComprarComicDTO;
  public gestionarComicForm : FormGroup;
  constructor(private fb : FormBuilder, private router : Router, private activatedRoute: ActivatedRoute, private gestionComicsService : GestionarComicService) {
    this.gestionarComicForm = this.fb.group({
      cantidad : [null, Validators.required]});

   }
  
  ngOnInit() {
    let comic : any = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
    this.comprarComicDTO.id = this.comicDTO.id;
  }
  public comprarComic() {
    this.comprarComicDTO.cantidadComprar = this.f.cantidad.value;
    this.gestionComicsService.comprarComic(this.comprarComicDTO).subscribe(data => {
      if (data[0].exitoso) {
        console.log("Venta exitosa");
      } else {
        console.log(data[0].mensajeEjecucion);
      }
    }, error => {
      console.log(error);
    });

  }
  get f() {
    return this.gestionarComicForm.controls;
  }

}

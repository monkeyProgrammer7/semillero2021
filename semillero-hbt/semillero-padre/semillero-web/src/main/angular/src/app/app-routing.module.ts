import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionarCompraComponent } from './semillero/componentes/gestionarCompra/gestionar-compra.component';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';

const routes: Routes = [
  { path: 'bienvenida', component: BienvenidaComponent },
  { path: 'gestionar-comic', component: GestionarCompraComponent },
  { path: 'gestionar-compra', component: GestionarCompraComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

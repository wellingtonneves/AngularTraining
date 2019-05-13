import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteComponent } from './clientes/cliente/cliente.component';
import { ClienteListComponent } from './clientes/cliente-list/cliente-list.component';
import { ClienteService } from './shared/cliente.service';
import { HttpClientModule } from '@angular/common/http'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
//import { Observable } from 'rxjs/Observable';

//import { ConfirmModule} from 'angular-confirm';
import { ModalOptionsComponent } from './modal-options/modal-options.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PipeModule } from './pipes/pipe.module';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    ClienteComponent,
    ClienteListComponent,
    ModalOptionsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    //ConfirmModule,
    NgbModule.forRoot(),
    PipeModule
  ],
  providers: [ClienteService],
  bootstrap: [AppComponent],
  entryComponents: [
    ModalOptionsComponent
  ]
})
export class AppModule { }

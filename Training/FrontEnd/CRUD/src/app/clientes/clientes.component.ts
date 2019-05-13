import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../shared/cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  constructor(private serivice: ClienteService) { }

  ngOnInit() {
  }

}

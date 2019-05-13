import { Injectable } from '@angular/core';
import { Cliente } from './cliente.model';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  formData : Cliente;
  readonly rootURL: String = "http://localhost:8081/clientes";
  list: Cliente[];

  constructor(private http: HttpClient) { }

  postCliente(formData: Cliente){   
    return this.http.post(this.rootURL + '/add', formData);
  }  

  putCliente(formData: Cliente){   
    return this.http.put(this.rootURL + '/update/' + formData.id, formData);
  }  

  delCliente(id: number){   
    return this.http.delete(this.rootURL + '/delete/' + id);
  }  

  getListaClientes(){
    return this.http.get(this.rootURL + '/list')
    .toPromise().then(res => this.list = res as Cliente[]);
  }
}



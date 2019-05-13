import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/shared/cliente.service';
import { NgForm } from '@angular/forms';
import { NullAstVisitor } from '@angular/compiler';
import { Cliente } from 'src/app/shared/cliente.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {

  constructor(private service: ClienteService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this.resetForm();
  }

  onSubmit(form: NgForm){
    if (form.value.id == null){
      this.insert(form);
    }
    else{
      this.update(form);      
    }
  }

  insert(form: NgForm){
    this.service.postCliente(form.value).subscribe(() => {
      this.toastr.success('Cliente inserido com sucesso', 'Cadastro básicos');
      this.resetForm(form);
      this.service.getListaClientes();
    }, erro => console.log(erro));
  }

  update(form: NgForm){
    this.service.putCliente(form.value).subscribe(() => {
      this.toastr.warning('Cliente atualizado com sucesso', 'Cadastro básicos');      
      this.resetForm();
      this.service.getListaClientes();
    }, erro => console.log(erro));
  }

  

  resetForm(form?: NgForm){
    if (form != null)
      form.resetForm();

    this.service.formData = {
      id: null,
      nome: '',
      ultimoNome: '',
      telefone: '',
      ativo: false,
      dataCadastro: null
    }    
  }
}

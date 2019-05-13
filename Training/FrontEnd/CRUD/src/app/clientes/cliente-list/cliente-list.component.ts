import { Component, OnInit  } from '@angular/core';
import { ClienteService } from 'src/app/shared/cliente.service';
import { Cliente } from 'src/app/shared/cliente.model';
import { ToastrService } from 'ngx-toastr';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalOptionsComponent } from '../../modal-options/modal-options.component';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.scss']
})
export class ClienteListComponent implements OnInit {

  constructor(private service: ClienteService,
              private toastr: ToastrService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.service.getListaClientes();    
  }

  popularForm(cli: Cliente){
    this.service.formData = Object.assign({}, cli);
  }

  openFormModal() {
    
  }

  onDeletar(id: number){    
    const modalRef = this.modalService.open(ModalOptionsComponent);

    modalRef.componentInstance.confirmTitle = "Clientes"
    modalRef.componentInstance.confirmMessage = "Deseja realmente excluir este cliente?"

    modalRef.result.then((result) => {
      switch (result) {
        case "Ok":
          this.service.delCliente(id).subscribe(()=> {
              this.service.getListaClientes();
              this.toastr.info('Cliente deletado com sucesso!', 'Cadastro básicos');      
          }); 
        case "Cancelar": 
            break;
        default:            
      }
     }).catch((error) => {
       console.log(error);
     });  
  }
}

package crudclientes.clientes.resources;

import crudclientes.clientes.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import crudclientes.clientes.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource
{
    @Autowired
    private ClienteRepository cli;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = cli.findAll();

        if (clientes == null || clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable("id") Long id){
        Optional<Cliente> cliente = cli.findById(id);

        if (!cliente.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Long> salvar (@RequestBody Cliente cliente){
        Cliente clienteSave = cli.save(cliente);
        return new ResponseEntity<Long>(clienteSave.getId(), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> alteracliente = cli.findById(id);
        if (!alteracliente.isPresent()){
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }

        cliente.setId(alteracliente.get().getId());
        cliente.setDataCadastro(alteracliente.get().getDataCadastro());
        cli.save(cliente);

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id){
        Optional<Cliente> removecliente = cli.findById(id);
        if (!removecliente.isPresent()){
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }

        cli.deleteById(id);
        return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
    }
}

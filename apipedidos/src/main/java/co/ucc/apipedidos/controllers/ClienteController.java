package co.ucc.apipedidos.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.ucc.apipedidos.dto.ClienteDTO;
import co.ucc.apipedidos.models.Cliente;
import co.ucc.apipedidos.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody ClienteDTO dto) {
        Cliente cliente = clienteService.crearCliente(dto.getNombre(), dto.getCorreo());
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }
}
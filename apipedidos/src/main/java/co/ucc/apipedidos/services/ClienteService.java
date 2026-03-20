// ClienteService.java
package co.ucc.apipedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Cliente;
import co.ucc.apipedidos.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(String nombre, String correo) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (correo == null || !correo.contains("@"))
            throw new IllegalArgumentException("Correo invalido");
        clienteRepository.findByCorreo(correo).ifPresent(c -> {
            throw new IllegalStateException("Ya existe un cliente con ese correo");
        });
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }

    public Cliente buscarPorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + correo));
    }
}
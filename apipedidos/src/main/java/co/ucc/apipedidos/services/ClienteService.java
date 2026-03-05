package co.ucc.apipedidos.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Cliente;

@Service
public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();
    private int contadorId = 1;

    public Cliente crearCliente(String nombre, String correo) {
        Cliente cliente = new Cliente(contadorId++, nombre, correo);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return Collections.unmodifiableList(clientes);
    }
}
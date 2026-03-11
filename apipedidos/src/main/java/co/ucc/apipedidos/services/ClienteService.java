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
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        Cliente cliente = new Cliente(contadorId++, nombre, correo);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return Collections.unmodifiableList(clientes);
    }

    public Cliente buscarPorId(int idCliente) {
        return clientes.stream()
                .filter(c -> c.getIdCliente() == idCliente)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Cliente no encontrado con id: " + idCliente));
    }
}
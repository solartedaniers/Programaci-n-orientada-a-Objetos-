// ClienteService.java
package co.ucc.apipedidos.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Cliente;
import co.ucc.apipedidos.repositories.ClienteRepository;

@Service
public class ClienteService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(
            String nombre,
            String correo,
            String genero,
            String numeroIdentificacion,
            String tipoIdentificacion) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (correo == null || !EMAIL_PATTERN.matcher(correo).matches())
            throw new IllegalArgumentException("Correo invalido");
        if (genero == null || genero.isBlank())
            throw new IllegalArgumentException("El genero es obligatorio");
        if (numeroIdentificacion == null || numeroIdentificacion.isBlank())
            throw new IllegalArgumentException("El numero de identificacion es obligatorio");
        if (tipoIdentificacion == null || tipoIdentificacion.isBlank())
            throw new IllegalArgumentException("El tipo de identificacion es obligatorio");
        clienteRepository.findByCorreo(correo).ifPresent(c -> {
            throw new IllegalStateException("Ya existe un cliente con ese correo");
        });
        clienteRepository.findByNumeroIdentificacion(numeroIdentificacion).ifPresent(c -> {
            throw new IllegalStateException("Ya existe un cliente con ese numero de identificacion");
        });
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);
        cliente.setGenero(genero);
        cliente.setNumeroIdentificacion(numeroIdentificacion);
        cliente.setTipoIdentificacion(tipoIdentificacion);
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

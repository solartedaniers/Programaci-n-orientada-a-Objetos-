// EnvioService.java
package co.ucc.apipedidos.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Envio;
import co.ucc.apipedidos.models.EnvioDron;
import co.ucc.apipedidos.models.EnvioEstandar;
import co.ucc.apipedidos.models.EnvioExpress;
import co.ucc.apipedidos.models.EnvioInternacional;
import co.ucc.apipedidos.models.enums.TipoEnvio;
import co.ucc.apipedidos.repositories.EnvioRepository;
import co.ucc.apipedidos.repositories.PedidoRepository;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;
    private final PedidoRepository pedidoRepository;

    public EnvioService(EnvioRepository envioRepository, PedidoRepository pedidoRepository) {
        this.envioRepository = envioRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Envio crearEnvio(int idPedido, double peso, double volumen, TipoEnvio tipo) {
        pedidoRepository.findById(idPedido)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + idPedido));
        if (peso <= 0)
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        if (!envioRepository.findByIdPedido(idPedido).isEmpty())
            throw new IllegalStateException("El pedido ya tiene un envio asignado");
        Envio envio = fabricarEnvio(idPedido, peso, volumen, Objects.requireNonNull(tipo, "El tipo de envio es obligatorio"));
        return envioRepository.save(envio);
    }

    public double calcularCostoEnvio(int idEnvio) {
        return buscarEnvio(idEnvio).calcularCosto();
    }

    public List<Envio> listarEnvios() {
        return envioRepository.findAll();
    }

    public Envio buscarEnvio(int id) {
        return envioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado: " + id));
    }

    private Envio fabricarEnvio(int idPedido, double peso, double volumen, TipoEnvio tipo) {
        Envio e = switch (tipo) {
            case ESTANDAR -> new EnvioEstandar();
            case EXPRESS -> new EnvioExpress();
            case INTERNACIONAL -> new EnvioInternacional();
            case DRON -> new EnvioDron();
        };
        e.setIdPedido(idPedido);
        e.setPeso(peso);
        e.setVolumen(volumen);
        return e;
    }
}

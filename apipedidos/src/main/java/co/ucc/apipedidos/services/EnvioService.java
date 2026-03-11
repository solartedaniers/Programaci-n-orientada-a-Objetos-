package co.ucc.apipedidos.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Envio;
import co.ucc.apipedidos.models.EnvioEstandar;
import co.ucc.apipedidos.models.EnvioExpres;
import co.ucc.apipedidos.models.EnvioInternacional;
import co.ucc.apipedidos.models.EnvioPorDron;
import co.ucc.apipedidos.models.enums.TipoEnvio;

/**
 * Servicio que gestiona los envíos.
 *
 * POLIMORFISMO: List<Envio> contiene cualquier subclase.
 *   calcularCostoEnvio() invoca envio.calcularCosto() sin conocer el tipo concreto.
 */
@Service
public class EnvioService {

    private final List<Envio> envios = new ArrayList<>();
    private int contadorId = 1;

    @Autowired
    private PedidoService pedidoService;

    public Envio crearEnvio(int idPedido, double peso, double volumen, TipoEnvio tipo) {
        pedidoService.buscarPorId(idPedido);

        if (peso <= 0 || volumen <= 0) {
            throw new IllegalArgumentException(
                "El peso y el volumen deben ser mayores que cero.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de envío no puede ser nulo.");
        }

        Envio envio = fabricarEnvio(contadorId++, peso, volumen, idPedido, tipo);
        envios.add(envio);
        return envio;
    }

    /** Rule switch (Java 14+): instancia la subclase correcta. */
    private Envio fabricarEnvio(int id, double peso, double volumen,
                                 int idPedido, TipoEnvio tipo) {
        return switch (tipo) {
            case ESTANDAR      -> new EnvioEstandar(id, peso, volumen, idPedido);
            case EXPRES        -> new EnvioExpres(id, peso, volumen, idPedido);
            case INTERNACIONAL -> new EnvioInternacional(id, peso, volumen, idPedido);
            case DRON          -> new EnvioPorDron(id, peso, volumen, idPedido);
        };
    }

    /**
     * POLIMORFISMO: llama calcularCosto() sobre referencia Envio abstracta.
     * El objeto real ejecuta su propia fórmula sin que el service la conozca.
     */
    public double calcularCostoEnvio(int idEnvio) {
        return buscarPorId(idEnvio).calcularCosto();
    }

    public List<Envio> listarEnvios() {
        return Collections.unmodifiableList(envios);
    }

    public Envio buscarPorId(int idEnvio) {
        return envios.stream()
                .filter(e -> e.getIdEnvio() == idEnvio)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Envío no encontrado con id: " + idEnvio));
    }
}
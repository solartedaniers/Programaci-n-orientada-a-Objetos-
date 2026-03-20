// EnvioService.java
package co.ucc.apipedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Envio crearEnvio(int idPedido, double peso, double volumen, TipoEnvio tipo) {
        pedidoRepository.findById(idPedido)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + idPedido));
        if (peso <= 0)
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        if (!envioRepository.findByIdPedido(idPedido).isEmpty())
            throw new IllegalStateException("El pedido ya tiene un envio asignado");
        Envio envio = fabricarEnvio(idPedido, peso, volumen, tipo);
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
        Envio e;
        switch (tipo) {
            case ESTANDAR:      e = new EnvioEstandar();      break;
            case EXPRESS:       e = new EnvioExpress();       break;
            case INTERNACIONAL: e = new EnvioInternacional(); break;
            case DRON:          e = new EnvioDron();          break;
            default: throw new IllegalArgumentException("Tipo de envio no valido");
        }
        e.setIdPedido(idPedido);
        e.setPeso(peso);
        e.setVolumen(volumen);
        return e;
    }
}
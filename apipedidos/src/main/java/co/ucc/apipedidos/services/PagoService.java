package co.ucc.apipedidos.services;

import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.enums.EstadoPago;
import co.ucc.apipedidos.models.enums.MetodoPago;
import co.ucc.apipedidos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago pagar(MetodoPago metodo) {
        Pago pago = new Pago(metodo);
        return pagoRepository.save(pago);
    }

    public void procesarPago(int idPago) {
        Pago pago = pagoRepository.findById(idPago).orElseThrow();
        pago.setEstado(EstadoPago.APROBADO);
        pagoRepository.save(pago);
    }
}
// Pago.java
package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.EstadoTransaccion;
import co.ucc.apipedidos.models.enums.MetodoPago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("PAGO")
public class Pago extends Transaccion {

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    public MetodoPago getMetodoPago()        { return metodoPago; }
    public void setMetodoPago(MetodoPago m)  { this.metodoPago = m; }

    @Override
    public void procesar() {
        setEstado(EstadoTransaccion.PROCESADO);
    }
}
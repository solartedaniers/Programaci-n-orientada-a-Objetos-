// Devolucion.java
package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.EstadoTransaccion;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEVOLUCION")
public class Devolucion extends Transaccion {

    @Column
    private String motivo;

    public String getMotivo()        { return motivo; }
    public void setMotivo(String m)  { this.motivo = m; }

    @Override
    public void procesar() {
        setEstado(EstadoTransaccion.REEMBOLSADO);
    }
}
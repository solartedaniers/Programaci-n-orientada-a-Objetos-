// EnvioInternacional.java
package co.ucc.apipedidos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INTERNACIONAL")
public class EnvioInternacional extends Envio {

    @Override
    public double calcularCosto() {
        return getPeso() * 15000;
    }
}
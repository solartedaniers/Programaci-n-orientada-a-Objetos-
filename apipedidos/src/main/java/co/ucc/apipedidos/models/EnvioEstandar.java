// EnvioEstandar.java
package co.ucc.apipedidos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ESTANDAR")
public class EnvioEstandar extends Envio {

    @Override
    public double calcularCosto() {
        return getPeso() * 5000;
    }
}
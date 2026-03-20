// EnvioDron.java
package co.ucc.apipedidos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DRON")
public class EnvioDron extends Envio {

    @Override
    public double calcularCosto() {
        return getPeso() * 20000;
    }
}
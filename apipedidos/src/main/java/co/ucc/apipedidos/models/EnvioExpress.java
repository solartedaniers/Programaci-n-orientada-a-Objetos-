// EnvioExpress.java
package co.ucc.apipedidos.models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EXPRESS")
public class EnvioExpress extends Envio {

    @Override
    public double calcularCosto() {
        return getPeso() * 8000;
    }
}
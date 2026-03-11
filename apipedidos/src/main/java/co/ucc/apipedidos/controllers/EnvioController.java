package co.ucc.apipedidos.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.ucc.apipedidos.dto.EnvioDTO;
import co.ucc.apipedidos.models.Envio;
import co.ucc.apipedidos.services.EnvioService;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    /**
     * Crea un envío del tipo indicado para un pedido.
     * tipoEnvio acepta: ESTANDAR, EXPRES, INTERNACIONAL, DRON
     */
    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody EnvioDTO dto) {
        Envio envio = envioService.crearEnvio(
            dto.getIdPedido(), dto.getPeso(), dto.getVolumen(), dto.getTipoEnvio());
        return ResponseEntity.status(HttpStatus.CREATED).body(envio);
    }

    /**
     * Calcula el costo de un envío ya creado.
     * POLIMORFISMO: envio.calcularCosto() ejecuta la fórmula de la subclase real.
     */
    @GetMapping("/{idEnvio}/costo")
    public ResponseEntity<Double> calcularCosto(@PathVariable int idEnvio) {
        return ResponseEntity.ok(envioService.calcularCostoEnvio(idEnvio));
    }

    @GetMapping
    public ResponseEntity<List<Envio>> listar() {
        return ResponseEntity.ok(envioService.listarEnvios());
    }
}
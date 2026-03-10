package co.ucc.apipedidos.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RuntimeException ex) {
        return construirRespuesta("Recurso no encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarSolicitudInvalida(IllegalArgumentException ex) {
        return construirRespuesta("Solicitud invalida", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> manejarConflicto(IllegalStateException ex) {
        return construirRespuesta("Conflicto de estado", ex.getMessage(), HttpStatus.CONFLICT);
    }

    private ResponseEntity<Map<String, Object>> construirRespuesta(
            String error, String mensaje, HttpStatus status) {

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("status",    status.value());
        respuesta.put("error",     error);
        respuesta.put("mensaje",   mensaje);
        respuesta.put("timestamp", LocalDateTime.now().toString());

        HttpStatusCode codigo = HttpStatusCode.valueOf(status.value());
        return new ResponseEntity<Map<String, Object>>(respuesta, codigo);
    }
}
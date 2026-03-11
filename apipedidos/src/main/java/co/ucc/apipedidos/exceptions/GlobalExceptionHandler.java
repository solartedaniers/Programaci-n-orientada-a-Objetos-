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
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("status",    status.value());
        cuerpo.put("error",     error);
        cuerpo.put("mensaje",   mensaje);
        cuerpo.put("timestamp", LocalDateTime.now().toString());
        return new ResponseEntity<>(cuerpo, HttpStatusCode.valueOf(status.value()));
    }
}
package co.ucc.apipedidos.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(
            RuntimeException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error",     "Recurso no encontrado");
        respuesta.put("mensaje",   ex.getMessage());
        respuesta.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarSolicitudInvalida(
            IllegalArgumentException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error",     "Solicitud invalida");
        respuesta.put("mensaje",   ex.getMessage());
        respuesta.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> manejarConflicto(
            IllegalStateException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error",     "Conflicto de estado");
        respuesta.put("mensaje",   ex.getMessage());
        respuesta.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }
}
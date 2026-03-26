# Guia de pruebas en Postman

## Configuracion base

- Base URL: `http://localhost:8081`
- Header para `POST` y `PUT`:

```http
Content-Type: application/json
```

Puedes crear una variable en Postman:

```text
{{baseUrl}} = http://localhost:8081
```

## Valores permitidos en enums

### Tipo de envio

```text
ESTANDAR
EXPRESS
INTERNACIONAL
DRON
```

### Metodo de pago

```text
TARJETA
PSE
EFECTIVO
```

## Orden recomendado para probar toda la API

### 1. Listar productos

```http
GET {{baseUrl}}/productos
```

Uso: revisar los `id` de productos antes de agregarlos a un pedido.

### 2. Listar clientes

```http
GET {{baseUrl}}/clientes
```

### 3. Crear cliente

```http
POST {{baseUrl}}/clientes
```

Body:

```json
{
  "nombre": "Juan Perez",
  "correo": "juan@mail.com"
}
```

Esperado: `201 Created`

### 4. Crear pedido

```http
POST {{baseUrl}}/pedidos
```

Body:

```json
{
  "idCliente": 1
}
```

Esperado: `201 Created`

### 5. Listar pedidos

```http
GET {{baseUrl}}/pedidos
```

### 6. Consultar stock de un producto

```http
GET {{baseUrl}}/inventario/1/stock
```

### 7. Verificar si hay stock

```http
GET {{baseUrl}}/inventario/1/haystock?cantidad=2
```

### 8. Agregar stock

```http
PUT {{baseUrl}}/inventario/1/agregar
```

Body:

```json
{
  "cantidad": 10
}
```

Esperado: `200 OK`

### 9. Descontar stock

```http
PUT {{baseUrl}}/inventario/1/descontar
```

Body:

```json
{
  "cantidad": 2
}
```

Esperado: `200 OK`

### 10. Agregar producto a un pedido

```http
POST {{baseUrl}}/pedidos/1/productos
```

Body:

```json
{
  "idProducto": 1,
  "cantidad": 2
}
```

Esperado: `200 OK`

### 11. Ver resumen del pedido

```http
GET {{baseUrl}}/pedidos/1/resumen
```

### 12. Ver total del pedido

```http
GET {{baseUrl}}/pedidos/1/total
```

### 13. Crear envio

```http
POST {{baseUrl}}/envios
```

Body:

```json
{
  "idPedido": 1,
  "peso": 2.5,
  "volumen": 1.2,
  "tipoEnvio": "EXPRESS"
}
```

Esperado: `201 Created`

### 14. Listar envios

```http
GET {{baseUrl}}/envios
```

### 15. Calcular costo de envio

```http
GET {{baseUrl}}/envios/1/costo
```

### 16. Registrar pago

```http
POST {{baseUrl}}/transacciones/pagos
```

Body:

```json
{
  "idPedido": 1,
  "monto": 50000,
  "metodo": "TARJETA"
}
```

Esperado: `201 Created`

### 17. Registrar devolucion

```http
POST {{baseUrl}}/transacciones/devoluciones
```

Body:

```json
{
  "idPedido": 1,
  "monto": 20000,
  "motivo": "Producto defectuoso"
}
```

Esperado: `201 Created`

### 18. Listar transacciones

```http
GET {{baseUrl}}/transacciones
```

### 19. Procesar transaccion

```http
PUT {{baseUrl}}/transacciones/1/procesar
```

Esperado: `200 OK`

## Pruebas de error recomendadas

### 1. Cliente con correo invalido

```http
POST {{baseUrl}}/clientes
```

Body:

```json
{
  "nombre": "Pedro",
  "correo": "pedrocorreo.com"
}
```

Esperado: `400 Bad Request`

### 2. Cliente repetido con el mismo correo

```http
POST {{baseUrl}}/clientes
```

Body:

```json
{
  "nombre": "Juan Perez",
  "correo": "juan@mail.com"
}
```

Esperado: `409 Conflict`

### 3. Crear pedido con cliente inexistente

```http
POST {{baseUrl}}/pedidos
```

Body:

```json
{
  "idCliente": 999
}
```

Esperado: `404 Not Found`

### 4. Agregar producto con stock insuficiente

```http
POST {{baseUrl}}/pedidos/1/productos
```

Body:

```json
{
  "idProducto": 1,
  "cantidad": 9999
}
```

Esperado: `409 Conflict`

### 5. Agregar stock con cantidad invalida

```http
PUT {{baseUrl}}/inventario/1/agregar
```

Body:

```json
{
  "cantidad": 0
}
```

Esperado: `400 Bad Request`

### 6. Descontar stock con cantidad invalida

```http
PUT {{baseUrl}}/inventario/1/descontar
```

Body:

```json
{
  "cantidad": -1
}
```

Esperado: `400 Bad Request`

### 7. Crear envio con peso invalido

```http
POST {{baseUrl}}/envios
```

Body:

```json
{
  "idPedido": 1,
  "peso": 0,
  "volumen": 1.2,
  "tipoEnvio": "ESTANDAR"
}
```

Esperado: `400 Bad Request`

### 8. Crear segundo envio para el mismo pedido

```http
POST {{baseUrl}}/envios
```

Body:

```json
{
  "idPedido": 1,
  "peso": 1.5,
  "volumen": 1.0,
  "tipoEnvio": "DRON"
}
```

Esperado: `409 Conflict`

### 9. Registrar pago con monto negativo

```http
POST {{baseUrl}}/transacciones/pagos
```

Body:

```json
{
  "idPedido": 1,
  "monto": -5000,
  "metodo": "PSE"
}
```

Esperado: `400 Bad Request`

### 10. Registrar devolucion sin motivo

```http
POST {{baseUrl}}/transacciones/devoluciones
```

Body:

```json
{
  "idPedido": 1,
  "monto": 10000,
  "motivo": ""
}
```

Esperado: `400 Bad Request`

### 11. Consultar recurso inexistente

```http
GET {{baseUrl}}/envios/999/costo
```

Esperado: `404 Not Found`

## Ejemplo de respuesta de error

```json
{
  "status": 400,
  "error": "Solicitud invalida",
  "mensaje": "El monto debe ser positivo",
  "timestamp": "2026-03-26T10:00:00"
}
```

# Guia de pruebas en Postman

## Configuracion base

- Base URL: `http://localhost:8081`
- Header para `POST` y `PUT`:

```http
Content-Type: application/json
```

Puedes crear esta variable en Postman:

```text
{{baseUrl}} = http://localhost:8081
```

## Campos libres del cliente

### Genero

Ahora `genero` es texto libre. Ejemplos:

```text
Masculino
Femenino
No binario
Prefiero no decirlo
Otro
```

### Tipo de identificacion

Ahora `tipoIdentificacion` tambien es texto libre. Ejemplos:

```text
Cedula
Pasaporte
Tarjeta de identidad
NIT
Cedula de extranjeria
```

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

## Orden recomendado de pruebas

### 1. Crear cliente

```http
POST {{baseUrl}}/clientes
```

```json
{
  "nombre": "Juan Perez",
  "correo": "juan@mail.com",
  "genero": "Masculino",
  "numeroIdentificacion": "1234567890",
  "tipoIdentificacion": "Pasaporte"
}
```

### 2. Listar clientes

```http
GET {{baseUrl}}/clientes
```

### 3. Listar productos

```http
GET {{baseUrl}}/productos
```

### 4. Consultar stock

```http
GET {{baseUrl}}/inventario/1/stock
```

### 5. Verificar stock

```http
GET {{baseUrl}}/inventario/1/haystock?cantidad=2
```

### 6. Agregar stock

```http
PUT {{baseUrl}}/inventario/1/agregar
```

```json
{
  "cantidad": 10
}
```

### 7. Crear pedido

```http
POST {{baseUrl}}/pedidos
```

```json
{
  "idCliente": 1
}
```

### 8. Agregar producto al pedido

```http
POST {{baseUrl}}/pedidos/1/productos
```

```json
{
  "idProducto": 1,
  "cantidad": 2
}
```

### 9. Ver resumen del pedido

```http
GET {{baseUrl}}/pedidos/1/resumen
```

### 10. Ver total del pedido

```http
GET {{baseUrl}}/pedidos/1/total
```

### 11. Crear envio

```http
POST {{baseUrl}}/envios
```

```json
{
  "idPedido": 1,
  "peso": 2.5,
  "volumen": 1.2,
  "tipoEnvio": "EXPRESS"
}
```

### 12. Listar envios

```http
GET {{baseUrl}}/envios
```

### 13. Calcular costo del envio

```http
GET {{baseUrl}}/envios/1/costo
```

### 14. Registrar pago

```http
POST {{baseUrl}}/transacciones/pagos
```

```json
{
  "idPedido": 1,
  "monto": 50000,
  "metodo": "TARJETA"
}
```

### 15. Registrar devolucion

```http
POST {{baseUrl}}/transacciones/devoluciones
```

```json
{
  "idPedido": 1,
  "monto": 20000,
  "motivo": "Producto defectuoso"
}
```

### 16. Listar transacciones

```http
GET {{baseUrl}}/transacciones
```

### 17. Procesar transaccion

```http
PUT {{baseUrl}}/transacciones/1/procesar
```

## Pruebas de validacion

### Correo invalido

```http
POST {{baseUrl}}/clientes
```

```json
{
  "nombre": "Pedro",
  "correo": "pedromail.com",
  "genero": "No binario",
  "numeroIdentificacion": "99887766",
  "tipoIdentificacion": "Cedula"
}
```

Esperado: `400 Bad Request`

### Correo repetido

```http
POST {{baseUrl}}/clientes
```

```json
{
  "nombre": "Juan Repetido",
  "correo": "juan@mail.com",
  "genero": "Masculino",
  "numeroIdentificacion": "11111111",
  "tipoIdentificacion": "Cedula"
}
```

Esperado: `409 Conflict`

### Numero de identificacion repetido

```http
POST {{baseUrl}}/clientes
```

```json
{
  "nombre": "Maria Lopez",
  "correo": "maria@mail.com",
  "genero": "Femenino",
  "numeroIdentificacion": "1234567890",
  "tipoIdentificacion": "Cedula"
}
```

Esperado: `409 Conflict`

### Genero faltante

```http
POST {{baseUrl}}/clientes
```

```json
{
  "nombre": "Ana",
  "correo": "ana@mail.com",
  "numeroIdentificacion": "77777777",
  "tipoIdentificacion": "CEDULA"
}
```

Esperado: `400 Bad Request`

### Tipo de identificacion faltante

```http
POST {{baseUrl}}/clientes
```

```json
{
  "nombre": "Luis",
  "correo": "luis@mail.com",
  "genero": "MASCULINO",
  "numeroIdentificacion": "33333333"
}
```

Esperado: `400 Bad Request`



1. Objetivo del Taller
Diseñar e implementar un módulo de pagos dentro del sistema de pedidos, aplicando correctamente los modificadores de acceso (private, public, protected y default/package-private) en un proyecto Spring Boot organizado por capas. El objetivo no es únicamente que funcione, sino que esté correctamente protegido y diseñado de forma profesional.

2. Arquitectura del Proyecto
El proyecto sigue una arquitectura de tres capas con responsabilidad única por capa:

co.ucc.apipedidos/
├── controllers/      ← Solo maneja HTTP, sin lógica de negocio
├── services/         ← Lógica de negocio y listas en memoria
├── models/           ← Datos y reglas de dominio
│   └── enums/        ← EstadoPago, EstadoPedido, MetodoPago
└── exceptions/       ← Manejo global de errores


3. Diagrama de Clases por Capa
Capa Model

┌─────────────────────────────────────────────────┐
│                     Pago                         │
├─────────────────────────────────────────────────┤
│ - idPago      : int            (final)           │
│ - monto       : double         (final) SIN setter│
│ - fecha       : LocalDateTime  (final) SIN setter│
│ - estado      : EstadoPago            SIN setter │
│ - metodo      : MetodoPago     (final)           │
│ - idPedido    : int            (final)           │
├─────────────────────────────────────────────────┤
│ + Pago(idPago, monto, metodo, idPedido)          │
│ + procesarPago() : void                          │
│ - validarMonto() : void  ← PRIVATE               │
│ + getIdPago()    : int                           │
│ + getMonto()     : double                        │
│ + getFecha()     : LocalDateTime                 │
│ + getEstado()    : EstadoPago                    │
│ + getMetodo()    : MetodoPago                    │
│ + getIdPedido()  : int                           │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│                    Pedido                        │
├─────────────────────────────────────────────────┤
│ - idPedido      : int            (final)         │
│ - detalles      : List<Det..>    (final) PRIVADA │
│ - nombreCliente : String         (final)         │
│ - estado        : EstadoPedido        SIN setter │
│ - total         : double                         │
├─────────────────────────────────────────────────┤
│ + Pedido(idPedido, nombreCliente)                │
│ + agregarDetalle(DetallePedido) : void           │
│ + calcularTotal()               : double         │
│ + marcarComoPagado()            : void           │
│ + getDetalles() : List INMUTABLE                 │
│ + getIdPedido()      : int                       │
│ + getEstado()        : EstadoPedido              │
│ + getTotal()         : double                    │
│ + getNombreCliente() : String                    │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│                  DetallePedido                   │
├─────────────────────────────────────────────────┤
│ - idDetalle      : int     (final)               │
│ - cantidad       : int     (final)               │
│ - subtotal       : double  (final) auto-calculado│
│ - nombreProducto : String  (final)               │
│ - precioUnitario : double  (final)               │
├─────────────────────────────────────────────────┤
│ + DetallePedido(id, nombre, precio, cantidad)    │
│ + getters únicamente — sin setters               │
└─────────────────────────────────────────────────┘

┌──────────────────────┐   ┌──────────────────────┐
│       Cliente        │   │       Producto        │
├──────────────────────┤   ├──────────────────────┤
│ - idCliente (final)  │   │ - idProducto (final)  │
│ - nombre    (final)  │   │ - nombre     (final)  │
│ - correo    (final)  │   │ - precio     (final)  │
├──────────────────────┤   ├──────────────────────┤
│ + getters únicamente │   │ + getters únicamente  │
└──────────────────────┘   └──────────────────────┘

Capa Service
┌─────────────────────────────────────────────────────────┐
│                     PagoService  @Service                │
├─────────────────────────────────────────────────────────┤
│ - pagos      : List<Pago>  PRIVATE — nunca se expone    │
│ - contadorId : int         PRIVATE                      │
├─────────────────────────────────────────────────────────┤
│ + registrarPago(idPedido, monto, metodo) : Pago         │
│ + listarPagos()                : List<Pago> inmutable   │
│ + procesarPago(idPago)         : Pago                   │
│ ~ buscarPorId(idPago)          : Pago  PACKAGE-PRIVATE  │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│                    PedidoService  @Service               │
├─────────────────────────────────────────────────────────┤
│ - pedidos           : List<Pedido>   PRIVATE            │
│ - catalogoProductos : List<Producto> PRIVATE            │
│ - contadorPedido    : int            PRIVATE            │
│ - contadorDetalle   : int            PRIVATE            │
├─────────────────────────────────────────────────────────┤
│ + crearPedido(nombreCliente)              : Pedido      │
│ + agregarProducto(idPedido,idProd,cant)   : Pedido      │
│ + mostrarResumen(idPedido)                : Pedido      │
│ + listarPedidos()                         : List<Pedido>│
│ + marcarComoPagado(idPedido)              : void        │
│ + listarProductos()                       : List<Prod>  │
│ ~ buscarPorId(idPedido)   : Pedido  PACKAGE-PRIVATE    │
│ - buscarProducto(idProd)  : Producto PRIVATE            │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│                  InventarioService  @Service             │
├─────────────────────────────────────────────────────────┤
│ - stock : Map<Integer, Integer>  PRIVATE                │
├─────────────────────────────────────────────────────────┤
│ + mostrarStock(idProducto)          : int               │
│ + hayStock(idProducto, cantidad)    : boolean           │
│ + descontar(idProducto, cantidad)   : void              │
│ + agregar(idProducto, cantidad)     : void              │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│                   ClienteService  @Service               │
├─────────────────────────────────────────────────────────┤
│ - clientes   : List<Cliente>  PRIVATE                   │
│ - contadorId : int            PRIVATE                   │
├─────────────────────────────────────────────────────────┤
│ + crearCliente(nombre, correo) : Cliente                │
│ + listarClientes()             : List<Cliente> inmutable│
└─────────────────────────────────────────────────────────┘

Capa Controller
┌─────────────────────────────────────────────────────────┐
│            PagoController  @RestController  /pagos       │
├─────────────────────────────────────────────────────────┤
│ - pagoService : PagoService  @Autowired                 │
├─────────────────────────────────────────────────────────┤
│ + registrar(idPedido, monto, metodo) : Pago   [POST]    │
│ + listar()                           : List   [GET]     │
│ + procesar(idPago)                   : Pago   [PUT]     │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│          PedidoController  @RestController  /pedidos     │
├─────────────────────────────────────────────────────────┤
│ - pedidoService : PedidoService  @Autowired             │
├─────────────────────────────────────────────────────────┤
│ + crear(nombreCliente)              : Pedido  [POST]    │
│ + agregar(idPedido, idProd, cant)   : Pedido  [POST]    │
│ + mostrarResumen(idPedido)          : Pedido  [GET]     │
│ + calcularTotal(idPedido)           : double  [GET]     │
│ + listar()                          : List    [GET]     │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│       InventarioController  @RestController  /inventario │
├─────────────────────────────────────────────────────────┤
│ - inventarioService : InventarioService  @Autowired     │
├─────────────────────────────────────────────────────────┤
│ + consultarStock(idProducto)          : int    [GET]    │
│ + verificarStock(idProducto, cant)    : bool   [GET]    │
│ + descontarStock(idProducto, cant)    : String [PUT]    │
│ + agregarStock(idProducto, cant)      : String [PUT]    │
└─────────────────────────────────────────────────────────┘

4. Tipos de Relaciones Entre Clases
RelaciónTipoJustificaciónController → ServiceDependenciaEl controller solo usa el service, no gestiona su ciclo de vida. Spring inyecta con @AutowiredService → ModelCreación / AsociaciónEl service instancia y opera sobre los objetos del dominioPedido → DetallePedidoComposición (1 a muchos)Un DetallePedido no existe sin su Pedido. Se destruye con élPedido → ClienteAsociación (muchos a 1)El Cliente existe independientemente. Un cliente puede tener varios pedidosDetallePedido → ProductoAsociación (muchos a 1)El Producto existe independientemente. Varios detalles pueden referenciar el mismo productoPago → PedidoReferencia por idPedidoSin BD, Pago guarda el idPedido como int para evitar dependencia circular entre services

5. Endpoints de la API
MétodoEndpointParámetrosDescripciónHTTPGET/productos—Lista el catálogo de productos200POST/clientesnombre, correoCrea un nuevo cliente201GET/clientes—Lista todos los clientes200POST/pedidosnombreClienteCrea un nuevo pedido201POST/pedidos/{id}/productosidProducto, cantidadAgrega un producto al pedido200GET/pedidos/{id}/resumen—Muestra el resumen del pedido200 / 404GET/pedidos/{id}/total—Retorna el total del pedido200 / 404GET/pedidos—Lista todos los pedidos200POST/pagosidPedido, monto, metodoRegistra un pago201 / 400GET/pagos—Lista todos los pagos200PUT/pagos/{id}/procesar—Procesa el pago200 / 404 / 409GET/inventario/{id}/stock—Consulta stock200GET/inventario/{id}/haystockcantidadVerifica si hay stock200PUT/inventario/{id}/descontarcantidadDescuenta stock200 / 400PUT/inventario/{id}/agregarcantidadAgrega stock200

6. Manejo de Errores
ExcepciónCódigo HTTPCuándo ocurreRuntimeException404 Not FoundPedido o pago no encontrado por idIllegalArgumentException400 Bad RequestMonto inválido o stock insuficienteIllegalStateException409 ConflictPago ya fue procesado anteriormente

7. Parte de Análisis — Respuestas Obligatorias
1. ¿Qué atributos decidieron proteger y por qué?
Se protegieron con private y sin setter público:

monto en Pago: Si alguien pudiera modificar el monto desde el Controller, podría pagar menos de lo que debe. Es un dato crítico de negocio que no debe cambiar tras la creación del pago.
estado en Pago y Pedido: El estado define si un pago es válido o si un pedido ya fue procesado. Permitir cambiarlo libremente desde cualquier capa rompería la integridad del sistema.
fecha en Pago: La fecha de creación es inmutable. Un pago no puede cambiar cuándo fue realizado.
detalles en Pedido: La lista interna nunca se expone directamente. Se retorna una copia inmutable para que nadie externo pueda agregar o quitar detalles sin pasar por agregarDetalle().


2. ¿Por qué el estado no debe tener setter público?
Porque un setter público permitiría que cualquier capa, incluyendo el Controller, ejecute pago.setEstado(EstadoPago.PROCESADO) directamente sin ninguna validación. Esto significaría que:

Se podría marcar un pago como PROCESADO sin verificar si realmente fue procesado.
Se eliminaría toda la lógica de negocio asociada al cambio de estado.
Cualquier desarrollador podría saltarse el flujo correcto de la aplicación.

Con procesarPago() como único punto de cambio, se garantiza que siempre se ejecuten las validaciones antes de cambiar el estado. La lógica no puede ser eludida.

3. ¿Qué ventaja tiene declarar métodos sin modificador (default/package-private)?
El método buscarPorId() en PagoService y PedidoService no tiene modificador de acceso, lo que lo hace package-private — solo visible para clases dentro del mismo paquete services.
La ventaja concreta es que el Controller, aunque quisiera, físicamente NO PUEDE llamar pagoService.buscarPorId(id) desde el paquete controllers. Es una barrera arquitectónica real impuesta por el compilador de Java, no solo una convención. La búsqueda interna de pagos queda completamente oculta para las capas superiores.

4. ¿Qué pasaría si todos los atributos fueran públicos?
Si todos los atributos fueran públicos, cualquier clase podría hacer:
javapago.estado = EstadoPago.PROCESADO;  // Sin validación
pago.monto  = 0.01;                  // Monto manipulado
pedido.detalles.clear();             // Lista borrada
Las consecuencias serían:

El encapsulamiento dejaría de existir — el sistema sería inseguro por diseño.
Cualquier cambio en la estructura interna de las clases rompería todo el código que accede a esos atributos directamente.
Sería imposible garantizar la consistencia de los datos.
No habría forma de agregar validaciones en el futuro sin romper la compatibilidad.
El principio de responsabilidad única se violaría porque cualquier clase podría modificar el estado de cualquier objeto.


5. ¿Dónde debe vivir la lógica de negocio y por qué?
La lógica de negocio se divide en dos niveles:

Lógica de DOMINIO → vive en el MODEL. Ejemplo: validarMonto() y procesarPago() viven en Pago porque son reglas que definen cómo debe comportarse un pago, independientemente de dónde se use.
Lógica de ORQUESTACIÓN → vive en el SERVICE. Ejemplo: registrarPago(), buscarPorId() y marcarComoPagado() viven en PagoService porque coordinan la creación y gestión de pagos.

El Controller NO debe tener lógica de negocio porque:

Su única responsabilidad es traducir requests HTTP a llamadas de service y retornar responses.
Si el negocio cambia, solo se modifica el Service o el Model, no el Controller.
Facilita las pruebas unitarias — se puede probar el Service sin levantar el servidor HTTP.
Permite reutilizar la lógica de negocio desde diferentes puntos de entrada sin duplicar código.


8. Cómo Ejecutar
bashmvn clean spring-boot:run
La API levanta en: http://localhost:8081
Flujo de prueba en Postman
PasoMétodoURLParámetros1 — Ver productosGET/productos—2 — Crear pedidoPOST/pedidosnombreCliente=Juan Perez3 — Agregar productoPOST/pedidos/1/productosidProducto=1&cantidad=14 — Ver resumenGET/pedidos/1/resumen—5 — Registrar pagoPOST/pagosidPedido=1&monto=2500000&metodo=TARJETA6 — Listar pagosGET/pagos—7 — Procesar pagoPUT/pagos/1/procesar—8 — Error 404GET/pedidos/999/resumen—9 — Error 400POST/pagosidPedido=1&monto=-500&metodo=PSE10 — Error 409PUT/pagos/1/procesar— (segunda vez)
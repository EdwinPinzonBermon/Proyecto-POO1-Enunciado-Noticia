------------------------------------------------------------------------
PROYECTO: Sistema de gestión de vuelos - LATAM Airlines Colombia
ASIGNATURA: Programación Orientada a Objetos I
VERSIÓN: Revisión de requerimientos y consultas explícitas - noviembre de 2026
------------------------------------------------------------------------

PROPÓSITO
Aplicación de escritorio para que el Administrador registre y consulte
ciudades, rutas, aviones, tripulantes, vuelos, pasajeros y pasajes. También
permite actualizar los estados de los vuelos y del embarque de maletas,
cancelar pasajes y consultar ocupación e ingresos.

CÓMO INICIAR
- En BlueJ, abrir el proyecto y ejecutar la clase main para iniciar sin datos.
- Ejecutar main2 para iniciar con los datos de demostración de DatosPrueba.
- También se puede compilar desde la carpeta del proyecto con:
  javac -encoding UTF-8 modelo/*.java vista/*.java controlador/*.java *.java

ORGANIZACIÓN
- modelo/: clases del dominio y reglas del negocio.
- vista/: ventana principal y paneles Swing.
- controlador/: clase Control, que recibe los eventos y conecta la vista
  con el modelo.
- DatosPrueba.java: datos de ejemplo para probar la interfaz.
  Incluye 10 aviones, 8 vuelos, 28 tripulantes, 19 pasajeros y 24 pasajes.
  Hay tripulantes asignados y libres, vuelos con diferentes estados, un vuelo
  sin avión, un vuelo con tripulación incompleta y un pasaje cancelado.
  VUE-001 y VUE-008 tienen la misma ocupación máxima para probar los empates
  del ranking. Todos los datos son ficticios.

NOTAS DE USO
- Los botones y combos ejecutan sus acciones desde Control; la vista no
  contiene la lógica del negocio.
- El enunciado no solicita editar libremente todos los datos: las operaciones
  de modificación contempladas son cambiar el avión asignado y avanzar los
  estados del vuelo y del embarque, además de cancelar pasajes.
- Los pasajes se registran directamente como PAGADOS, porque el Administrador
  los registra después de recibir presencialmente el pago en efectivo.
- Para asignar avión, avanzar estados o consultar tripulación, primero se
  selecciona un vuelo en la tabla de vuelos programados.
- El pasajero puede registrarse por separado desde el panel de pasajes;
  también puede registrarse automáticamente al registrar su primer pasaje.
- Para registrar un pasaje, el vuelo debe estar en estado DISPONIBLE o
  ABORDANDO. El precio base debe respetar el tope tarifario de su ruta.
- Para buscar un pasaje específico, use el botón "Buscar pasaje" con el
  código del vuelo y el número del asiento. Para consultar todos los pasajes
  de un vuelo, use "Ver pasajes del vuelo".
- El vuelo solo puede pasar a DESPEGUE cuando tiene avión, tripulación
  obligatoria completa y maletas en estado A_BORDO.
- En JPAvionesYVuelos, las tablas de aviones y vuelos se distribuyen con un
  separador vertical ajustable. Se puede arrastrar la división para dar más
  espacio a cualquiera de las dos tablas.

AUTORES
Karen Catalina Villamizar Vivas
Edwin Eduardo Pinzón Bermón
Juan Manuel Villamizar Sierra
------------------------------------------------------------------------

INTEGRACION DE INTERFAZ - NOVIEMBRE DE 2026
- Se recuperó la distribución de la snapshot en los paneles de Tripulación,
  Pasajes y Asientos, y Ocupación e Ingresos.
- Se conservaron las operaciones del controlador y las reglas del modelo de
  la versión actual. Los ActionListener continúan en controlador/Control.java.
- El panel Aviones y Vuelos conserva los controles adicionales de búsqueda y
  actualización que requiere el controlador actual.
- Para emitir un pasaje, escriba el código del vuelo en el campo correspondiente.
  El selector visual de vuelo se conserva para consulta y navegación del panel.
- En el ranking se muestran las ocho columnas previstas por la interfaz
  recuperada, incluyendo avión, pasajes pagados, capacidad y empates.

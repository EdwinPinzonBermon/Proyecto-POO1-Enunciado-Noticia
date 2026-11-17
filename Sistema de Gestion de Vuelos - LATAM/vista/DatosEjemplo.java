package vista;

import java.util.ArrayList;
import modelo.*;

public class DatosEjemplo {

    public static void cargar(Aerolinea aerolinea) {
        try {
            // El constructor vacío de Aerolinea no crea las listas: las inicializamos
            if (aerolinea.getNombre() == null || aerolinea.getNombre().trim().isEmpty()) {
                aerolinea.setNombre("LATAM Airlines Colombia");
            }
            if (aerolinea.getAviones() == null) aerolinea.setAviones(new ArrayList<Avion>());
            if (aerolinea.getTripulantes() == null) aerolinea.setTripulantes(new ArrayList<Tripulante>());
            if (aerolinea.getPaises() == null) aerolinea.setPaises(new ArrayList<Pais>());
            if (aerolinea.getCiudades() == null) aerolinea.setCiudades(new ArrayList<Ciudad>());
            if (aerolinea.getRutas() == null) aerolinea.setRutas(new ArrayList<Ruta>());
            if (aerolinea.getVuelos() == null) aerolinea.setVuelos(new ArrayList<Vuelo>());
            if (aerolinea.getPasajeros() == null) aerolinea.setPasajeros(new ArrayList<Pasajero>());

            // País y ciudades
            Pais colombia = new Pais("CO", "Colombia", new ArrayList<Ciudad>());
            exigir(aerolinea.registrarPais(colombia), "país CO");

            Ciudad bogota = crearCiudad(aerolinea, colombia, "BOG", "Bogota");
            Ciudad medellin = crearCiudad(aerolinea, colombia, "MDE", "Medellin");
            Ciudad cartagena = crearCiudad(aerolinea, colombia, "CTG", "Cartagena");
            Ciudad cucuta = crearCiudad(aerolinea, colombia, "CUC", "Cucuta");

            exigir(aerolinea.registrarRuta(bogota, medellin, 55, 260000), "ruta BOG->MDE");
            exigir(aerolinea.registrarRuta(bogota, cartagena, 85, 310000), "ruta BOG->CTG");
            exigir(aerolinea.registrarRuta(cucuta, bogota, 80, 290000), "ruta CUC->BOG");
            Ruta bogMde = aerolinea.buscarRuta(bogota, medellin);
            Ruta bogCtg = aerolinea.buscarRuta(bogota, cartagena);

            // Aviones
            Avion hk5101 = new Avion("HK-5101", "Airbus A320", "Blanco", 12, 4, new ArrayList<Vuelo>(), aerolinea);
            Avion hk5202 = new Avion("HK-5202", "Airbus A319", "Rojo", 16, 4, new ArrayList<Vuelo>(), aerolinea);
            exigir(aerolinea.registrarAvion(hk5101), "avión HK-5101");
            exigir(aerolinea.registrarAvion(hk5202), "avión HK-5202");

            // Fechas futuras (día + 2 y + 3 respecto a hoy)
            FechaHora ahora = FechaHora.ahora();
            Fecha fecha101 = new Fecha(ahora.getFecha().getDia(), ahora.getFecha().getMes(), ahora.getFecha().getAnio());
            // sumar ~2 días de forma simple
            fecha101 = sumarDias(fecha101, 2);
            Fecha fecha202 = sumarDias(new Fecha(ahora.getFecha().getDia(), ahora.getFecha().getMes(), ahora.getFecha().getAnio()), 3);

            Vuelo vue101 = new Vuelo("VUE-101", fecha101, new Hora(8, 30), 12, hk5101, bogMde,
                new ArrayList<Tripulante>(), new ArrayList<Pasaje>());
            Vuelo vue202 = new Vuelo("VUE-202", fecha202, new Hora(14, 15), 16, null, bogCtg,
                new ArrayList<Tripulante>(), new ArrayList<Pasaje>());

            // TODO [Modelo]: getFechaHoraSalida() aún devuelve null; registrarVuelo puede fallar por eso
            boolean ok101 = aerolinea.registrarVuelo(vue101);
            boolean ok202 = aerolinea.registrarVuelo(vue202);
            if (!ok101) System.out.println("Aviso: no se registró VUE-101 (revisar getFechaHoraSalida en el modelo).");
            if (!ok202) System.out.println("Aviso: no se registró VUE-202 (revisar getFechaHoraSalida en el modelo).");

            // Tripulación
            registrarTripulante(aerolinea, "1001", "Ana Gomez", Rol.CAPITAN, ok101 ? "VUE-101" : null);
            registrarTripulante(aerolinea, "1002", "Luis Perez", Rol.COPILOTO, ok101 ? "VUE-101" : null);
            registrarTripulante(aerolinea, "1003", "Marta Ruiz", Rol.AZAFATA, ok101 ? "VUE-101" : null);
            registrarTripulante(aerolinea, "1004", "Jorge Ramirez", Rol.CAPITAN, null);
            registrarTripulante(aerolinea, "1005", "Paula Castro", Rol.COPILOTO, null);
            registrarTripulante(aerolinea, "1006", "Diana Torres", Rol.AZAFATA, null);
            registrarTripulante(aerolinea, "1007", "Felipe Ortiz", Rol.AZAFATA, null);

            if (ok101) {
                venderPasaje(aerolinea, "VUE-101", "5550001", "Carlos Diaz", 1, ClasePasaje.PRIORITARIA, 200000);
                venderPasaje(aerolinea, "VUE-101", "5550002", "Lucia Mora", 2, ClasePasaje.PRIORITARIA, 200000);
                venderPasaje(aerolinea, "VUE-101", "5550003", "Andres Rojas", 5, ClasePasaje.ECONOMICA, 180000);
                venderPasaje(aerolinea, "VUE-101", "5550004", "Valentina Cruz", 6, ClasePasaje.ECONOMICA, 180000);
                venderPasaje(aerolinea, "VUE-101", "5550005", "Camilo Vargas", 9, ClasePasaje.ECONOMICA, 180000);
            }
        } catch (Exception ex) {
            System.out.println("No se pudieron cargar todos los datos de ejemplo: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static Fecha sumarDias(Fecha f, int dias) {
        // Aproximación simple sin Calendar del modelo: avanza día/mes/año
        int d = f.getDia() + dias;
        int m = f.getMes();
        int a = f.getAnio();
        int[] max = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((a % 4 == 0 && a % 100 != 0) || a % 400 == 0) max[2] = 29;
        while (d > max[m]) {
            d -= max[m];
            m++;
            if (m > 12) {
                m = 1;
                a++;
                max[2] = ((a % 4 == 0 && a % 100 != 0) || a % 400 == 0) ? 29 : 28;
            }
        }
        return new Fecha(d, m, a);
    }

    private static Ciudad crearCiudad(Aerolinea aerolinea, Pais pais, String codigo, String nombre) {
        Ciudad ciudad = new Ciudad(codigo, nombre, true, pais, aerolinea, new ArrayList<Ruta>(), new ArrayList<Ruta>());
        exigir(aerolinea.registrarCiudad(ciudad), "ciudad " + codigo);
        return ciudad;
    }

    private static void registrarTripulante(Aerolinea aerolinea, String id, String nombre, Rol rol, String codigoVuelo) {
        Tripulante t = new Tripulante(id, nombre, rol, aerolinea, new ArrayList<Vuelo>());
        exigir(aerolinea.registrarTripulante(t, codigoVuelo), "tripulante " + id);
    }

    private static void venderPasaje(Aerolinea aerolinea, String codigoVuelo, String id, String nombre,
                                     int asiento, ClasePasaje clase, int precioBase) {
        Pasajero pasajero = aerolinea.buscarPasajero(id);
        if (pasajero == null) {
            pasajero = new Pasajero(id, nombre, new ArrayList<Pasaje>());
            exigir(aerolinea.registrarPasajero(pasajero), "pasajero " + id);
        }
        exigir(aerolinea.registrarPasaje(codigoVuelo, pasajero, asiento, clase, precioBase), "pasaje asiento " + asiento);
    }

    private static void exigir(boolean correcto, String que) {
        if (!correcto) {
            throw new IllegalStateException("falló el registro de " + que);
        }
    }
}

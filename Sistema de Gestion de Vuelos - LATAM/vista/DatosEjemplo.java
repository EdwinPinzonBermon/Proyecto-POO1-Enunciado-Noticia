package vista;
import java.util.ArrayList;
import modelo.*;

/**
 * DATOS DE EJEMPLO SOLO PARA VER LA VISTA mientras el modelo no esta terminado.
 * Agrega los objetos directamente a las listas de la Aerolinea (no usa registrarAvion, registrarVuelo, etc.,
 * porque esos metodos todavia pueden estar vacios).
 *
 * Cuando el modelo este listo: borrar esta clase y la linea DatosEjemplo.cargar(...) del Main.
 */
public class DatosEjemplo
{
    public static void cargar(Aerolinea aerolinea){
        try {
            // Pais y ciudades
            Pais colombia = new Pais("CO", "Colombia", new ArrayList<Ciudad>());
            aerolinea.getPaises().add(colombia);

            Ciudad bogota = new Ciudad("BOG", "Bogota", true, colombia, aerolinea, new ArrayList<Ruta>(), new ArrayList<Ruta>());
            Ciudad medellin = new Ciudad("MDE", "Medellin", true, colombia, aerolinea, new ArrayList<Ruta>(), new ArrayList<Ruta>());
            Ciudad pereira = new Ciudad("PEI", "Pereira", true, colombia, aerolinea, new ArrayList<Ruta>(), new ArrayList<Ruta>());
            Ciudad armenia = new Ciudad("AXM", "Armenia", true, colombia, aerolinea, new ArrayList<Ruta>(), new ArrayList<Ruta>());
            aerolinea.getCiudades().add(bogota);
            aerolinea.getCiudades().add(medellin);
            aerolinea.getCiudades().add(pereira);
            aerolinea.getCiudades().add(armenia);

            // Rutas
            Ruta bogPei = new Ruta(50, 228000, new ArrayList<Vuelo>(), bogota, pereira);
            Ruta medPei = new Ruta(45, 228000, new ArrayList<Vuelo>(), medellin, pereira);
            Ruta bogAxm = new Ruta(55, 234000, new ArrayList<Vuelo>(), bogota, armenia);
            aerolinea.getRutas().add(bogPei);
            aerolinea.getRutas().add(medPei);
            aerolinea.getRutas().add(bogAxm);

            // Aviones
            Avion avion1 = new Avion("HK-4821", "Airbus A320", "Blanco", 150, 12, new ArrayList<Vuelo>(), aerolinea);
            Avion avion2 = new Avion("HK-1111", "Airbus A319", "Rojo", 120, 8, new ArrayList<Vuelo>(), aerolinea);
            aerolinea.getAviones().add(avion1);
            aerolinea.getAviones().add(avion2);

            // Vuelos
            Vuelo vuelo1 = new Vuelo("VUE-045", new Fecha(15, 3, 2030), new Hora(6, 30), 150, avion1, bogPei, new ArrayList<Tripulante>(), new ArrayList<Pasaje>());
            Vuelo vuelo2 = new Vuelo("VUE-046", new Fecha(15, 3, 2030), new Hora(9, 0), 100, null, medPei, new ArrayList<Tripulante>(), new ArrayList<Pasaje>());
            aerolinea.getVuelos().add(vuelo1);
            aerolinea.getVuelos().add(vuelo2);

            // Tripulantes
            Tripulante capitan = new Tripulante("1001", "Ana Gomez", Rol.CAPITAN, aerolinea, new ArrayList<Vuelo>());
            Tripulante copiloto = new Tripulante("1002", "Luis Perez", Rol.COPILOTO, aerolinea, new ArrayList<Vuelo>());
            Tripulante azafata = new Tripulante("1003", "Marta Ruiz", Rol.AZAFATA, aerolinea, new ArrayList<Vuelo>());
            aerolinea.getTripulantes().add(capitan);
            aerolinea.getTripulantes().add(copiloto);
            aerolinea.getTripulantes().add(azafata);
            vuelo1.getTripulantes().add(capitan);
            vuelo1.getTripulantes().add(copiloto);

            // Pasajeros y pasajes
            Pasajero pasajero1 = new Pasajero("5550001", "Carlos Diaz", new ArrayList<Pasaje>());
            Pasajero pasajero2 = new Pasajero("5550002", "Lucia Mora", new ArrayList<Pasaje>());
            aerolinea.getPasajeros().add(pasajero1);
            aerolinea.getPasajeros().add(pasajero2);

            Pasaje pasaje1 = new PasajeEconomico(10, 200000, vuelo1, pasajero1);
            Pasaje pasaje2 = new PasajePrioritario(1, 200000, vuelo1, pasajero2);
            vuelo1.getPasajes().add(pasaje1);
            vuelo1.getPasajes().add(pasaje2);
            pasajero1.getPasajes().add(pasaje1);
            pasajero2.getPasajes().add(pasaje2);
        } catch (Exception ex) {
            // Si alguna clase del modelo aun no esta lista (constructor o lista null) no se cae el programa
            System.out.println("No se pudieron cargar todos los datos de ejemplo: " + ex);
        }
    }
}

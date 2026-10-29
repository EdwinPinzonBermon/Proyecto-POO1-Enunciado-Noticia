package modelo;

public class PasajeEconomico extends Pasaje {

    public PasajeEconomico() {
    }

    public PasajeEconomico(int numeroAsiento, int precioBase, Vuelo vuelo, Pasajero pasajero) {
        super(numeroAsiento, precioBase, vuelo, pasajero);
    }

    @Override
    public ClasePasaje getClase() {
        return ClasePasaje.ECONOMICA;
    }

    @Override
    public int calcularPrecioFinal() {
        //Falta por implementar 
        return 0;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
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
        return precioBase;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
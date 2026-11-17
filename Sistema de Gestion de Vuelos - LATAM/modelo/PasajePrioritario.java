package modelo;

public class PasajePrioritario extends Pasaje {

    private static final int PORCENTAJE_RECARGO = 20;

    public PasajePrioritario() {
    }

    public PasajePrioritario(int numeroAsiento, int precioBase, Vuelo vuelo, Pasajero pasajero) {
        super(numeroAsiento, precioBase, vuelo, pasajero);
    }

    @Override
    public ClasePasaje getClase() {
        return ClasePasaje.PRIORITARIA;
    }

    public int calcularRecargo() {
        return precioBase * PORCENTAJE_RECARGO / 100;
    }

    @Override
    public int calcularPrecioFinal() {
        return precioBase + calcularRecargo();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
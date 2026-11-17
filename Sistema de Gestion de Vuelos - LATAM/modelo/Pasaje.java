package modelo;

public abstract class Pasaje {

    protected int numeroAsiento;
    protected int precioBase;
    protected EstadoPasaje estado;
    protected Vuelo vuelo;
    protected Pasajero pasajero;

    public Pasaje() {
    }

    public Pasaje(int numeroAsiento, int precioBase, Vuelo vuelo, Pasajero pasajero) {
        this.numeroAsiento = numeroAsiento;
        this.precioBase = precioBase;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.estado = EstadoPasaje.PAGADO;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public EstadoPasaje getEstado() {
        return estado;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public abstract ClasePasaje getClase();

    public abstract int calcularPrecioFinal();

    public boolean cancelar() {
        if (estado == EstadoPasaje.CANCELADO) {
            return false;
        }
        estado = EstadoPasaje.CANCELADO;
        return true;
    }

    public boolean estaCancelado() {
        return estado == EstadoPasaje.CANCELADO;
    }

    @Override
    public String toString() {
        return "Asiento: " + numeroAsiento + " - Precio Final: " + calcularPrecioFinal() + " - Clase: " + getClase() + " - Estado: " + estado;
    }
}
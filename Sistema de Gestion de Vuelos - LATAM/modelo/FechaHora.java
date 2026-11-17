package modelo;
import java.util.Calendar;

public class FechaHora {
    private Fecha fecha;
    private Hora hora;

    public FechaHora() {
    }

    public FechaHora(Fecha fecha, Hora hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public static FechaHora ahora() {
        Calendar actual = Calendar.getInstance();
        Fecha fechaActual = new Fecha(actual.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.MONTH) + 1, actual.get(Calendar.YEAR));
        Hora horaActual = new Hora(actual.get(Calendar.HOUR_OF_DAY), actual.get(Calendar.MINUTE));
        return new FechaHora(fechaActual, horaActual);
    }

    public boolean esAnteriorA(FechaHora otra) {
        if (fecha.esAnteriorA(otra.getFecha())) {
            return true;
        }
        if (otra.getFecha().esAnteriorA(fecha)) {
            return false;
        }
        return hora.esAnteriorA(otra.getHora());
    }

    public FechaHora sumarMinutos(int minutos) {
        int totalMinutos = hora.getHora() * 60 + hora.getMinuto() + minutos;
        int diasExtra = totalMinutos / (24 * 60);
        int minutosDelDia = totalMinutos % (24 * 60);

        int dia = fecha.getDia();
        int mes = fecha.getMes();
        int anio = fecha.getAnio();

        for (int i = 0; i < diasExtra; i++) {
            int diasDelMes = 31;
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                diasDelMes = 30;
            } else if (mes == 2) {
                boolean bisiesto = (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
                if (bisiesto) {
                    diasDelMes = 29;
                } else {
                    diasDelMes = 28;
                }
            }
            dia++;
            if (dia > diasDelMes) {
                dia = 1;
                mes++;
                if (mes > 12) {
                    mes = 1;
                    anio++;
                }
            }
        }

        Fecha nuevaFecha = new Fecha(dia, mes, anio);
        Hora nuevaHora = new Hora(minutosDelDia / 60, minutosDelDia % 60);
        return new FechaHora(nuevaFecha, nuevaHora);
    }

    @Override
    public String toString() {
        return fecha.toString() + " " + hora.toString();
    }
}
package vista;

import modelo.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class JFPanelPrincipal extends JPanel {

    private Aerolinea aerolinea;

    // Tarjetas de resumen (números importantes)
    private JLabel lblVuelosActivos;
    private JLabel lblVuelosDetalle;
    private JLabel lblFlota;
    private JLabel lblFlotaDetalle;
    private JLabel lblPilotos;
    private JLabel lblPilotosDetalle;
    private JLabel lblProgramados;
    private JLabel lblProgramadosDetalle;
    private JLabel lblIngresos;
    private JLabel lblIngresosDetalle;

    // Alertas
    private JPanel pnlListaAlertas;
    private JScrollPane scrollAlertas;

    // Estado aviones
    private JPanel pnlTarjetasAviones;
    private JScrollPane scrollAviones;

    // Callbacks de navegación (seteados por Control)
    private ActionListener navegarRutas;
    private ActionListener navegarAvionesVuelos;
    private ActionListener navegarTripulacion;

    private static final Color FONDO = new Color(243, 246, 251);
    private static final Color TARJETA = Color.WHITE;
    private static final Color TEXTO_SEC = new Color(90, 106, 133);
    private static final Color AZUL_OSCURO = new Color(28, 43, 75);
    private static final Color VERDE = new Color(22, 163, 74);
    private static final Color AZUL_ESTADO = new Color(37, 99, 235);
    private static final Color NARANJA = new Color(234, 88, 12);
    private static final Color ROJO = new Color(220, 38, 38);

    public JFPanelPrincipal(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
        setLayout(new BorderLayout(0, 16));
        setBackground(FONDO);
        setBorder(new EmptyBorder(16, 18, 16, 18));
        construir();
        actualizarDatos();
    }

    public void setNavegadores(ActionListener aRutas, ActionListener aAviones, ActionListener aTrip) {
        this.navegarRutas = aRutas;
        this.navegarAvionesVuelos = aAviones;
        this.navegarTripulacion = aTrip;
    }

    private void construir() {
        // --- tarjetas de resumen superiores ---
        JPanel pnlResumen = new JPanel(new GridLayout(1, 5, 12, 0));
        pnlResumen.setOpaque(false);

        // 1. Vuelos Activos
        lblVuelosActivos = new JLabel("0");
        lblVuelosDetalle = new JLabel("0 en aire · 0 plataforma");
        pnlResumen.add(crearTarjetaResumen("Vuelos Activos", lblVuelosActivos, lblVuelosDetalle, AZUL_ESTADO));

        // 2. Aviones Libres
        lblFlota = new JLabel("0");
        lblFlotaDetalle = new JLabel("0 asignados · 0 en total");
        pnlResumen.add(crearTarjetaResumen("Aviones Libres", lblFlota, lblFlotaDetalle, new Color(124, 58, 237)));

        // 3. Pilotos Activos
        lblPilotos = new JLabel("0");
        lblPilotosDetalle = new JLabel("0 en total · 0 libres");
        pnlResumen.add(crearTarjetaResumen("Pilotos Activos", lblPilotos, lblPilotosDetalle, new Color(13, 148, 136)));

        // 4. Vuelos Programados
        lblProgramados = new JLabel("0");
        lblProgramadosDetalle = new JLabel("ocup. promedio 0%");
        pnlResumen.add(crearTarjetaResumen("Vuelos Programados", lblProgramados, lblProgramadosDetalle, NARANJA));

        // 5. Ingresos Totales
        lblIngresos = new JLabel("$ 0");
        lblIngresosDetalle = new JLabel("pasajes confirmados");
        pnlResumen.add(crearTarjetaResumen("Ingresos Totales", lblIngresos, lblIngresosDetalle, ROJO));

        add(pnlResumen, BorderLayout.NORTH);

        // --- Zona inferior: dos paneles cuadrados ---
        JPanel pnlInferior = new JPanel(new GridLayout(1, 2, 16, 0));
        pnlInferior.setOpaque(false);

        // Izquierda: Atención operativa
        JPanel pnlAtencion = crearPanelSeccion("Atención Operativa y Pendientes");
        pnlListaAlertas = new JPanel();
        pnlListaAlertas.setLayout(new BoxLayout(pnlListaAlertas, BoxLayout.Y_AXIS));
        pnlListaAlertas.setBackground(TARJETA);
        scrollAlertas = new JScrollPane(pnlListaAlertas);
        scrollAlertas.setBorder(null);
        scrollAlertas.getViewport().setBackground(TARJETA);
        pnlAtencion.add(scrollAlertas, BorderLayout.CENTER);
        pnlInferior.add(pnlAtencion);

        // Derecha: Estado de aviones
        JPanel pnlEstado = crearPanelSeccion("Estado de Aviones: En Aire / En Tierra");
        pnlTarjetasAviones = new JPanel();
        pnlTarjetasAviones.setLayout(new BoxLayout(pnlTarjetasAviones, BoxLayout.Y_AXIS));
        pnlTarjetasAviones.setBackground(TARJETA);
        scrollAviones = new JScrollPane(pnlTarjetasAviones);
        scrollAviones.setBorder(null);
        scrollAviones.getViewport().setBackground(TARJETA);
        pnlEstado.add(scrollAviones, BorderLayout.CENTER);
        pnlInferior.add(pnlEstado);

        add(pnlInferior, BorderLayout.CENTER);
    }

    private JPanel crearPanelSeccion(String titulo) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBackground(TARJETA);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(12, 14, 12, 14)
        ));
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setForeground(AZUL_OSCURO);
        p.add(lbl, BorderLayout.NORTH);
        return p;
    }

    private JPanel crearTarjetaResumen(String titulo, JLabel valor, JLabel detalle, Color acento) {
        JPanel t = new JPanel(new BorderLayout(0, 4));
        t.setBackground(TARJETA);
        t.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, acento),
            new EmptyBorder(12, 14, 12, 14)
        ));
        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblTit.setForeground(TEXTO_SEC);
        valor.setFont(new Font("SansSerif", Font.BOLD, 22));
        valor.setForeground(AZUL_OSCURO);
        detalle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        detalle.setForeground(TEXTO_SEC);
        t.add(lblTit, BorderLayout.NORTH);
        t.add(valor, BorderLayout.CENTER);
        t.add(detalle, BorderLayout.SOUTH);
        return t;
    }

    public void actualizarDatos() {
        // Tarjetas de resumen (números importantes)
        int enAire = 0, enPlataforma = 0, totalActivos = 0;
        int asignados = 0, libres = 0;
        int pilotosActivos = 0, totalPilotos = 0, tripulantesLibres = 0;
        int programados = 0, sumaOcup = 0, totalIngresos = 0, pasajesActivos = 0;

        for (Vuelo v : aerolinea.getVuelos()) {
            programados++;
            if (v.estaActivo()) {
                totalActivos++;
                if (v.getEstadoVuelo() == EstadoVuelo.EN_EL_AIRE || v.getEstadoVuelo() == EstadoVuelo.ATERRIZAJE) {
                    enAire++;
                } else {
                    enPlataforma++;
                }
            }
            sumaOcup += v.calcularOcupacion();
            totalIngresos += v.calcularIngresoConfirmado();
            pasajesActivos += v.contarPasajesNoCancelados();
        }
        for (Avion a : aerolinea.getAviones()) {
            if (a.estaLibre()) libres++;
            else asignados++;
        }
        for (Tripulante t : aerolinea.getTripulantes()) {
            if (t.getRol() == Rol.CAPITAN || t.getRol() == Rol.COPILOTO) {
                totalPilotos++;
                if (!t.estaLibre()) pilotosActivos++;
            }
            if (t.estaLibre()) tripulantesLibres++;
        }

        // Tarjeta 1: Vuelos Activos
        lblVuelosActivos.setText(String.valueOf(totalActivos));
        lblVuelosDetalle.setText(enAire + " en aire · " + enPlataforma + " plataforma");

        // Tarjeta 2: Aviones Libres (número grande = libres)
        lblFlota.setText(String.valueOf(libres));
        lblFlotaDetalle.setText(asignados + " asignados · " + aerolinea.getAviones().size() + " en total");

        // Tarjeta 3: Pilotos Activos (número grande = activos)
        lblPilotos.setText(String.valueOf(pilotosActivos));
        lblPilotosDetalle.setText(totalPilotos + " en total · " + tripulantesLibres + " tripulantes libres");

        // Tarjeta 4: Vuelos Programados
        lblProgramados.setText(String.valueOf(programados));
        int promOcup = programados > 0 ? sumaOcup / programados : 0;
        lblProgramadosDetalle.setText("ocup. promedio " + promOcup + "%");

        // Tarjeta 5: Ingresos Totales
        NumberFormat nf = NumberFormat.getIntegerInstance(new Locale("es", "CO"));
        lblIngresos.setText("$ " + nf.format(totalIngresos));
        lblIngresosDetalle.setText(pasajesActivos + " pasajes confirmados");

        // Alertas
        pnlListaAlertas.removeAll();
        ArrayList<Alerta> alertas = generarAlertas();
        if (alertas.isEmpty()) {
            JLabel ok = new JLabel("✓ Sin pendientes operativos. Todo en orden.");
            ok.setFont(new Font("SansSerif", Font.PLAIN, 13));
            ok.setForeground(VERDE);
            ok.setBorder(new EmptyBorder(8, 4, 8, 4));
            pnlListaAlertas.add(ok);
        } else {
            for (Alerta a : alertas) {
                pnlListaAlertas.add(crearFilaAlerta(a));
                pnlListaAlertas.add(Box.createRigidArea(new Dimension(0, 6)));
            }
        }
        pnlListaAlertas.revalidate();
        pnlListaAlertas.repaint();

        // Tarjetas de aviones / vuelos
        pnlTarjetasAviones.removeAll();
        boolean hay = false;
        for (Avion av : aerolinea.getAviones()) {
            Vuelo vueloActivo = null;
            for (Vuelo v : av.getVuelos()) {
                if (v.estaActivo()) {
                    vueloActivo = v;
                    break;
                }
            }
            pnlTarjetasAviones.add(crearTarjetaAvion(av, vueloActivo));
            pnlTarjetasAviones.add(Box.createRigidArea(new Dimension(0, 8)));
            hay = true;
        }
        if (!hay) {
            JLabel vacio = new JLabel("No hay aviones registrados.");
            vacio.setForeground(TEXTO_SEC);
            vacio.setBorder(new EmptyBorder(8, 4, 8, 4));
            pnlTarjetasAviones.add(vacio);
        }
        pnlTarjetasAviones.revalidate();
        pnlTarjetasAviones.repaint();
    }

    private static class Alerta {
        String mensaje;
        String accion;
        ActionListener listener;
        Alerta(String m, String a, ActionListener l) {
            mensaje = m; accion = a; listener = l;
        }
    }

    private ArrayList<Alerta> generarAlertas() {
        ArrayList<Alerta> list = new ArrayList<>();
        if (aerolinea.getRutas().isEmpty()) {
            list.add(new Alerta("No hay rutas registradas. Configure la red de destinos.", "Ir a Rutas", navegarRutas));
        }
        for (Vuelo v : aerolinea.getVuelos()) {
            if (v.getAvion() == null && v.estaActivo()) {
                list.add(new Alerta("Vuelo " + v.getCodigo() + " sin avión asignado.", "Asignar avión", navegarAvionesVuelos));
            }
            if (v.getAvion() != null && !v.tripulacionCompleta() && v.estaActivo()) {
                list.add(new Alerta("Vuelo " + v.getCodigo() + " con tripulación incompleta.", "Completar tripulación", navegarTripulacion));
            }
        }
        return list;
    }

    private JPanel crearFilaAlerta(Alerta a) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        fila.setBackground(new Color(254, 243, 199));
        fila.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(253, 230, 138), 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        JLabel msg = new JLabel(a.mensaje);
        msg.setFont(new Font("SansSerif", Font.PLAIN, 12));
        msg.setForeground(new Color(146, 64, 14));
        fila.add(msg, BorderLayout.CENTER);
        if (a.listener != null) {
            JButton btn = new JButton(a.accion);
            btn.setFont(new Font("SansSerif", Font.BOLD, 11));
            btn.setFocusPainted(false);
            btn.setBackground(NARANJA);
            btn.setForeground(Color.WHITE);
            btn.setBorder(new EmptyBorder(4, 10, 4, 10));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.addActionListener(a.listener);
            fila.add(btn, BorderLayout.EAST);
        }
        return fila;
    }

    private JPanel crearTarjetaAvion(Avion av, Vuelo v) {
        JPanel t = new JPanel(new BorderLayout(8, 4));
        t.setBackground(new Color(248, 250, 252));
        t.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(10, 12, 10, 12)
        ));
        t.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        // Header: matrícula + estado
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel mat = new JLabel(av.getMatricula() + "  ·  " + av.getModelo());
        mat.setFont(new Font("SansSerif", Font.BOLD, 13));
        mat.setForeground(AZUL_OSCURO);
        header.add(mat, BorderLayout.WEST);

        String estadoTxt;
        Color estadoColor;
        if (v == null) {
            estadoTxt = "EN TIERRA";
            estadoColor = AZUL_ESTADO;
        } else if (v.getEstadoVuelo() == EstadoVuelo.EN_EL_AIRE || v.getEstadoVuelo() == EstadoVuelo.ATERRIZAJE) {
            estadoTxt = "EN AIRE";
            estadoColor = VERDE;
        } else {
            estadoTxt = "EN TIERRA";
            estadoColor = AZUL_ESTADO;
        }
        JLabel badge = new JLabel(" " + estadoTxt + " ");
        badge.setOpaque(true);
        badge.setBackground(estadoColor);
        badge.setForeground(Color.WHITE);
        badge.setFont(new Font("SansSerif", Font.BOLD, 10));
        badge.setBorder(new EmptyBorder(2, 6, 2, 6));
        header.add(badge, BorderLayout.EAST);
        t.add(header, BorderLayout.NORTH);

        // Detalle
        String ruta = "—";
        String capitan = "Sin capitán";
        if (v != null) {
            if (v.getRuta() != null) {
                ruta = v.getRuta().getCiudadSalida().getCodigo() + " → " + v.getRuta().getCiudadLlegada().getCodigo()
                    + "  (" + v.getCodigo() + ")";
            }
            for (Tripulante tr : v.getTripulantes()) {
                if (tr.getRol() == Rol.CAPITAN) {
                    capitan = tr.getNombre();
                    break;
                }
            }
        }
        // Dos etiquetas normales (más fácil que usar HTML dentro del JLabel)
        JPanel detalle = new JPanel(new GridLayout(2, 1));
        detalle.setOpaque(false);
        JLabel lblRuta = new JLabel(ruta);
        lblRuta.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRuta.setForeground(TEXTO_SEC);
        JLabel lblCapitan = new JLabel("Capitán: " + capitan);
        lblCapitan.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCapitan.setForeground(TEXTO_SEC);
        detalle.add(lblRuta);
        detalle.add(lblCapitan);
        t.add(detalle, BorderLayout.CENTER);

        // Barra de progreso de fase
        JProgressBar bar = new JProgressBar(0, 5);
        bar.setStringPainted(true);
        if (v == null) {
            bar.setValue(0);
            bar.setString("Disponible");
        } else {
            int fase = v.getEstadoVuelo().ordinal();
            bar.setValue(fase);
            // Texto legible del estado (sin guiones bajos)
            bar.setString(textoEstado(v.getEstadoVuelo()));
            if (fase >= 3) bar.setForeground(VERDE);
            else if (fase >= 1) bar.setForeground(NARANJA);
            else bar.setForeground(AZUL_ESTADO);
        }
        bar.setPreferredSize(new Dimension(10, 18));
        t.add(bar, BorderLayout.SOUTH);
        return t;
    }

    
    private String textoEstado(EstadoVuelo estado) {
        if (estado == EstadoVuelo.DISPONIBLE) return "DISPONIBLE";
        if (estado == EstadoVuelo.ABORDANDO) return "ABORDANDO";
        if (estado == EstadoVuelo.DESPEGUE) return "DESPEGUE";
        if (estado == EstadoVuelo.EN_EL_AIRE) return "EN EL AIRE";
        if (estado == EstadoVuelo.ATERRIZAJE) return "ATERRIZAJE";
        if (estado == EstadoVuelo.EN_EL_DESTINO) return "EN EL DESTINO";
        return "";
    }
}

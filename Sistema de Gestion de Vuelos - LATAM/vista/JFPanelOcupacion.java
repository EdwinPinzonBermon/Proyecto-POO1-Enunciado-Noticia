package vista;

import modelo.Aerolinea;
import modelo.Vuelo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JFPanelOcupacion extends JPanel {

    private Aerolinea aerolinea;

    // --- CONSULTA DE UN VUELO ---
    private JComboBox<Vuelo> cmbVueloConsultar;
    private JButton btnCalcularMetricas;
    private JLabel lblPorcentajeOcupacion;
    private JLabel lblIngresoConfirmado;
    private JLabel lblDetalleAsientos;

    // --- RANKING ---
    private JButton btnActualizarRanking;
    private JLabel lblPorcentajeMaximo;
    private JTable tblVuelosTop;
    private DefaultTableModel modeloTablaTop;

    public JFPanelOcupacion(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;

        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(243, 246, 251));

        crearPanelConsulta();
        crearPanelRanking();
        actualizarDatos();
    }

    // =========================================================================
    // PARTE SUPERIOR: consulta de un vuelo + tarjetas
    // =========================================================================
    private void crearPanelConsulta() {
        JPanel pnlSuperior = new JPanel(new BorderLayout(0, 12));
        pnlSuperior.setBackground(new Color(243, 246, 251));
        pnlSuperior.setBorder(BorderFactory.createTitledBorder("Ocupación e Ingreso Confirmado por Vuelo"));

        // Selector de vuelo
        JPanel pnlSelector = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        pnlSelector.setBackground(new Color(243, 246, 251));

        cmbVueloConsultar = new JComboBox<>();
        cmbVueloConsultar.setPreferredSize(new Dimension(280, 28));
        btnCalcularMetricas = Utilidades.botonAccion("Calcular Métricas", false);

        pnlSelector.add(new JLabel("Seleccionar Vuelo:"));
        pnlSelector.add(cmbVueloConsultar);
        pnlSelector.add(btnCalcularMetricas);
        pnlSuperior.add(pnlSelector, BorderLayout.NORTH);

        // 3 tarjetas de resumen
        JPanel pnlTarjetas = new JPanel(new GridLayout(1, 3, 15, 0));
        pnlTarjetas.setBackground(new Color(243, 246, 251));
        pnlTarjetas.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        lblPorcentajeOcupacion = crearTarjeta("Ocupación del Vuelo", "0%", new Color(27, 0, 136), pnlTarjetas);
        lblIngresoConfirmado   = crearTarjeta("Ingreso Confirmado", "$ 0", new Color(0, 128, 75), pnlTarjetas);
        lblDetalleAsientos     = crearTarjeta("Asientos Pagados / Capacidad", "0 / 0", new Color(60, 65, 80), pnlTarjetas);

        pnlSuperior.add(pnlTarjetas, BorderLayout.CENTER);
        add(pnlSuperior, BorderLayout.NORTH);
    }

    
    private JLabel crearTarjeta(String titulo, String valorInicial, Color colorValor, JPanel contenedor) {
        JPanel tarjeta = new JPanel(new BorderLayout(0, 5));
        tarjeta.setBackground(new Color(245, 246, 250));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(215, 218, 226)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblTitulo.setForeground(new Color(90, 95, 110));

        JLabel lblValor = new JLabel(valorInicial);
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblValor.setForeground(colorValor);

        tarjeta.add(lblTitulo, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);
        contenedor.add(tarjeta);

        return lblValor;
    }

    // =========================================================================
    // PARTE CENTRAL: ranking de ocupación
    // =========================================================================
    private void crearPanelRanking() {
        JPanel pnlCentro = new JPanel(new BorderLayout(0, 10));
        pnlCentro.setBackground(new Color(243, 246, 251));
        pnlCentro.setBorder(BorderFactory.createTitledBorder("Ranking de Ocupación (de mayor a menor)"));

        // Barra superior del ranking
        JPanel pnlBarra = new JPanel(new BorderLayout());
        pnlBarra.setBackground(new Color(255, 243, 246));
        pnlBarra.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        lblPorcentajeMaximo = new JLabel("Mayor ocupación registrada: 0%");
        lblPorcentajeMaximo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPorcentajeMaximo.setForeground(new Color(232, 17, 75));

        btnActualizarRanking = Utilidades.botonAccion("Actualizar Ranking", true);

        pnlBarra.add(lblPorcentajeMaximo, BorderLayout.WEST);
        pnlBarra.add(btnActualizarRanking, BorderLayout.EAST);
        pnlCentro.add(pnlBarra, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {
            "Puesto", "Código Vuelo", "Ruta", "Avión",
            "Pasajes Pagados", "Capacidad", "Ocupación (%)", "Ingreso Confirmado ($)"
        };
        modeloTablaTop = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblVuelosTop = new JTable(modeloTablaTop);
        pnlCentro.add(new JScrollPane(tblVuelosTop), BorderLayout.CENTER);

        add(pnlCentro, BorderLayout.CENTER);
    }

    // =========================================================================
    // ACTUALIZAR DATOS
    // =========================================================================
    public void actualizarDatos() {
        if (aerolinea == null) return;

        // Llenar combo de vuelos
        Vuelo vueloPrevio = (Vuelo) cmbVueloConsultar.getSelectedItem();
        cmbVueloConsultar.removeAllItems();
        for (Vuelo v : aerolinea.getVuelos()) {
            cmbVueloConsultar.addItem(v);
        }
        if (vueloPrevio != null) {
            cmbVueloConsultar.setSelectedItem(vueloPrevio);
        }

        actualizarMetricasVuelo();
        actualizarRankingTop();
    }

    public void actualizarMetricasVuelo() {
        Vuelo v = (Vuelo) cmbVueloConsultar.getSelectedItem();
        if (v == null) {
            lblPorcentajeOcupacion.setText("0%");
            lblIngresoConfirmado.setText("$ 0");
            lblDetalleAsientos.setText("0 / 0");
            return;
        }
        lblPorcentajeOcupacion.setText(v.calcularOcupacion() + "%");
        lblIngresoConfirmado.setText("$ " + v.calcularIngresoConfirmado());
        lblDetalleAsientos.setText(v.contarPasajesNoCancelados() + " / " + v.getCapacidad());
    }

    public void actualizarRankingTop() {
        modeloTablaTop.setRowCount(0);

        // Ordenar vuelos de mayor a menor ocupación (burbuja simple)
        ArrayList<Vuelo> ranking = new ArrayList<>();
        if (aerolinea.getVuelos() != null) {
            ranking.addAll(aerolinea.getVuelos());
        }
        for (int i = 0; i < ranking.size(); i++) {
            for (int j = i + 1; j < ranking.size(); j++) {
                if (ranking.get(j).calcularOcupacion() > ranking.get(i).calcularOcupacion()) {
                    Vuelo tmp = ranking.get(i);
                    ranking.set(i, ranking.get(j));
                    ranking.set(j, tmp);
                }
            }
        }

        if (ranking.isEmpty()) {
            lblPorcentajeMaximo.setText("Sin vuelos: primero programa uno en 'Aviones y Vuelos'");
            return;
        }

        lblPorcentajeMaximo.setText(
            "Mayor ocupación registrada: " + ranking.get(0).calcularOcupacion() + "%  ("
            + ranking.size() + (ranking.size() == 1 ? " vuelo)" : " vuelos)")
        );

        // Llenar la tabla
        int puesto = 1;
        for (Vuelo v : ranking) {
            String ruta = "?";
            if (v.getRuta() != null) {
                ruta = v.getRuta().getCiudadSalida().getCodigo()
                     + " → " + v.getRuta().getCiudadLlegada().getCodigo();
            }
            String avion = (v.getAvion() != null) ? v.getAvion().getMatricula() : "Sin avión";

            modeloTablaTop.addRow(new Object[]{
                puesto,
                v.getCodigo(),
                ruta,
                avion,
                v.contarPasajesNoCancelados(),
                v.getCapacidad(),
                v.calcularOcupacion() + "%",
                v.calcularIngresoConfirmado()
            });
            puesto++;
        }
    }

    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JComboBox<Vuelo> getCmbVueloConsultar() { return cmbVueloConsultar; }
    public JButton getBtnCalcularMetricas() { return btnCalcularMetricas; }
    public JButton getBtnActualizarRanking() { return btnActualizarRanking; }
    public JTable getTblVuelosTop() { return tblVuelosTop; }
    public DefaultTableModel getModeloTablaTop() { return modeloTablaTop; }
}

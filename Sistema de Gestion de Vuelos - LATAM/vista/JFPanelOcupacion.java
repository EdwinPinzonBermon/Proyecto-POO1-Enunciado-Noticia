package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelOcupacion extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;

    private JComboBox cmbVuelo;
    private JButton btnCalcular, btnMayorOcupacion;
    private JLabel lblLibres, lblOcupacion, lblIngreso;
    private DefaultTableModel mdlMayor, mdlTodos;
    private JTable tblMayor, tblTodos;

    public JFPanelOcupacion(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        setLayout(new GridLayout(3, 1, 0, 6));
        add(panelConsulta());
        add(panelMayor());
        add(panelResumen());
    }

    public JPanel panelConsulta(){
        JPanel palConsulta = new JPanel(new BorderLayout());
        palConsulta.setBorder(BorderFactory.createTitledBorder("Consultar un vuelo"));

        JPanel palSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palSuperior.add(new JLabel("Vuelo"));
        cmbVuelo = new JComboBox();
        palSuperior.add(cmbVuelo);
        btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(this);
        palSuperior.add(btnCalcular);

        JPanel palCifras = new JPanel(new GridLayout(2, 3));
        palCifras.add(new JLabel("Asientos libres", SwingConstants.CENTER));
        palCifras.add(new JLabel("Ocupacion", SwingConstants.CENTER));
        palCifras.add(new JLabel("Ingreso confirmado", SwingConstants.CENTER));
        lblLibres = new JLabel("-", SwingConstants.CENTER);
        lblOcupacion = new JLabel("-", SwingConstants.CENTER);
        lblIngreso = new JLabel("-", SwingConstants.CENTER);
        Font fuenteGrande = new Font("SansSerif", Font.BOLD, 24);
        lblLibres.setFont(fuenteGrande);
        lblOcupacion.setFont(fuenteGrande);
        lblIngreso.setFont(fuenteGrande);
        lblLibres.setForeground(Utilidades.AZUL_MEDIO);
        lblOcupacion.setForeground(Utilidades.AZUL_MEDIO);
        lblIngreso.setForeground(Utilidades.AZUL_MEDIO);
        palCifras.add(lblLibres);
        palCifras.add(lblOcupacion);
        palCifras.add(lblIngreso);

        palConsulta.add(palSuperior, BorderLayout.NORTH);
        palConsulta.add(palCifras, BorderLayout.CENTER);
        return palConsulta;
    }

    public JPanel panelMayor(){
        JPanel palMayor = new JPanel(new BorderLayout());
        palMayor.setBorder(BorderFactory.createTitledBorder("Vuelo(s) de mayor ocupacion (si hay empate se muestran todos)"));

        JPanel palBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnMayorOcupacion = new JButton("Identificar");
        btnMayorOcupacion.addActionListener(this);
        palBoton.add(btnMayorOcupacion);

        mdlMayor = new DefaultTableModel(new String[]{"Vuelo", "Ruta", "Ocupacion", "Ingreso confirmado"}, 0);
        tblMayor = Utilidades.crearTabla(mdlMayor);

        palMayor.add(palBoton, BorderLayout.NORTH);
        palMayor.add(new JScrollPane(tblMayor), BorderLayout.CENTER);
        return palMayor;
    }

    public JPanel panelResumen(){
        JPanel palResumen = new JPanel(new BorderLayout());
        palResumen.setBorder(BorderFactory.createTitledBorder("Resumen de todos los vuelos"));

        mdlTodos = new DefaultTableModel(new String[]{"Vuelo", "Ruta", "Pasajes", "Capacidad", "Ocupacion", "Ingreso confirmado"}, 0);
        tblTodos = Utilidades.crearTabla(mdlTodos);
        palResumen.add(new JScrollPane(tblTodos), BorderLayout.CENTER);
        return palResumen;
    }

    public void actualizar(){
        cmbVuelo.removeAllItems();
        mdlTodos.setRowCount(0);
        mdlMayor.setRowCount(0);
        lblLibres.setText("-");
        lblOcupacion.setText("-");
        lblIngreso.setText("-");

        ArrayList<Vuelo> vuelos = Utilidades.segura(aerolinea.getVuelos());
        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo v = vuelos.get(i);
            cmbVuelo.addItem(v.getCodigo() + " - " + Utilidades.textoRuta(v.getRuta()));
            Object[] fila = {v.getCodigo(), Utilidades.textoRuta(v.getRuta()), v.contarPasajesNoCancelados(), v.getCapacidad(),
                v.calcularOcupacion() + " %", Utilidades.textoDinero(v.calcularIngresoConfirmado())};
            mdlTodos.addRow(fila);
        }
        Utilidades.ajustarColumnas(tblTodos);
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnCalcular) {
            calcular();
        } else if (e.getSource() == btnMayorOcupacion) {
            mayorOcupacion();
        }
    }

    private void calcular(){
        int posicion = cmbVuelo.getSelectedIndex();
        if (posicion == -1) {
            Utilidades.mostrarError(this, "No hay vuelos registrados");
            return;
        }
        Vuelo vuelo = Utilidades.segura(aerolinea.getVuelos()).get(posicion);
        String codigo = vuelo.getCodigo();
        lblLibres.setText(aerolinea.consultarAsientosLibres(codigo) + " / " + vuelo.getCapacidad());
        lblOcupacion.setText(aerolinea.calcularOcupacionVuelo(codigo) + " %");
        lblIngreso.setText(Utilidades.textoDinero(aerolinea.calcularIngresoVuelo(codigo)));
    }

    private void mayorOcupacion(){
        mdlMayor.setRowCount(0);
        if (Utilidades.segura(aerolinea.getVuelos()).size() == 0) {
            Utilidades.mostrarError(this, "No hay vuelos registrados");
            return;
        }
        ArrayList<Vuelo> mayores = Utilidades.segura(aerolinea.vuelosMayorOcupacion());
        for (int i = 0; i < mayores.size(); i++) {
            Vuelo v = mayores.get(i);
            Object[] fila = {v.getCodigo(), Utilidades.textoRuta(v.getRuta()), v.calcularOcupacion() + " %", Utilidades.textoDinero(v.calcularIngresoConfirmado())};
            mdlMayor.addRow(fila);
        }
        Utilidades.ajustarColumnas(tblMayor);
    }
}

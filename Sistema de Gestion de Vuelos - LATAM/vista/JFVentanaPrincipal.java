package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelo.*;

public class JFVentanaPrincipal extends JFrame implements ActionListener
{
    private Aerolinea aerolinea;

    // Encabezado
    private JLabel lblLogo, lblTitulo, lblSubtitulo, lblDecoracion;

    // Menu
    private JButton btnVuelos, btnPasajes, btnPasajeros, btnAviones, btnTripulacion, btnRutas, btnOcupacion;

    // Pantallas (se muestran una a la vez con el CardLayout)
    private CardLayout cardLayout;
    private JPanel palContenido;
    private JFPanelVuelos panelVuelos;
    private JFPanelPasajes panelPasajes;
    private JFPanelPasajeros panelPasajeros;
    private JFPanelAviones panelAviones;
    private JFPanelTripulacion panelTripulacion;
    private JFPanelRutas panelRutas;
    private JFPanelOcupacion panelOcupacion;

    public JFVentanaPrincipal(Aerolinea aerolinea){
        Utilidades.aplicarTema(); // colores del programa (antes de crear los componentes)
        this.aerolinea = aerolinea;
        setTitle("Sistema de Gestion de Vuelos - " + aerolinea.getNombre());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon logo = Utilidades.cargarIcono("imagenes/logo.png");
        if (logo != null) {
            setIconImage(logo.getImage()); // icono de la ventana
        }

        add(panelEncabezado(), BorderLayout.NORTH);
        add(panelMenu(), BorderLayout.WEST);
        add(panelContenido(), BorderLayout.CENTER);
        Utilidades.aplicarEstilo(palContenido); // botones azules en todas las pantallas

        setSize(1150, 720);
        setLocationRelativeTo(null);
        mostrarPanel("vuelos");
        setVisible(true);
    }

    public JPanel panelEncabezado(){
        JPanel palEncabezado = new JPanel(new BorderLayout(14, 0));
        palEncabezado.setBackground(Utilidades.AZUL_OSCURO);
        // linea celeste debajo del encabezado + margen interno
        palEncabezado.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 4, 0, Utilidades.ACENTO),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));

        // Logo a la izquierda
        lblLogo = new JLabel(Utilidades.cargarIcono("imagenes/logo.png"));

        // Titulo y subtitulo en el centro
        JPanel palTextos = new JPanel(new GridLayout(2, 1));
        palTextos.setOpaque(false);
        lblTitulo = new JLabel("LATAM Airlines Colombia");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblSubtitulo = new JLabel("Sistema de gestion de vuelos");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Utilidades.CELESTE_OSCURO);
        palTextos.add(lblTitulo);
        palTextos.add(lblSubtitulo);

        // Dibujo de nubes y avion a la derecha
        lblDecoracion = new JLabel(Utilidades.cargarIcono("imagenes/decoracion.png"));

        palEncabezado.add(lblLogo, BorderLayout.WEST);
        palEncabezado.add(palTextos, BorderLayout.CENTER);
        palEncabezado.add(lblDecoracion, BorderLayout.EAST);
        return palEncabezado;
    }

    public JPanel panelMenu(){
        JPanel palMenu = new JPanel();
        palMenu.setLayout(new GridLayout(7, 1, 0, 2));
        palMenu.setBackground(Utilidades.AZUL_OSCURO);

        btnVuelos = crearBotonMenu("Vuelos", "imagenes/icono_vuelos.png");
        btnPasajes = crearBotonMenu("Pasajes", "imagenes/icono_pasajes.png");
        btnPasajeros = crearBotonMenu("Pasajeros", "imagenes/icono_pasajeros.png");
        btnAviones = crearBotonMenu("Aviones", "imagenes/icono_aviones.png");
        btnTripulacion = crearBotonMenu("Tripulacion", "imagenes/icono_tripulacion.png");
        btnRutas = crearBotonMenu("Ciudades y rutas", "imagenes/icono_rutas.png");
        btnOcupacion = crearBotonMenu("Ocupacion e ingresos", "imagenes/icono_ocupacion.png");

        palMenu.add(btnVuelos);
        palMenu.add(btnPasajes);
        palMenu.add(btnPasajeros);
        palMenu.add(btnAviones);
        palMenu.add(btnTripulacion);
        palMenu.add(btnRutas);
        palMenu.add(btnOcupacion);

        JLabel lblPie = new JLabel("Panel del administrador", SwingConstants.CENTER);
        lblPie.setForeground(Utilidades.CELESTE_OSCURO);
        lblPie.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Este panel evita que los botones se estiren por toda la altura
        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.setBackground(Utilidades.AZUL_OSCURO);
        palIzquierdo.setBorder(BorderFactory.createEmptyBorder(14, 8, 0, 8));
        palIzquierdo.setPreferredSize(new Dimension(230, 0));
        palIzquierdo.add(palMenu, BorderLayout.NORTH);
        palIzquierdo.add(lblPie, BorderLayout.SOUTH);
        return palIzquierdo;
    }

    // Crea un boton del menu con su icono (si la imagen no existe, el boton queda solo con texto)
    private JButton crearBotonMenu(String texto, String rutaIcono){
        JButton boton = new JButton(texto, Utilidades.cargarIcono(rutaIcono));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setIconTextGap(14);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(Utilidades.AZUL_OSCURO);
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createEmptyBorder(11, 14, 11, 14));
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.addActionListener(this);
        return boton;
    }

    public JPanel panelContenido(){
        cardLayout = new CardLayout();
        palContenido = new JPanel(cardLayout);
        palContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelVuelos = new JFPanelVuelos(aerolinea);
        panelPasajes = new JFPanelPasajes(aerolinea);
        panelPasajeros = new JFPanelPasajeros(aerolinea);
        panelAviones = new JFPanelAviones(aerolinea);
        panelTripulacion = new JFPanelTripulacion(aerolinea);
        panelRutas = new JFPanelRutas(aerolinea);
        panelOcupacion = new JFPanelOcupacion(aerolinea);

        palContenido.add(panelVuelos, "vuelos");
        palContenido.add(panelPasajes, "pasajes");
        palContenido.add(panelPasajeros, "pasajeros");
        palContenido.add(panelAviones, "aviones");
        palContenido.add(panelTripulacion, "tripulacion");
        palContenido.add(panelRutas, "rutas");
        palContenido.add(panelOcupacion, "ocupacion");
        return palContenido;
    }

    // Antes de mostrar una pantalla se recargan sus datos desde el modelo
    private void mostrarPanel(String nombre){
        String seccion = "";
        JButton botonActivo = btnVuelos;
        if (nombre.equals("vuelos")) {
            panelVuelos.actualizar();
            seccion = "Vuelos";
            botonActivo = btnVuelos;
        } else if (nombre.equals("pasajes")) {
            panelPasajes.actualizar();
            seccion = "Pasajes";
            botonActivo = btnPasajes;
        } else if (nombre.equals("pasajeros")) {
            panelPasajeros.actualizar();
            seccion = "Pasajeros";
            botonActivo = btnPasajeros;
        } else if (nombre.equals("aviones")) {
            panelAviones.actualizar();
            seccion = "Aviones";
            botonActivo = btnAviones;
        } else if (nombre.equals("tripulacion")) {
            panelTripulacion.actualizar();
            seccion = "Tripulacion";
            botonActivo = btnTripulacion;
        } else if (nombre.equals("rutas")) {
            panelRutas.actualizar();
            seccion = "Ciudades y rutas";
            botonActivo = btnRutas;
        } else if (nombre.equals("ocupacion")) {
            panelOcupacion.actualizar();
            seccion = "Ocupacion e ingresos";
            botonActivo = btnOcupacion;
        }
        cardLayout.show(palContenido, nombre);
        lblSubtitulo.setText("Sistema de gestion de vuelos   |   " + seccion);
        marcarBoton(botonActivo);
    }

    // Pinta el boton de la seccion actual con un azul mas claro
    private void marcarBoton(JButton activo){
        JButton[] botones = {btnVuelos, btnPasajes, btnPasajeros, btnAviones, btnTripulacion, btnRutas, btnOcupacion};
        for (int i = 0; i < botones.length; i++) {
            if (botones[i] == activo) {
                botones[i].setBackground(Utilidades.AZUL_MEDIO);
            } else {
                botones[i].setBackground(Utilidades.AZUL_OSCURO);
            }
        }
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnVuelos) {
            mostrarPanel("vuelos");
        } else if (e.getSource() == btnPasajes) {
            mostrarPanel("pasajes");
        } else if (e.getSource() == btnPasajeros) {
            mostrarPanel("pasajeros");
        } else if (e.getSource() == btnAviones) {
            mostrarPanel("aviones");
        } else if (e.getSource() == btnTripulacion) {
            mostrarPanel("tripulacion");
        } else if (e.getSource() == btnRutas) {
            mostrarPanel("rutas");
        } else if (e.getSource() == btnOcupacion) {
            mostrarPanel("ocupacion");
        }
    }
}

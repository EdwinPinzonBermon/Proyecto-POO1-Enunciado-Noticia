package vista;

import javax.swing.*;
import java.awt.*;
import modelo.*;

public class JFVentanaPrincipal extends JFrame {

    // Colores de la interfaz
    private Color colorMenu = new Color(28, 43, 75);
    private Color colorMenuActivo = new Color(42, 63, 107);
    private Color colorTextoMenu = new Color(226, 232, 240);
    private Color colorTextoGrupo = new Color(148, 163, 184);
    private Color colorFondo = new Color(243, 246, 251);

    private Aerolinea aerolinea;

    // CardLayout = varias pantallas, solo una visible
    private CardLayout cardLayout;
    private JPanel pnlContenido;
    private JPanel pnlSidebar;
    private JPanel pnlMenuItems;

    // true = menú con texto; false = menú solo con símbolo
    private boolean menuAncho = true;

    // Botones del menú (son JButton normales)
    private JButton btnMenuPrincipal;
    private JButton btnMenuRutas;
    private JButton btnMenuAvionesVuelos;
    private JButton btnMenuTripulacion;
    private JButton btnMenuPasajes;
    private JButton btnMenuOcupacion;

    // Texto completo y símbolo de cada botón (para cambiar al estrechar el menú)
    private String[] textosCompletos;
    private String[] simbolos;

    // Etiquetas de grupo (RESUMEN, CONFIGURACIÓN, etc.)
    private JLabel lblGrupoResumen;
    private JLabel lblGrupo1;
    private JLabel lblGrupo2;
    private JLabel lblGrupo3;

    // Los 6 paneles de contenido
    private JFPanelPrincipal pnlPrincipal;
    private JFPanelRutas pnlRutas;
    private JFPanelAvionesVuelos pnlAvionesVuelos;
    private JFPanelTripulacion pnlTripulacion;
    private JFPanelPasajes pnlPasajes;
    private JFPanelOcupacion pnlOcupacion;

    public JFVentanaPrincipal(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;

        setTitle(aerolinea.getNombre() + " - Gestión de Vuelos");
        setSize(1280, 800);
        setMinimumSize(new Dimension(1000, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);

        crearComponentes();
        conectarBotonesDelMenu();
        mostrarPanel("PRINCIPAL");
    }

    // =========================================================================
    // CREAR TODA LA INTERFAZ
    // =========================================================================
    private void crearComponentes() {
        crearBarraSuperior();
        crearMenuLateral();
        crearContenidoCentral();
    }

    // ----- BARRA SUPERIOR (norte) -----
    private void crearBarraSuperior() {
        JPanel barraArriba = new JPanel(new BorderLayout());
        barraArriba.setBackground(Color.WHITE);
        barraArriba.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 16));
        barraArriba.setPreferredSize(new Dimension(0, 52));

        // Parte izquierda: botón ☰ + nombre
        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);

        JButton btnMenu = new JButton("☰");
        btnMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnMenu.setFocusPainted(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(colorMenu);
        btnMenu.addActionListener(e -> cambiarAnchoDelMenu());
        izquierda.add(btnMenu);

        JLabel lblTitulo = new JLabel(aerolinea.getNombre());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(colorMenu);
        izquierda.add(lblTitulo);

        barraArriba.add(izquierda, BorderLayout.WEST);

        // Parte derecha: etiqueta "Admin"
        JLabel lblAdmin = new JLabel("  Admin  ");
        lblAdmin.setOpaque(true);
        lblAdmin.setBackground(colorMenu);
        lblAdmin.setForeground(Color.WHITE);
        lblAdmin.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 4));
        derecha.setOpaque(false);
        derecha.add(lblAdmin);
        barraArriba.add(derecha, BorderLayout.EAST);

        add(barraArriba, BorderLayout.NORTH);
    }

    // ----- MENÚ LATERAL (oeste) -----
    private void crearMenuLateral() {
        pnlSidebar = new JPanel(new BorderLayout());
        pnlSidebar.setBackground(colorMenu);
        pnlSidebar.setPreferredSize(new Dimension(240, 0));

        pnlMenuItems = new JPanel();
        pnlMenuItems.setLayout(new BoxLayout(pnlMenuItems, BoxLayout.Y_AXIS));
        pnlMenuItems.setOpaque(false);
        pnlMenuItems.setBorder(BorderFactory.createEmptyBorder(16, 8, 16, 8));

        // Textos y símbolos de cada botón
        textosCompletos = new String[]{
            "■  Panel Principal",
            "●  Rutas y Ciudades",
            "✈  Aviones y Vuelos",
            "☺  Tripulación",
            "▤  Pasajes y Asientos",
            "▦  Ocupación e Ingresos"
        };
        simbolos = new String[]{"■", "●", "✈", "☺", "▤", "▦"};

        // Crear los 6 botones como JButton normales
        btnMenuPrincipal      = crearBotonMenu(textosCompletos[0]);
        btnMenuRutas          = crearBotonMenu(textosCompletos[1]);
        btnMenuAvionesVuelos  = crearBotonMenu(textosCompletos[2]);
        btnMenuTripulacion    = crearBotonMenu(textosCompletos[3]);
        btnMenuPasajes        = crearBotonMenu(textosCompletos[4]);
        btnMenuOcupacion      = crearBotonMenu(textosCompletos[5]);

        // Etiquetas de grupo
        lblGrupoResumen = crearEtiquetaGrupo("RESUMEN");
        lblGrupo1 = crearEtiquetaGrupo("1 · CONFIGURACIÓN BASE");
        lblGrupo2 = crearEtiquetaGrupo("2 · FLOTA Y OPERACIÓN");
        lblGrupo3 = crearEtiquetaGrupo("3 · VENTAS Y MÉTRICAS");

        // Orden de los elementos en el menú
        pnlMenuItems.add(lblGrupoResumen);
        pnlMenuItems.add(btnMenuPrincipal);
        pnlMenuItems.add(lblGrupo1);
        pnlMenuItems.add(btnMenuRutas);
        pnlMenuItems.add(lblGrupo2);
        pnlMenuItems.add(btnMenuAvionesVuelos);
        pnlMenuItems.add(Box.createRigidArea(new Dimension(0, 2)));
        pnlMenuItems.add(btnMenuTripulacion);
        pnlMenuItems.add(lblGrupo3);
        pnlMenuItems.add(btnMenuPasajes);
        pnlMenuItems.add(Box.createRigidArea(new Dimension(0, 2)));
        pnlMenuItems.add(btnMenuOcupacion);

        pnlSidebar.add(pnlMenuItems, BorderLayout.NORTH);
        add(pnlSidebar, BorderLayout.WEST);
    }

    
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 13));
        boton.setForeground(colorTextoMenu);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 8));
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setPreferredSize(new Dimension(224, 42));
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return boton;
    }

    
    private JLabel crearEtiquetaGrupo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        lbl.setForeground(colorTextoGrupo);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(14, 10, 4, 0));
        return lbl;
    }

    // ----- CONTENIDO CENTRAL (CardLayout) -----
    private void crearContenidoCentral() {
        cardLayout = new CardLayout();
        pnlContenido = new JPanel(cardLayout);
        pnlContenido.setBackground(colorFondo);

        // Se crean los 6 paneles
        pnlPrincipal     = new JFPanelPrincipal(aerolinea);
        pnlRutas         = new JFPanelRutas(aerolinea);
        pnlAvionesVuelos = new JFPanelAvionesVuelos(aerolinea);
        pnlTripulacion   = new JFPanelTripulacion(aerolinea);
        pnlPasajes       = new JFPanelPasajes(aerolinea);
        pnlOcupacion     = new JFPanelOcupacion(aerolinea);

        // Se agregan al CardLayout con un nombre (clave)
        pnlContenido.add(pnlPrincipal,     "PRINCIPAL");
        pnlContenido.add(pnlRutas,         "RUTAS");
        pnlContenido.add(pnlAvionesVuelos, "AVIONES_VUELOS");
        pnlContenido.add(pnlTripulacion,   "TRIPULACION");
        pnlContenido.add(pnlPasajes,       "PASAJES");
        pnlContenido.add(pnlOcupacion,     "OCUPACION");

        add(pnlContenido, BorderLayout.CENTER);
    }

    // =========================================================================
    // COMPORTAMIENTO DEL MENÚ
    // =========================================================================

    private void conectarBotonesDelMenu() {
        btnMenuPrincipal.addActionListener(e -> mostrarPanel("PRINCIPAL"));
        btnMenuRutas.addActionListener(e -> mostrarPanel("RUTAS"));
        btnMenuAvionesVuelos.addActionListener(e -> mostrarPanel("AVIONES_VUELOS"));
        btnMenuTripulacion.addActionListener(e -> mostrarPanel("TRIPULACION"));
        btnMenuPasajes.addActionListener(e -> mostrarPanel("PASAJES"));
        btnMenuOcupacion.addActionListener(e -> mostrarPanel("OCUPACION"));
    }

    
    private void cambiarAnchoDelMenu() {
        menuAncho = !menuAncho;

        if (menuAncho) {
            pnlSidebar.setPreferredSize(new Dimension(240, 0));
        } else {
            pnlSidebar.setPreferredSize(new Dimension(64, 0));
        }

        // Actualizar el texto de cada botón
        actualizarTextoBoton(btnMenuPrincipal,     0);
        actualizarTextoBoton(btnMenuRutas,         1);
        actualizarTextoBoton(btnMenuAvionesVuelos, 2);
        actualizarTextoBoton(btnMenuTripulacion,   3);
        actualizarTextoBoton(btnMenuPasajes,       4);
        actualizarTextoBoton(btnMenuOcupacion,     5);

        // Las etiquetas de grupo solo se ven cuando el menú está ancho
        lblGrupoResumen.setVisible(menuAncho);
        lblGrupo1.setVisible(menuAncho);
        lblGrupo2.setVisible(menuAncho);
        lblGrupo3.setVisible(menuAncho);

        pnlSidebar.revalidate();
        pnlSidebar.repaint();
    }

    
    private void actualizarTextoBoton(JButton boton, int indice) {
        if (menuAncho) {
            boton.setText(textosCompletos[indice]);
            boton.setHorizontalAlignment(SwingConstants.LEFT);
            boton.setPreferredSize(new Dimension(224, 42));
        } else {
            boton.setText(simbolos[indice]);
            boton.setHorizontalAlignment(SwingConstants.CENTER);
            boton.setPreferredSize(new Dimension(52, 42));
        }
        boton.revalidate();
    }

    
    private void marcarBotonActivo(JButton boton, boolean activo) {
        if (activo) {
            boton.setOpaque(true);
            boton.setBackground(colorMenuActivo);
        } else {
            boton.setOpaque(false);
        }
        boton.repaint();
    }

    
    public void mostrarPanel(String nombre) {
        cardLayout.show(pnlContenido, nombre);

        // Todos inactivos primero
        marcarBotonActivo(btnMenuPrincipal,     false);
        marcarBotonActivo(btnMenuRutas,         false);
        marcarBotonActivo(btnMenuAvionesVuelos, false);
        marcarBotonActivo(btnMenuTripulacion,   false);
        marcarBotonActivo(btnMenuPasajes,       false);
        marcarBotonActivo(btnMenuOcupacion,     false);

        // Activar el que corresponde y refrescar sus datos
        if (nombre.equals("PRINCIPAL")) {
            marcarBotonActivo(btnMenuPrincipal, true);
            pnlPrincipal.actualizarDatos();
        } else if (nombre.equals("RUTAS")) {
            marcarBotonActivo(btnMenuRutas, true);
            pnlRutas.actualizarDatos();
        } else if (nombre.equals("AVIONES_VUELOS")) {
            marcarBotonActivo(btnMenuAvionesVuelos, true);
            pnlAvionesVuelos.actualizarDatos();
        } else if (nombre.equals("TRIPULACION")) {
            marcarBotonActivo(btnMenuTripulacion, true);
            pnlTripulacion.actualizarDatos();
        } else if (nombre.equals("PASAJES")) {
            marcarBotonActivo(btnMenuPasajes, true);
            pnlPasajes.actualizarDatos();
        } else if (nombre.equals("OCUPACION")) {
            marcarBotonActivo(btnMenuOcupacion, true);
            pnlOcupacion.actualizarDatos();
        }
    }

    
    public void actualizarKpis() {
        if (pnlPrincipal != null) {
            pnlPrincipal.actualizarDatos();
        }
    }

    // --- Getters para el controlador ---
    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JButton getBtnMenuPrincipal() { return btnMenuPrincipal; }
    public JButton getBtnMenuRutas() { return btnMenuRutas; }
    public JButton getBtnMenuAvionesVuelos() { return btnMenuAvionesVuelos; }
    public JButton getBtnMenuTripulacion() { return btnMenuTripulacion; }
    public JButton getBtnMenuPasajes() { return btnMenuPasajes; }
    public JButton getBtnMenuOcupacion() { return btnMenuOcupacion; }

    public JFPanelPrincipal getPnlPrincipal() { return pnlPrincipal; }
    public JFPanelRutas getPnlRutas() { return pnlRutas; }
    public JFPanelAvionesVuelos getPnlAvionesVuelos() { return pnlAvionesVuelos; }
    public JFPanelTripulacion getPnlTripulacion() { return pnlTripulacion; }
    public JFPanelPasajes getPnlPasajes() { return pnlPasajes; }
    public JFPanelOcupacion getPnlOcupacion() { return pnlOcupacion; }
}

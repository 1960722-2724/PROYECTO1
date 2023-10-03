package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Jugador;

public class VentanaJuego extends JFrame {
    private JLabel lblNombre;
    private JLabel lblNivel;
    private JLabel lblIntentos;
    private JLabel lblFallos;
    private JLabel lblFiguraMuestra;
    private JButton btnTerminarJuego;
    private JLabel[] figurasLabels;
    private Jugador jugador; // Agregamos el jugador como atributo

    public VentanaJuego(Jugador jugador) {
        super("Juego de Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Inicializamos el jugador
        this.jugador = jugador;

        initComponents();
        setupLayout();
        setupActions();
    }

    private void initComponents() {
        lblNombre = new JLabel("Jugador: " + jugador.getNombre()); // Mostramos el nombre del jugador
        lblNivel = new JLabel("Nivel: 1");
        lblIntentos = new JLabel("Intentos: 0");
        lblFallos = new JLabel("Fallos: 0");
        lblFiguraMuestra = new JLabel();

        btnTerminarJuego = new JButton("Terminar Juego");
        figurasLabels = new JLabel[4];

        for (int i = 0; i < figurasLabels.length; i++) {
            ImageIcon icono = new ImageIcon("src/imagenes/nivel1/" + (i + 1) + ".png");
            JLabel label = new JLabel(icono);
            label.setName("src/imagenes/nivel1/" + (i + 1) + ".png");
            figurasLabels[i] = label;
        }
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelFiguraMuestra = new JPanel();
        panelFiguraMuestra.add(lblFiguraMuestra);

        JPanel panelFiguras = new JPanel(new GridLayout(2, 2));
        for (JLabel label : figurasLabels) {
            panelFiguras.add(label);
        }

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.add(lblNombre);
        panelDatos.add(lblNivel);
        panelDatos.add(lblIntentos);
        panelDatos.add(lblFallos);

        JPanel panelBotonTerminar = new JPanel();
        panelBotonTerminar.add(btnTerminarJuego);

        add(panelFiguraMuestra, BorderLayout.WEST);
        add(panelFiguras, BorderLayout.CENTER);
        add(panelDatos, BorderLayout.EAST);
        add(panelBotonTerminar, BorderLayout.SOUTH);
    }

    private void setupActions() {
        btnTerminarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarResumen();
            }
        });

        for (JLabel label : figurasLabels) {
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    figuraLabelClicked(evt);
                }
            });
        }
    }

    private void figuraLabelClicked(java.awt.event.MouseEvent evt) {
        String labelClicked = ((JLabel) evt.getSource()).getName();
        String figuraCorrecta = "src/imagenes/nivel1/1.png"; // Reemplaza con la figura correcta actual

        if (labelClicked.equals(figuraCorrecta)) {
            JOptionPane.showMessageDialog(null, "¡Felicidades!");
            // Actualizar lógica del juego (avanzar nivel, etc.)
            actualizarVentana();
        } else {
            // Incrementar fallos u otra lógica de juego
            JOptionPane.showMessageDialog(null, "Intenta de nuevo.");
            // Actualizar lógica del juego (incrementar fallos, etc.)
            actualizarVentana();
        }
    }

    private void actualizarVentana() {
        // Actualiza la información de la ventana según la lógica del juego
        // Por ejemplo, actualiza lblNivel, lblIntentos, lblFallos, lblFiguraMuestra
    }

    private void mostrarResumen() {
        // Muestra un resumen del juego y cierra la aplicación
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador("Ejemplo");
            new VentanaJuego(jugador).setVisible(true);
        });
    }
}

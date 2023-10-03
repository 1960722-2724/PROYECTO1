package vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.Jugador;

public class VentanaJuego extends JFrame {
    private JLabel lblNombre;
    private JLabel lblNivel;
    private JLabel lblIntentos;
    private JLabel lblFallos;
    private JLabel lblFiguraMuestra;
    private JButton btnTerminarJuego;
    private JLabel[] figurasLabels;
    private Jugador jugador;

    public VentanaJuego(Jugador jugador) {
        super("Juego de Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        this.jugador = jugador;

        initComponents();
        setupLayout();
        setupActions();
    }

    private void initComponents() {
        lblNombre = new JLabel("Jugador: " + jugador.getNombre());
        lblNivel = new JLabel("Nivel: 1");
        lblIntentos = new JLabel("Intentos: 0");
        lblFallos = new JLabel("Fallos: 0");

        btnTerminarJuego = new JButton("Terminar Juego");
        figurasLabels = new JLabel[4]; // Mostraremos 4 imágenes en total

        List<String> rutas = new ArrayList<>();
        for (int i = 1; i <= 4; i++) { // Cambiamos a 4 rutas en lugar de 5
            rutas.add("src/imagenes/nivel1/" + i + ".png");
        }

        // Shuffle para que las imágenes estén en orden aleatorio
        Collections.shuffle(rutas);

        // Elegimos una imagen aleatoria que se mostrará dos veces (como muestra y opción)
        Random random = new Random();
        int indiceImagenDuplicada = random.nextInt(rutas.size());
        String rutaImagenDuplicada = rutas.get(indiceImagenDuplicada);

        // Las siguientes imágenes serán las opciones (las primeras 3)
        for (int i = 0; i < figurasLabels.length; i++) {
            String rutaOpcion;
            if (i == 3) {
                // Si es la última opción, usamos la ruta duplicada como imagen de muestra
                rutaOpcion = rutaImagenDuplicada;
            } else {
                rutaOpcion = rutas.get(i);
            }

            ImageIcon iconoOpcion = new ImageIcon(rutaOpcion);
            JLabel labelOpcion = new JLabel(iconoOpcion);
            labelOpcion.setName(rutaOpcion);
            figurasLabels[i] = labelOpcion;

            if (i == 3) {
                lblFiguraMuestra = labelOpcion;
            }
        }

        System.out.println("Ruta de la imagen de muestra: " + lblFiguraMuestra.getName());
        for (int i = 0; i < 3; i++) {
            System.out.println("Ruta de opción " + (i + 1) + ": " + figurasLabels[i].getName());
        }
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelFiguraMuestra = new JPanel();
        panelFiguraMuestra.add(lblFiguraMuestra);

        JPanel panelFiguras = new JPanel(new GridLayout(1, 3)); // Cambiamos a 1 fila y 3 columnas
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

        add(panelFiguraMuestra, BorderLayout.NORTH); // Cambiamos la ubicación de la imagen de muestra
        add(panelFiguras, BorderLayout.CENTER);
        add(panelDatos, BorderLayout.EAST);
        add(panelBotonTerminar, BorderLayout.SOUTH);
    }

    private void setupActions() {
        // Agrega acciones de botones y clics como se hacía anteriormente
    }

    // Resto del código (actualizarVentana, mostrarResumen, main) igual que antes

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador("Ejemplo");
            new VentanaJuego(jugador).setVisible(true);
        });
    }
}

package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private int nivel = 1;
    private int intentos = 0;
    private int fallos = 0;

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
        lblNivel = new JLabel("Nivel: " + nivel);
        lblIntentos = new JLabel("Intentos: " + intentos);
        lblFallos = new JLabel("Fallos: " + fallos);

        btnTerminarJuego = new JButton("Terminar Juego");
        figurasLabels = new JLabel[4]; // Mostraremos 4 imágenes en total

        List<String> rutas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            rutas.add("src/imagenes/nivel" + nivel + "/" + i + ".png");
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
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelFiguraMuestra = new JPanel();
        panelFiguraMuestra.add(lblFiguraMuestra);

        JPanel panelFiguras = new JPanel(new GridLayout(1, 3));
        for (JLabel label : figurasLabels) {
            panelFiguras.add(label);
        }

        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelDatos.add(lblNombre);
        panelDatos.add(lblNivel);
        panelDatos.add(lblIntentos);
        panelDatos.add(lblFallos);

        JPanel panelBotonTerminar = new JPanel();
        panelBotonTerminar.add(btnTerminarJuego);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelDatos, BorderLayout.NORTH);
        panelInferior.add(panelBotonTerminar, BorderLayout.SOUTH);

        add(panelFiguraMuestra, BorderLayout.NORTH);
        add(panelFiguras, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void setupActions() {
        // Agregamos un ActionListener a cada imagen de opción
        for (int i = 0; i < figurasLabels.length; i++) {
            final int index = i;
            figurasLabels[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    imagenOpcionMouseClicked(evt, index);
                }
            });
        }

        btnTerminarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir del juego?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void imagenOpcionMouseClicked(java.awt.event.MouseEvent evt, int index) {
        String rutaSeleccionada = figurasLabels[index].getName();
        if (rutaSeleccionada.equals(lblFiguraMuestra.getName())) {
            // La imagen seleccionada es la correcta
            JOptionPane.showMessageDialog(null, "¡Felicitaciones! Has seleccionado la opción correcta.");
            nivel++;
            actualizarDatosJuego();
        } else {
            // La imagen seleccionada es incorrecta
            JOptionPane.showMessageDialog(null, "Has seleccionado la opción incorrecta.");
            fallos++;
            actualizarDatosJuego();
        }
    }

    private void actualizarDatosJuego() {
        lblNivel.setText("Nivel: " + nivel);
        intentos++;
        lblIntentos.setText("Intentos: " + intentos);
        lblFallos.setText("Fallos: " + fallos);

        List<String> rutas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            rutas.add("src/imagenes/nivel" + nivel + "/" + i + ".png");
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
            figurasLabels[i].setIcon(iconoOpcion);
            figurasLabels[i].setName(rutaOpcion);

            if (i == 3) {
                lblFiguraMuestra.setIcon(iconoOpcion);
                lblFiguraMuestra.setName(rutaOpcion);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador("Ejemplo");
            new VentanaJuego(jugador).setVisible(true);
        });
    }
}

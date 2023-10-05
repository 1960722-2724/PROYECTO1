package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel lblOpcion1;
    private JLabel lblOpcion2;
    private JLabel lblOpcion3;
    private JButton btnNivel2;
    private JButton btnTerminarJuego; // Nuevo botón
    private Jugador jugador;

    private int nivel = 0; // Cambio: Inicialmente, el nivel debe ser 1
    private int intentos = 0;
    private int fallos = 0;
    private int intentosAcertados = 0; // Nuevo contador para intentos acertados
    private int cicloNivelesCompletos = 0; // Nueva variable para rastrear ciclos de niveles completados

    public VentanaJuego(Jugador jugador) {
        super("Juego de Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        this.jugador = jugador;

        initComponents();
        setupLayout();
        setupActions();
        actualizarNivel();
    }

    private void initComponents() {
        lblNombre = new JLabel("Jugador: " + jugador.getNombre());
        lblNivel = new JLabel("aciertos: " + nivel);
        lblIntentos = new JLabel("Intentos: " + intentos);
        lblFallos = new JLabel("Fallos: " + fallos);

        btnNivel2 = new JButton("Nivel 2");

        lblFiguraMuestra = new JLabel();
        lblOpcion1 = new JLabel();
        lblOpcion2 = new JLabel();
        lblOpcion3 = new JLabel();
        btnTerminarJuego = new JButton("Terminar Juego"); // Nuevo botón
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel para las imágenes de las opciones
        JPanel panelOpciones = new JPanel(new GridLayout(1, 3));

        // Configuración de los JLabels de las opciones
        configureImageLabel(lblOpcion1);
        panelOpciones.add(lblOpcion1);

        configureImageLabel(lblOpcion2);
        panelOpciones.add(lblOpcion2);

        configureImageLabel(lblOpcion3);
        panelOpciones.add(lblOpcion3);

        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelDatos.add(lblNombre);
        panelDatos.add(lblNivel);
        panelDatos.add(lblIntentos);
        panelDatos.add(lblFallos);
        panelDatos.add(btnTerminarJuego); // Agrega el nuevo botón al panel de datos

        // Crear un JSplitPane para dividir la ventana en dos partes (izquierda y derecha)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lblFiguraMuestra, panelOpciones);
        splitPane.setDividerLocation(200); // Establecer la ubicación de la división

        add(splitPane, BorderLayout.CENTER);
        add(panelDatos, BorderLayout.SOUTH);
    }

    private void setupActions() {
        btnNivel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivel = 1;
                actualizarNivel();
            }
        });

        btnTerminarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarResumenJuego();
            }
        });

        // Agregar oyentes de clic para las etiquetas de imagen de las opciones
        lblOpcion1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verificarSeleccion(lblOpcion1);
            }
        });

        lblOpcion2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verificarSeleccion(lblOpcion2);
            }
        });

        lblOpcion3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verificarSeleccion(lblOpcion3);
            }
        });
    }

    private void configureImageLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor de la imagen
        label.setIcon(null); // Inicialmente, no muestra ninguna imagen
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al puntero de mano
    }

    private void actualizarNivel() {
        lblNivel.setText("aciertos: " + nivel);
        intentos = 0;
        intentosAcertados = 0; // Reiniciar los intentos acertados
        lblIntentos.setText("Intentos: " + intentos);
        lblFallos.setText("Fallos: " + fallos);

        List<String> rutas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String ruta = "src/imagenes/nivel" + (nivel % 6 + 1) + "/" + i + ".png"; // Asegurarse de que el nivel esté en el rango 1-7
            rutas.add(ruta);
        }

        Collections.shuffle(rutas);

        // Elegir una imagen aleatoria que se mostrará dos veces (como muestra y opción)
        Random random = new Random();
        int indiceImagenDuplicada = random.nextInt(3); // Índice aleatorio entre 0 y 2
        String rutaImagenDuplicada = rutas.get(indiceImagenDuplicada);

        // Las siguientes imágenes serán las opciones
        rutas.remove(indiceImagenDuplicada); // Eliminar la imagen duplicada de las opciones
        Collections.shuffle(rutas); // Barajar las rutas nuevamente

        // Establecer la imagen de muestra
        ImageIcon iconoMuestra = new ImageIcon(rutaImagenDuplicada);
        lblFiguraMuestra.setIcon(iconoMuestra);

        // Las siguientes imágenes serán las opciones
        ImageIcon iconoOpcion1 = new ImageIcon(rutas.get(0));
        lblOpcion1.setIcon(iconoOpcion1);
        ImageIcon iconoOpcion2 = new ImageIcon(rutas.get(1));
        lblOpcion2.setIcon(iconoOpcion2);
        ImageIcon iconoOpcion3 = new ImageIcon(rutaImagenDuplicada); // La imagen duplicada como opción
        lblOpcion3.setIcon(iconoOpcion3);
    }

    private void verificarSeleccion(JLabel label) {
    intentos++;
    lblIntentos.setText("Intentos: " + intentos);

    // Obtener la ruta de la imagen seleccionada
    Icon icon = label.getIcon();
    if (icon != null && icon instanceof ImageIcon) {
        ImageIcon selectedImageIcon = (ImageIcon) icon;
        String selectedImagePath = selectedImageIcon.getDescription();

        // Obtener la ruta de la imagen de muestra
        Icon muestraIcon = lblFiguraMuestra.getIcon();
        if (muestraIcon != null && muestraIcon instanceof ImageIcon) {
            ImageIcon muestraImageIcon = (ImageIcon) muestraIcon;
            String muestraImagePath = muestraImageIcon.getDescription();

            // Comparar las rutas de las imágenes seleccionada y de muestra
            if (selectedImagePath.equals(muestraImagePath)) {
                // La selección es correcta
                JOptionPane.showMessageDialog(this, "¡Selección Correcta!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                
                intentosAcertados++; // Incrementar los intentos acertados
            } else {
                // La selección es incorrecta
                fallos++;
                lblFallos.setText("Fallos: " + fallos);
                JOptionPane.showMessageDialog(this, "Selección Incorrecta", "Incorrecto", JOptionPane.ERROR_MESSAGE);

                // Reordenar las imágenes y mostrarlas en un orden diferente
                actualizarNivel();
            }
        }
    }

    // Actualizar el nivel si el jugador ha acertado todas las selecciones
    if (intentosAcertados == 1) {
        nivel++;
        actualizarNivel();
    }
}


    // Método para mostrar el resumen del juego en una ventana emergente
    private void mostrarResumenJuego() {
        
        double porcentajeFallos = (double) fallos / (nivel + fallos) * 100;
        double porcentajeAciertos = (double) nivel / (nivel + fallos) * 100;
        double nota = (porcentajeAciertos / 20.0) + 1.0;
        
        String resumen = "Resumen del Juego:\n" +
                "Jugador:           " + jugador.getNombre() + "\n" + "\n"+
                "Figuras mostradas:            " + (nivel + fallos) + "\n" + "\n"+
                "Aciertos:             " + nivel + "    |    " + String.format("%.2f", porcentajeAciertos) + "%" + "\n" + "\n"+
                "Fallos totales:    " + fallos + "    |    " + String.format("%.2f", porcentajeFallos) + "%" + "\n" + "\n"+
                "NOTA:              " + String.format("%.2f",nota);
        

        JOptionPane.showMessageDialog(this, resumen, "Resumen del Juego", JOptionPane.INFORMATION_MESSAGE);
         System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador("Ejemplo");
            new VentanaJuego(jugador).setVisible(true);
        });
    }
}


package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    private Header jpHeader;
    private JTextField txtNombre;
    private JButton btnJugar;
    private JButton btnInstrucciones;
    private JButton btnLimpiar;
    private JButton btnAceptar;

    public VentanaPrincipal() {
        setTitle("Juego de Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        btnLimpiar = new JButton("Limpiar");
        btnAceptar = new JButton("Aceptar");
        btnJugar = new JButton("Iniciar Juego");
        btnInstrucciones = new JButton("Instrucciones");
        jpHeader = new Header("/imagenes/header.png");
        
        txtNombre = new JTextField(20);

        btnLimpiar.setBounds(50, 500, 200, 35);
        btnAceptar.setBounds(260, 500, 200, 35);
        btnJugar.setBounds(700, 450, 200, 35);
        btnInstrucciones.setBounds(700, 500, 200, 35);
        txtNombre.setBounds(50, 450, 410, 40);
        jpHeader.setSize(1000,300);
        
        add(jpHeader);
        add(btnLimpiar);
        add(btnAceptar);
        add(btnJugar);
        add(btnInstrucciones);
        add(txtNombre);

        JLabel jNombre1 = new JLabel("INGRESA TU NOMBRE");
        jNombre1.setBounds(50, 400, 519, 35);
        add(jNombre1);

        ManejadorDeEventos manejadorEventos = new ManejadorDeEventos();

        btnJugar.addActionListener(manejadorEventos);
        btnInstrucciones.addActionListener(manejadorEventos);
        btnLimpiar.addActionListener(manejadorEventos);
        btnAceptar.addActionListener(manejadorEventos);
        txtNombre.addKeyListener(manejadorEventos);

        txtNombre.requestFocusInWindow();
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        //});
    }

    class ManejadorDeEventos implements ActionListener, KeyListener {
              public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == btnAceptar) {
                btnJugar.setEnabled(true); // Habilitar el botón "Iniciar Juego"
            } 
            else if (evento.getSource() == btnJugar) {
                // Aquí puedes realizar acciones cuando se hace clic en "Iniciar Juego"
                JOptionPane.showMessageDialog(
                        null,
                        "¡Juego iniciado para: " + txtNombre.getText(),
                        "Mensaje",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } 
            else if (evento.getSource() == btnInstrucciones) {
                        JOptionPane.showMessageDialog(
                        null,
                        "escoge la figura correcta, tomando como referencia la imagen de la izquierda",
                        "Instrucciones",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } 
            else if (evento.getSource() == btnLimpiar) {
                txtNombre.setText(""); // Limpiar el campo de texto
                btnJugar.setEnabled(false); // Deshabilitar el botón "Iniciar Juego"
            }
        }

        public void keyTyped(KeyEvent e) {
        }
        public void keyPressed(KeyEvent e) {
        }
        public void keyReleased(KeyEvent e) {
            if (!txtNombre.getText().isEmpty()) {
                btnAceptar.setEnabled(true); // Habilitar el botón "Aceptar"
            } else {
                btnAceptar.setEnabled(false); // Deshabilitar el botón "Aceptar"
            }
        }
    }
}


package vista;

import modelo.Jugador;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *
 * @author andre
 */
public final class VentanaPrincipal extends JFrame {
    private Header jpHeader;
    private JPanel jpContenido;
    private JLabel jNombre1;
    private JTextField txtNombre;
    private JButton btnJugar; 
    private JButton btnInstrucciones; 
    private JButton btnLimpiar; 
    private JButton btnAceptar; 
    
    public VentanaPrincipal(){
        iniciarComponentes();
    }
    
//recuadro principal
    private void Ventana(){
    
        setTitle("juego de figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,650);
        setLocationRelativeTo(null);
        setVisible(true); 
        setResizable(false);
        setLayout(null);
        
    btnLimpiar = new JButton("Limpiar");
    btnAceptar = new JButton("Aceptar");
    btnJugar = new JButton("Iniciar Juego");
    btnInstrucciones = new JButton("Instrucciones");
    
    txtNombre = new JTextField(20);
    
    btnLimpiar.setBounds(50, 550, 150, 35);
    btnAceptar.setBounds(210, 550, 150, 35);
    btnJugar.setBounds(600, 500, 150, 35);
    btnInstrucciones.setBounds(600, 550, 150, 35);
    txtNombre.setBounds(50, 100, 410, 40);   
    jpHeader.setSize(1000,300);
    jpContenido.setSize(1000,300);
    jpContenido.setBounds(0,110,1000,300);
    jpContenido.setLayout(null);
    
    jpContenido.add(jpHeader);
    jpContenido.add(jNombre1);
    jpContenido.add(btnJugar);
    jpContenido.add(btnInstrucciones);
    jpContenido.add(btnLimpiar);
    jpContenido.add(btnAceptar);
    jpContenido.add(txtNombre);
    add (jpHeader);
    add (jpContenido);
    
    jNombre1 = new JLabel("INGRESA TU NOMBRE");
    jNombre1.setBounds(50, 50, 519, 35);
    
    jpHeader = new Header("/imagenes/header.png"); 
    jpContenido = new JPanel();
    
    ManejadorDeEventos manejadorEventos = new ManejadorDeEventos();
    
    btnJugar.addActionListener(manejadorEventos);
    btnInstrucciones.addActionListener(manejadorEventos);
    btnLimpiar.addActionListener(manejadorEventos);
    btnAceptar.addActionListener(manejadorEventos);
    txtNombre.addKeyListener(manejadorEventos);
    
    txtNombre.requestFocusInWindow();
    Toolkit miPantalla = Toolkit.getDefaultToolkit();
    }
    
    
//necesito ayuda con la logica de los botones 
private void ingresoNombre(){
  String nombre =txtNombre.getText();
        if(!nombre.trim().isEmpty() || nombre.trim().length() > 0){
            Jugador jugador = new Jugador(nombre);        
            VentanaJuego ventanaJuego = new VentanaJuego(jugador);              
        } else {
            JOptionPane.showMessageDialog(null,"ingrese un nombre valido", 
                    "Advertencia", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocusInWindow();
        }
    }

private void desBtnJugar(){
    //funcion que desloquea el boton de jugar cuando ya se haya presionado el boton de aceptar
    //y esta me lleva al juego como tal
}
        
        
        
class ManejadorDeEventos implements ActionListener, KeyListener{
       
        public void actionPerformed(ActionEvent evento){
            if(evento.getSource() == btnIngresar){                
                iniciarJuego();
            }
        }
        
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == e.VK_ENTER){
                btnJugar.doClick();
            }
        }
        
        public void keyPressed(KeyEvent e) {   
        }
        
        public void keyTyped(KeyEvent e) {
        }
    }
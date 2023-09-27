/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
    private JLabel jMensaje;
    private JPanel jpContenido;
    private JLabel jNombre;
    private JTextField txtNombre;
    private JButton btnJugar; 
    private JButton btnInstrucciones; 
    private JButton btnLimpiar; 
    private JButton btnAceptar; 
    
    public VentanaPrincipal(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        //recuadro principal
        setTitle("juego figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,650);
        setLocationRelativeTo(null);
        setVisible(true); 
        setResizable(false);
        setLayout(null);
    
    Toolkit miPantalla = Toolkit.getDefaultToolkit();
    
    //configuracion de la vista del juego
     jpHeader = new Header("/imagenes/header.png"); 
        jpContenido = new JPanel();
    
    jlMensaje = new JLabel("BIENVENIDO",SwingConstants.CENTER);
        jlNombre = new JLabel("INGRESA TU NOMBRE",SwingConstants.LEFT);

    jpHeader.setSize(1000,300);
    
    jpContenido.setSize(1000,300);
    jpContenido.setBounds(0,110,1000,300);
    jpContenido.setLayout(null);

    add (jpHeader);
    add (jpContenido);
    
    jMensaje.setBounds(0,20, 519,20);
    jMensaje.setForeground(Color.blue);
    jMensaje.setFont(new Font("arial", Font.BOLD, 20));  
        
        
    jNombre.setBounds(0,130, 519,35);
    jNombre.setForeground( Color.ORANGE);
    jNombre.setFont(new Font("arial", Font.BOLD, 20)); 
    
    btnJugar = new JButton("Iniciar Juego");
    btnJugar.setBounds(180,270, 150,35);
     
    btnInstrucciones = new JButton ("instrucciones");
    btnInstrucciones.setBounds(100,190, 150,35);
     
    btnLimpiar = new JButton ("limpiar"); 
    btnLimpiar.setBounds(50,100,150,35);
    
    btnAceptar = new JButton ("aceptar");
    btnAceptar.setBounds(80,130, 150, 30);
     
    jpContenido.add(jMensaje);
    jpContenido.add(jNombre);
    jpContenido.add(btnJugar);
    jpContenido.add(btnInstrucciones);
    jpContenido.add(btnLimpiar);
    jpContenido.add(btnAceptar);
    
    txtNombre = new JTextField("");
    txtNombre.setHorizontalAlignment(JTextField.LEFT);
    txtNombre.setForeground(Color.GRAY);
    txtNombre.setFont(new Font("arial", Font.BOLD, 20));
    
    jpContenido.add(txtNombre);
    
    txtNombre.setBounds(50,190,410, 40);
    
    //ORGANIZAR COORDENADAS
    
    //MANEJADOR DE EVENTOS
    
      
    
    
    
    
    
    
    
    
    }

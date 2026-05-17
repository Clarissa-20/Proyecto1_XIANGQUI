/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 *
 * @author HP
 */
public class ModificarDatos extends JFrame {
    private Player playerActual;
    private Sistema sistema;
    private JPasswordField txtConfirmarPass, txtnuevaPass;
    
    public ModificarDatos(Player playerActual, Sistema sistema){
        this.playerActual = playerActual;
        this.sistema = sistema;
        
        setTitle("Xiangqi - Modificar Datos");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        ClaseFondo cf = new ClaseFondo("/img/miPerfil.png");
        cf.setLayout(new BorderLayout());
        cf.setBorder(BorderFactory.createEmptyBorder(20, 200, 10, 200));
        
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 5), "MODIFICAR DATOS",
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Bodoni Bd BT", Font.BOLD, 30), new Color(255, 204, 51)));
        
        txtConfirmarPass = new JPasswordField(5);
        txtConfirmarPass.setBackground(Color.BLACK);
        txtConfirmarPass.setForeground(Color.WHITE);
        txtConfirmarPass.setFont(new Font("Bodoni Bd BT", Font.PLAIN, 18));
        
        txtnuevaPass = new JPasswordField(5);
        txtnuevaPass.setBackground(Color.BLACK);
        txtnuevaPass.setForeground(Color.WHITE);
        txtnuevaPass.setFont(new Font("Bodoni Bd BT", Font.PLAIN, 18));
        
        JButton btnCambiarPass = new JButton("CAMBIAR CONTRASEÑA");
        btnCambiarPass.setBackground(Color.BLACK);
        btnCambiarPass.setForeground(Color.WHITE);
        btnCambiarPass.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnCambiarPass.setFont(new Font("Bodoni Bd BT", Font.BOLD, 18));
        
        btnCambiarPass.setPreferredSize(new Dimension(250, 50));
        
        JPanel panelContenedorBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelContenedorBoton.setOpaque(false);
        panelContenedorBoton.add(btnCambiarPass);
        
        panel.add(crearLabel("CONTRASEÑA ACTUAL:"));
        panel.add(txtConfirmarPass);
        panel.add(crearLabel("NUEVA CONTRASEÑA:"));
        panel.add(txtnuevaPass);
        panel.add(new JLabel(""));
        
        panel.add(panelContenedorBoton);
        
        btnCambiarPass.addActionListener(e -> manejarCambioPass());
        
        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Bodoni Bd BT", Font.BOLD, 18));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setPreferredSize(new Dimension(170, 50));
        btnVolver.addActionListener(e -> vtnVolver());
        
        JPanel panelbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelbtn.setOpaque(false);
        panelbtn.add(btnVolver);
        cf.add(panelbtn, BorderLayout.SOUTH);

        cf.add(panel, BorderLayout.CENTER);
        this.setContentPane(cf);
    }
    
    private JLabel crearLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        return label;
    }
    
    private void manejarCambioPass(){
        String vieja = new String(txtConfirmarPass.getPassword());
        String nueva = new String(txtnuevaPass.getPassword());

        if(vieja.isEmpty() || nueva.isEmpty()) {
            DecoMensajes.mostrarMensaje(this, "Debe ingresar la contraseña actual y la nueva.", "XIANGQI");
            return;
        }

        if (sistema.cambiarPassword(playerActual.getUsername(), vieja, nueva)) {
            DecoMensajes.mostrarMensaje(this, "¡Contraseña actualizada!", "XIANGQI");
            vtnVolver();
        } 
    }
    
    private void vtnVolver(){
        MiPerfil mp = new MiPerfil(playerActual, sistema);
        mp.setVisible(true);
        this.dispose();
    }
}
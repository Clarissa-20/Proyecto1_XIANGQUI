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
public class EliminarCuenta extends JFrame {

    private Player playerActual; 
    private Sistema sistema;
    private JPasswordField txtConfirmarPassword;

    public EliminarCuenta(Player playerActual, Sistema sistema) {
        this.playerActual = playerActual;
        this.sistema = sistema;

        setTitle("Xiangqi - Eliminar Cuenta - " + playerActual.getUsername());
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        ClaseFondo cp = new ClaseFondo("/img/miPerfil.png");
        cp.setLayout(new BorderLayout());
        cp.setBorder(BorderFactory.createEmptyBorder(80, 150, 60, 150));

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 15));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 5), "ELIMINAR CUENTA",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Bodoni Bd BT", Font.BOLD, 30), new Color(255, 204, 51)));

        JLabel lblConfirmar = crearLabel("CONFIRMAR CONTRASEÑA:");
        lblConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblConfirmar);

        txtConfirmarPassword = new JPasswordField();
        txtConfirmarPassword.setBackground(Color.BLACK);
        txtConfirmarPassword.setForeground(Color.WHITE);
        txtConfirmarPassword.setCaretColor(Color.WHITE);
        txtConfirmarPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        txtConfirmarPassword.setPreferredSize(new Dimension(250, 40));
        
        JPanel panelCampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCampo.setOpaque(false);
        panelCampo.add(txtConfirmarPassword);
        panel.add(panelCampo);

        JButton btnEliminar = new JButton("ELIMINAR CUENTA");
        btnEliminar.setBackground(Color.BLACK);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Bodoni Bd BT", Font.BOLD, 18));
        btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new Dimension(220, 45));

        JPanel panelBotonEliminar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotonEliminar.setOpaque(false);
        panelBotonEliminar.add(btnEliminar);
        panel.add(panelBotonEliminar);

        btnEliminar.addActionListener(e -> manejarEliminarCuenta());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Bodoni Bd BT", Font.BOLD, 22));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(180, 45)); 
        btnVolver.addActionListener(e -> vtnVolver());

        JPanel panelbtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelbtn.setOpaque(false);
        panelbtn.add(btnVolver);
        
        cp.add(panelbtn, BorderLayout.SOUTH);
        cp.add(panel, BorderLayout.CENTER);
        this.setContentPane(cp);
    }

    private JLabel crearLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        return label;
    }
    
    private void manejarEliminarCuenta() {
        String confirmarPassword = new String(txtConfirmarPassword.getPassword());

        if (confirmarPassword.isEmpty()) {
            DecoMensajes.mostrarMensaje(this, "Por favor, ingrese su contraseña.", "XIANGQI");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Esta seguro que desea eliminar su cuenta?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar == JOptionPane.YES_OPTION) {
            boolean exito = sistema.eliminarCuenta(playerActual.getUsername(), confirmarPassword);

            if (exito) {
                DecoMensajes.mostrarMensaje(this, "Cuenta eliminada con exito.", "XIANGQI");
                MenuInicio mi = new MenuInicio(sistema);
                mi.setVisible(true);
                this.dispose();
            } else {
                DecoMensajes.mostrarMensaje(this, "Contraseña incorrecta. No se pudo eliminar la cuenta.", "XIANGQI");
                txtConfirmarPassword.setText("");
            }
        }
    }

    private void vtnVolver() {
        MiPerfil mp = new MiPerfil(playerActual, sistema);
        mp.setVisible(true);
        this.dispose();
    }
}
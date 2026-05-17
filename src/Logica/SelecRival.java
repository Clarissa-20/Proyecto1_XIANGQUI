/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class SelecRival extends JFrame {
    private JTextField nombreOponenteField;
    private Player jugadorPrincipal;
    private Sistema sistema;
    private JList<String> listaOponentes;

    public SelecRival(Player jugadorPrincipal, Sistema sistema) {
        this.jugadorPrincipal = jugadorPrincipal;
        this.sistema = sistema;

        setTitle("Xiangqi - Seleccionar Rival");
        setSize(600, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ClaseFondo cf = new ClaseFondo("/img/selecRival.png");
        cf.setLayout(new BorderLayout(10, 10));
        cf.setBorder(BorderFactory.createEmptyBorder(40, 90, 40, 90));

        JLabel titulo = new JLabel("SELECCIONA TU RIVAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Bodoni Bd BT", Font.BOLD, 30));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titulo.setForeground(new Color(255, 204, 51));
        cf.add(titulo, BorderLayout.NORTH);

        cf.add(getPanelCentral(), BorderLayout.CENTER);
        this.setContentPane(cf);
    }

    private JPanel getPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setOpaque(false);
        
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelInput.setOpaque(false);

        JLabel label = new JLabel("Tu Rival:");
        label.setFont(new Font("Bodoni Bd BT", Font.BOLD, 18));
        label.setForeground(Color.WHITE);

        nombreOponenteField = new JTextField(12);
        panelInput.add(label);
        panelInput.add(nombreOponenteField);

        String[] nombres = obtenerNombresDisponibles();
        listaOponentes = new JList<>(nombres);
        listaOponentes.setFont(new Font("Bodoni Bd BT", Font.BOLD, 20));

        JScrollPane scroll = new JScrollPane(listaOponentes);
        scroll.setBorder(BorderFactory.createTitledBorder("JUGADORES REGISTRADOS"));

        JButton btnAceptar = estiloBoton("Comenzar Partida");
        JButton btnVolver = estiloBoton("Volver al Menu");

        JPanel contenedorBotonesEstructura = new JPanel();
        contenedorBotonesEstructura.setLayout(new BoxLayout(contenedorBotonesEstructura, BoxLayout.Y_AXIS));
        contenedorBotonesEstructura.setOpaque(false);
        
        btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contenedorBotonesEstructura.add(btnAceptar);
        contenedorBotonesEstructura.add(Box.createVerticalStrut(12)); // Espaciado entre botones
        contenedorBotonesEstructura.add(btnVolver);

        JPanel panelSurBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSurBotones.setOpaque(false);
        panelSurBotones.add(contenedorBotonesEstructura);

        panelCentral.add(panelInput, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);
        panelCentral.add(panelSurBotones, BorderLayout.SOUTH); 

        listaOponentes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    nombreOponenteField.setText(listaOponentes.getSelectedValue());
                }
            }
        });

        btnAceptar.addActionListener(e -> validarYArrancar());
        
        btnVolver.addActionListener(e -> {
            new MenuPrincipal(jugadorPrincipal, sistema).setVisible(true);
            this.dispose();
        });

        return panelCentral;
    }

    private String[] obtenerNombresDisponibles() {
        Player[] todos = sistema.getListaPlayers();
        int cant = sistema.getContadorPlayers();

        java.util.ArrayList<String> lista = new java.util.ArrayList<>();

        for (int i = 0; i < cant; i++) {
            if (todos[i] != null && !todos[i].getUsername().equals(jugadorPrincipal.getUsername())) {
                lista.add(todos[i].getUsername());
            }
        }
        return lista.toArray(new String[0]);
    }

    private void validarYArrancar() {
        String oponente = nombreOponenteField.getText().trim();

        if (oponente.isEmpty()) {
            DecoMensajes.mostrarMensaje(this, "Debe ingresar o seleccionar un nombre.", "XIANGQI");
            return;
        }

        if (oponente.equals(jugadorPrincipal.getUsername())) {
            DecoMensajes.mostrarMensaje(this, "¡No puedes ser tu propio rival!", "XIANGQI");
            return;
        }

        Player encontrado = sistema.buscarPlayer(oponente);
        if (encontrado != null) {
            sistema.setPlayerActual(this.jugadorPrincipal);
            sistema.setRival(encontrado);

            Juego jg = new Juego(sistema);
            jg.setVisible(true);
            this.dispose();
        } else {
            DecoMensajes.mostrarMensaje(this, "Jugador no encontrado. Vuelve a intentarlo.", "XIANGQI");
        }
    }

    private JButton estiloBoton(String t) {
        JButton b = new JButton(t);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Bodoni Bd BT", Font.BOLD, 18));
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(220, 45));
        b.setMaximumSize(new Dimension(220, 45));
        b.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 5));
        
        return b;
    }
}

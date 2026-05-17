/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Juego extends JFrame {

    private Sistema sistema;
    private Player dueñoSesion;
    private Tablero logicaTablero;

    private JButton[][] botones;
    private JLabel lblTurnoJugador;
    private JLabel lblInfoPartida;
    private JPanel contenedorRojo;
    private JPanel contenedorNegro;
    private JPanel cementerioRojo;
    private JPanel cementerioNegro;
    private JButton btnRetirarse;

    private Equipo turnoActual = Equipo.ROJO;
    private int filaSel = -1, colSel = -1;
    private String ultimaPiezaMovida = "Pieza";

    public Juego(Sistema sistema) {
        this.sistema = sistema;
        this.dueñoSesion = sistema.getPlayerActual();
        this.logicaTablero = new Tablero();

        configurarVentana();
        inicializarComponentes();
        actualizarVisual();
    }

    private void configurarVentana() {
        setTitle("XIANGQI");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(25, 25, 25));
        setLayout(new BorderLayout(15, 15));
    }

    private void inicializarComponentes() {
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
        panelNorte.setOpaque(false);
        panelNorte.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        lblTurnoJugador = new JLabel("TURNO DE: " + sistema.getPlayerActual().getUsername().toUpperCase() + " (ROJO)", SwingConstants.CENTER);
        lblTurnoJugador.setFont(new Font("Arial", Font.BOLD, 22));
        lblTurnoJugador.setForeground(Color.RED);
        lblTurnoJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblInfoPartida = new JLabel("¡BIENVENIDOS AL XIANGQI! SELECCIONA UNA PIEZA PARA INICIAR LA BATALLA", SwingConstants.CENTER);
        lblInfoPartida.setFont(new Font("Arial", Font.BOLD, 15));
        lblInfoPartida.setForeground(Color.YELLOW);
        lblInfoPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblInfoPartida.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panelNorte.add(lblTurnoJugador);
        panelNorte.add(lblInfoPartida);
        add(panelNorte, BorderLayout.NORTH);

        String nombreRojo = sistema.getPlayerActual().getUsername().toUpperCase();
        String nombreNegro = sistema.getRival().getUsername().toUpperCase();

        contenedorRojo = new JPanel();
        contenedorRojo.setLayout(new BoxLayout(contenedorRojo, BoxLayout.Y_AXIS));
        contenedorRojo.setPreferredSize(new Dimension(240, 0)); 
        contenedorRojo.setOpaque(false);
        contenedorRojo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitRojo1 = new JLabel("PIEZAS CAPTURADAS POR:", SwingConstants.CENTER);
        lblTitRojo1.setFont(new Font("Arial", Font.BOLD, 11));
        lblTitRojo1.setForeground(Color.LIGHT_GRAY);
        lblTitRojo1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitRojo2 = new JLabel(nombreRojo, SwingConstants.CENTER);
        lblTitRojo2.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitRojo2.setForeground(Color.RED);
        lblTitRojo2.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitRojo2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        cementerioRojo = new JPanel(new GridLayout(0, 3, 6, 6));
        cementerioRojo.setBackground(new Color(245, 242, 225)); 
        cementerioRojo.setOpaque(true);
        cementerioRojo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        cementerioRojo.setPreferredSize(new Dimension(210, 420)); 
        cementerioRojo.setMaximumSize(new Dimension(210, 420)); 
        cementerioRojo.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenedorRojo.add(lblTitRojo1);
        contenedorRojo.add(lblTitRojo2);
        contenedorRojo.add(cementerioRojo);
        contenedorRojo.add(Box.createVerticalGlue()); 
        add(contenedorRojo, BorderLayout.WEST);

        contenedorNegro = new JPanel();
        contenedorNegro.setLayout(new BoxLayout(contenedorNegro, BoxLayout.Y_AXIS));
        contenedorNegro.setPreferredSize(new Dimension(240, 0)); 
        contenedorNegro.setOpaque(false);
        contenedorNegro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitNegro1 = new JLabel("PIEZAS CAPTURADAS POR:", SwingConstants.CENTER);
        lblTitNegro1.setFont(new Font("Arial", Font.BOLD, 11));
        lblTitNegro1.setForeground(Color.LIGHT_GRAY);
        lblTitNegro1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitNegro2 = new JLabel(nombreNegro, SwingConstants.CENTER);
        lblTitNegro2.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitNegro2.setForeground(Color.WHITE);
        lblTitNegro2.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitNegro2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        cementerioNegro = new JPanel(new GridLayout(0, 3, 6, 6));
        cementerioNegro.setBackground(new Color(245, 242, 225)); 
        cementerioNegro.setOpaque(true);
        cementerioNegro.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cementerioNegro.setPreferredSize(new Dimension(210, 420)); 
        cementerioNegro.setMaximumSize(new Dimension(210, 420)); 
        cementerioNegro.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenedorNegro.add(lblTitNegro1);
        contenedorNegro.add(lblTitNegro2);
        contenedorNegro.add(cementerioNegro);
        contenedorNegro.add(Box.createVerticalGlue()); 
        add(contenedorNegro, BorderLayout.EAST);

        JPanel panelTableroContenedor = new JPanel(new GridLayout(10, 9));
        panelTableroContenedor.setBorder(BorderFactory.createLineBorder(new Color(20, 60, 20), 3));
        botones = new JButton[10][9];

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 9; c++) {
                botones[f][c] = new JButton();
                botones[f][c].setFont(new Font("Arial", Font.BOLD, 13));
                botones[f][c].setFocusPainted(false);

                final int fila = f, col = c;
                botones[f][c].addActionListener(e -> gestionarClicTablero(fila, col));
                panelTableroContenedor.add(botones[f][c]);
            }
        }
        add(panelTableroContenedor, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSur.setOpaque(false);
        panelSur.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        btnRetirarse = new JButton("RETIRARSE");
        btnRetirarse.setFont(new Font("Arial", Font.BOLD, 14));
        btnRetirarse.setForeground(Color.WHITE);
        btnRetirarse.setBackground(Color.BLACK);
        btnRetirarse.setPreferredSize(new Dimension(180, 40));
        btnRetirarse.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 51), 3));
        btnRetirarse.addActionListener(e -> alRetirarse());

        panelSur.add(btnRetirarse);
        add(panelSur, BorderLayout.SOUTH);
    }
    
     private void alRetirarse() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas retirarte de la partida?",
                "Confirmar Retirada", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            Player p1 = sistema.getPlayerActual();
            Player p2 = sistema.getRival();

            Player perdedor = (turnoActual == Equipo.ROJO) ? p1 : p2;
            Player ganador = (turnoActual == Equipo.ROJO) ? p2 : p1;

            ganador.agregarPuntos(3);
            
            LogPartida nuevoLog;
            if (dueñoSesion.getUsername().equals(perdedor.getUsername())) {
                nuevoLog = new LogDetallado(ganador.getUsername(), "PERDISTE", "Retiro", "Ninguna");
            } else {
                nuevoLog = new LogDetallado(perdedor.getUsername(), "GANASTE", "Retiro", "Ninguna");
            }
            
            dueñoSesion.agregarLog(nuevoLog);
            
            String mensajeExacto = nuevoLog.formatearTexto();

            JOptionPane.showMessageDialog(this, 
                    "¡PARTIDA FINALIZADA!\n\n" + mensajeExacto.toUpperCase() + "\n\n"
                    + "¡" + ganador.getUsername().toUpperCase() + " HA GANADO 3 PUNTOS!", 
                    "Fin de Partida", JOptionPane.INFORMATION_MESSAGE);

            new MenuPrincipal(dueñoSesion, sistema).setVisible(true);
            this.dispose();
        }
    }

    private void gestionarClicTablero(int f, int c) {
        String nickRojo = sistema.getPlayerActual().getUsername().toUpperCase();
        String nickNegro = sistema.getRival().getUsername().toUpperCase();

        try {
            if (filaSel == -1) {
                Pieza p = logicaTablero.getTablero()[f][c];
                if (p != null && p.getEquipo() == turnoActual) {
                    filaSel = f;
                    colSel = c;

                    actualizarVisual();
                    botones[f][c].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                    iluminarMovimientosDisponibles(f, c);

                } else {
                    lblInfoPartida.setText("SELECCIONA UNA PIEZA VALIDA DE TU PROPIO EQUIPO");
                }
            } else {
                if (f == filaSel && c == colSel) {
                    filaSel = -1;
                    colSel = -1;
                    actualizarVisual();
                    return;
                }

                Pieza atacada = logicaTablero.getTablero()[f][c];
                Pieza atacante = logicaTablero.getTablero()[filaSel][colSel];
                
                this.ultimaPiezaMovida = atacante.getTipo();
                logicaTablero.moverPieza(filaSel, colSel, f, c);

                if (atacada != null) {
                    registrarEnCementerio(atacada);
                    if (turnoActual == Equipo.ROJO) {
                        lblInfoPartida.setText("EL " + atacante.getTipo() + " DE " + nickRojo + " CAPTURO AL " + atacada.getTipo() + " DE " + nickNegro);
                    } else {
                        lblInfoPartida.setText("EL " + atacante.getTipo() + " DE " + nickNegro + " CAPTURO AL " + atacada.getTipo() + " DE " + nickRojo);
                    }
                } else {
                    if (turnoActual == Equipo.ROJO) {
                        lblInfoPartida.setText(nickRojo + " MOVIO SU " + atacante.getTipo());
                    } else {
                        lblInfoPartida.setText(nickNegro + " MOVIO SU " + atacante.getTipo());
                    }
                }

                if (verificarCapturaTotalPiezas()) {
                    return;
                }

                if (turnoActual == Equipo.ROJO) {
                    turnoActual = Equipo.NEGRO;
                    lblTurnoJugador.setText("TURNO DE: " + nickNegro + " (NEGRO)");
                    lblTurnoJugador.setForeground(Color.WHITE);
                } else {
                    turnoActual = Equipo.ROJO;
                    lblTurnoJugador.setText("TURNO DE: " + nickRojo + " (ROJO)");
                    lblTurnoJugador.setForeground(Color.RED);
                }

                filaSel = -1;
                colSel = -1;
                actualizarVisual();
            }
        } catch (MovimientoIlegalException ex) {
            lblInfoPartida.setText("MOVIMIENTO INVALIDO: " + ex.getMessage().toUpperCase());
            filaSel = -1;
            colSel = -1;
            actualizarVisual();
        }
    }

    private void iluminarMovimientosDisponibles(int origF, int origC) {
        Pieza p = logicaTablero.getTablero()[origF][origC];
        if (p == null) {
            return;
        }

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 9; c++) {
                if (f == origF && c == origC) {
                    continue;
                }

                if (logicaTablero.esMovimientoValido(origF, origC, f, c)) {
                    botones[f][c].setBackground(new Color(255, 235, 150));
                }
            }
        }
    }
    
     private boolean verificarCapturaTotalPiezas() {
        Pieza[][] t = logicaTablero.getTablero();
        int piezasRojas = 0, piezasNegras = 0;

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 9; c++) {
                if (t[f][c] != null) {
                    if (t[f][c].getEquipo() == Equipo.ROJO) {
                        piezasRojas++;
                    } else {
                        piezasNegras++;
                    }
                }
            }
        }

        if (piezasRojas == 0 || piezasNegras == 0) {
            Player ganador = (piezasRojas > 0) ? sistema.getPlayerActual() : sistema.getRival();
            Player perdedor = (piezasRojas > 0) ? sistema.getRival() : sistema.getPlayerActual();

            ganador.agregarPuntos(3);
            
            LogPartida nuevoLog;
            if (dueñoSesion.getUsername().equals(ganador.getUsername())) {
                nuevoLog = new LogDetallado(perdedor.getUsername(), "GANASTE", "Captura", ultimaPiezaMovida);
            } else {
                nuevoLog = new LogDetallado(ganador.getUsername(), "PERDISTE", "Captura", ultimaPiezaMovida);
            }
            
            dueñoSesion.agregarLog(nuevoLog);

            String mensajeExacto = nuevoLog.formatearTexto();

            JOptionPane.showMessageDialog(this,
                    "¡PARTIDA FINALIZADA!\n\n" + mensajeExacto.toUpperCase() + "\n\n"
                    + "¡" + ganador.getUsername().toUpperCase() + " HA GANADO 3 PUNTOS!",
                    "Victoria", JOptionPane.INFORMATION_MESSAGE);

            new MenuPrincipal(dueñoSesion, sistema).setVisible(true);
            this.dispose();
            return true;
        }
        return false;
    }

    private void registrarEnCementerio(Pieza p) {
        JLabel miniImagenLabel = new JLabel();
        miniImagenLabel.setHorizontalAlignment(JLabel.CENTER);

        String equipoStr = (p.getEquipo() == Equipo.ROJO) ? "Rojo" : "Negro";
        String nombreArchivo = p.getTipo() + equipoStr + ".png";
        String ruta = "/imgPiezas/" + nombreArchivo;

        try {
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(ruta));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH);
            miniImagenLabel.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception ex) {
            miniImagenLabel.setText("[" + p.getTipo().substring(0, 3).toUpperCase() + "]");
            miniImagenLabel.setFont(new Font("Arial", Font.BOLD, 11));
            miniImagenLabel.setForeground(p.getEquipo() == Equipo.ROJO ? Color.RED : Color.BLACK);
        }

        if (p.getEquipo() == Equipo.ROJO) {
            cementerioNegro.add(miniImagenLabel);
        } else {
            cementerioRojo.add(miniImagenLabel);
        }

        cementerioRojo.revalidate();
        cementerioRojo.repaint();
        cementerioNegro.revalidate();
        cementerioNegro.repaint();
    }

    private void actualizarVisual() {
        Pieza[][] t = logicaTablero.getTablero();
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 9; c++) {

                if ((f + c) % 2 == 0) {
                    botones[f][c].setBackground(new Color(245, 245, 220));
                } else {
                    botones[f][c].setBackground(new Color(34, 100, 34));
                }

                int top = 0, left = 0, bottom = 0, right = 0;
                Color colorBordeActual = Color.BLACK;

                if (f == 4) {
                    bottom = 4;
                    colorBordeActual = new Color(30, 144, 255);
                }
                if (f == 5) {
                    top = 4;
                    colorBordeActual = new Color(30, 144, 255);
                }

                if (f >= 0 && f <= 2 && c >= 3 && c <= 5) {
                    if (f == 0) top = 3;
                    if (f == 2) bottom = 3;
                    if (c == 3) left = 3;
                    if (c == 5) right = 3;
                }

                if (f >= 7 && f <= 9 && c >= 3 && c <= 5) {
                    if (f == 7) top = 3;
                    if (f == 9) bottom = 3;
                    if (c == 3) left = 3;
                    if (c == 5) right = 3;
                }

                if (top > 0 || left > 0 || bottom > 0 || right > 0) {
                    botones[f][c].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, colorBordeActual));
                } else {
                    botones[f][c].setBorder(null);
                }

                if (t[f][c] != null) {
                    botones[f][c].setText(""); 

                    String equipoStr = (t[f][c].getEquipo() == Equipo.ROJO) ? "Rojo" : "Negro";
                    String nombreArchivo = t[f][c].getTipo() + equipoStr + ".png";

                    try {
                        String ruta = "/imgPiezas/" + nombreArchivo;
                        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(ruta));

                        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                        botones[f][c].setIcon(new ImageIcon(imagenEscalada));

                    } catch (Exception ex) {
                        botones[f][c].setIcon(null);
                        botones[f][c].setText(t[f][c].getTipo());
                        botones[f][c].setForeground(t[f][c].getEquipo() == Equipo.ROJO ? Color.RED : Color.BLUE);
                    }
                } else {
                    botones[f][c].setText("");
                    botones[f][c].setIcon(null); 
                }
            }
        }
    }
}
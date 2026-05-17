/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class Elefante extends Pieza {

    public Elefante(Equipo equipo, int fila, int columna) {
        super(equipo, fila, columna, "ELEFANTE");
    }

    @Override
    public boolean esMovimientoValido(int df, int dc, Pieza[][] tablero) {
        int diffF = Math.abs(df - fila);
        int diffC = Math.abs(dc - columna);

        if (diffF != 2 || diffC != 2) {
            return false;
        }

        // Regla del Río
        if (equipo == Equipo.NEGRO && df > 4) {
            return false; 
        }
        if (equipo == Equipo.ROJO && df < 5) {
            return false;  
        }
        
        int medioF = (fila + df) / 2;
        int medioC = (columna + dc) / 2;
        if (tablero[medioF][medioC] != null) {
            return false;
        }

        if (tablero[df][dc] != null && tablero[df][dc].getEquipo() == this.equipo) {
            return false;
        }
        return true;
    }
}

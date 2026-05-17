/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class Soldado extends Pieza {

    public Soldado(Equipo equipo, int fila, int columna) {
        super(equipo, fila, columna, "SOLDADO");
    }

    @Override
    public boolean esMovimientoValido(int df, int dc, Pieza[][] tablero) {
        int diffF = (equipo == Equipo.NEGRO) ? df - fila : fila - df;
        int diffC = Math.abs(dc - columna);

        if ((equipo == Equipo.NEGRO && df < fila) || (equipo == Equipo.ROJO && df > fila)) {
            return false;
        }

        boolean cruzadoRio = (equipo == Equipo.NEGRO) ? fila > 4 : fila < 5;

        if (!cruzadoRio) {
            if (diffF != 1 || diffC != 0) {
                return false;
            }
        } else {
            if (!((diffF == 1 && diffC == 0) || (diffF == 0 && diffC == 1))) {
                return false;
            }
        }

        if (tablero[df][dc] != null && tablero[df][dc].getEquipo() == this.equipo) {
            return false;
        }
        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class Cañon extends Pieza {

    public Cañon(Equipo equipo, int fila, int columna) {
        super(equipo, fila, columna, "CAÑON");
    }

    @Override
    public boolean esMovimientoValido(int df, int dc, Pieza[][] tablero) {
        if (fila != df && columna != dc) {
            return false;
        }

        if (tablero[df][dc] != null && tablero[df][dc].getEquipo() == this.equipo) {
            return false;
        }

        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class Oficial extends Pieza {

    public Oficial(Equipo equipo, int fila, int columna) {
        super(equipo, fila, columna, "OFICIAL");
    }

    @Override
    public boolean esMovimientoValido(int df, int dc, Pieza[][] tablero) {
        int diffF = Math.abs(df - fila);
        int diffC = Math.abs(dc - columna);

        if (diffF != 1 || diffC != 1) {
            return false;
        }

        if (dc < 3 || dc > 5) {
            return false;
        }
        if (equipo == Equipo.NEGRO ? (df < 0 || df > 2) : (df < 7 || df > 9)) {
            return false;
        }

        if (tablero[df][dc] != null && tablero[df][dc].getEquipo() == this.equipo) {
            return false;
        }

        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class General extends Pieza {

    public General(Equipo equipo, int fila, int columna) {
        super(equipo, fila, columna, "GENERAL");
    }

    @Override
    public boolean esMovimientoValido(int df, int dc, Pieza[][] tablero) {
        int diffF = Math.abs(df - fila);
        int diffC = Math.abs(dc - columna);

        if ((diffF + diffC) != 1) {
            return false;
        }

        if (dc < 3 || dc > 5) {
            return false;
        }

        if (equipo == Equipo.NEGRO) {
            if (df < 0 || df > 2) {
                return false;
            }
        } else {
            if (df < 7 || df > 9) {
                return false; 
            }
        }

        if (tablero[df][dc] != null && tablero[df][dc].getEquipo() == this.equipo) {
            return false;
        }

        return true;
    }
}

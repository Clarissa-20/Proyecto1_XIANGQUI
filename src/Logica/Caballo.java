/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class Caballo extends Pieza {

    public Caballo(Equipo equipo, int fila, int columna) {
        super(equipo, fila, columna, "CABALLO");
    }

    @Override
    public boolean esMovimientoValido(int df, int dc, Pieza[][] tablero) {
        int diffF = Math.abs(df - fila);
        int diffC = Math.abs(dc - columna);

        if (!((diffF == 2 && diffC == 1) || (diffF == 1 && diffC == 2))) {
            return false;
        }

        if (diffF == 2) {
            int pasoF = (df > fila) ? fila + 1 : fila - 1;
            if (tablero[pasoF][columna] != null) {
                return false; 
            }
        } else {
            int pasoC = (dc > columna) ? columna + 1 : columna - 1;
            if (tablero[fila][pasoC] != null) {
                return false; 
            }
        }

        if (tablero[df][dc] != null && tablero[df][dc].getEquipo() == this.equipo) {
            return false;
        }
        return true;
    }
}

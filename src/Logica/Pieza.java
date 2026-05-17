/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public abstract class Pieza {

    protected Equipo equipo;
    protected int fila, columna;
    protected String tipo;

    public Pieza(Equipo equipo, int fila, int columna, String tipo) {
        this.equipo = equipo;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    public abstract boolean esMovimientoValido(int destinoF, int destinoC, Pieza[][] tablero);

    public final Equipo getEquipo() {
        return equipo;
    }

    public final String getTipo() {
        return tipo;
    }

    public void setPosicion(int f, int c) {
        this.fila = f;
        this.columna = c;
    }
}

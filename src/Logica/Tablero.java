/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public final class Tablero {
    private Pieza[][] tablero; 

    public Tablero() {
        this.tablero = new Pieza[10][9];
        inicializarTablero();
    }

    private void inicializarTablero() {
        tablero[0][0] = new Carro(Equipo.NEGRO, 0, 0);
        tablero[0][1] = new Caballo(Equipo.NEGRO, 0, 1);
        tablero[0][2] = new Elefante(Equipo.NEGRO, 0, 2);
        tablero[0][3] = new Oficial(Equipo.NEGRO, 0, 3); 
        tablero[0][4] = new General(Equipo.NEGRO, 0, 4);
        tablero[0][5] = new Oficial(Equipo.NEGRO, 0, 5); 
        tablero[0][6] = new Elefante(Equipo.NEGRO, 0, 6);
        tablero[0][7] = new Caballo(Equipo.NEGRO, 0, 7);
        tablero[0][8] = new Carro(Equipo.NEGRO, 0, 8);
        tablero[2][1] = new Cañon(Equipo.NEGRO, 2, 1);
        tablero[2][7] = new Cañon(Equipo.NEGRO, 2, 7);
        tablero[3][0] = new Soldado(Equipo.NEGRO, 3, 0);
        tablero[3][2] = new Soldado(Equipo.NEGRO, 3, 2);
        tablero[3][4] = new Soldado(Equipo.NEGRO, 3, 4);
        tablero[3][6] = new Soldado(Equipo.NEGRO, 3, 6);
        tablero[3][8] = new Soldado(Equipo.NEGRO, 3, 8);

        tablero[9][0] = new Carro(Equipo.ROJO, 9, 0);
        tablero[9][1] = new Caballo(Equipo.ROJO, 9, 1);
        tablero[9][2] = new Elefante(Equipo.ROJO, 9, 2);
        tablero[9][3] = new Oficial(Equipo.ROJO, 9, 3);
        tablero[9][4] = new General(Equipo.ROJO, 9, 4);
        tablero[9][5] = new Oficial(Equipo.ROJO, 9, 5);
        tablero[9][6] = new Elefante(Equipo.ROJO, 9, 6);
        tablero[9][7] = new Caballo(Equipo.ROJO, 9, 7);
        tablero[9][8] = new Carro(Equipo.ROJO, 9, 8);
        tablero[7][1] = new Cañon(Equipo.ROJO, 7, 1);
        tablero[7][7] = new Cañon(Equipo.ROJO, 7, 7);
        tablero[6][0] = new Soldado(Equipo.ROJO, 6, 0);
        tablero[6][2] = new Soldado(Equipo.ROJO, 6, 2);
        tablero[6][4] = new Soldado(Equipo.ROJO, 6, 4);
        tablero[6][6] = new Soldado(Equipo.ROJO, 6, 6);
        tablero[6][8] = new Soldado(Equipo.ROJO, 6, 8);
    }

    public int contarPiezasRecursivo(int f, int c, int df, int dc) {
        if (f == df && c == dc) return 0;
        int sigF = f, sigC = c;
        if (f < df) sigF++; else if (f > df) sigF--;
        if (c < dc) sigC++; else if (c > dc) sigC--;
        int hayPieza = 0;
        if ((sigF != df || sigC != dc) && tablero[sigF][sigC] != null) {
            hayPieza = 1;
        }
        return hayPieza + contarPiezasRecursivo(sigF, sigC, df, dc);
    }

    public int[] buscarGeneralRecursivo(Equipo e, int f, int c) {
        if (f >= 10) return null;
        if (tablero[f][c] instanceof General && tablero[f][c].getEquipo() == e) {
            return new int[]{f, c};
        }
        int sigF = (c == 8) ? f + 1 : f;
        int sigC = (c == 8) ? 0 : c + 1;
        return buscarGeneralRecursivo(e, sigF, sigC);
    }

    public void moverPieza(int f, int c, int df, int dc) throws MovimientoIlegalException {
        Pieza p = tablero[f][c];
        if (p == null) throw new MovimientoIlegalException("No hay pieza en la seleccion");

        if (!p.esMovimientoValido(df, dc, tablero)) {
            throw new MovimientoIlegalException("Movimiento no permitido para esta pieza");
        }

        if (p instanceof Carro || p instanceof Cañon) {
            int piezasEnMedio = contarPiezasRecursivo(f, c, df, dc);
            if (p instanceof Carro && piezasEnMedio > 0) 
                throw new MovimientoIlegalException("El Carro no puede saltar piezas");
            if (p instanceof Cañon) {
                boolean esCaptura = tablero[df][dc] != null;
                if (esCaptura && piezasEnMedio != 1) 
                    throw new MovimientoIlegalException("El Cañón necesita 1 pieza intermedia para capturar");
                if (!esCaptura && piezasEnMedio != 0) 
                    throw new MovimientoIlegalException("El Cañón no puede saltar si no captura");
            }
        }

        Pieza piezaDestinoOriginal = tablero[df][dc];
        tablero[df][dc] = p;
        tablero[f][c] = null;

        int[] posNegro = buscarGeneralRecursivo(Equipo.NEGRO, 0, 0);
        int[] posRojo = buscarGeneralRecursivo(Equipo.ROJO, 0, 0);

        if (posNegro != null && posRojo != null && posNegro[1] == posRojo[1]) {
            if (contarPiezasRecursivo(posNegro[0], posNegro[1], posRojo[0], posRojo[1]) == 0) {
                tablero[f][c] = p;
                tablero[df][dc] = piezaDestinoOriginal;
                throw new MovimientoIlegalException("¡Los Generales no pueden verse directamente!");
            }
        }

        p.setPosicion(df, dc);
    }

    public boolean esMovimientoValido(int f, int c, int df, int dc) {
        Pieza p = tablero[f][c];
        if (p == null) return false;

        if (!p.esMovimientoValido(df, dc, tablero)) {
            return false;
        }

        if (p instanceof Carro || p instanceof Cañon) {
            int piezasEnMedio = contarPiezasRecursivo(f, c, df, dc);
            if (p instanceof Carro && piezasEnMedio > 0) return false;
            if (p instanceof Cañon) {
                boolean esCaptura = tablero[df][dc] != null;
                if (esCaptura && piezasEnMedio != 1) return false;
                if (!esCaptura && piezasEnMedio != 0) return false;
            }
        }

        Pieza piezaDestinoOriginal = tablero[df][dc];
        tablero[df][dc] = p;
        tablero[f][c] = null;

        boolean generalesSeVen = false;
        int[] posNegro = buscarGeneralRecursivo(Equipo.NEGRO, 0, 0);
        int[] posRojo = buscarGeneralRecursivo(Equipo.ROJO, 0, 0);

        if (posNegro != null && posRojo != null && posNegro[1] == posRojo[1]) {
            if (contarPiezasRecursivo(posNegro[0], posNegro[1], posRojo[0], posRojo[1]) == 0) {
                generalesSeVen = true;
            }
        }

        tablero[f][c] = p;
        tablero[df][dc] = piezaDestinoOriginal;

        if (generalesSeVen) return false;

        return true;
    }

    public Pieza[][] getTablero() { return tablero; }
}

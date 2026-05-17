/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public class LogPartida {
      protected String oponente;
    protected String resultado; 

    public LogPartida(String oponente, String resultado) {
        this.oponente = oponente;
        this.resultado = resultado;
    }

    public String formatearTexto() {
        return resultado + " contra " + oponente;
    }
}

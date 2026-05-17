/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public class LogDetallado extends LogPartida{
    private String motivo; 
    private String piezaFinal;

    public LogDetallado(String oponente, String resultado, String motivo, String piezaFinal) {
        super(oponente, resultado); 
        this.motivo = motivo;
        this.piezaFinal = piezaFinal;
    }

    @Override
    public String formatearTexto() {
        if (resultado.equals("PERDISTE") && motivo.equalsIgnoreCase("Retiro")) {
            return "Te retiraste de la partida. Ganador: " + oponente;
        } 
        
        if (resultado.equals("GANASTE") && motivo.equalsIgnoreCase("Retiro")) {
            return "Ganaste la partida por retirada de " + oponente;
        }
        
        if (motivo.equalsIgnoreCase("Captura") && resultado.equals("GANASTE")) {
            return "Ganaste por captura total de piezas contra " + oponente + " usando la pieza: " + piezaFinal;
        }

        if (motivo.equalsIgnoreCase("Captura") && resultado.equals("PERDISTE")) {
            return oponente + " te gano por captura total de piezas usando la pieza: " + piezaFinal;
        }

        return super.formatearTexto() + " (Motivo: " + motivo + ")";
    }
}

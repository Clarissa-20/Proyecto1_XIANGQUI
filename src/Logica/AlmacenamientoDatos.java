/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logica;

/**
 *
 * @author HP
 */
public interface AlmacenamientoDatos {
    
    boolean crearPlayer(String user, String pass);
    Player logIn(String user, String pass);
    Player buscarPlayer(String user);
    boolean cambiarPassword(String userActual, String passVieja, String passNueva);
    boolean eliminarCuenta(String username, String password);
    void logout();
    
    Player getPlayerActual();
    void setPlayerActual(Player p);
    Player getRival();
    void setRival(Player rival);
    Player[] getListaPlayers();
    int getContadorPlayers();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author HP
 */
public class Sistema {

    private Player[] listaPlayers;
    private int contadorPlayers;
    private Player playerActual;
    
    private Player rival;

    public Sistema() {
        this.listaPlayers = new Player[100];
        this.contadorPlayers = 0;
        this.playerActual = null;
        
    }

    public boolean crearPlayer(String user, String pass) {
        if (buscarPlayer(user) == null && contadorPlayers < listaPlayers.length) {
            listaPlayers[contadorPlayers] = new Player(user, pass);
            contadorPlayers++;
            return true;
        }
        return false;
    }

    public Player logIn(String user, String pass) {
        Player p = buscarPlayer(user);
        if (p != null && p.getPassword().equals(pass)) {
            if (p.isActivo()) { 
                this.playerActual = p;
                return p;
            }
        }
        return null;
    }

    public Player buscarPlayer(String user) {
        for (int i = 0; i < contadorPlayers; i++) {
            if (listaPlayers[i].getUsername().equals(user)) {
                return listaPlayers[i];
            }
        }
        return null;
    }

    public boolean cambiarPassword(String userActual, String passVieja, String passNueva) {
        Player p = buscarPlayer(userActual);
        if (p != null && p.getPassword().equals(passVieja)) {
            p.setPassword(passNueva);
            return true;
        }
        return false;
    }

    public boolean eliminarCuenta(String username, String password) {
        Player p = buscarPlayer(username);
        if (p != null && p.getPassword().equals(password)) {
            p.setActivo(false); 
            return true;
        }
        return false;
    }

    public Player getPlayerActual() {
        return playerActual;
    }

    public void setPlayerActual(Player p) {
        this.playerActual = p;
    }

    public Player[] getListaPlayers() {
        return listaPlayers; 
    }

    public int getContadorPlayers() {
        return contadorPlayers;
    }

    public Player getRival() {
        return rival;
    }

    public void setRival(Player rival) {
        this.rival = rival;
    }
    
    public void logout() {
        this.playerActual = null;
    }
}
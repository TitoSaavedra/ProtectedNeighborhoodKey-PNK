/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dto;

/**
 *
 * @author TitoS
 */
public class Barrera {
    private int estadoBarrera;

    public Barrera() {
    }

    public Barrera(int estadoBarrera) {
        this.estadoBarrera = estadoBarrera;
    }

    public int getEstadoBarrera() {
        return estadoBarrera;
    }

    public void setEstadoBarrera(int estadoBarrera) {
        this.estadoBarrera = estadoBarrera;
    }

    @Override
    public String toString() {
        return "Barrera{" + "estadoBarrera=" + estadoBarrera + '}';
    }
    
}

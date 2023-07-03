/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author luciano
 */
public class Aeropuerto implements Serializable{
    private String nombre;
    private int codigo;
    
    public Aeropuerto(){}
    
    public Aeropuerto(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    public int getCodigo()
    {
        return this.codigo;
    }
}

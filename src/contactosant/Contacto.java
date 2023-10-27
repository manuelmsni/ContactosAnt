/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vespertino
 */
@XmlRootElement
public class Contacto implements Serializable{
    
    String nombre;
    int telefono;
    
    public static final long serialVersionUID = 42L;
    
    public Contacto(String nombre, int telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public Contacto(){
    }
    
    @XmlElement
    public String getNombre(){
        return nombre;
    }
    
    @XmlElement(name = "telefono")
    public int getTelefono(){
        return telefono;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return telefono == contacto.telefono;
    }
    
    public int hashCode() {
        return Objects.hash(telefono);
    }
    
    public String toString() {
        return "Contacto:{" +
                "nombre:'" + nombre + '\'' +
                ", telefono:" + telefono +
                '}';
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
}
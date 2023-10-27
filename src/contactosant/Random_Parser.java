/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

/**
 *
 * @author Vespertino
 */
public class Random_Parser implements Persistencia{
    
    private String ruta;
    
    public Random_Parser(String ruta){
        this.ruta = ruta;
    }
    
    public Contacto[] recuperaContactos() {
        return null;
    }
    
    public boolean guardaContactos(Contacto[] contactos) {
        return false;
    }
}

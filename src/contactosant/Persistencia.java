/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

/**
 *
 * @author Vespertino
 */
public interface Persistencia {

    public Contacto[] recuperaContactos();
    
    public boolean guardaContactos(Contacto[] contactos);
    
}

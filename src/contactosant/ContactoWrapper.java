/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vespertino
 */
    @XmlRootElement(name = "Contactos")
    public class ContactoWrapper {
        
        private Contacto[] contactos;

        @XmlElement(name = "Contacto")
        public Contacto[] getContactos() {
            return contactos;
        }

        public void setContactos(Contacto[] contactos) {
            this.contactos = contactos;
        }
    }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package contactosant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import javax.xml.bind.annotation.XmlElementWrapper;


/**
 *
 * @author manuelmsni
 */
public class XML_JAXB_Parser implements Persistencia{
        private String ruta;

    public XML_JAXB_Parser(String ruta) {
        this.ruta = ruta;
    }
    
    public Contacto[] recuperaContactos() {
        if (ruta == null || ruta.isBlank()) return null;

        try {
            JAXBContext context = JAXBContext.newInstance(ContactoWrapper.class);

            // deserializar desde el XML
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File archivoXML = new File(ruta);
            ContactoWrapper wrapper = (ContactoWrapper) unmarshaller.unmarshal(archivoXML);

            return wrapper.getContactos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean guardaContactos(Contacto[] contactos) {
        if (contactos == null || contactos.length == 0) return false;

        try {
            JAXBContext context = JAXBContext.newInstance(ContactoWrapper.class);

            // Preparar Marshaller para serializar a XML posteriormente
            Marshaller marshaller = context.createMarshaller();

            // Empaquetar los contactos en un wrapper
            ContactoWrapper wrapper = new ContactoWrapper();
            wrapper.setContactos(contactos);

            // Serializar a un archivo XML
            File archivoXML = new File(ruta);
            marshaller.marshal(wrapper, archivoXML);

            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }
}

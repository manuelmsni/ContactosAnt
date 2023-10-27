/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package contactosant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vespertino
 */
public class Modelo { // Datos y lógica de negocio
    
    private Agenda agenda;
    
    private Persistencia persistencia;
    
    private Modelo() {
        agenda = new Agenda();
    }
    
    public static Modelo newModeloDOM(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new XML_DOM_Parser(ruta));
        return m;
    }
    
    public static Modelo newModeloSAX(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new XML_SAX_Parser(ruta));
        return m;
    }
    
    public static Modelo newModeloJAXB(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new XML_JAXB_Parser(ruta));
        return m;
    }
    
    public static Modelo newModeloObjetos(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new Object_Parser(ruta));
        return m;
    }
    
    public static Modelo newModeloTXT(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new TXT_Parser(ruta));
        return m;
    }
    
    private void setPersistencia(Persistencia p){
        persistencia = p;
    }
    
    public boolean cagaContactosDesde(boolean sustituir){
        
        //Transformo los datos de disco en un Array de Contactos
        Contacto[] contactos = persistencia.recuperaContactos();
        if(contactos == null) return false;
        
        if(sustituir) agenda.reseteaAgenda();
        
        for(Contacto c: contactos){
            agenda.addContacto(c);
        }
        
        return true;
    }
    
    public boolean guardaContactos(){
        int size = agenda.getSize();
        if(size == 0){
            System.out.println("La lista de contactos está vacía");
            return false;
        }
        persistencia.guardaContactos(agenda.getContactos());
        return true;
    }
    
    public boolean registraContacto(Contacto c){
        return agenda.addContacto(c);
    }
    
    public boolean modificaContacto(int telefono, String nuevoNombre, int nuevoTelefono){
        Contacto viejo = agenda.getContactoPorTelefono(telefono);
        if(viejo == null) return false;
        return agenda.modificaContacto(viejo, nuevoNombre, nuevoTelefono);
    }
    
    public void listaContactos(){
        System.out.print(agenda.toString());
    }
    
    public boolean borraContacto(int telefono){
        Contacto c = agenda.getContactoPorTelefono(telefono);
        if(c == null) return false;
        return agenda.borraContacto(c);
    }
    
        public boolean guardaContactos(int opcion){
        Propiedades p = Propiedades.getPropiedades();
        int size = agenda.getSize();
        if(size == 0){
            System.out.println("La lista de contactos está vacía");
            return false;
        }
        Persistencia guardar;
        switch(opcion){
            case 1:
                guardar = new XML_DOM_Parser(p.get("RutaGuardadoXML"));
                p.add("Persistencia", "DOM");
                break;
            case 2:
                guardar = new XML_SAX_Parser(p.get("RutaGuardadoXML"));
                p.add("Persistencia", "SAX");
                break;
            case 3:
                guardar = new XML_JAXB_Parser(p.get("RutaGuardadoXML"));
                p.add("Persistencia", "JAXB");
                break;
            case 4:
                guardar = new TXT_Parser(p.get("RutaGuardadoTXT"));
                p.add("Persistencia", "TXT");
                break;
            case 5:
                guardar = new Object_Parser(p.get("RutaGuardadoObjetos"));
                p.add("Persistencia", "Obj");
                break;
            default:
                guardar = new XML_DOM_Parser(p.get("RutaGuardadoXML"));
                p.add("Persistencia", "DOM");
                break;
        }
        p.save();
        guardar.guardaContactos(agenda.getContactos());
        return true;
    }
    
}

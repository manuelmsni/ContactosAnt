/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Manuel Martín Santamaría
 */
public class Main {
    
    static final String PROPPATH = "configuracion.conf";
    
    public static void main(String[] args){
        
        Propiedades prop = Propiedades.getPropiedades();

        boolean loaded = prop.setPath(PROPPATH);
        
        if(loaded){

            String persistencia = prop.get("Persistencia").replaceAll("/", System.getProperty("file.separator")).replaceAll("\\\\", System.getProperty("file.separator"));
            String rutaXML = prop.get("RutaGuardadoXML").replaceAll("/", System.getProperty("file.separator")).replaceAll("\\\\", System.getProperty("file.separator"));
            String rutaTXT = prop.get("RutaGuardadoTXT").replaceAll("/", System.getProperty("file.separator")).replaceAll("\\\\", System.getProperty("file.separator"));
            String rutaBIN = prop.get("RutaGuardadoObjetos").replaceAll("/", System.getProperty("file.separator")).replaceAll("\\\\", System.getProperty("file.separator"));

            Modelo m = null;

            switch(persistencia){
                case "DOM":
                    m = Modelo.newModeloDOM(rutaXML);
                    break;
                case "SAX":
                    m = Modelo.newModeloSAX(rutaXML);
                    break;
                case "JAXB":
                    m = Modelo.newModeloJAXB(rutaXML);
                    break;
                case "Obj":
                    m = Modelo.newModeloObjetos(rutaBIN);
                    break;
                case "TXT":
                    m = Modelo.newModeloTXT(rutaTXT);
                    break;
                default:
                    m = Modelo.newModeloDOM(rutaXML);
                    break;
            }

            Controlador c = new Controlador(m, new Vista());

            /*
            try {
                OutputStream archivoSalida = new FileOutputStream(PROPPATH);
                propiedades.store(archivoSalida, "ConfiguraciÃ³n del programa");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            */
            
        } else {
            System.out.println("No se ha localizado la configuraciÃ³n del programa.");
        }
        System.exit(0);
    }
}
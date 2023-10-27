/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

import static contactosant.Main.PROPPATH;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Vespertino
 */
public class Propiedades {
    
    private static Propiedades propiedades;
    private String path;
    private static Properties properties;
    
    private Propiedades(){
        properties = new Properties();
    }
    
    public boolean setPath(String path){
        boolean loaded = false;
        this.path = path;
        try (InputStream archivoEntrada = new FileInputStream(path)) {
            properties.load(archivoEntrada);
            loaded = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return loaded;
    }
    
    public static Propiedades getPropiedades(){
        if(propiedades == null){
            propiedades = new Propiedades();
            return propiedades;
        }
        return propiedades;
    }
    
    public Properties getPropierties(){
        return properties;
    }
    
    public void add(String propiedad, String valor){
        properties.setProperty(propiedad, valor);
    }
    
    public String get(String propiedad){
        return properties.getProperty(propiedad);
    }
    
    public boolean save(){
        OutputStream archivoSalida = null;
        try {
            archivoSalida = new FileOutputStream(path);
            properties.store(archivoSalida, "Configuración del programa");
            archivoSalida.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

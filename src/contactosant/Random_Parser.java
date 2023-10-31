/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactosant;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Vespertino
 */
public class Random_Parser implements Persistencia{
    
    private String ruta;
    
    private RandomAccessFile raf;
    
    private static final int TAMANO_FILA = 44;
    
    private static final int TAMANO_NOMBRE = 40;
    
    public Random_Parser(String ruta){
        this.ruta = ruta;
        try {
            raf = new RandomAccessFile(ruta, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cerrar() {
        try {
            if (raf != null) {
                raf.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public long longitudFichero(){
        long longitudFichero = 0;
        try {
            longitudFichero = raf.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return longitudFichero;
    }
    
    public long numContactos(){
        return longitudFichero() / TAMANO_FILA;
    }
    
    public void agregarContacto(String nombre, int telefono) {
        try {
            raf.seek(raf.length());
            escribirRegistro(nombre, telefono);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void modificarContacto(int numero, String nuevoNombre, int nuevoTelefono) {
        long posicion;
        if ((posicion = getPosicionEnFichero(numero)) != -1) {
            try {
                raf.seek(posicion);
                escribirRegistro(nuevoNombre, nuevoTelefono);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontró un contacto en la posición especificada.");
        }
    }
    
    private void escribirRegistro(String nombre, int telefono) throws IOException {
        StringBuilder sb = new StringBuilder(nombre);
        sb.setLength(TAMANO_NOMBRE); // Asegurarse de que el nombre tenga una longitud fija de 50 caracteres
        raf.writeBytes(sb.toString());
        raf.writeInt(telefono);
    }
    
    private long getPosicionEnFichero(int numero){
        long posicion = (numero - 1) * TAMANO_FILA;
        if (posicion < 0 || posicion >= longitudFichero()) return -1;
        return posicion;
    }
    
    public Contacto getContacto(int numero) {
        long posicion;
        if ((posicion = getPosicionEnFichero(numero)) != -1) {
            try {
                raf.seek(posicion);
                String nombre = leerCadena(TAMANO_NOMBRE);
                int telefono = raf.readInt();
                return new Contacto(nombre, telefono);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontró un contacto en la posición especificada.");
        }
        return null;
    }
    
    private String leerCadena(int longitud) throws IOException {
        byte[] bytes = new byte[longitud];
        raf.readFully(bytes);
        return new String(bytes);
    }
    
    
    public Contacto[] recuperaContactos() {
        int numContactos = (int) numContactos();
        Contacto[] contactos = new Contacto[numContactos];
        for(int i = 0; i < numContactos; i++){
            contactos[i] = getContacto(i);
        }
        return contactos;
    }
    
    public boolean guardaContactos(Contacto[] contactos) {
        return false;
    }
}

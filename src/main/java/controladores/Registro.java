/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author luciano
 * @param <T>
 */
public class Registro<T> {
    private final String ruta="archivo.txt";
    
    
    private static final File archivo=new File("archivo.txt");

	public void guardarArchivo(T es) {
		ObjectOutputStream salida = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(archivo);
			salida = new ObjectOutputStream(fos);
			salida.writeObject(es);
                        
		} catch (IOException ex) {
			System.out.println("Fallo al cerrar el archivo");
 
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ex) {
				System.out.println("Fallo al cerrar el archivo");
			}
			try {
				if (salida != null) {
					salida.close();
				}
			} catch (IOException ex) {
				System.out.println("Fallo al cerrar el archivo");
			}
		}
	}
        public  T cargar() {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		T es = null;
		try {
			fis = new FileInputStream(archivo);
			entrada = new ObjectInputStream(fis);
			es = (T) entrada.readObject();
		} catch (IOException ex) {
			System.out.println("Fallo la carga del archivo");
		} catch (ClassNotFoundException ex) {
			System.out.println("Fallo la carga del objeto");
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				System.out.println("Fallo la carga del archivo");
			}
			try {
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException ex) {
				System.out.println("Fallo la carga del archivo");
			}
		}
		return es;
	}
}

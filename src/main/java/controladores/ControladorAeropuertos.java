/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelo.Aeropuerto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author luciano
 */
public class ControladorAeropuertos {
    public HashMap<Map<Aeropuerto,Aeropuerto>,Boolean> conexiones;
    Aeropuerto []aeropuertos;
    
    public ControladorAeropuertos() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        conexiones=new HashMap<>();
        generarAeropuertos();
        generarConexiones();
    }
    
    public HashMap<Map<Aeropuerto, Aeropuerto>, Boolean> metodoWarshall() {
    // Crear una copia de las conexiones existentes
    HashMap<Map<Aeropuerto, Aeropuerto>, Boolean> cierreTransitivo = new HashMap<>(conexiones);

        for (Aeropuerto k : aeropuertos) {
            for (Aeropuerto i : aeropuertos) {
                for (Aeropuerto j : aeropuertos) {
                    // Verificar si hay una conexión directa de i a k y de k a j
                    boolean existeConexionDirecta = cierreTransitivo.get(Map.of(aeropuertos[i.getCodigo()], aeropuertos[k.getCodigo()])) && cierreTransitivo.get(Map.of(aeropuertos[k.getCodigo()], aeropuertos[j.getCodigo()]));

                    // Si existe una conexión directa de i a k y de k a j, agregar una conexión de i a j en el cierre transitivo
                    if (existeConexionDirecta) {
                        cierreTransitivo.put(Map.of(aeropuertos[i.getCodigo()], aeropuertos[j.getCodigo()]), true);
                    }
                }
            }
        }

        return cierreTransitivo;
    }
    
    
    
    private void generarAeropuertos()
    {
        aeropuertos=new Aeropuerto[21];
        aeropuertos[0]=new Aeropuerto("AerodromoMaria",0);
        aeropuertos[1]=new Aeropuerto("AlejandroVelasco",1);
        aeropuertos[2]=new Aeropuerto("AlferezFAP",2);
        aeropuertos[3]=new Aeropuerto("CapFAPPedroCanga",3);
        aeropuertos[4]=new Aeropuerto("CapFAPVictor",4);
        aeropuertos[5]=new Aeropuerto("CaptainFAPCarlos",5);
        aeropuertos[6]=new Aeropuerto("CaptainFAPGuillermo",6);
        aeropuertos[7]=new Aeropuerto("CnlFAPFran",7);
        aeropuertos[8]=new Aeropuerto("ComGerman",8);
        aeropuertos[9]=new Aeropuerto("CoronoelFAP",9);
        aeropuertos[10]=new Aeropuerto("FAPCaptainDavid",10);
        aeropuertos[11]=new Aeropuerto("FraciscoCarle",11);
        aeropuertos[12]=new Aeropuerto("IncaMancoCapac",12);
        aeropuertos[13]=new Aeropuerto("JorgeChavez",13);
        aeropuertos[14]=new Aeropuerto("Juanjui",14);
        aeropuertos[15]=new Aeropuerto("MariscalLamar",15);
        aeropuertos[16]=new Aeropuerto("MoisesBezanquen",16);
        aeropuertos[17]=new Aeropuerto("PadreAldamiz",17);
        aeropuertos[18]=new Aeropuerto("Pisco",18);
        aeropuertos[19]=new Aeropuerto("RodriguezBallon",19);
        aeropuertos[20]=new Aeropuerto("SanJuan",20);
    }
   public List<String> verConexiones(Aeropuerto i) {
       List<String> retornar=new ArrayList<>();
        for (Aeropuerto j : aeropuertos) {
            if(i.getCodigo()==j.getCodigo()){continue;}
                Map<Aeropuerto, Aeropuerto> connectionKey = Map.of(aeropuertos[i.getCodigo()], aeropuertos[j.getCodigo()]);
                retornar.add("[" + i.getNombre() + ";" + j.getNombre() + "]" + "," + conexiones.get(connectionKey));
        }
        return retornar;
    }
    private void generarConexiones() throws IOException, FileNotFoundException, ClassNotFoundException {
    // Lógica para generar conexiones de manera aleatoria
    Random random = new Random();
    Registro<List<Boolean>> r=new Registro<>();
    List<Boolean> generador=new ArrayList<>();
    generador=r.cargar();
    int i=0;
    for (Aeropuerto origen : aeropuertos) {
        for (Aeropuerto destino : aeropuertos) {
            // Evitar crear una conexión entre un aeropuerto y sí mismo
            if (origen.getCodigo()!=destino.getCodigo()) {
                boolean existeConexion = generador.get(i);
                conexiones.put(Map.of(aeropuertos[origen.getCodigo()],aeropuertos[destino.getCodigo()]), existeConexion);
                i++;
            }
            else
            {
                conexiones.put(Map.of(aeropuertos[origen.getCodigo()],aeropuertos[destino.getCodigo()]), false);
            }
        }
        
    }

    }
    public Aeropuerto []getAeropuertos()
    {
        return aeropuertos;
    }
    public Aeropuerto getAeropuerto(int codigo)
    {
        return aeropuertos[codigo];
    }
    public Boolean getConexion(int codigo1,int codigo2)
    {
        return conexiones.get(Map.of(aeropuertos[codigo1],aeropuertos[codigo2]));
    }

   
}

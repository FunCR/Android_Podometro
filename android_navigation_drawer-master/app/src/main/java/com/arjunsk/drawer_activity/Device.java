package com.arjunsk.drawer_activity;

import java.util.ArrayList;

/**
 * Created by sneer on 22/08/17.
 */

public class Device {

    public String name, ip;

    public ArrayList<Coordenada> listaCoordenadasAcelerometro;

    public ArrayList<Coordenada> listaCoordenadasGiroscopio;

    public Device(String name, String ip) {
        this.name = name;
        this.ip = ip;
        listaCoordenadasAcelerometro = new ArrayList<Coordenada>();
        listaCoordenadasGiroscopio = new ArrayList<Coordenada>();
    }

    //Recibe un parametro con el formato: "x:y:z:x:y:z"
    //"xA:yA:zA:xG:yG:zG" xA = eje x Acelerometro & xG eje x Giroscopio
    //Crea un objeto tipo Coordenada y lo agrega a lista
    public void addCoordenada(String coordString){

        String[] splitCoord = coordString.split(":");

        Coordenada coorAux1 = new Coordenada(
                Integer.parseInt(splitCoord[0]),
                Integer.parseInt(splitCoord[1]),
                Integer.parseInt(splitCoord[2]));

        Coordenada coorAux2 = new Coordenada(
                Integer.parseInt(splitCoord[3]),
                Integer.parseInt(splitCoord[4]),
                Integer.parseInt(splitCoord[5]));

        listaCoordenadasAcelerometro.add(coorAux1);

        listaCoordenadasGiroscopio.add(coorAux2);
    }

    //Clase Coordenada
    public class Coordenada{

        public int x,y,z;
        public ArrayList<Integer> listaLineasGrafico;

        public Coordenada(int pX,int pY,int pZ){
            x=pX;
            y=pY;
            z=pZ;
        }

        public ArrayList<Integer> getValueForGraph(){
            //TODO: Necesito operar los 3 valores y retornar los resultados

            ArrayList<Integer> resultado = new ArrayList<>();
            resultado.add(x);
            resultado.add(y);
            resultado.add(z);
            return resultado;
        }
    }

}

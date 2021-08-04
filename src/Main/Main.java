package Main;

import Graph.Graph;
import Persistence.Persistence;

import java.io.*;
import java.lang.Math;


public class Main {



    public static void  main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello Endava");

       Graph migrafito= new Graph();



       for (int i=0; i<10; i++){
           migrafito.addNodo("n"+String.valueOf(i));

       }

        //migrafito.verNodos();
       // migrafito.verAdyacencia();

        for (int i=0; i<15; i++){
            int numeroRandome = (int)(Math.random()*(10)+0);
            int numeroRandome2 = (int)(Math.random()*(10)+0);
            migrafito.addVertice("n"+String.valueOf(numeroRandome),
                    i,"n"+String.valueOf(numeroRandome2));


        }

        //migrafito.verNodos();
       // migrafito.verAdyacencia();

        Persistence pers = new Persistence();
        pers.setGrafo(migrafito);
        pers.serializar("graph.txt");
        pers.getGrafo().verAdyacencia();









    }
}

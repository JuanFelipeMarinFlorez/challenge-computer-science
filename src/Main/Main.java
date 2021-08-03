package Main;

import Grafo.Grafo;

import java.lang.Math;

import java.util.*;

public class Main {



    public static void  main(String[] args){
        System.out.println("Hello Endava");

       Grafo migrafito= new Grafo();

       for (int i=0; i<10; i++){
           migrafito.addNodo("n"+String.valueOf(i));

       }

        migrafito.verNodos();
        migrafito.verAdyacencia();

        for (int i=0; i<15; i++){
            int numeroRandome = (int)(Math.random()*(10)+0);
            int numeroRandome2 = (int)(Math.random()*(10)+0);
            migrafito.addVertice("n"+String.valueOf(numeroRandome),
                    i,"n"+String.valueOf(numeroRandome2));


        }

        migrafito.verNodos();
        migrafito.verAdyacencia();





    }
}

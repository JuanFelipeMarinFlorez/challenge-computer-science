package Main;

import Graph.Graph;
import Persistence.Persistence;

import java.io.*;


public class Main {



    public static void  main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello Endava");

       Graph migrafito= new Graph();



       for (int i=1; i<10; i++){
           migrafito.addNode("n"+String.valueOf(i));

       }

       /*migrafito.addVertice("n1",2,"n2");
       migrafito.addVertice("n1",3,"n3");
       migrafito.addVertice("n2",5,"n4");
       migrafito.addVertice("n2",2,"n5");
       migrafito.addVertice("n3",5,"n5");
       migrafito.addVertice("n4",1,"n5");
       migrafito.addVertice("n4",2,"n6");
       migrafito.addVertice("n5",4,"n6");*/


        for (int i=0; i<15; i++){
            int numeroRandome = (int)(Math.random()*(10)+1);
            int numeroRandome2 = (int)(Math.random()*(10)+1);
            if(numeroRandome!=numeroRandome2)
            migrafito.addEdge("n"+String.valueOf(numeroRandome),
                    i,"n"+String.valueOf(numeroRandome2));


        }



        System.out.println(migrafito.getNodes().toArray()[0]);
         migrafito.seeAdjacency();

        migrafito.dijkstra("n1","n9");
        //migrafito.verNodos();
       // migrafito.verAdyacencia();

        Persistence pers = new Persistence();
        pers.setGrafo(migrafito);
        pers.serializar("graph.txt");
        //pers.getGrafo().seeAdjacency();









    }
}

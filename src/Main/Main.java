package Main;

import Graph.Graph;
import Persistence.Persistence;

import java.io.*;


public class Main {



    public static void  main(String[] args) throws IOException, ClassNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selection=-1;
        String answer, initalNode, finalNode;
        Persistence pers = new Persistence();
        Graph graph = null;
        boolean exit=false;

        pers.descomprimir("graph.txt");
        if(pers.getGrafo()!=null){
           graph=pers.getGrafo();
        }
        while(!exit){

            while(selection < 1 || selection>4 ){

                System.out.println("Hello Endava");
                System.out.println("Computer Science challenge");
                System.out.println("by Juan Felipe Marin");
                System.out.println("- - - - - - - - -");
                System.out.println("1. Open graph from file and serialize.");
                System.out.println("2. find the best path between two.");
                System.out.println("3. Watch current graph.");
                System.out.println("4. Exit.");
                answer = reader.readLine();
                selection= Integer.valueOf(answer.replace(" ",""));

            }

            if(selection==1){
                System.out.println("Please write the file where the graph is");
                answer = reader.readLine();
                graph= pers.createGraphByFile(answer);
                pers.setGrafo(graph);
                pers.serializar("graph.txt");
                selection=-1;

            }
            if(selection==2){
                System.out.println("Please write the initial node");
                initalNode = reader.readLine();
                System.out.println("Please write the final node");
                finalNode = reader.readLine();

                System.out.println("Path: \n");
                System.out.println("");
                graph.dijkstra(initalNode,finalNode);
                System.out.println("");
                selection=-1;

            }
            if(selection==3){
                if(graph.getNodes().size()>0){
                    graph.seeAdjacency();
                }
                else{
                    System.out.println("There is not current graph.");
                }

                selection=-1;
            }
            if(selection==4){
                exit=true;
            }

        }











    }
}

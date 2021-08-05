package Persistence;

import Graph.Graph;

import java.io.*;
import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Persistence {

    private Graph graph;

    public Graph getGrafo() {
        return graph;
    }

    public void setGrafo(Graph graph) {
        this.graph = graph;
    }

    public void serializar(String filename) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("nocomprimido.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.graph);
        fileOutputStream.close();
        comprimir(filename);



    }

    public void comprimir (String filename) throws IOException {

        FileInputStream fis=new FileInputStream("nocomprimido.txt");
        FileOutputStream fos=new FileOutputStream(filename);
        DeflaterOutputStream dos=new DeflaterOutputStream(fos);
        int info;
        while ((info=fis.read())!=-1)
        {
            dos.write(info);
        }
        fis.close();
        dos.close();
        File fichero = new File("nocomprimido.txt");
        fichero.delete();
    }

    public void descomprimir(String filename) throws IOException, ClassNotFoundException {

        File tempFile = new File(filename);
        if(!tempFile.exists()){
            this.graph=null;

        }
        else{
            FileInputStream fis=new FileInputStream(filename);
            FileOutputStream fos=new FileOutputStream("descomprimido.txt");
            InflaterInputStream iis=new InflaterInputStream(fis);
            int info;
            while((info=iis.read())!=-1)
            {
                fos.write(info);
            }
            fos.close();
            iis.close();
            deserializar("descomprimido.txt");

        }




    }

    public void deserializar(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.graph = (Graph) ois.readObject();
        fis.close();

        File fichero = new File(filename);
        fichero.delete();
    }

    public Graph createGraphByFile(String filename) throws FileNotFoundException {
        Graph graph= new Graph();
        Scanner input = new Scanner(new File(filename));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] array = line.split(",");
            if(array.length==1){
                graph.addNode(line.replace(" ",""));
            }
            else if(array.length==3){
                graph.addEdge(array[0],Integer.valueOf(array[2].replace(" ","")),array[1]);
            }

        }
        input.close();

        return graph;

    }

}



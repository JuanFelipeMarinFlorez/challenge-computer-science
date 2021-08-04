package Persistencia;

import Grafo.Grafo;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Persistencia {

    private Grafo grafo;

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void serializar(String filename) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("nocomprimido.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.grafo);
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

    public void deserializar(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.grafo = (Grafo) ois.readObject();
        fis.close();

        File fichero = new File(filename);
        fichero.delete();
    }

}

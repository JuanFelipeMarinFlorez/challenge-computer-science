package Graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph implements Serializable {



    private List<String> nodos;
    private Map<String, Map> adyacencia;
    // =new HashMap<String,Map>()

    // = new ArrayList<>();

    /*

     */
    public Graph(List<String> nodos, Map<String, Map> adyacencia) {
        this.nodos = nodos;
        this.adyacencia = adyacencia;
    }

    public Graph(){
        this.nodos =new ArrayList<String>();;
        this.adyacencia =new HashMap<String,Map>();
    }

    public List<String> getNodos() {
        return nodos;
    }

    public Map<String, Map> getAdyacencia() {
        return adyacencia;
    }

    public void setNodos(List<String> nodos) {
        this.nodos = nodos;
    }

    public void setAdyacencia(Map<String, Map> adyacencia) {
        this.adyacencia = adyacencia;
    }

    public void addNodo(String nodo){
        if(this.nodos.contains((String)nodo)){
            System.out.println("El nodo: "+nodo+" ya existe en este grafo. ");
        }
        else {
            this.nodos.add(nodo);
            Map<String, Integer> mapNuevo = new HashMap<String, Integer>();
            this.adyacencia.put(nodo, mapNuevo);
        }
    }

    public void addVertice(String nodoA, int peso, String nodoB){
        if(!this.nodos.contains((String)nodoA)){
            System.out.println("El nodo: "+nodoA+" no existe en este grafo. ");
        }
        else if(!this.nodos.contains((String)nodoB)){
            System.out.println("El nodo: "+nodoB+" no existe en este grafo. ");
        }
        else{
            this.adyacencia.get(nodoA).put(nodoB,peso);
            this.adyacencia.get(nodoB).put(nodoA,peso);
        }

    }

    public void verNodos(){
        for(String m:nodos){
            System.out.println(m);
        }
    }

    public void verAdyacencia(){
        for(Map.Entry m:this.adyacencia.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }


}

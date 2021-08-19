package Graph;

import java.io.Serializable;
import java.util.*;
import java.util.function.UnaryOperator;

public class Graph implements Serializable {

    private List<String> nodes;
    private Map<String, Map> adjacency;

    public Graph(List<String> nodes, Map<String, Map> adjacency) {
        this.nodes = nodes;
        this.adjacency = adjacency;
    }
    public Graph(){
        this.nodes =new ArrayList<String>();;
        this.adjacency =new HashMap<String,Map>();
    }
    public List<String> getNodes() {
        return nodes;
    }
    public Map<String, Map> getAdjacency() {
        return adjacency;
    }
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
    public void setAdjacency(Map<String, Map> adjacency) {
        this.adjacency = adjacency;
    }
    public void addNode(String node){
        if(this.nodes.contains((String)node)){
            System.out.println("The node: "+node+"  already exists ");
        }
        else {
            this.nodes.add(node);
            Map<String, Integer> mapNuevo = new HashMap<String, Integer>();
            this.adjacency.put(node, mapNuevo);
        }
    }
    public void addEdge(String nodoA, int weight, String nodoB){
        if(!this.nodes.contains((String)nodoA)){
            System.out.println("The node: "+nodoA+" does not exists in this graph. ");
        }
        else if(!this.nodes.contains((String)nodoB)){
            System.out.println("The node: "+nodoB+" does not exists in this graph. ");
        }
        else{
            this.adjacency.get(nodoA).put(nodoB,weight);
            this.adjacency.get(nodoB).put(nodoA,weight);
        }

    }
    public void seeNodes(){
        for(String m: nodes){
            System.out.println(m);
        }
    }
    public void seeAdjacency(){
        for(Map.Entry m:this.adjacency.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }

    public Vector<String> dijkstra(String inicialNode, String finalNode){
        Vector<String> path = new Vector<String>();
        // Here we can see if there are the selected nodes in the graph
        if(!this.nodes.contains((String)finalNode) || !this.nodes.contains((String)inicialNode)){
            path.add("No existence of such a node");
            return path;
        }
        Map<String, Integer> weights =new HashMap<String,Integer>();
        Map<String, String> referencesNodes= new HashMap<String,String>();
        Map<String, String> selectedNodes= new HashMap<String,String>();
        int tam=this.getNodes().size();
        int infinite=10000000;
        String node;
        boolean finalNodeFlag= false;
        // here we check if the both nodes have an grade higher  than zero to be posible the path
        if(grade(inicialNode)>0 && grade(finalNode)>0){
            // in this for we put all the weights on infinite
            for(int i=0; i<tam; i++){
                weights.put((String) this.nodes.toArray()[i],infinite);
            }
            weights.put((String) inicialNode, 0);
            referencesNodes.put(inicialNode,inicialNode);
            node=inicialNode;
            //in this while we can check every good node until the final node is found
            while(! finalNodeFlag){
                Map<String, Integer> edgeCopy = (Map)this.adjacency.get(node);
                int minWeight=1000000;
                String minNode="N/A";
                // in this for we can recalculate all the weights of the nodes
                // with which the current node has adjacency
                for(Map.Entry m:edgeCopy.entrySet()) {
                    // we check if the node isn't in the selected Nodes
                    if (!selectedNodes.containsKey(m.getKey())) {
                        String actualNode = (String) m.getKey();
                        int actualWeight = (int) m.getValue();
                        // we check if the new weight is les than the old
                        if (weights.get(node) + actualWeight < weights.get(actualNode)) {
                            weights.put((String) actualNode, weights.get(node) + actualWeight);
                            referencesNodes.put((String) m.getKey(),node);
                        }
                    }
                }
                // In this for we check every thw weight of every node
                for (Map.Entry m: weights.entrySet()){
                    // we make sure that not check the selected nodes
                    if(!selectedNodes.containsKey(m.getKey())){
                        // we check if the weight of the node is the lesser
                        if((Integer )m.getValue()<minWeight){
                            minWeight=(Integer )m.getValue();
                            minNode= (String) m.getKey();
                        }
                    }
                }
                // now we know which is the lesser node, we selected for the next iteration
                node=minNode;
                selectedNodes.put(minNode,node);
                if(node.equalsIgnoreCase(finalNode)){
                    finalNodeFlag=true;
                }
            }
            String nodoAux=finalNode;
            Deque<String> pilaAux= new ArrayDeque<String>();
            while(nodoAux!=inicialNode){
                String actual=nodoAux;
                pilaAux.push(actual);
                nodoAux= referencesNodes.get(actual);
            }
            pilaAux.push(nodoAux);
            while(!pilaAux.isEmpty()){
                path.add(pilaAux.pop());
            }
            for(String a:path){
                System.out.println(a);
            }
        }
        else{
            path.add("There is no path");
        }
        return path;
    }

    public int grade(String node){
        return adjacency.get(node).size();
    }
    public Vector<String> findAllNodeVisitedPath(){
        Vector<String> path = new Vector<String>();
        Map<String, Vector<String>> referencesNodes= new HashMap<String,Vector<String>>();
        Vector<String> pendingNodes =  new Vector<String>();
        int tam=this.getNodes().size();
        boolean max=false;
        String node;
        // Here we  add all the nodes in the pending nodes queue and add the vector and node in referencesNode
        for(int i=0; i<tam; i++){
            Vector<String> vector = new Vector<String>();
            referencesNodes.put((String) this.nodes.toArray()[i],vector);
            pendingNodes.add((String) this.nodes.toArray()[i]);
        }
        node = pendingNodes.firstElement();
        pendingNodes.remove(0);
        referencesNodes.get(node).add(node);
        Scanner scanner = new Scanner(System.in);
        while(!pendingNodes.isEmpty()){
            Map<String, Integer> edgeCopy = (Map)this.adjacency.get(node);
            int maxWeight=-1;
            String maxNode="N/A";

            for(Map.Entry m:edgeCopy.entrySet()) {
                // we check if the node isn't in the list of nodes already visited
                if(!referencesNodes.get(node).contains(m.getKey())){
                    // we check if the number of node visited is higher than before
                    if(referencesNodes.get(node).size()+1 > referencesNodes.get(m.getKey()).size() ){
                        referencesNodes.get(m.getKey()).removeAllElements();
                        referencesNodes.get(m.getKey()).addAll(referencesNodes.get(node));
                        referencesNodes.get(m.getKey()).add((String)m.getKey());
                        if(referencesNodes.get(m.getKey()).size()==tam){
                            return referencesNodes.get(m.getKey());
                        }
                    }
                    if(!pendingNodes.contains(m.getKey()) && !referencesNodes.get(node).contains(m.getKey())){
                        pendingNodes.add((String)m.getKey());
                    }
                }
            }
            // In this for we check every thw weight of every node
            for (Map.Entry m: referencesNodes.entrySet()){

                if(referencesNodes.get(m.getKey()).size()>maxWeight && !referencesNodes.get(node).contains(m.getKey())){
                    max=true;
                    maxWeight=referencesNodes.get(m.getKey()).size();
                    maxNode=(String)m.getKey();
                }

            }
            if(max){
                pendingNodes.remove(maxNode);
                pendingNodes.insertElementAt(maxNode,0);

            }

            if(!pendingNodes.isEmpty()){
                node = pendingNodes.firstElement();
                pendingNodes.remove(0);
            }

        }
        return path;
    }


}

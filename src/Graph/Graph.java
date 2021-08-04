package Graph;

import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {



    private List<String> nodes;
    private Map<String, Map> adjacency;

    /*
    Function name: graph
    Description: This function is a constructor of the class with two parameters
    Parameters: a list of nodes and a map that represents the adjacency matrix
    Return: it doesn't return anything

     */
    public Graph(List<String> nodes, Map<String, Map> adjacency) {
        this.nodes = nodes;
        this.adjacency = adjacency;
    }

    /*
    Function name: graph
    Description: This function is a constructor of the class
    parameters:it doesn't receive parameters
    Return: it doesn't return anything
     */
    public Graph(){
        this.nodes =new ArrayList<String>();;
        this.adjacency =new HashMap<String,Map>();
    }

    /*
    Function name: getNodes
    Description: This function is a getter that gives you the node list of the graph
    parameters:it doesn't receive parameters
    Return: The list of string that represent all the nodes
     */
    public List<String> getNodes() {
        return nodes;
    }

    /*
    Function name: getAdjacency
    Description: This function is a getter that gives you the adjacency matrix
    parameters:it doesn't receive parameters
    Return: The adjacency matrix
     */
    public Map<String, Map> getAdjacency() {
        return adjacency;
    }

    /*
    Function name: setNodes
    Description: This function allows you change the nodes of the graph
    parameters: The new list of nodes
    Return: It doesn't return anything
     */
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    /*
    Function name: setAdjacency
    Description:  This function allows you change the adjacency matrix of the graph
    parameters: The new map that represents the adjacency matrix
    Return: It doesn't return anything
     */
    public void setAdjacency(Map<String, Map> adjacency) {
        this.adjacency = adjacency;
    }

    /*
    Function name: addNode
    Description: this function allows you add a new node into the graph
    parameters: the new node in string
    Return: It doesn't return anything
     */
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

    /*
     Function name: addEdge
     Description: this method allows you insert a new edge into the graph
     parameters:
        - nodeA: the origin node
        - weight: the weight of the edge
        - nodeB: the destination node
     Return: It doesn't return anything
      */
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

    /*
    Function name: seeNodes
    Description: this function allows you print in the screen by console all the nodes
    parameters: It doesn't have  parameters
    Return: It doesn't return anything
     */
    public void seeNodes(){
        for(String m: nodes){
            System.out.println(m);
        }
    }
    /*
       Function name: seeNodes
       Description: this function allows you print in the screen by console the adjacency matrix
       parameters: It doesn't have  parameters
       Return: It doesn't return anything
        */
    public void seeAdjacency(){
        for(Map.Entry m:this.adjacency.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }

    /*
       Function name: Dijkstra
       Description: this function allows you find the optimal path between two nodes
       parameters:
        - inicialNode: the node where the path starts
        - finalNode: the node where the path has to finish
       Return: It returns the path in a vector of string
        */

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
            path.add("No se puede llegar a los nodos");
        }
        return path;
    }

    /*
       Function name: grade
       Description: this function allows you find the grade of a node. The number of edges from this node.
       parameters:
        - node: The node we want to take the degree from
       Return: It returns the grade of the node in an integer
        */
    public int grade(String node){
        return adjacency.get(node).size();

    }


}

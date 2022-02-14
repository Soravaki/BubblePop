import java.io.File;
import java.util.*;

public class Graph {
    public HashMap<String, TreeSet<String>> adjmap;
    private int numEdges;

    public Graph(){
        adjmap = new HashMap<>();
    }

    public Graph (String filename, String delim){
        adjmap = new HashMap<>();
        try {
            // read file
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String[] inputLine = sc.nextLine().split(delim);
                addEdge(inputLine[0], inputLine[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEdge(String v, String w){
        if (!adjmap.containsKey(v))
            adjmap.put(v, new TreeSet<>());
        if (!adjmap.containsKey(w))
            adjmap.put(w, new TreeSet<>());
        adjmap.get(v).add(w);
        adjmap.get(w).add(v); // omit this for digraph
        numEdges++;
    }

    public int V(){
        return adjmap.keySet().toArray().length;  // ????? probably
    }

    public int E(){
        return numEdges; // harder
    }

    public Iterable<String> vertices(){
        return adjmap.keySet();
    }

    public Iterable<String> adjacentTo(String v) {
        return adjmap.get(v);
    }

    public int degree(String v) {
        return adjmap.get(v).size();
    }

    public boolean hasVertex(String v){
        return adjmap.containsKey(v);
    }

    public boolean hasEdge(String v, String w){
        if (!hasVertex(v))
            return false;
        if (!hasVertex(w))
            return false;
        return adjmap.get(v).contains(w);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String v : vertices()){
            sb.append(v + " : ");
            for (String adj : adjacentTo(v))
                sb.append(adj + " ");
            sb.append("\n");
        }
        return sb.toString();
    }


}

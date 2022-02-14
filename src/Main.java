import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class Main extends Graph{
    Main(String filename) throws IOException {
        //adjmap = new HashMap<>();
        // Instantiate Scanner and takes in input for how many mazes
        Scanner sc = new Scanner(new File(filename));

        Graph g = new Graph();
        int height = 10;
        int length = 10;
        System.out.println("dimensions in");
        // ties the characters together into a graph
        // until num = height -1

        // creates 2d array that stores the vaultmap and another array to act its location
        String[][] maze = new String[height][length];
        int[][] adjMazeRef = new int[height][length];
        // creates maze
        for (int i = 0; i < height; i++) {
            maze[i] = sc.nextLine().split("|");
        }
        // creates maze reference for making adjacent list
        int num = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                adjMazeRef[y][x] = num;
                num++;
            }
        }

        // traverses 2d array to make adjList
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                //System.out.print(adjMazeRef[y][x] + " ");
                // addEdge to the right if in bounds
                if (x < length - 1 && !(hasVertex(Integer.toString(adjMazeRef[y][x]))))
                    g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x] + 1)));

                // addEdge to the left if in bounds
                if (x > 0 && !(hasVertex(Integer.toString(adjMazeRef[y][x]))))
                    g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x] - 1)));

                // addEdge above if in bounds
                if (y > 0 && !(hasVertex(Integer.toString(adjMazeRef[y][x]))))
                    g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x] - height)));

                // addEdge below if in bounds
                if (y < height - 1 && !(hasVertex(Integer.toString(adjMazeRef[y][x]))))
                    g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x] + height)));
            }
        }
        //System.out.println(g.adjmap.keySet());
        /*for (int i=0; i<length*height; i++){
            System.out.println(adjmap.containsKey(Integer.toString(i)));
        }*/
        // debug - prints out maze
        for (int i = 0; i < height; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
        //System.out.println("compiled. length : " + length + " height : " + height + " num : " +numMaze );

        // takes in how many bubble sets to pop
        int numDataSets = sc.nextInt();
        sc.nextLine();

        // creates coords
        while (numDataSets != 0){
            String bubbleCords[] = sc.nextLine().split(" ");
            //System.out.println(Arrays.toString(bubbleCords));
            // runs bubble popping method
            pop(maze, adjMazeRef, bubbleCords, g);
            numDataSets--;
        }
    }

    public int[] getCoords(int[][] array, int item){
        int[] coords = new int[2];
        for (int y=0; y<array.length; y++){
            for (int x=0; x<array[y].length; x++){
                if (item == array[y][x]) {
                    // stores location into array that houses the cords
                    coords[0] = y;
                    coords[1] = x;
                }
            }
        }
        return coords;
    }

    public void pop(String[][] mazeArray, int[][] adjMazeArray, String[] coords, Graph g) throws ArrayIndexOutOfBoundsException {
        // instantiate objects for pathfinding
        ArrayList<Integer> visited = new ArrayList<>();
        Queue<String> q = new LinkedList<>();

        int[] startPosCords = new int[2];

        // looks up bubble position
        int popLocation = adjMazeArray[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])];

        // start DFS algorithm
        for (String enqueue : g.adjacentTo(String.valueOf(popLocation))){
            q.add(enqueue);
        }
        if (g.contains(q.remove())){

        }
    }
}

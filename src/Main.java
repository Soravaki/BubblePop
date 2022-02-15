import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class Main extends Graph{
    String[][] maze;
    int[][] adjMazeRef;
    int popped, popLocation;
    String popContent;
    Queue<Integer> q;

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


        //sets arrays
        this.adjMazeRef = adjMazeRef;
        this.maze = maze;

        // creates coords
        while (numDataSets != 0){
            String bubbleCords[] = sc.nextLine().split(" ");
            //System.out.println(Arrays.toString(bubbleCords));
            // runs bubble popping method
            pop(bubbleCords, g);
            numDataSets--;
        }
    }

    public int[] getNumCoords(int item){
        int[] coords = new int[2];
        for (int y=0; y<adjMazeRef.length; y++){
            for (int x=0; x<adjMazeRef[y].length; x++){
                if (item == adjMazeRef[y][x]) {
                    // stores location into array that houses the cords
                    coords[0] = y;
                    coords[1] = x;
                }
            }
        }
        return coords;
    }

    public int[] getContentCoords(String item){
        int[] coords = new int[2];
        for (int y=0; y<maze.length; y++){
            for (int x=0; x<maze[y].length; x++){
                if (Objects.equals(item, maze[y][x])) {
                    // stores location into array that houses the cords
                    coords[0] = y;
                    coords[1] = x;
                }
            }
        }
        return coords;
    }


    public void pop(String[] startPosCoords, Graph g) throws ArrayIndexOutOfBoundsException {
        this.popped = 0;
        // instantiate objects for pathfinding
        ArrayList<Integer> visited = new ArrayList<>();
        this.q = new LinkedList<>();

        // looks up bubble position number
        this.popLocation = adjMazeRef[Integer.parseInt(startPosCoords[0])][Integer.parseInt(startPosCoords[1])];
        // stores what kind of bubble
        this.popContent = maze[Integer.parseInt(startPosCoords[0])][Integer.parseInt(startPosCoords[1])];

        System.out.println(popContent);

    }

    public void floodFill(int location, String bubbleContent){
        // start DFS algorithm
        for (String enqueue : g.adjacentTo(String.valueOf(popLocation))){
            q.add(Integer.parseInt(enqueue));
        }
        int[] locationCoords = getNumCoords(location);
        String content = maze[locationCoords[0][locationCoords[1]];
        if ( content == bubbleContent){
            popped++;

        }
    }
}

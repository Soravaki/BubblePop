import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    // Instatiate vars
    String[][] maze;
    int[][] adjMazeRef;
    int popped, popLocation, height, length;
    String popContent;
    ArrayList<Integer> visited;

    Main(String filename) throws IOException {
        // Instantiate Scanner and takes in input for how many mazes
        Scanner sc = new Scanner(new File(filename));
        this.height = 10;
        this.length = 10;

        // creates 2d array that stores the two maps, one for contents and one for location
        String[][] maze = new String[height][length];
        int[][] adjMazeRef = new int[height][length];

        // creates maze and maze reference and sets them
        for (int i = 0; i < height; i++) {
            maze[i] = sc.nextLine().split("|");
        }
        int num = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                adjMazeRef[y][x] = num;
                num++;
            }
        }
        this.adjMazeRef = adjMazeRef;
        this.maze = maze;

        // takes in how many bubble sets to pop
        int numDataSets = sc.nextInt();
        sc.nextLine();

        // creates coords
        while (numDataSets != 0){
            String bubbleCords[] = sc.nextLine().split(" ");
            // runs bubble popping method
            pop(bubbleCords);
            numDataSets--;
        }
    }

    // method for grabbing coords for the number array
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

    // method for grabbing coords for the content array
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


    public void pop(String[] startPosCoords) throws ArrayIndexOutOfBoundsException {
        this.popped = 0;
        // instantiate objects for pathfinding
        this.visited = new ArrayList<>();

        // looks up bubble position number and stores location and content in vars
        this.popLocation = adjMazeRef[Integer.parseInt(startPosCoords[0])][Integer.parseInt(startPosCoords[1])];
        this.popContent = maze[Integer.parseInt(startPosCoords[0])][Integer.parseInt(startPosCoords[1])];

        // DEBUG
        //System.out.print(popContent + " ");
        floodFill(popLocation, popContent);

        // output
        if (popped < 3) System.out.println("NO ");
        else System.out.println("YES " + popped);
        //System.out.println(visited);
    }

    public void floodFill(int popLocation, String popContent){
        // add locatino to visited ArrayList
        visited.add(popLocation);
        // get the coords of the numLocation
        int[] locationCoords = getNumCoords(popLocation);
        // gets the letter content of the location
        String content = maze[locationCoords[0]][locationCoords[1]];
        // recursive algorithm
        if (Objects.equals(content, popContent)){
            popped++;
            // right of location
            if ((popLocation+1)%10<length && !(visited.contains((popLocation+1))))
                floodFill(popLocation+1, popContent);

            // left of location
            if (popLocation-1>=0 && !(visited.contains((popLocation-1))))
                floodFill(popLocation-1, popContent);


            // up from location
            if (popLocation+height<length*height && !(visited.contains((popLocation+height))))
                floodFill(popLocation+height, popContent);


            // down from location
            if (popLocation-height>=0 && !(visited.contains((popLocation-height))))
                floodFill(popLocation-height, popContent);

        }
    }
}

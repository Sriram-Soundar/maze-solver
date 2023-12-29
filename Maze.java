import java.util.*;
/**
 * A class that defines the maze object.
 * The methods included are useful for traversing the maze and discerning
 * qualities about it or the nodes that make it up.
 *
 * @author Prof. Praire Rose
 * @version 1.0
 */
public class Maze
{
    //These are fields containing the qualities of the maze
    Node[][] maze;
    int size;
    Move start;
    Move end;

    /**
     * A constructor that instantiates and creates a new square random maze.
     * @param size: the number of nodes on each side (square)
     */
    public Maze(int size){
        this.size = size;
        this.maze = new Node[size][size];
        for(int i = 0; i< size; i++){
            for (int j=0;j<size; j++){
                this.maze[i][j] = new Node();
            }
        }
        setStart(new Move(0,0));
        generateMaze(this.start);
        setEnd(new Move(size-1,size-1));
        reset();//Clears information used to generate the maze.
    }

    //method making the start state of the maze

    /**
     * Removes a wall to the outside creating the start of the maze visually
     * and sets the instance variable.
     *
     * @param m Move: the row/col ordered pair of the start.
     */
    public void setStart(Move m){
        this.start = new Move(m.row,m.col);
        Node cur = maze[m.row][m.col];
        cur.addEdge(DIRECTION.NORTH,1);
    }

    /**
     * Removes a wall to the outside creating the start of the maze visually
     * and sets the instance variable.
     *
     * @param m row,col location
     */
    public void setEnd(Move m){
        this.end = new Move(m.row,m.col);
        Node cur = maze[m.row][m.col];

        cur.addEdge(DIRECTION.SOUTH,1);
    }


    /**
     * In order to generate the maze, we must keep track of nodes we have visited, but once we are done, we must reset everything back to unvisited.  It is also useful to run different algorithms on the same maze.
     */
    public void reset(){
        for(int i = 0; i< size; i++){
            for (int j=0;j<size; j++){
                this.maze[i][j].status = Node.STATUS.UNVISITED;
                //this.maze[i][j].step = 0;
            }
        }
    }

    /**
     * Helper method to get a Node with a Move.  Purely syntactic sugar.
     * @param m The location of the node
     * @return The node
     */
    public Node get(Move m){
        return this.maze[m.row][m.col];
    }

    /**
     * method checking whether a move is within bounds
     *
     * @param m The possible location
     * @return boolean answer
     */
    boolean isValidLoc(Move m){
        return (0 <= m.row && m.row < this.size) && (0 <= m.col && m.col < this.size);
    }


    /**
     * Returns a direction (north, south, east wast) of the next randomly chosen valid neighbor.
     *
     * @param m current node to explore neighbors of
     * @return a direction (north, south, east wast)
     */
    DIRECTION getRandomUnvisitedNeighbor(Move m){
        DIRECTION[] dirs = DIRECTION.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (DIRECTION dir : dirs) {
            int newRow = m.row + dir.dy;
            int newCol = m.col + dir.dx;
            if(isValidLoc(new Move(newRow, newCol))){
                Node neighbor = maze[newRow][newCol];
                if(!neighbor.status.equals(Node.STATUS.FINISHED)){
                    return dir;
                }

            }

        }

        return null;
    }

    /**
     * Generates the maze using the recursive DFS algorithm discussed in class
     *
     * @param m starting/current location
     */
    public void generateMaze(Move m){
        Node n = maze[m.row][m.col];
        n.status = Node.STATUS.FINISHED;

        DIRECTION nextDir = getRandomUnvisitedNeighbor(m);
        while(nextDir != null){
            addEdge(m.row,m.col,nextDir);
            generateMaze(new Move((m.row + nextDir.dy), (m.col + nextDir.dx)));
            nextDir = getRandomUnvisitedNeighbor(m);
        }
    }

    /**
     * Creates a bidirectional opening in the maze based on the row and column location provided, and the direction to make the opening
     * @param row row index
     * @param col col index
     * @param dir north, south, east, or west
     */
    public void addEdge(int row, int col, DIRECTION dir){
        Node cur = maze[row][col];
        Node next = maze[row + dir.dy][col + dir.dx];

        cur.addEdge(dir,1);
        next.addEdge(dir.opposite,1);

    }

    /**
     * Prints the Maze to the terminal as a grid.
     * @return the string to be printed
     */
    @Override
    public String toString(){
        String s = "";

        for(int col = 0; col<size; col++){
            //s += "+---";
            Node n = maze[0][col];
            s += n.n > 0?  "+   ":"+---" ;

        }
        s += "+\n";
        for(int row=0; row<maze.length; row++){
            String line2 = "";
            String line3 = "";
            for(int col=0;col<maze[row].length; col++){
                Node n = maze[row][col];
                line2 += n.w > 0?  "  "+n.status+" ":"| "+n.status+" " ;
                //line2 += n.w > 0?  "    ":"|   ";//   ":"|   " ;
                line3 += n.s > 0? "+   " : "+---";
            }
            //s += line1 + "\n";
            s += line2 + "|\n";
            s += line3 + "+\n";

        }

        return s;
    }


    /**
     *     enumerated variable that defines the cardinal directions, the numerical equivalent of that direction
     *     in a 2D matrix representation, a constructor, and some functionality helpers.
     *
     */
    enum DIRECTION {
        NORTH(0, -1),
        SOUTH(0, 1),
        EAST(1, 0),
        WEST(-1, 0);

        final int dx;//add to col
        final int dy;//add to row
        DIRECTION opposite;

        // utility to simplify bidirectional walls
        static {
            NORTH.opposite = SOUTH;
            SOUTH.opposite = NORTH;
            EAST.opposite = WEST;
            WEST.opposite = EAST;
        }

        // enum constructor
        private DIRECTION(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

}

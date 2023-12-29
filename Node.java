/**
 * A class that defines a node object that is used for traversing the map.
 * The class also includes global variables that are binary variables meant to represent
 * if there is an edge in the direction that they correspond to.
 *
 * @author Prof. Praire Rose
 * @version 1.0
 */
public class Node
{

    //variables indicating whether there is an edge
    //in that direction
    int n = 0;
    int e = 0;
    int s = 0;
    int w = 0;


    /**
     * Status of node, can be UNVISITED, IN_PROGRESS, OR FINISHED
     */
    public STATUS status = STATUS.UNVISITED;



    /**
     *  Empty constructor for easy initialization.  No edges by default
     */
    public Node(){
    }



    /**
     * method used for adding an edge
     *
     * @param dir north,south,east, or west
     * @param weight Anything above zero becomes a direction you can travel
     */
    public void addEdge(Maze.DIRECTION dir, int weight){
        switch(dir){
            case NORTH:
                this.n = weight;
                break;
            case SOUTH:
                this.s = weight;
                break;
            case EAST:
                this.e = weight;
                break;
            case WEST:
                this.w = weight;
                break;
            default:
                System.out.println("error trying to set edge: " + dir);
        }

    }

    /**
     * String to be printed
     * @return a string
     */
    public String toString(){
        String string = "";
        string += n > 0? "+   +\n" : "+---+\n";
        string += w > 0? "|   " : "    ";
        string += e > 0? "|\n" : " \n";
        string += s > 0? "+   +\n" : "+---+\n";

        return string;
    }

    /**
     *     enumerated variable that defines the state of the node and defines the string corresponding to that state
     */
    enum STATUS{
        IN_PROGRESS('v'),
        UNVISITED(' '),
        FINISHED('.');

        final char symbol;

        private STATUS(char symbol){
            this.symbol = symbol;
        }
        public String toString(){
            return "" + this.symbol;
        }
    }

}

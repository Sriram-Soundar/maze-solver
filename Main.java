/**
 * A class that holds the main method in order to run the program.
 *
 *
 * @author <your name here>
 * @version 1.0
 */


public class Main {

    /**
     * Welcome the user, create the maze, and then solve it using the algorithms you wrote.
     *
     * @param args from commandline (unused)
     */
    public static void main(String[] args){
        Maze m = new Maze(10);
        m = Algorithms.solveDFS(m);
        System.out.println(m);
        Maze m2 = new Maze(10);
        m2 = Algorithms.solveBFS(m2);
        System.out.println(m2);

    }

}

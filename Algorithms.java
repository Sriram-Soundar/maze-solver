import java.util.*;
/**
 * A class containing three static methods.
 * The methods solveDFS and solveBFS are implementations of
 * the corresponding graph traversing algorithms. They will be used to find the end point
 * of the input maze.
 * The helper method takes in the maze and the current location, and returns the possible
 * locations for the algorithm to move to in all four cardinal directions from the current location.
 *
 * @author <your name here>
 * @version 1.0
 */
public class Algorithms {

    /**
     * An implementation of Depth First search to solve the maze.  Method should use a stack implementation, NOT recursion.
     * Method is static so that we can pass a maze to it to be solved without creating a new instance.  It is a utility method.
     *
     * @param m maze to be solved
     * @return maze to be solved (side effects include the status of the nodes changing to reflect where we've been)
     */
    public static Maze solveDFS(Maze m) {
        Stack<Move> moveStack = new Stack<>();
        moveStack.push(m.start);
        while(!moveStack.isEmpty()){
            Move currMove = moveStack.peek();
            Node currNode = m.get(currMove);
            currNode.status = Node.STATUS.IN_PROGRESS;
            if(currMove.equals(m.end)){
                return m;
            }
            List<Move> possibleMoves = getAllPossibleMoves(m, currMove);
            if(possibleMoves.size() == 0){
                currNode.status = Node.STATUS.FINISHED;
                moveStack.pop();
            }
            else{
                Move nextMove = possibleMoves.get(0);
                moveStack.push(nextMove);
            }
        }
        return m;
    }

    /**
     *  An implementation of Depth First search to solve the maze.  Method should use a stack implementation, NOT recursion.
     *  Method is static so that we can pass a maze to it to be solved without creating a new instance.  It is a utility method.
     * @param m maze to be solved
     * @return the solved maze(side effects include the status of the nodes changing to reflect where we've been)
     */
    public static Maze solveBFS(Maze m) {
        ArrayDeque<Move> moveQueue = new ArrayDeque<>();
        moveQueue.offer(m.start);
        while(!moveQueue.isEmpty()){
            Move currMove = moveQueue.poll();
            Node currNode = m.get(currMove);
            currNode.status = Node.STATUS.IN_PROGRESS;
            if(currMove.equals(m.end)){
                return m;
            }
            List<Move> possibleMoves = getAllPossibleMoves(m, currMove);
            if(possibleMoves.size() == 0){
                currNode.status = Node.STATUS.FINISHED;
            }else {
                for(Move move : possibleMoves){
                    moveQueue.offer(move);
                }
            }
        }
        return m;
    }

    /**
     * Should return a list of locations that represent the neighbors of the node(Move) passed in given the current maze.
     *
     * @param maze the maze we are looking at
     * @param m current location to explore the neighbors
     * @return List of next possible moves.
     */
    public static List<Move> getAllPossibleMoves(Maze maze, Move m){
        LinkedList<Move> movesList = new LinkedList<>();
        Node node = maze.get(m);
        if(node.n > 0){
            int nextRow = m.row + Maze.DIRECTION.NORTH.dy;
            int nextCol = m.col + Maze.DIRECTION.NORTH.dx;
            Move playerMove = new Move(nextRow, nextCol);
            if(maze.isValidLoc(playerMove)){
                Node playerMoveNode = maze.get(playerMove);
                if(playerMoveNode.status.equals(Node.STATUS.UNVISITED)){
                    movesList.add(new Move(nextRow, nextCol));
                }
            }
        }

        if(node.e > 0){
            int nextRow = m.row + Maze.DIRECTION.EAST.dy;
            int nextCol = m.col + Maze.DIRECTION.EAST.dx;
            Move playerMove = new Move(nextRow, nextCol);
            if(maze.isValidLoc(playerMove)){
                Node playerMoveNode = maze.get(playerMove);
                if(playerMoveNode.status.equals(Node.STATUS.UNVISITED)){
                    movesList.add(new Move(nextRow, nextCol));
                }
            }
        }

        if(node.s > 0){
            int nextRow = m.row + Maze.DIRECTION.SOUTH.dy;
            int nextCol = m.col + Maze.DIRECTION.SOUTH.dx;
            Move playerMove = new Move(nextRow, nextCol);
            if(maze.isValidLoc(playerMove)){
                Node playerMoveNode = maze.get(playerMove);
                if(playerMoveNode.status.equals(Node.STATUS.UNVISITED)){
                    movesList.add(new Move(nextRow, nextCol));
                }
            }
        }

        if(node.w > 0){
            int nextRow = m.row + Maze.DIRECTION.WEST.dy;
            int nextCol = m.col + Maze.DIRECTION.WEST.dx;
            Move playerMove = new Move(nextRow, nextCol);
            if(maze.isValidLoc(playerMove)){
                Node playerMoveNode = maze.get(playerMove);
                if(playerMoveNode.status.equals(Node.STATUS.UNVISITED)){
                    movesList.add(new Move(nextRow, nextCol));
                }
            }
        }
        return movesList;
    }
}


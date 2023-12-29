import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    void getAllPossibleMoves() {
        int size = 10;
        Maze m = new Maze(size);
        //zeros out the random maze to a closed grid
        for(int i=0;i<m.size;i++){
            for(int j=0;j<m.size;j++){
                m.maze[i][j] = new Node();
            }
        }

        //No openings means no neighbors
        Move origin = new Move(0,0);
        List<Move> neighbors = Algorithms.getAllPossibleMoves(m,origin);
        assertEquals(0,neighbors.size(), "No openings means no neighbors");

        //Opening to the outside is not a valid move
        m.setStart(origin);
        neighbors = Algorithms.getAllPossibleMoves(m,origin);
        assertEquals(0, neighbors.size(), "Opening to the outside is not a valid neighbor");


        //Adding 1 edge just below the origin
        m.addEdge(0,0, Maze.DIRECTION.SOUTH);
        neighbors = Algorithms.getAllPossibleMoves(m,origin);
        assertEquals(1, neighbors.size(), "1 edge just below the origin");


        //Testing the midpoint by progressively adding more possible neighbors.
        int half = size/2;
        Move midPoint = new Move(half,half);
        m.addEdge(midPoint.row,midPoint.col, Maze.DIRECTION.SOUTH);

        neighbors = Algorithms.getAllPossibleMoves(m,midPoint);
        assertEquals(1, neighbors.size());
        assertEquals(new Move(midPoint.row + 1,midPoint.col), neighbors.get(0), "Similar to last test, should be one neighbor to the south");

        m.addEdge(midPoint.row,midPoint.col, Maze.DIRECTION.NORTH);
        neighbors = Algorithms.getAllPossibleMoves(m,midPoint);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains((new Move(midPoint.row - 1,midPoint.col))), "2 neighbors, added North");

        m.addEdge(midPoint.row,midPoint.col, Maze.DIRECTION.WEST);
        neighbors = Algorithms.getAllPossibleMoves(m,midPoint);
        assertEquals(3, neighbors.size());
        assertTrue(neighbors.contains((new Move(midPoint.row,midPoint.col -1))), "3 neighbors, added west");

        m.addEdge(midPoint.row,midPoint.col, Maze.DIRECTION.EAST);
        neighbors = Algorithms.getAllPossibleMoves(m,midPoint);
        assertEquals(4, neighbors.size());
        assertTrue(neighbors.contains((new Move(midPoint.row,midPoint.col + 1))), "4 neighbors added east");

        //Should be checking for status as well
        m.maze[midPoint.row][midPoint.col + 1].status = Node.STATUS.IN_PROGRESS;
        neighbors = Algorithms.getAllPossibleMoves(m,midPoint);
        assertEquals(3, neighbors.size());
        assertFalse(neighbors.contains(new Move(midPoint.row,midPoint.col + 1)), "East is no longer a valid next move");

        //Should be checking for status as well
        m.maze[midPoint.row][midPoint.col - 1].status = Node.STATUS.FINISHED;
        neighbors = Algorithms.getAllPossibleMoves(m,midPoint);
        assertEquals(2, neighbors.size());
        assertFalse(neighbors.contains(new Move(midPoint.row,midPoint.col - 1)), "West is no longer a valid next move");



        System.out.println(m);

    }
}
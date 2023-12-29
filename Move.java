/**
 * A class that defines the move object.
 *
 * @author Prof. Praire Rose
 * @version 1.0
 */

public class Move {
    int row;
    int col;

    /**
     * Constructor
     *
     * @param row row index
     * @param col col index
     */
    Move(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * method testing equivalence between move objects
     * @param other object
     * @return boolean equivalence
     */
    public boolean equals(Object other){
        if(other instanceof Move){
            Move m = (Move)other;
            return this.row == m.row && this.col == m.col;
        }

        return false;
    }

    /**
     * Printing method as (row,col)
     *
     * @return String
     */
    public String toString(){
        return "(" + this.row + "," + this.col + ")";
    }
}

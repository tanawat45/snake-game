
/**
 * Accessories class to handle location. 
 */

public class Position implements iPosition
{    private int row;
    private int col;
    
    /**
     * The constructor requires two parameters, which cann't be changed.
     * This make the objects from this class immutable. 
     * 
     * @param   row row value of this location
     * @param   col column value of this location
     */
    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Get the row value of this location
     * @return  the row of this location
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Get the column value of this location
     * @return  the column of this location
     */
    public int getCol()
    {
        return col;
    }
    
    public boolean equals(iPosition pos)
    {
        if(pos == null || ! (pos instanceof Position)) {
            return false;
        }
        return ((Position)pos).getRow() == row && ((Position)pos).getCol() == col;
    }
}

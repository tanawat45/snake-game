public class Grid implements iGrid
{
    

    private int[][] grid = new int[GRID_ROW][GRID_COL];
    

    public Grid()
    {
    }
    
        
    /** 
     * clear grid data 
     */
    public void resetGrid()
    {
        for(int r = 0; r != GRID_ROW; r++) {
            for(int c = 0; c != GRID_COL; c++) {
                grid[r][c] = TILE_EMPTY;
            }
        }
    }
    
    public int getTile(int row, int col)
    {
        return grid[row][col];
    }
    
    public void setTile(int row, int col, int tile)
    {
        grid[row][col] = tile;
    }
}

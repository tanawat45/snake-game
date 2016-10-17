
public interface iGrid
{
    /* constant */

    public static int TILE_EMPTY = 0;
    public static int TILE_SNAKE = 1;
    public static int TILE_FOOD = 2;
    
    public static int GRID_WIDTH = 10;
    public static int GRID_HEIGHT = 10;

    public static int GRID_ROW = 40;
    public static int GRID_COL = 40;
    public void resetGrid();
    public int getTile(int row, int col);
    public void setTile(int row, int col, int tile);
}

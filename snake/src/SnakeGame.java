import ucigame.*;
import java.util.*;


/**
 * Main game class, snake is a simple game. A player controls a snake through keyboard (ijkl).
 * The snake will keep moving and the player has to control the snake to avoid hitting the wall,
 * or itself. When snake eats a food (yellow dot), it gets longer and faster.
 * 
 */
public class SnakeGame extends Ucigame
{
 
    /* constant */
    private static int STATUS_OK = 0;
    private static int STATUS_HIT = 1;
    
    /* field */
    private iSnake snake;
    private iGrid grid;
    private Image snakeTile;
    private Image blankTile;
    private Image foodTile;
    
    private boolean isStarted = false;
    private boolean isOver = false;
    private boolean isFoodAvailable = false;
    
    private iPosition foodPosition = null;
    
    private int speed = 5;
    
    private Random random;
    
    
    /**
     * Setup the game: loading image, initialize snake body, update initial food location 
     */
    public void setup()
    {
        window.size(iGrid.GRID_WIDTH * iGrid.GRID_COL, iGrid.GRID_HEIGHT * iGrid.GRID_ROW);
        window.title("Snake");
        
        blankTile = getImage("gfx/black.png");
        snakeTile = getImage("gfx/white.png");
        foodTile = getImage("gfx/yellow.png");
        
        grid = new Grid();
        snake = new Snake();
        snake.append(4,7).append(4,8).append(4,9).append(4,10).append(4,11);
        
        random = new Random();
        updateFood();
    }

    /**
     * Handle player's key, update snake movement, check if hit wall or food. 
     */
    protected int updateSnake()
    {
        int status = STATUS_OK;
        iPosition next = null;
        
        /* where is the head location ? */
        iPosition head = snake.getHead();
        
        /* Depend on the snake direction, check if hit the wall, set the next location of snake */
        if(snake.getDirection() == 'l') {
            if(head.getCol() == 1) status = STATUS_HIT;
            next = new Position(head.getRow(), head.getCol() - 1);
        } else if(snake.getDirection() == 'r') {
            if(head.getCol() == iGrid.GRID_COL - 2) status = STATUS_HIT;
            next = new Position(head.getRow(), head.getCol() + 1);
        } else if(snake.getDirection() == 'u') {
            if(head.getRow() == 1) status = STATUS_HIT;
            next = new Position(head.getRow() - 1, head.getCol());
        } else if(snake.getDirection() == 'd') {
            if(head.getRow() == iGrid.GRID_ROW - 2) status = STATUS_HIT;
            next = new Position(head.getRow() + 1, head.getCol());
        }
        
        /* check if hit itself */
        if(snake.contains(next)) {
            status = STATUS_HIT;
        }
        
        /* check if hit food */
        if(next.equals(foodPosition)) {
            speed++;
            isFoodAvailable = false;
        }
        
        /* add new head */
        snake.addHead(next);
        
        /* if not eating food, remove the end of the body */
        if(isFoodAvailable) {
            snake.removeTail();
        }
        
        return status;
    }
    
    /**
     * Update new food location
     */
    protected void updateFood()
    {
        /* we should do this only when snake just eat the food */
        if(!isFoodAvailable) {
            int food_row, food_col;
            isFoodAvailable = true;
            /* we need to make sure that we don't random the food location on the snake body */
            while(true) {
                /* we also need to avoid the area close to the wall */
                food_row = random.nextInt(iGrid.GRID_ROW - 1) + 1;
                food_col = random.nextInt(iGrid.GRID_COL - 1) + 1;
                if(!snake.contains(foodPosition = new Position(food_row, food_col))) {
                    break;
                }
            }
        }
    }
    
    /*
     * Update the grid data
     */
    protected void updateGrid()
    {
        /* clear the grid */
        grid.resetGrid();
        
        /* put the snake body into the grid */
        for(iPosition p: snake.getBody()) {
            grid.setTile(p.getRow(), p.getCol(), iGrid.TILE_SNAKE);
        }
        
        /* put the food into the grid */
        grid.setTile(foodPosition.getRow(), foodPosition.getCol(), iGrid.TILE_FOOD);
                      
        /* draw the grid into the screen */
        for(int r = 0; r != iGrid.GRID_ROW; r++) {
            for(int c = 0; c != iGrid.GRID_COL; c++) {
                if(grid.getTile(r,c) == iGrid.TILE_SNAKE) {
                    snakeTile.draw(c * iGrid.GRID_WIDTH, r * iGrid.GRID_HEIGHT);
                } else if(grid.getTile(r,c) == iGrid.TILE_FOOD) {
                    foodTile.draw(c * iGrid.GRID_WIDTH, r * iGrid.GRID_HEIGHT);
                } else { //empty
                    blankTile.draw(c * iGrid.GRID_WIDTH, r * iGrid.GRID_HEIGHT);
                }
            }
        }  
    }
    
    /**
     * Show the start screen at the beginning of the game
     */
    public void showStartScreen()
    {
        canvas.font("SansSerif", BOLD, 20,  0, 0, 0);
        canvas.putText("Press SPACEBAR to start", 75, 100);
    }
    
    /**
     * Show the game over screen
     */
    public void showGameOver()
    {
        canvas.font("SansSerif", BOLD, 20, 255, 255, 255);
        canvas.putText("GAME OVER", 150, 100);
        canvas.putText("SCORE=" + (speed - 5), 150, 200);
    }
    
    /**
     * Draw the screen, all of the function related to the screen has to be inside this method, 
     * or called from this method.
     */
    public void draw()
    {
        int status = STATUS_OK;
        framerate(speed);
        if(!isStarted) {
            showStartScreen();
        } else if (!isOver) {
            canvas.clear();
            status = updateSnake();
            updateFood();
            updateGrid();
        }
        if(status == STATUS_HIT) {
            isOver = true;
        }
        if(isOver) {
            showGameOver();
        }
    }
    
    /**
     * Handle keyboard event , always use keyRelease instead of keyPress
     */
    public void onKeyRelease()
    {
        if(keyboard.key() == keyboard.SPACE) {
            isStarted = true;
        }
        if(keyboard.key() == keyboard.I) {
            snake.setDirection('u');
        }
        if(keyboard.key() == keyboard.J) {
            snake.setDirection('l');
        }
         if(keyboard.key() == keyboard.K) {
            snake.setDirection('d');
        }
        if(keyboard.key() == keyboard.L) {
            snake.setDirection('r');
        }
    }
    
}

import java.util.*;

public class Snake implements iSnake
{
    private ArrayList<iPosition> snakeBody;
    private char snakeDirection;
    
    public Snake()
    {
        snakeBody = new ArrayList<iPosition>();
        snakeDirection = 'l';
    }
    
    public iSnake append(int row, int col)
    {
        snakeBody.add(new Position(row, col));
        return this;
    }
    
    public iSnake addHead(iPosition pos)
    {
        snakeBody.add(0, pos);
        return this;
    }
    
    public iPosition getHead()
    {
        return snakeBody.get(0);
    }
    
    public iSnake removeTail()
    {
        snakeBody.remove(snakeBody.size() - 1);
        return this;
    }
    
    public char getDirection()
    {
        return snakeDirection;
    }
    
    public void setDirection(char dir)
    {
        snakeDirection = dir;
    }
    
    public boolean contains(iPosition pos)
    {
        return snakeBody.contains(pos);
    }
    
    public ArrayList<iPosition> getBody()
    {
        return snakeBody;
    }
}

import java.util.*;
/**
 * Write a description of interface iSnake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface iSnake
{
    public iSnake append(int row, int col);
    public iSnake addHead(iPosition pos);
    public iPosition getHead();
    public iSnake removeTail();
    public char getDirection();
    public void setDirection(char dir);
    public boolean contains(iPosition pos);
    public ArrayList<iPosition> getBody();
}

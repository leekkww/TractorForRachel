/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class Player {

    private Hand hand;
    private int points = 0;
    private int level = 2;
    private ArrayList<Player> friends;
    private boolean napoleon;
    
    //random pointless shit
    public String name = "Rachel Zhang";
    public String gender = "Male";
    public int age = 16;

    public void addPoints(int points) {this.points+=points;}
    public int getLevel(){return level;}
    public int getPoints() {return points;}
    
    //after each round clear everyone of points, etc.
    public void reset() {
        points = 0;
        friends.clear();
        napoleon = false;
    }
    
    /* //uncomment when ready to actually do this
    public ArrayList<Card> move() {
        //either put in an ai here or ask for moves from console or something
    }*/
}

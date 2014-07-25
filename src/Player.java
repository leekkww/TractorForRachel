/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class Player {

    private Hand hand;
    private int points = 0;
    private int level = 2;
    private ArrayList<Player> friends;
    private boolean napolean;

    public void addPoints(int points) {this.points+=points;}
    public int getLevel(){return level;}
    public int getPoints() {return points;}
}

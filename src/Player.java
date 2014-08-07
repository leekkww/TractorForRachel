/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class Player {

    private Hand hand = new Hand();
    private int points = 0;
    private int level = 2;
    private boolean isFriend; //friend of the napolean, that is
    private boolean napoleon;
    private Player nextPlayer;

    /**
     * Name of the player.
     * aka random pointless shit
     */
    public String name;
    /**
     * Gender of the player.
     * aka random pointless shit
     */
    public String gender;
    /**
     * Age of the player.
     * aka random pointless shit
     */
    public int age;

    /**
     * Initializes a newly created Player object that is set to the optimal state.
     */
    public Player() {
        name = "Rachel Zhang";
        gender = "Male";
        age = 16;
    }

    /**
     * Initializes a newly created Player object given a name and a level.
     * @param name  name of the player to be displayed
     * @param level  level of the player in the game of tractor
     */
    public Player(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void addPoints(int points) {this.points+=points;}
    public int getLevel() {return level;}
    public int getPoints() {return points;}
    public Hand getHand() {return hand;}

    public void setNextPlayer(Player p) {
        nextPlayer = p;
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    //after each round clear everyone of points, etc.
    public void reset() {
        points = 0;
        isFriend = false;
        napoleon = false;
    }

    public ArrayList<Trick> move() {
        //either put in an ai here or ask for moves from console or something
        //then divide into different Hands (cuz of that annoying "can play multiple hands sort of" rule thing
        return new ArrayList<Trick>();
    }
}

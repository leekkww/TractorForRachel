/**
 * Created by Jolee on 7/24/2014.
 */

public class Player {

    private Hand hand = new Hand();
    private int points = 0;
    private int level = 2;
    private boolean isFriend; //friend of the napolean, that is
    private boolean napoleon;
    private Player nextPlayer;
    
    //random pointless shit
    public String name;
    public String gender;
    public int age;

    //the optimized player
    public Player() {
        name = "Rachel Zhang";
        gender = "Male";
        age = 16;
    }

    public Player(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void addPoints(int points) {this.points+=points;}
    public int getLevel() {return level;}
    public int getPoints() {return points;}

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
    
    /* //uncomment when ready to actually do this
    public ArrayList<CTrick> move() {
        //either put in an ai here or ask for moves from console or something
        //then divide into different Hands (cuz of that annoying "can play multiple hands sort of" rule thing
    }*/
}

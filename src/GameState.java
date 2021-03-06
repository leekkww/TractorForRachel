/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class GameState {

    private static ArrayList<Trick> played; //stores all the tricks that have been played
    private static Player taking; //who is taking the points? is this necessary?
    private static Card.Suit playedSuit; //what suit was played first

    public static Card.Suit getPlayedSuit() {return playedSuit;}

    public static void play(Trick t) {
        if(played.isEmpty()) playedSuit = t.suit();
        played.add(t);
    }

    public static void take() {
        //this should happen after all the players have gone
        //compare to see who is boss here
        //only consider tricks whose suits are trump or playedsuit
        Trick highest = played.get(0); //this better be the first trick played...
        taking = highest.getPlayer();
        for(Trick t: played) {
            if(t.suit() != Card.trumpSuit() && t.suit() != playedSuit) continue;
            if(t.compareTo(highest) > 0) {
                taking = t.getPlayer();
                highest = t;
            }
        }


        
        int points = 0;
        for(Trick t : played)
        {
            points += t.pointValue();
        }
        taking.addPoints(points);
    }

    //reset after each round
    public static void reset() {
        played.clear();
        taking = null;
        playedSuit = null;
    }

}

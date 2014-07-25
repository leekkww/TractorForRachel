/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class GameState {

    private ArrayList<Trick> played; //stores all the tricks that have been played
    private Player taking; //who is taking the points?
    private Card.Suit playedSuit; //what suit was played first

    public void take() {
        //this should happen after all the players have gone
        //compare to see who is boss here



        //default for now
        taking = played.get(0).getPlayer();

        int points = 0;
        for(Trick t : played)
        {
            points += t.pointValue();
        }
        taking.addPoints(points);
    }

    //reset after each round
    public void reset() {
        played.clear();
        taking = null;
        playedSuit = null;
    }

}

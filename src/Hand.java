/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class Hand {

    private ArrayList<Card> hand;

    /**
     * checks if a play is valid at the start of a round
     */
    public boolean isValidPlay(ArrayList<Card> play)
    {
        if(play.size()==1) {return true;}
        //if played hand is bigger than 1, check that all are of the same suit
        else return false;
    }

    public boolean isValidPlay(ArrayList<Card> play, ArrayList<Card> initPlay, Card.Suit currentSuit)
    {
        for(int i = 0; i < initPlay.size(); ++i)
        {
            //initPlay.get(0).isTrump();
        }
        return false;
    }
}

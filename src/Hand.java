/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class Hand {

    private ArrayList<Card> hand;

    @SuppressWarnings("unchecked")
    public Hand(ArrayList<Card> h) {
        hand = (ArrayList<Card>) h.clone();
    }

    public void add(Card c) {
        hand.add(c);
    }

    /**
     * checks if a play is valid at the start of a round
     */

    //now that Trick is built in, check if it is a valid trick instead
    public boolean isValidPlay(ArrayList<Card> play)
    {
        if(play.size() == 0) {
        	System.out.println("Invalid play: can't play zero cards"); //comment out if too annoying or something
        	return false; //just in case bad things happen
        }
        if(play.size()==1) {return true;}
        //if played hand is bigger than 1, check that all are of the same suit
        Card.Suit suit = play.get(0).suit();
        if(suit == Card.Suit.NONE) suit = Card.trumpSuit(); //joker suit == trump suit
        for(int i = 1; i < play.size(); i++) {
        	if(suit.isTrump() && play.get(i).suit().isTrump()) continue;//trumps are all the same suit
        	if(play.get(i).suit() != suit) return false;
        }
        //is this all? ew are including challenging so yes i think
        return true;
    }

    public boolean isValidPlay(ArrayList<Card> play, ArrayList<Card> initPlay, Card.Suit currentSuit)
    {
        for(int i = 0; i < initPlay.size(); ++i)
        {
            //initPlay.get(0).isTrump();
        }
        return false;
    }

    public boolean hasTractor(int size) {
        //tractor must be same suit and consecutive
        return false;
    }

    public boolean hasPair(Card.Suit suit) {
        //stuff here
        return false;
    }

    public boolean hasLeadingCards() {
        return false;
    }

    public void play(ArrayList<Card> playedCards) {
        for(Card c : playedCards) {
            hand.remove(c);
        }
    }
}

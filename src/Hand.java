/**
 * Created by Jolee on 7/24/2014.
 */
import java.util.*;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() { hand = new ArrayList<Card>();}

    @SuppressWarnings("unchecked")
    public Hand(ArrayList<Card> h) {
        hand = (ArrayList<Card>) h.clone();
    }

    public void add(Card c) {
        hand.add(c);
    }

    public void remove(ArrayList<Card> d) {
        for(Card c : d) {
            hand.remove(c);
        }
    }
    
    /*
     * returns whether the hand contains a certain card
     */
    public boolean contains(Card c) {
        for(Card handCard : hand)
        {
            if(c.equals(handCard)) { return true; }
        }
        return false;
    }
    
    
    /*
     * returns whether the hand contains a pair of this card
     */
    public boolean containsPair(Card c) {
    	int ct = 0;
    	for(Card handCard : hand)
    	{
    		if(c.equals(handCard)) ct ++;
    	}
    	return (ct >= 2);
    }

    public void sort() {
        Collections.sort(hand);
    }

    public Card get(int i) {
        return hand.get(i);
    }

    /**
     * checks if a play is valid at the start of a round
     * now that Trick is built in, check if it is a valid trick instead
     */
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

    public boolean isValidPlay(Trick play, Trick initPlay) //TODO: make this better and finish
    {
        for(int i = 0; i < initPlay.size(); ++i)
        {
            //initPlay.get(0).isTrump();
        }
        return false;
    }

    /**
     * this checks whether or not there is a tractor
     * @param suit  suit of the tractor
     * @param size  size of the tractor
     * @return  a boolean value for whether or not there is a tractor in the hand
     */
    public boolean hasTractor(Card.Suit suit, int size) {
        //maybe just make a trick out of every combination and see if there is a tractor?

        //tractor must be same suit and consecutive
        ArrayList<Card> sameSuit = new ArrayList<Card>();
        for(Card c : hand) {
            if(c.suit() == suit) sameSuit.add(c);
        }
        Collections.sort(sameSuit); //dude i hope we have an ordering
        for(int i = 0; i < sameSuit.size() - size + 1; i++) {
            Trick t = new Trick(new Player(), (ArrayList<Card>) sameSuit.subList(i, i + size));
            if(t.isTractor()) return true;
        }
        return false;
    }

    public boolean hasPair(Card.Suit suit) {
        ArrayList<Card> sameSuit = new ArrayList<Card>();
        for(Card c : hand) {
            if(c.suit() == suit) sameSuit.add(c);
        }
        Collections.sort(sameSuit); //dude i hope we have an ordering
        for(int i = 0; i < sameSuit.size() - 1; i++) {
            if(sameSuit.get(i) == sameSuit.get(i+1)) return true;
        }
        return false;
    }

    public boolean hasLeadingCards() {
        return false;
    }

    /**
     * this turns the hand into a string.
     *
     * @return  String value of the hand
     */
    public String toString() { return hand.toString(); }

    /**
     * gives the size of the hand
     *
     * @return  size of hand as int
     */
    public int size() { return hand.size();}

    /**
     * this gives the point value of the entire hand
     * hmm not sure if this is actually necessary
     *
     * @return  point value of the hand
     */
    public int pointValue(){
        int pts = 0;
        for(Card c : hand) {
            pts += c.pointValue();
        }
        return pts;
    }
}

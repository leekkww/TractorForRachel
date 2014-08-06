/*
 *  Created by Sean on 7/25/2014
 */
import java.util.*;

public class Trick {

	private ArrayList<Card> cards;
	private Player player;

	//Never ever use
	public Trick() {
        cards = new ArrayList<Card>();
        player = null;
    }

	public Trick(Player p, ArrayList<Card> c) {
		cards = (ArrayList<Card>)(c.clone());//c.clone()? is this necessary? (possibly)
		Collections.sort(cards);
		player = p;
	}
	
	public int size() {
		return cards.size();
	}
	
	public Card get(int i) {
		return cards.get(i);
	}

    /**
     * yo this is hard
     * this is for seeing if we have friend card(s) in the given trick
     * @param c the card to compare
     * @return the number of cards in the trick that are equal to c
     */
    public int getCardCount(Card c) {
        int count = 0;
        for(Card c2 : cards) {
            if(c.equals(c2)) count++;
        }
        return count;
    }

	public Player getPlayer() {
		return player;
	}

    public int pointValue(){
        int pts = 0;
        for(Card c : cards) {
            pts += c.pointValue();
        }
        return pts;
    }
	
	/**
	 *	If the trick is made of different suits this will return NONE
	 *	otherwise it will return the appropriate suit (one of Clubs, Diamonds, Hearts, Spades)
	 */
	public Card.Suit suit() {
		Card.Suit s = cards.get(0).effectiveSuit();
		for(int i = 1; i < cards.size(); i++) {
			if(s.isTrump() != cards.get(i).isTrump()) return Card.Suit.NONE;
			else if(!s.isTrump() && s != cards.get(i).effectiveSuit()) return Card.Suit.NONE;
		}
		return s;
	}
	
	public int compareTo(Trick t) {
		if(size() != t.size()) return 0; //can't compare these things
		
		if(suit() == Card.trumpSuit() && t.suit() != Card.trumpSuit()) return 1;
		if(suit() != Card.trumpSuit() && t.suit() == Card.trumpSuit()) return -1;
		
		if(suit() == Card.Suit.NONE && t.suit() != Card.Suit.NONE) return -1;
		if(suit() != Card.Suit.NONE && t.suit() == Card.Suit.NONE) return 1;
		
		//add stuff depending on contents of cards,
		//by now t has cards of one suit, same with this
        //also both are either trump or not trump

		return 0;
	}

    /**
     * @return
     */
    public String toString() {
        return cards.toString();
    }

    /**
     * @return  whether or not the trick is a pair
     */
	public boolean isPair() {
		return size() == 2 && cards.get(0).equals(cards.get(1));
	}

    /**
     * @return  whether or not the trick is a tractor
     */
	public boolean isTractor() {
		if(size() % 2 != 0 || size() == 2) return false;
		for(int i = 0; i < size() / 2; i++) {
			if(!cards.get(2*i).equals(cards.get(2*i+1))) return false;
		}
		//check if they're in order
		for(int i = 1; i < size()-2; i += 2) {
			if(!cards.get(i).isNextTo(cards.get(i+1))) return false;
		}
		return true;
	}

    /**
     * for now this method accepts anything that is of the same suit
     * so like if it can be challenged that is okay too
     * also if you can think of a shorter method name that would be great
     *
     * @return  whether or not it is an acceptable leading trick
     */
    public boolean isAcceptableLeadingTrick() {
        if(suit() == Card.Suit.NONE)
        {
            for(int i = 0; i<size(); ++i) {
                if (!cards.get(i).isTrump()) {return false;}
            }
        }
        return true;
    }
}
/*
 *  Created by Sean on 7/25/2014
 */
import java.util.*;

public class Trick {
	
	private ArrayList<Card> cards;
	
	public int size() {
		return cards.size();
	}
	
	public Card get(int i) {
		return cards.get(i);
	}
	
	/*
	 *	If the trick is made of different suits this will return NONE
	 *	otherwise it will return the appropriate suit (one of Clubs, Diamonds, Hearts, Spades)
	 */
	public Card.Suit suit() {
		Card.Suit s = cards.get(0).suit();
		if(s == Card.Suit.NONE) s = Card.trumpSuit();
		for(int i = 1; i < cards.size(); i++) {
			if(s.isTrump() != cards.get(i).isTrump()) return Card.Suit.NONE;
			else if(!s.isTrump() && s != cards.get(i).suit()) return Card.Suit.NONE;
		}
		return s;
	}
}

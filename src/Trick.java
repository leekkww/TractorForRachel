/*
 *  Created by Sean on 7/25/2014
 */
import java.util.*;

public class Trick {
	
	private ArrayList<Card> cards;
	private Player player;
	
	//Never ever use
	public Trick() {
		cards = new ArrayList<Card> ();
	}
	
	public Trick(Player p, ArrayList<Card> c) {
		cards = c.clone();//c.clone()? is this necessary? (possibly)
		Collections.sort(cards);
		player = p;
	}
	
	public int size() {
		return cards.size();
	}
	
	public Card get(int i) {
		return cards.get(i);
	}
	
	public Player getPlayer() {
		return player;
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
	
	public int compareTo(Trick t) {
		if(size() != t.size()) return 0; //can't compare these things
		
		if(suit() == Card.trumpSuit() && t.suit() != Card.trumpSuit()) return 1;
		if(suit() != Card.trumpSuit() && t.suit() == Card.trumpSuit()) return -1;
		
		if(suit() == Card.Suit.NONE && t.suit() != Card.Suit.NONE) return -1;
		if(suit() != Card.Suit.NONE && t.suit() == Card.Suit.NONE) return 1;
		
		//add stuff depending on contents of cards
	}
	
	public boolean isPair() {
		return size() == 2 && cards.get(0).equals(cards.get(1));
	}
}

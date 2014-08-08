/*
 *  Created by Sean on 7/25/2014
 */
import java.util.*;
import java.lang.*;

public class Trick {

	private ArrayList<Card> cards;
	private Player player;

	//Never ever use
	public Trick() {
        cards = new ArrayList<Card>();
        player = null;
    }

    public Trick(Player p, Card c) {
        cards = new ArrayList<Card>();
        cards.add(c);
        player = p;
    }

	public Trick(Player p, ArrayList<Card> c) {
        //notice that this constructor does not check whether or not it is a valid trick
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
	
	/**
	 * Tractor is literally only there so I can do compareTo somewhat nicely (keyword somewhat)
	 * 
	 */
	 
	private class Tractor implements Comparable {
		public Card firstCard;
		public int length;
		
		public Tractor(Card c, int l) {
			firstCard = c;
			length = l;
		}
		
		/**
		 * returns negative if t is smaller so that sorting makes larger tractors go first (since im lazy af)
		 * sorry --bro it's k
		 */
		public int compareTo(Object o) {
            Tractor t = (Tractor)o;
			if(length != t.length) return -length + t.length;
			return -firstCard.compareTo(t.firstCard);
		}
	}


    /**
     *
     *
     * @param s the suit in question
     * @return whether the suit is a suit that has the possibility of taking
     */
    private boolean canTake(Card.Suit s) {
        return s == Card.trumpSuit() || s == GameState.getPlayedSuit();
    }
	
	/**
	 * @return an integer depending on whether t is bigger, smaller, or "equal/incomparable" to this
	 * 
	 * Note that if two tricks are both nontrump but different suit they can't be compared.
	 * The suit difference needs to be handled elsewhere where compareTo was called from.
	 */
	public int compareTo(Trick t) {
		if(size() != t.size()) return 0; //can't compare these things

        //BAD
		//if(suit() == Card.trumpSuit() && t.suit() != Card.trumpSuit()) return 1;
		//if(suit() != Card.trumpSuit() && t.suit() == Card.trumpSuit()) return -1;

		if(suit() == Card.Suit.NONE && t.suit() != Card.Suit.NONE) return -1;
		if(suit() != Card.Suit.NONE && t.suit() == Card.Suit.NONE) return 1;

        if(canTake(suit()) && !canTake(t.suit())) return 1;
        if(!canTake(suit()) && canTake(t.suit())) return -1;
        if(!canTake(suit()) && !canTake(t.suit())) return 0;
		//also screw compartmentalization and stuff imma stuff everything into compareTo
		
		//add stuff depending on contents of cards,
		//by now t has cards of one suit, same with this
		//also both are either trumpsuit or original played suit

		//break each trick into tractors, pairs, singles
		//imma modify the cards for convenience
		ArrayList<Card> ownCards = (ArrayList<Card>) cards.clone();
		ArrayList<Card> tCards = (ArrayList<Card>) t.cards.clone();
		
		ArrayList<Card> ownPairs = new ArrayList<Card> ();
		ArrayList<Card> tPairs = new ArrayList<Card> ();
		
		for(int i = 0; i < ownCards.size(); i++) {
			if(i < ownCards.size() - 1 && ownCards.get(i) == ownCards.get(i+1)) {
				ownPairs.add(ownCards.get(i));
				ownCards.remove(i);
				ownCards.remove(i);
				i--;
			}
		}
		for(int i = 0; i < tCards.size(); i++) {
			if(i < tCards.size() - 1 && tCards.get(i) == tCards.get(i+1)) {
				tPairs.add(tCards.get(i));
				tCards.remove(i);
				tCards.remove(i);
				i--;
			}
		}
		
		//by now tCards, ownCards should only have singles
		
		/* tractors consist of adjacent pairs, if we consider pairs as dumby tractors
		 * then we can store the set of tractors as a set of ordered pairs (first card in tractor, length)
		 * and do a lexicographic ordering on that set where length takes priority
		 * 
		 */
		ArrayList<Tractor> ownTractor = pairToTractor(ownPairs);
		ArrayList<Tractor> tTractor = pairToTractor(tPairs);

        if(suit() == t.suit()) {
            //yo this better work lol
            //not too necessary cuz of challenging
            int numTractors = Math.min(ownTractor.size(), tTractor.size());
            for (int i = 0; i < numTractors; i++) {
                if (ownTractor.get(i).compareTo(tTractor.get(i)) != 0)
                    return -ownTractor.get(i).compareTo(tTractor.get(i)); // compareTo is backwards lol
            }
            if (ownTractor.size() != tTractor.size()) return ownTractor.size() - tTractor.size();

            //compare single cards (there better be equal numbers of them)
            for (int i = ownCards.size() - 1; i >= 0; i--) {
                if (ownCards.get(i).compareTo(tCards.get(i)) != 0) return ownCards.get(i).compareTo(tCards.get(i));
            }
        } else {
            boolean tIsTrump = (t.suit() == Card.trumpSuit());
            if(!tIsTrump) {
                //switch ownTractor and tTractor s.t. tTractor holds the trump tractors, also switch ownCards, tCards
                ArrayList <Tractor> tmp = ownTractor;
                ownTractor = tTractor;
                tTractor = tmp;

                /*
                 * the trump hand wins if and only if it can match all tractors in the non tromp hand
                 * with some combination of tractors/split tractors (wtf)
                 * Then all that matters are the lengths of the tractors
                 */
                int[] ownLengths = new int[20];
                int[] tLengths = new int[20];

                for(int i = 0; i < ownTractor.size(); i ++) {
                    ownLengths[ownTractor.get(i).length] ++;
                }

                for(int i = 0; i < tTractor.size(); i ++) {
                    tLengths[tTractor.get(i).length] ++;
                }

                /*
                 * now we want to test whether it is possible to make a set of ownLengths out of tLengths
                 * uhh this seems hard
                 * i actually have to think darn
                 * you know what a simple recursive brute force will work (problem size = at most 3 tractors lol)
                 */

                //return 1 if t is trump xor trump beats nontrump (the function for this requires work...)
                //else return -1
            }
        }
		return 0;
	}
	
	/**
	 * @return an ArrayList of tractors from the pairs
	 * @param pairs the set of pairs 
	 * This is also just to make the compareTo nicer
	 */
	private ArrayList<Tractor> pairToTractor(ArrayList<Card> pairs) {
		ArrayList<Tractor> tractors = new ArrayList<Tractor> ();
		Collections.sort(pairs); //just in case, theoretically it should be sorted already
		
		while(!pairs.isEmpty()) {
			int length = 1;
			
			//this is supposed to set length = length of the tractor
			while(length < pairs.size() && pairs.get(length-1).isNextTo(pairs.get(length))) length++; //TODO: test this
			tractors.add(new Tractor(pairs.get(0), length));
			//pairs.removeRange(0, length); //yo bro removeRange cannot be used because protected
		}
		
		Collections.sort(tractors);
		return tractors;
	}

    /**
     * @return the cards in the trick
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

/**
 * Created by Jolee on 7/18/2014.
 */
import java.util.*;
 
public class TractorGame {
	
	private GameState gameState;
	private ArrayList<Player> players;
	public ArrayList<Card> deck; //hmm this should have 108 cards
	private Player firstPlayer;
	private Card.Suit trump;
    private Hand baggage;

    public TractorGame(){
        deck = new ArrayList<Card>();
    }

    //dealing or flipping stage

    //napolean decides on baggage


    public ArrayList<Card> shuffledDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        for(Card.Value v : Card.normalValues())
        {
            for(Card.Suit s : Card.normalSuits())
            {
                deck.add(new Card(v,s));
                deck.add(new Card(v,s));
            }
        }
        deck.add(new Card(Card.Value.HIGHJOKER,Card.Suit.NONE));
        deck.add(new Card(Card.Value.HIGHJOKER,Card.Suit.NONE));
        deck.add(new Card(Card.Value.LOWJOKER,Card.Suit.NONE));
        deck.add(new Card(Card.Value.LOWJOKER,Card.Suit.NONE));
        Collections.shuffle(deck);
        return deck;
    }

    public static void main(String[] args) {
        //test for deck
        TractorGame tg = new TractorGame();
        tg.deck = tg.shuffledDeck();
        for(Card c : tg.deck)
        {
            System.out.println(c.toString());
        }
        System.out.println(tg.deck.size());
    }
}

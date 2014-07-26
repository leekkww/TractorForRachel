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
        TractorGame tg = new TractorGame();
        tg.deck = tg.shuffledDeck();
        Card.setTrump(Card.Suit.CLUBS);
        Card.setTrump(Card.Value.ACE);
        Player rachel = new Player();
        for(int k = 0; k < 108; k ++)
            for(int l = k+1; l <108;l++)
        for(int i = l+1; i < 108; i++) {
            for(int j = i+1; j < 108; j++) {
                ArrayList<Card> a = new ArrayList<Card>();
                a.add(tg.deck.get(i));
                a.add(tg.deck.get(j));
                a.add(tg.deck.get(k));
                a.add(tg.deck.get(l));
                Trick t = new Trick(rachel, a);
                if(t.isTractor()) System.out.println(t.toString() + " is a tractor");
            }
        }
    }
}

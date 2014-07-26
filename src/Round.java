/**
 * Created by Jolee on 7/18/2014.
 */
import java.util.*;
 
public class Round {
	
	private GameState gameState;
	private ArrayList<Player> players;
	public ArrayList<Card> deck; //hmm this should have 108 cards
	private Player firstPlayer;
	private Card.Suit trump;
    private Hand baggage;

    public Round(){
        deck = shuffledDeck();
    }

    //move to Game class
    public void initialize() {
        //ask for names and stuff, but for now everyone is optimized
        //or like load saved game with previously established settings
        players = new ArrayList<Player>();
        for(int i = 0; i<5; ++i) {
            players.add(new Player());
        }
        for(int i = 0; i<5; ++i) {
            players.get(i).setNextPlayer(players.get((i+1)%5));
        }
    }

    //dealing or flipping stage

    //napolean decides on baggage

    public void deal() {
        //
    }

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
        Round tg = new Round();
        tg.deck = tg.shuffledDeck();
        Card.setTrump(Card.Suit.CLUBS);
        Card.setTrump(Card.Value.TWO);
        Player rachel = new Player();
        for(int o = 0; o < 108; o ++)
            for(int p = o+1; p < 108; p ++)
        for(int k = p+1; k < 108; k ++)
            for(int l = k+1; l <108;l++)
        //for(int i = l+1; i < 108; i++)
        //    for(int j = i+1; j < 108; j++)
            {
                ArrayList<Card> a = new ArrayList<Card>();
                //a.add(tg.deck.get(i));
                //a.add(tg.deck.get(j));
                a.add(tg.deck.get(k));
                a.add(tg.deck.get(l));
                a.add(tg.deck.get(o));
                a.add(tg.deck.get(p));
                Trick t = new Trick(rachel, a);
                if(t.isTractor()) System.out.println(t.toString() + " is a tractor");
            }

    }
}

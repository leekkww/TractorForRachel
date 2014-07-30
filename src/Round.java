/**
 * Created by Jolee on 7/18/2014.
 */
import java.util.*;
 
public class Round {
	
	private GameState gameState;
    public ArrayList<Card> deck; //hmm this should have 108 cards
    private ArrayList<Player> players;
    private Player firstPlayer;
    private Card.Suit trump;
    private ArrayList<Card> baggage = new ArrayList<Card>();
    private boolean flipped;

    //default round with 4 players probably
    public Round(){
        deck = shuffledDeck();
    }

    public Round(ArrayList<Player> p) {
        players = p;
    }

    //dealing or flipping stage

    //napolean decides on baggage

    public void setup() {
        deck = shuffledDeck();
        //leave baggage here
        for(int i = 0; i<8; ++i)
        {
            baggage.add(deck.remove(0));
        }
        System.out.println(baggage.toString());

        //hmm so I'm thinking you can just deal all the cards at once and then


    }

    public void flip() {
        //in which you flip things
    }

    //will have to vary for different number of players
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

    //testing
    public static void main(String[] args) {
        Round tg = new Round();
        tg.deck = tg.shuffledDeck();
        Card.setTrump(Card.Suit.CLUBS);
        Card.setTrump(Card.Value.TWO);
        /*Player rachel = new Player();
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
            */
        tg.setup();

    }
}

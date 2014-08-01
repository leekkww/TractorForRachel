/**
 * Created by Jolee on 7/18/2014.
 */
import java.util.*;
 
public class Round {

    public enum Stage {
        DRAW, FLIP, CALL_FRIEND, PLAY, //dude these names suck
    }

	private GameState gameState = null;
    public ArrayList<Card> deck; //hmm this should have 108 cards
    private ArrayList<Player> players;
    private Player firstPlayer; //player who plays first
    private Card.Suit trump = Card.Suit.NONE;
    private ArrayList<Card> baggage = new ArrayList<Card>();
    private boolean flipped = false; //unnecessary?
    private Trick currentFlip = null;

    private Card friendCard;
    private boolean setFriendSoon;//halp name this better, also this sees if the next friend card played actually is friend card

    //default round with 4 players probably
    public Round(){
        deck = shuffledDeck();
    }

    public Round(ArrayList<Player> p) {
        players = p;
    }

    //dealing or flipping stage

    //napoleon decides on baggage

    /**
     * yo what does setup do???
     */
    @SuppressWarnings("unchecked")
    public void setup() {
        deck = shuffledDeck();

        //the following lines of code may cause things to break when flipping things
        Card.setTrump(Card.Suit.NONE);
        Card.setTrump((Card.Value) null); //this is probably really really bad

        //leave baggage here
        for(int i = 0; i<8; ++i)
        {
            baggage.add(deck.remove(0));
        }
        System.out.println(baggage.toString());


    }

    public void setFirstPlayer(Player p) {firstPlayer = p;}

    /**
     * yo the method parameters need to contain this sort of information
     * yo this method probably is really buggy
     *
     * @param p The player that flipped
     * @param h The trick used to flip
     */
    public void flip(Player p, Trick h) {
        //in which you flip things
        if(h.size() != 1 && !h.isPair()) return; //can't flip dumby tricks
        if(currentFlip == null || currentFlip.size() == 1 && h.size() == 2 || h.compareTo(currentFlip) > 0) { //this may fail joker flips
            //firstPlayer = p;
            currentFlip = h;
            trump = h.suit();
        }
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


}

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jolee on 7/24/2014.
 */
public class Card implements Comparable {
    
    private static Suit trumpSuit;
    private static Value trumpValue;
    
    private Value value;
    private Suit suit;

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, LOWJOKER, HIGHJOKER;
        
        //what would be the value of the next highest card if no trumpvalues
        public Value inc() {
            switch(this) {
                case TWO: return THREE;
                case THREE: return FOUR;
                case FOUR: return FIVE;
                case FIVE: return SIX;
                case SIX: return SEVEN;
                case SEVEN: return EIGHT;
                case EIGHT: return NINE;
                case NINE: return TEN;
                case TEN: return JACK;
                case JACK: return QUEEN;
                case QUEEN: return KING;
                case KING: return ACE;
                case ACE: return LOWJOKER; //this is bad
                case LOWJOKER: return HIGHJOKER;
                case HIGHJOKER: return HIGHJOKER;
                default: return HIGHJOKER;
            }
        }
        
        public String toString() {
            return this.name();
        }
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES, NONE;
        
        public boolean isTrump() {
            return this == Card.trumpSuit() || this == NONE; //syntax is hard
        }
        
        public String toString() {
            return this.name();
        }
    }
    
    //Never ever use this
    public Card() {
        value = Value.HIGHJOKER;
        suit = Suit.NONE;
    }
    
    //Always use this
    public Card(Value v, Suit s) {
        value = v;
        suit = s;
    }
    
    public static void setTrump(Suit s) {trumpSuit = s;}
    public static void setTrump(Value v) {trumpValue = v;}
    
    public static Suit trumpSuit() {return trumpSuit;}
    public static Value trumpValue() {return trumpValue;}
    
    public Suit suit() {return suit;}
    public Value value() {return value;}
    
    //basically if it's a joker, trumpnumber, etc it is the same as the trumpsuit
    public Suit effectiveSuit() {
        if(value() == trumpValue() || suit() == trumpSuit() || suit() == Suit.NONE) return trumpSuit;
        else return suit();
    }
    
    public boolean isTrump() {return suit == trumpSuit || value == trumpValue || value == Value.LOWJOKER || value == Value.HIGHJOKER;}
    public int pointValue() {
        switch(value) {
            case FIVE: return 5;
            case TEN: case KING: return 10;
            default: return 0;
        }
    }
    
    public boolean equals(Card c) {
        return value == c.value && suit == c.suit;
    }
    
    //returns what card is higher, e.g. which is going to win in a head to head
    //compareTo == 0 does not mean the cards are equal
    public int compareTo(Object o) {
        Card c = (Card)o;
        int selfIndex = valueToInt(value) + suitToInt(suit);
        int cIndex = valueToInt(c.value) + suitToInt(c.suit);
        return selfIndex - cIndex;
    }
    
    public String toString() {
        if(value == Value.HIGHJOKER || value == Value.LOWJOKER) return value.toString();
        return value.toString() + " of " + suit.toString();
    }

    /*
    *   valueToInt and suitToInt are the hackiest things ever pls help
    */
    private int valueToInt(Value v) {
        if(v == trumpValue) return 500;
        switch(v) {
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 11;
            case QUEEN: return 12;
            case KING: return 13;
            case ACE: return 14;
            case LOWJOKER: return 1000;
            case HIGHJOKER: return 2000;
            default: return 0;
        }
    }
    
    private int suitToInt(Suit s) {
        if(s == trumpSuit) return 100;
        return 0;
    }
    
    public static Suit[] normalSuits() {
        Suit[] suits = new Suit[4];
        suits[0] = Suit.CLUBS;
        suits[1] = Suit.DIAMONDS;
        suits[2] = Suit.HEARTS;
        suits[3] = Suit.SPADES;
        return suits;
    }
    
    public static Value[] normalValues() {
        Value[] value = new Value[13];
        value[0] = Value.TWO;
        value[1] = Value.THREE;
        value[2] = Value.FOUR;
        value[3] = Value.FIVE;
        value[4] = Value.SIX;
        value[5] = Value.SEVEN;
        value[6] = Value.EIGHT;
        value[7] = Value.NINE;
        value[8] = Value.TEN;
        value[9] = Value.JACK;
        value[10] = Value.QUEEN;
        value[11] = Value.KING;
        value[12] = Value.ACE;
        return value;
    }

    //will have to vary for different number of players
    public static ArrayList<Card> shuffledDeck() {
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
    
    /**
     *  returns if one card would be after another in a tractor
     *  pls test this
     *  @param c  The card to be right after this
     */
    public boolean isNextTo(Card c) {
        //handle jokers and cards with trump value
        if(this.value == Value.HIGHJOKER) return false;
        if(this.value == Value.LOWJOKER) return c.value() == Value.HIGHJOKER;
        if(this.value == trumpValue && this.suit == trumpSuit) return c.value() == Value.LOWJOKER;
        if(this.value == trumpValue && this.suit != trumpSuit) return c.value() == trumpValue && c.suit() == trumpSuit;
        if(this.isTrump()) {
            //eww we need to do annoying stuff
            if(this.value == Value.ACE) { //guaranteed not to be a trumpvalue else already handled above
                return c.value == trumpValue && c.suit != trumpSuit;
            }
            if(this.value == Value.KING) {
                if(Value.ACE == trumpValue) return c.value == trumpValue && c.suit != trumpSuit;
                else return c.value == Value.ACE && c.suit == this.suit;
            }
        } else {
            //more annoying stuff
            if(this.value == Value.ACE) return false;
            if(this.value == Value.KING && trumpValue == Value.ACE) return false;
        }
        if(this.value.inc() == trumpValue) return c.value == trumpValue.inc() && c.suit == suit;
        return c.value == value.inc() && c.suit == suit;
    }
}

/**
 * Created by Jolee on 7/24/2014.
 */
public class Card {
    
    private static Suit trumpSuit;
    private static Value trumpValue;
    
    private Value value;
    private Suit suit;

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, LOWJOKER, HIGHJOKER
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES, NONE
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
    public int compareTo(Card c) {
        int selfIndex = valueToInt(value) + suitToInt(suit);
        int cIndex = valueToInt(c.value) + suitToInt(c.suit);
        return selfIndex - cIndex;
    }
    /*
    *   valueToInt and suitToInt are the hackiest things ever pls help
    */
    private int valueToInt(Value v) {
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
    
    public Suit[] normalSuits() {
        Suit[] suits = new Suit[4];
        suits[0] = Suit.CLUBS;
        suits[1] = Suit.DIAMONDS;
        suits[2] = Suit.HEARTS;
        suits[3] = Suit.SPADES;
        return suits;
    }
    
    public Value[] normalValues() {
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
}

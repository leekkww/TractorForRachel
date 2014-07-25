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
    public Vaue value() {return value;}
    
    public boolean isTrump() {return suit == trumpSuit || value == trumpValue || value == LOWJOKER || value == HIGHJOKER;}
    public int pointValue() {
        switch(value) {
            case FIVE: return 5;
            case TEN: case KING: return 10;
            default: return 0;
        }
    }
    
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
            case Value.TWO: return 2;
            case Value.THREE: return 3;
            case Value.FOUR: return 4;
            case Value.FIVE: return 5;
            case Value.SIX: return 6;
            case Value.SEVEN: return 7;
            case Value.EIGHT: return 8;
            case Value.NINE: return 9;
            case Value.TEN: return 10;
            case Value.JACK: return 11;
            case Value.QUEEN: return 12;
            case Value.KING: return 13;
            case Value.ACE: return 14;
            case Value.LOWJOKER: return 1000;
            case Value.HIGHJOKER: return 2000;
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
        Value[] vals = new Value[13];
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
    }
}

import java.util.*;

/**
 * Created by Jolee on 7/30/2014.
 */
public class Testing {
    //testing

    /**
     * Pause increment between dealing cards, in miliseconds.
     */
    public static final int PAUSE_INCREMENT = 10; //hmm I think 200 is a pretty good speed, takes 20~25 seconds to deal all

    public static void main(String[] args) {
        
        final Round round = new Round();
        round.setup();
        Card.setTrump(Card.Suit.CLUBS);
        Card.setTrump(Card.Value.TWO);


        final ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i < 5; i++) players.add(new Player("Rachel Zhang" + i,2));
        for (int i = 0; i < 4; i++) players.get(i).setNextPlayer(players.get((i + 1) % 4));
        round.setFirstPlayer(players.get(0));

        //this is how you deal
        Timer dealer = new Timer("the one who deals");
        for (int i = 0; i < 100; i++) {
            //dealer.schedule(new DealACard(players.get(i % 4), round.deck.get(i)), i * PAUSE_INCREMENT + 5); //dude offset adding stuff and reading stuff so no concurrentmodificationexception
        	dealer.schedule(new DealACard(players.get(i % 4), round.deck.get(i), round),  i * PAUSE_INCREMENT + 5);
        }
        dealer.schedule(new DealACard(dealer), PAUSE_INCREMENT * 100); //this ends the dealing in a jank way

        //what does this part do?
        /*
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(int i = 0; i < 4; ++i)
                {
                    Hand h = players.get(i).getHand();
                    if(h.get(h.size()-1).value() == Card.Value.TWO)
                    {
                        //bigger method for flip  ----flip(new Trick(players.get(i),h.get(h.size()-1)));
                    	if(round.flip(new Trick(players.get(i), h.get(h.size()-1)))) {
                    		//flip the last card you get
                    		System.out.println("\n\n\n\n\n\n\nFlip successful: " + h.get(h.size()-1));
                    	}
                    }
                }

            }
        }, 2*60*1000, PAUSE_INCREMENT);
        */
        
        //everything here is a different thread
        //ask the console for user input as they see their things
        try {
            Thread.sleep(2010);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("yo patrick halp how does this work");
        
        //dealer.cancel();
        
        //testing compareTo for different tricks, note that trump = 2, clubs
        
        Card.setTrump(Card.Suit.CLUBS);
        Card.setTrump(Card.Value.TWO);
        int a;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        while(a != 0)
        {
	        ArrayList<Card> cardList1 = new ArrayList<Card> (), cardList2 = new ArrayList<Card> ();
	        for(int i = 0; i < a; i ++) {
	        	String nextCard = sc.next();
	        	cardList1.add(stringToCard(nextCard));
	        }
	        for(int i = 0; i < a; i ++) {
	        	String nextCard = sc.next();
	        	cardList2.add(stringToCard(nextCard));
	        }
	        
	        Collections.sort(cardList1);
	        Collections.sort(cardList2);
	        
	        Trick trick1 = new Trick(players.get(0), cardList1);
	        Trick trick2 = new Trick(players.get(1), cardList2);
	        System.out.println(trick1 + " " + trick1.suit());
	        System.out.println(trick2 + " " + trick2.suit());
	        System.out.println("trick1.compareTo(trick2): " + trick1.compareTo(trick2));
	        //trick1 beats trick2 iff compareTo > 0
	        
	        a = sc.nextInt();
        }
        sc.close();
    	/* for testing in Trick, compareTo, the comparing two sets of tractor things
        
        Scanner sc = new Scanner(System.in);
        while(true) {
            int m,n; int[] a, b;
            m = sc.nextInt();
            a = new int[m];
            for(int i = 0; i < m; i ++) a[i] = sc.nextInt();
            n = sc.nextInt();
            b = new int[n];
            for(int i = 0 ; i < n; i ++) b[i] = sc.nextInt();
            System.out.println(blah(a,b));
        }*/
    }

    //testing for (in Trick, compareTo) the comparing two sets of tractor things
    public static boolean blah(int[] a, int[] b) {
        int ct = -1;
        for(int i = b.length - 1; i > 0; i--) {
            if(b[i] > 0) {
                ct = i;
                b[i]--;
                break;
            }
        }
        if(ct == -1) return true;
        //boolean ret = false;
        for(int i = ct; i < a.length; i ++) {
            if(a[i] != 0) {
                a[i]--;
                if(i != ct) a[i-ct]++;
                if(blah(a,b)) {
                    return true;
                }
                a[i]++;
                if(i != ct) a[i-ct]--;
            }
        }
        b[ct]++;
        return false;
    }
    
    /*
     * returns a card given a string of the following format
     * Non-jokers have two parts, a letter/number representing rank/value, and a letter representing suit
     * (e.g. 2C is two of clubs)
     * Jokers are either LJ or HJ for low joker or high joker
     */
    public static Card stringToCard(String s) {
    	if(s.equals("LJ") || s.equals("lj")) return new Card(Card.Value.LOWJOKER, Card.Suit.NONE);
    	if(s.equals("HJ") || s.equals("hj")) return new Card(Card.Value.HIGHJOKER, Card.Suit.NONE);
    	//do other stuff
    	Card.Value val;
    	switch(s.charAt(0)) {
    	case '2': val = Card.Value.TWO; break;
    	case '3': val = Card.Value.THREE; break;
    	case '4': val = Card.Value.FOUR; break;
    	case '5': val = Card.Value.FIVE; break;
    	case '6': val = Card.Value.SIX; break;
    	case '7': val = Card.Value.SEVEN; break;
    	case '8': val = Card.Value.EIGHT; break;
    	case '9': val = Card.Value.NINE; break;
    	case 'J': case 'j': val = Card.Value.JACK; break;
    	case 'Q': case 'q': val = Card.Value.QUEEN; break;
    	case 'K': case 'k': val = Card.Value.KING; break;
    	case 'A': case 'a': val = Card.Value.ACE; break;
    	default: val = Card.Value.LOWJOKER; //if this happens bad things happen
    	}
    	Card.Suit suit;
    	switch(s.charAt(1)) {
    	case 'C': case 'c': suit = Card.Suit.CLUBS; break;
    	case 'D': case 'd': suit = Card.Suit.DIAMONDS; break;
    	case 'H': case 'h': suit = Card.Suit.HEARTS; break;
    	case 'S': case 's': suit = Card.Suit.SPADES; break;
    	default: suit = Card.Suit.NONE;
    	}
    	return new Card(val, suit);
    }
}

class DealACard extends TimerTask {
    Card c;
    Player p;
    boolean cancel;
    Timer t;
    
    Round r; //testing the dealing

    /*
     * 
     * if we use this constructor we are canceling the timer
     */
    public DealACard(Timer timer) {
        cancel = true;
        t = timer;
        c = null;
        p = null;
    }

    /*
     * if we use this constructor we are dealing a card
     */
    public DealACard(Player p, Card c) {
        this.p = p;
        this.c = c;
        cancel = false;
    }
    /*
     * constructor if we want round to be used (debug purposes only)
     */
    public DealACard(Player p, Card c, Round r) {
    	this.p = p;
    	this.c = c;
    	cancel = false;
    	this.r = r;
    }

    public void run() {
        if(cancel) {
            t.cancel();
            return;
        }
        p.addCard(c);
        //System.out.println(p.name + ": " + p.getHand() +p.getHand().size()+ "\n"); //removed for less clutter
        
        //testing flipping, REMOVE FOR FINAL
        
        //flip two cards
        if(p.getHand().containsPair(c)) {
        	ArrayList<Card> toFlip = new ArrayList<Card> ();
        	toFlip.add(c);
        	toFlip.add(c);
        	if(r.flip(new Trick(p, toFlip))) {
        		System.out.println("Flip successful: " + toFlip + " " + p.name);
        	} else {
        		//System.out.println("   Flip unsuccessful: " + toFlip + " " + p.name);
        	}
        }
        
        //flip one card
        if(r.flip(new Trick(p, c))) {
        	System.out.println("Flip successful: " + c + " " + p.name);
        } else {
    		//System.out.println("   Flip unsuccessful: " + c + " " + p.name);
    	}
    }

}


//tractor testing here
        /*Player rachel = new Player();
        for(int o = 0; o < 108; o ++)
            for(int p = o+1; p < 108; p ++)
        for(int k = p+1; k < 108; k ++)
            for(int l = k+1; l <108;l++)
        //for(int i = l+1; i < 108; i++)
        //    for(int j = i+1; j < 108; j++)
            {
                ArrayList<Card> a = new ArrayList<Card>();
                //a.add(round.deck.get(i));
                //a.add(round.deck.get(j));
                a.add(round.deck.get(k));
                a.add(round.deck.get(l));
                a.add(round.deck.get(o));
                a.add(round.deck.get(p));
                Trick t = new Trick(rachel, a);
                if(t.isTractor()) System.out.println(t.toString() + " is a tractor");
            }
            */
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
        Round tg = new Round();
        tg.deck = Card.shuffledDeck();
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
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) players.add(new Player());
        for (int i = 0; i < 4; i++) players.get(i).setNextPlayer(players.get((i + 1) % 4));
        tg.setFirstPlayer(players.get(0));
        players.get(0).name = "Rachel Zhang";
        players.get(1).name = "Rachel Zhang 2";
        players.get(2).name = "Rachel Zhang 3";
        players.get(3).name = "Rachel Zhang 4";

        //this is how you deal
        Timer dealer = new Timer("the one who deals");
        for (int i = 0; i < 100; i++) {
            dealer.schedule(new DealACard(players.get(i % 4), tg.deck.get(i)), i * PAUSE_INCREMENT + 5); //dude offset adding stuff and reading stuff so no concurrentmodificationexception
        }
        dealer.schedule(new DealACard(dealer), PAUSE_INCREMENT * 100);

        //everything here is a different thread
        //ask the console for user input as they see their things
        try {
            Thread.sleep(010);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("yo patrick");
        //dealer.cancel();

    }
}
    class DealACard extends TimerTask {
        Card c;
        Player p;
        boolean cancel;
        Timer t;

        public DealACard(Timer timer) {
            cancel = true;
            t = timer;
            c = null;
            p = null;
        }

        public DealACard(Player p, Card c) {
            this.p = p;
            this.c = c;
            cancel = false;
        }

        public void run() {
            if(cancel) {
                t.cancel();
                return;
            }
            p.addCard(c);
            System.out.println(p.name + ": " + p.getHand() +p.getHand().size()+ "\n");
        }

    }

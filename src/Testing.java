import java.util.*;

/**
 * Created by Jolee on 7/30/2014.
 */
public class Testing {
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
            dealer.schedule(new DealACard(players.get(i % 4), tg.deck.get(i)), i * 10 + 5); //dude offset adding stuff and reading stuff so no concurrentmodificationexception
        }
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        dealer.cancel();

    }
}
    class DealACard extends TimerTask {
        Card c;
        Player p;

        public DealACard(Player p, Card c) {
            this.p = p;
            this.c = c;
        }

        public void run() {
            p.addCard(c);
            System.out.println(p.name + ": " + p.getHand() + "\n");
        }

    }

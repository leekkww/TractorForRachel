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
        /*
        Round round = new Round();
        round.setup();
        Card.setTrump(Card.Suit.CLUBS);
        Card.setTrump(Card.Value.TWO);


        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i < 5; i++) players.add(new Player("Rachel Zhang" + i,2));
        for (int i = 0; i < 4; i++) players.get(i).setNextPlayer(players.get((i + 1) % 4));
        round.setFirstPlayer(players.get(0));

        //this is how you deal
        Timer dealer = new Timer("the one who deals");
        for (int i = 0; i < 100; i++) {
            dealer.schedule(new DealACard(players.get(i % 4), round.deck.get(i)), i * PAUSE_INCREMENT + 5); //dude offset adding stuff and reading stuff so no concurrentmodificationexception
        }
        dealer.schedule(new DealACard(dealer), PAUSE_INCREMENT * 100); //this ends the dealing in a jank way

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
                    }
                }

            }
        }, 2*60*1000, PAUSE_INCREMENT);


        //everything here is a different thread
        //ask the console for user input as they see their things
        try {
            Thread.sleep(2010);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("yo patrick");
        //dealer.cancel();
        */
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
        }
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
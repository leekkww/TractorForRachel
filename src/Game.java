/**
 * Created by Jolee on 7/25/2014.
 */
import java.util.*;

public class Game {

    private ArrayList<Player> players;

    public void initialize() {
        //ask for number of players
        //ask for names and stuff, but for now everyone is optimized
        //or maybe load saved game with previously established settings
        players = new ArrayList<Player>();
        for(int i = 0; i<5; ++i) {
            players.add(new Player());
        }
        for(int i = 0; i<5; ++i) {
            players.get(i).setNextPlayer(players.get((i+1)%5));
        }
        Round r = new Round(players);
    }

}

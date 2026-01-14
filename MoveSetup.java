import java.util.ArrayList;
import java.util.HashMap;

public class MoveSetup {

    public static ArrayList<Move> setup() {
        ArrayList<Move> moves = new ArrayList<Move>();
        HashMap<String, Integer> effects;

        //name, category, targeting, energyCost, accuracy, priority, type of move, effects
        //atk, add power, min hits, max hits

        //0001
        moves.add(new Move("Tackle", 1, "1 Opponent", 3, 100, 0, new int[]{1}, null, 40, 1, 1));

        moves.add(new Move("Water Gun", 2, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));

        moves.add(new Move("Ember", 3, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));

        moves.add(new Move("Vine Whip", 4, "1 Opponent", 3, 100, 0, new int[]{6}, null, 40, 1, 1));
        



        return moves;
    }

}


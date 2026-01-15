import java.util.ArrayList;

class Game {
    
    public static void main(String[] args) {

        ArrayList<BaseKreechin> kreechins = KreechinSetup.setup();

        Kreechin a = new Kreechin(kreechins.get(0), 10, false, new int[]{1, 2, 0, 0, 0}, 2);
        Kreechin b = new Kreechin(kreechins.get(3), 10, true, new int[]{1, 3, 0, 0, 0}, 1);
        Kreechin c = new Kreechin(kreechins.get(6), 10, true, new int[]{1, 4, 0, 0, 0}, 4);
        Kreechin d = new Kreechin(kreechins.get(3), 10, false, new int[]{1, 3, 0, 0, 0}, 3);

        Team team1 = new Team(new Kreechin[]{a, b});
        Team team2 = new Team(new Kreechin[]{c, d});

        Battle.startBattle(team1, team2, "Enemy");


        //opp stat spreads for initial tutorial battle: [8, 0, 8, -6, 0, 0, 0]
        //after tutorial: [6, 6, 6, 5, 5, 6, 6]

    }
}
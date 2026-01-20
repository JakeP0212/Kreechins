import java.util.ArrayList;

class Game {
    
    public static void main(String[] args) {

        ArrayList<BaseKreechin> kreechins = KreechinSetup.setup();

        Kreechin a = new Kreechin(kreechins.get(1), 29, false, new int[]{59, 0, 0, 0, 0}, 2);
        Kreechin b = new Kreechin(kreechins.get(5), 35, true, new int[]{67, 0, 0, 0, 0}, 1);
        Kreechin c = new Kreechin(kreechins.get(8), 32, true, new int[]{36, 63, 68, 52, 0}, 4);
        Kreechin d = new Kreechin(kreechins.get(2), 31, false, new int[]{34, 59, 22, 66, 49}, 3);

        Team team1 = new Team(new Kreechin[]{c, d});
        Team team2 = new Team(new Kreechin[]{a, b});

        Battle.startBattle(team1, team2, "Enemy");


        //opp stat spreads for initial tutorial battle: [8, 0, 8, -6, 0, 0, 0]
        //after tutorial: [6, 6, 6, 5, 5, 6, 6]

    }
}
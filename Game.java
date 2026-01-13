import java.util.ArrayList;

class Game {
    
    public static void main(String[] args) {

        ArrayList<BaseKreechin> kreechins = KreechinSetup.setup();

        Kreechin a = new Kreechin(kreechins.get(0), 10, false, new int[]{5, 6, 6, 5, 6, 6, 6}, new int[]{1, 2, 88, 0, 0}, 1, false);
        Kreechin b = new Kreechin(kreechins.get(0), 16, true);

    }
}
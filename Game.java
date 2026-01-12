class Game {
    
    public static void main(String[] args) {

        ArrayList<BaseKreechin> kreechins = KreechinSetup.setup();

        System.out.println(kreechins.get(0));

    }
}
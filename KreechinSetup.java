import java.util.ArrayList;

class KreechinSetup {

    public static ArrayList<BaseKreechin> setup() {
        ArrayList<BaseKreechin> kreechins = new ArrayList<BaseKreechin>();

        //Name, Num, Categories, Stats, TransLvl, TransNum, Powers, TeachMoves, LvlMoves

        //{65, 60, 60, 65, 55, 50, 50}
        //{1, 2}
        //[0]
        //[[1, 1,  1], [1, 2, 88]]

        //001
        kreechins.add(new BaseKreechin("Spliash", 1, new int[]{2}, new int[]{60, 60, 60, 65, 55, 50, 50}, 
        18, 2, new int[]{1, 2}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1}, 
                    {1, 2, 88}}));
        
        



        return kreechins;
    }

}
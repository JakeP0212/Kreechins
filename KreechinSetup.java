import java.util.ArrayList;

class KreechinSetup {

    public static ArrayList<BaseKreechin> setup() {
        ArrayList<BaseKreechin> kreechins = new ArrayList<BaseKreechin>();

        //Name, Num, Categories, Stats, TransLvl, TransNum, Powers, TeachMoves, LvlMoves

        //0001
        kreechins.add(new BaseKreechin("Spliash", 1, new int[]{2}, new int[]{39, 52, 48, 56, 50, 46, 44}, 
        18, 2, new int[]{1, 2}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18}}));

        kreechins.add(new BaseKreechin("Bunchy", 4, new int[]{3}, new int[]{40, 49, 51, 54, 51, 44, 46}, 
        18, 5, new int[]{1, 3}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16}, 
                    {1, 3, 88, 96, 91, 51, 120, 17, 19}}));

        kreechins.add(new BaseKreechin("Jumpio", 7, new int[]{4}, new int[]{42, 48, 51, 53, 50, 44, 47}, 
        18, 8, new int[]{1, 4}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12,  13, 16}, 
                    {1, 4, 88, 98, 89, 52, 126, 127, 20}}));
        
        



        return kreechins;
    }

}
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

        kreechins.add(new BaseKreechin("Sillaplash", 2, new int[]{2}, new int[]{49, 75, 63, 71, 67, 60, 55}, 
        30, 3, new int[]{1, 2}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16,  18, 20, 21, 23,  25, 28}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18, 111, 66, 22, 59, 131, 34}}));

        kreechins.add(new BaseKreechin("Navilash", 3, new int[]{2, 11}, new int[]{70, 100, 73, 100, 81, 69, 67}, 
        0, 0, new int[]{1, 2, 5}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16,  18, 20, 21, 23,  25, 28,  30,  33,  35,  38,  42, 46, 49,  53,  55,  59}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18, 111, 66, 22, 59, 131, 34, 104, 151, 123, 153, 111, 33, 41, 114, 152, 175}}));

        kreechins.add(new BaseKreechin("Bunchy", 4, new int[]{3}, new int[]{40, 49, 51, 54, 51, 44, 46}, 
        18, 5, new int[]{1, 3}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16}, 
                    {1, 3, 88, 96, 91, 51, 120, 17, 19}}));

        //0005
        kreechins.add(new BaseKreechin("Rosbit", 5, new int[]{3}, new int[]{57, 66, 59, 77, 68, 55, 58}, 
        30, 6, new int[]{1, 3}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16,  18, 20,  21, 23,  25, 28}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18, 112, 67, 172, 29, 132, 35}}));

        kreechins.add(new BaseKreechin("Hopskaboom", 6, new int[]{3, 13}, new int[]{86, 80, 81, 95, 75, 70, 73}, 
        0, 0, new int[]{1, 3, 6}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16, 18,  20, 21,  23, 25,  28,  30, 33,  35, 38, 42,  46, 49, 53,  55, 59}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18, 112, 67, 172, 29, 132, 35, 105, 86, 171, 45, 33, 168, 85, 87, 145, 77}}));

        kreechins.add(new BaseKreechin("Jumpio", 7, new int[]{4}, new int[]{42, 48, 51, 53, 50, 44, 47}, 
        18, 8, new int[]{1, 4}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12,  13, 16}, 
                    {1, 4, 88, 98, 89, 52, 126, 127, 20}}));

        kreechins.add(new BaseKreechin("Sperkline", 8, new int[]{4}, new int[]{68, 64, 62, 65, 69, 53, 59}, 
        30, 9, new int[]{1, 4}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16, 18, 20,  21, 23,  25, 28}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18, 84, 68, 178, 63, 134, 36}}));

        kreechins.add(new BaseKreechin("Magicat", 9, new int[]{4, 15}, new int[]{100, 73, 93, 70, 84, 63, 77}, 
        0, 0, new int[]{1, 4, 7}, 
        new int[]{0}, 
        new int[][]{{1, 1,  1,  4,  7,  9,  12, 13, 16, 18, 20,  21, 23,  25, 28,  30,  33,  35,  38,  42, 46,  49, 53,  55,  59}, 
                    {1, 2, 88, 95, 90, 50, 119, 49, 18, 84, 68, 178, 63, 134, 36, 106, 144, 158, 118, 115, 33, 146, 80, 182, 159}}));

        //0010
        
        



        return kreechins;
    }

}
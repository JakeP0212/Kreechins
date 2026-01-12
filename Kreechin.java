class BaseKreechin {
    String name;

    int baseHP;
    int baseATK;
    int baseDEF;
    int baseSPD;
    int baseEN;
    int baseFOC;
    int baseLUK;

    int[][] lvlMovepool;
    int[] teachMovepool;

    int transLevel;
    int number;
    int transInto;

    int[] possiblePowers;


    public BaseKreechin(String nm, int num, int[] stats, int transLvl, int transNum, int[] powers, int teachMoves[], int[][] lvlMoves) {
        name = nm;
        number = num;
        transLevel = transLvl;
        transInto = transNum;
        possiblePowers = powers;
        teachMovepool = teachMoves;
        lvlMovepool = lvlMoves;

        baseHP = stats[0];
        baseATK = stats[1];
        baseDEF = stats[2];
        baseSPD = stats[3];
        baseEN = stats[4];
        baseFOC = stats[5];
        baseLUK = stats[6];

        public String toString() {
            
        }
    }
}


class Kreechin extends BaseKreechin {
    int level;
    int[] statDeviations = new int[7];
    int[] moves = new int[4];
    int specialPower;

    int HP;
    int ATK;
    int DEF;
    int SPD;
    int EN;
    int FOC;
    int LUK;

    int status;
}
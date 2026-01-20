class BaseKreechin {
    String name;

    int baseHP;
    int baseATK;
    int baseDEF;
    int baseSPD;
    int baseEN;
    int baseFOC;
    int baseLUK;

    int cat1;
    int cat2;

    int[][] lvlMovepool;
    int[] teachMovepool;

    int transLevel;
    int number;
    int transInto;

    int[] possiblePowers;


    public BaseKreechin(String nm, int num, int[] categories, int[] stats, int transLvl, int transNum, int[] powers, int[] teachMoves, int[][] lvlMoves) {
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

        cat1 = categories[0];
        if (categories.length == 2) {
            cat2 = categories[1];
        } else {
            cat2 = 0;
        }

    }

    public BaseKreechin(BaseKreechin base) {
        this(new String(base.name), base.number, new int[]{base.cat1, base.cat2}, new int[]{base.baseHP, base.baseATK, base.baseDEF, base.baseSPD, base.baseEN, base.baseFOC, base.baseLUK}, 
        base.transLevel, base.transInto, base.possiblePowers, base.teachMovepool, base.lvlMovepool);

    }

    public String toString() {
        String finalString = name + " - " + number + "\n";
        finalString += "[" + baseHP + ", " + baseATK + ", " + baseDEF + ", " + baseSPD + ", " + baseEN + ", " + baseFOC + ", " + baseLUK + "]";
        return finalString;
    }
}


class Kreechin extends BaseKreechin {
    int level;
    boolean isMale;
    int[] statDeviations = new int[7];
    int[] moves = new int[5];
    int specialPower;

    int HP;
    int ATK;
    int DEF;
    int SPD;
    int EN;
    int FOC;
    int LUK;

    int currHP;
    int currEN;
    int status;

    public Kreechin(BaseKreechin base, int lvl, boolean gender) {
        super(base);
        level = lvl;
        isMale = gender;

        this.randomizeStats();
        this.randomizeMoves();
        specialPower = possiblePowers[(int)(Math.random() * possiblePowers.length)];

        this.fullHeal();
    }

    public Kreechin(BaseKreechin base, int lvl, boolean gender, int[] move, int specialPow) {
        super(base);
        level = lvl;
        isMale = gender;
        moves = move;
        specialPower = specialPow;
        this.randomizeStats();

        this.fullHeal();
    }

    public Kreechin(BaseKreechin base, int lvl, boolean gender, int[] move, int specialPow, int[] statDevs) {
        super(base);
        level = lvl;
        isMale = gender;
        statDeviations = statDevs;
        moves = move;
        specialPower = specialPow;

        this.fullHeal();
    }

    public void randomizeStats() {
        if (isMale) {
            statDeviations[1] += 5;
            statDeviations[3] += 5;
            statDeviations[0] -= 5;
            statDeviations[2] -= 5;
        } else {
            statDeviations[0] += 5;
            statDeviations[2] += 5;
            statDeviations[1] -= 5;
            statDeviations[3] -= 5;
        }
        statDeviations[(int) (Math.random() * 3) + 4] += 5;
        statDeviations[(int) (Math.random() * 3) + 4] -= 5;
        for (int i = -4; i < 5; i++) {
            if (i != 0) {
                statDeviations[(int) (Math.random() * 7)] += i;
            }
        }
        int remainingPts = 40;
        int stat = (int) (Math.random() * 7);
        while (remainingPts > 0) {
            stat += (int) (Math.random() * 2);
            if (stat > 6) {
                stat -= 7;
            }
            if (statDeviations[stat] < 15) {
                statDeviations[stat]++;
                remainingPts--;
            }
        }
    }

    public void randomizeMoves() {
        int slot = 0;
        for (int i = 0; i < lvlMovepool[0].length; i++) {
            if (lvlMovepool[0][i] <= level) {
                moves[slot] = lvlMovepool[1][i];
                if (i >= 4) {
                    slot += (int) (Math.random() * 3);
                } else {
                    slot++;
                }
                if (slot > 4) {
                    slot -= 5;
                }
            } else {
                break;
            }
        }
        if (Math.random() < 0.5) {
            moves[4] = 0;
            if (Math.random() < 0.25) {
                moves[3] = 0;
            }
        } 
    }

    public void resetStats() {
        HP = (int) Math.round(2 * (baseHP + statDeviations[0]) + (level / 3));
        ATK = (int) Math.round((baseATK + statDeviations[1]) + (level / 3));
        DEF = (int) Math.round((baseDEF + statDeviations[2]) + (level / 3));
        SPD = (int) Math.round((baseSPD + statDeviations[3]) + (level / 4));
        EN = (int) Math.round(0.5 * (baseEN + statDeviations[4]) + (level / 4));
        FOC = (int) Math.round((baseFOC + statDeviations[5]) + (level / 5));
        LUK = (int) Math.round((baseLUK + statDeviations[6]) + (level / 5));

        System.out.println("[" + HP + ", " + ATK + ", " + DEF + ", " + SPD + ", " + EN + ", " + FOC + ", " + LUK + "]");
        System.out.println("[" + moves[0] + ", " + moves[1] + ", " + moves[2] + ", " + moves[3] + ", " + moves[4] + "]");
        System.out.println("Power: " + specialPower);
    }

    public void fullHeal() {
        status = 0;
        this.resetStats();
        currHP = HP;
        currEN = EN;
    }

    public void checkHPandEN() {
        if (currHP < 0) {
            currHP = 0;
        } else if (currHP > HP) {
            currHP = HP;
        }
        if (currEN < 0) {
            currEN = 0;
        } else if (currEN > EN) {
            currEN = EN;
        }
    }

    public void damage(int damage) {
        currHP -= damage;
        checkHPandEN();
    }

    public void heal(int health) {
        currHP += health;
        checkHPandEN();
    }

    public void useEnergy(int energy) {
        currEN -= energy;
        checkHPandEN();
    }

    public boolean isInCat(int category) {
        if (cat1 == category || cat2 == category) {
            return true;
        }
        return false;
    }

    public String toString() {
        String gender;
        if (isMale) {
            gender = " (M)";
        } else {
            gender = " (F)";
        }
        String finalString = name + gender + " - Lvl. " + level;
        finalString += "\nHP: " + currHP + " / " + HP;
        finalString += "      EN: " + currEN + " / " + EN;
        finalString += "\n[" + HP + ", " + ATK + ", " + DEF + ", " + SPD + ", " + EN + ", " + FOC + ", " + LUK + "]";
        switch (status) {
            case 1:
                finalString += "\nStatus: Soaked";
                break;
            case 2:
                finalString += "\nStatus: Burned";
                break;
            case 3:
                finalString += "\nStatus: Shocked";
                break;
            case 4:
                finalString += "\nStatus: Frozen";
                break;
            case 5:
                finalString += "\nStatus: Wounded";
                break;
            case 6:
                finalString += "\nStatus: Attracted";
                break;
            case 7:
                finalString += "\nStatus: Suffocating";
                break;
            case 8:
                finalString += "\nStatus: Enchanted";
                break;
            case 9:
                finalString += "\nStatus: Asleep";
                break;
            case 10:
                finalString += "\nStatus: Confused";
                break;
        } 

        return finalString;
    }

    public boolean accuracyCheck(int accuracy, Kreechin target, boolean userIsPlayer, boolean targetIsPlayer) {
        if (userIsPlayer == targetIsPlayer) {
            if ((Math.random() * 100) < (accuracy + (FOC / 15.00) -2.20)) {
                return true; 
            }
        } else {
            if ((Math.random() * 100) < (accuracy + ((FOC - target.FOC) / 15.00))) {
                return true; 
            }
        }
        return false;
    }

    public boolean accuracyCheck(int accuracy) {
        if ((Math.random() * 100) < (accuracy + (FOC / 15.00) -2.20)) {
            return true; 
        }
        return false;
    }

}
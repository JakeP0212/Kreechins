import java.util.HashMap;

public class Move {

    String name;
    int cat;
    boolean isAttack;

    String targeting;
    int[] moveTypes;
    int energyCost;
    int priority;

    int minHits;
    int maxHits;
    int basePower;
    int baseAccuracy;

    HashMap<String, Integer> effects;

    public Move(String nm, int category, String target, int enCost, int accuracy, int prior, int[] types, HashMap<String, Integer> effs) {
        name = nm;
        cat = category;
        targeting = target;
        energyCost = enCost;
        baseAccuracy = accuracy;
        priority = prior;
        moveTypes = types;
        effects = effs;
        basePower = 0;
        minHits = 1;
        maxHits = 1;
        isAttack = false;
    }

    public Move(String nm, int category, String target, int enCost, int accuracy, int prior, int[] types, HashMap<String, Integer> effs, int basePwr, int minH, int maxH) {
        this(nm, category, target, enCost, accuracy, prior, types, effs);
        basePower = basePwr;
        minHits = minH;
        maxHits = maxH;
        isAttack = true;
    }

    public String toString() {
        String finalString = name + " - " + Battle.categoryNames[cat - 1];
        finalString += "\n   Power: " + basePower + " - Accuracy: " + baseAccuracy + " - Energy Cost: " + energyCost;
        return finalString;
    }
}

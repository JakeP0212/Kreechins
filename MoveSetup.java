import java.util.ArrayList;
import java.util.HashMap;

public class MoveSetup {

    public static ArrayList<Move> setup() {
        ArrayList<Move> moves = new ArrayList<Move>();
        HashMap<String, Integer> effects = new HashMap<String, Integer>();

        //name, category, targeting, energyCost, accuracy, priority, type of move, effects
        //atk, add power, min hits, max hits

        //0001
        moves.add(new Move("Tackle", 1, "1 Opponent", 3, 100, 0, new int[]{1}, null, 40, 1, 1));

        moves.add(new Move("Water Gun", 2, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));

        moves.add(new Move("Ember", 3, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));

        moves.add(new Move("Vine Whip", 4, "1 Opponent", 3, 100, 0, new int[]{6}, null, 40, 1, 1));
        
        //0005
        moves.add(new Move("Shock", 5, "1 Opponent", 3, 100, 0, new int[]{0}, null, 40, 1, 1));
        
        moves.add(new Move("Icicle", 6, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));
        
        moves.add(new Move("Draft", 7, "1 Opponent", 3, 100, 0, new int[]{0}, null, 40, 1, 1));
        
        moves.add(new Move("Mud Fling", 8, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));
        
        moves.add(new Move("Bolt Throw", 9, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));
        
        //0010
        moves.add(new Move("Eerie Light", 10, "1 Opponent", 3, 100, 0, new int[]{8}, null, 40, 1, 1));
        
        moves.add(new Move("Punch", 11, "1 Opponent", 3, 100, 0, new int[]{1, 5}, null, 40, 1, 1));
        
        moves.add(new Move("Heart Throw", 12, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));
        
        moves.add(new Move("Shriek", 13, "1 Opponent", 3, 100, 0, new int[]{2}, null, 40, 1, 1));
        
        moves.add(new Move("Asteroid Fling", 14, "1 Opponent", 3, 100, 0, new int[]{4}, null, 40, 1, 1));
        
        //0015
        moves.add(new Move("Spellcast", 15, "1 Opponent", 3, 100, 0, new int[]{0}, null, 40, 1, 1));
        
        moves.add(new Move("Blinding Light", 16, "1 Opponent", 3, 100, 0, new int[]{8}, null, 40, 1, 1));
        
        moves.add(new Move("Strike", 1, "1 Opponent", 3, 100, 0, new int[]{1, 5}, null, 60, 1, 1));
        
        moves.add(new Move("Water Pulse", 2, "1 Opponent", 3, 100, 0, new int[]{7}, null, 60, 1, 1));
        
        moves.add(new Move("Fireball", 3, "1 Opponent", 3, 100, 0, new int[]{4}, null, 60, 1, 1));
        
        //0020
        moves.add(new Move("Leaf Blade", 4, "1 Opponent", 3, 100, 0, new int[]{6}, null, 60, 1, 1));
        
        moves.add(new Move("Electric Pulse", 5, "1 Opponent", 3, 100, 0, new int[]{7}, null, 60, 1, 1));
        
        moves.add(new Move("Ice Beam", 6, "1 Opponent", 3, 100, 0, new int[]{7}, null, 60, 1, 1));
        
        moves.add(new Move("Swoop", 7, "1 Opponent", 3, 100, 0, new int[]{1}, null, 60, 1, 1));
        
        moves.add(new Move("Stone Smash", 8, "1 Opponent", 3, 100, 0, new int[]{4}, null, 60, 1, 1));
        
        //0025
        moves.add(new Move("Metallic Flash", 9, "1 Opponent", 3, 100, 0, new int[]{8}, null, 60, 1, 1));
        
        moves.add(new Move("Haunt", 10, "1 Opponent", 3, 100, 0, new int[]{0}, null, 60, 1, 1));
        
        moves.add(new Move("Ambush", 11, "1 Opponent", 3, 100, 0, new int[]{1}, null, 60, 1, 1));
        
        moves.add(new Move("Lovely Lash", 12, "1 Opponent", 3, 100, 0, new int[]{6}, null, 60, 1, 1));
        
        moves.add(new Move("Wacky Whip", 13, "1 Opponent", 3, 100, 0, new int[]{6}, null, 60, 1, 1));
        
        //0030
        moves.add(new Move("Space Punch", 14, "1 Opponent", 3, 100, 0, new int[]{1, 5}, null, 60, 1, 1));
        
        moves.add(new Move("Magical Mischief", 15, "1 Opponent", 3, 100, 0, new int[]{0}, null, 60, 1, 1));
        
        moves.add(new Move("Light Beam", 16, "1 Opponent", 3, 100, 0, new int[]{7, 8}, null, 60, 1, 1));
        
        effects.put("Target - Lower Attack", 40);
        moves.add(new Move("Slam", 1, "1 Opponent", 5, 90, 0, new int[]{1}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Soak", 20);
        moves.add(new Move("Whirlpool", 2, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        //0035
        effects.put("Target - Burn", 20);
        moves.add(new Move("Flamethrower", 3, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Lower Focus", 40);
        moves.add(new Move("Overgrowth", 4, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Shock", 20);
        moves.add(new Move("Thunderbolt", 5, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Freeze", 15);
        moves.add(new Move("Snowball Smash", 6, "1 Opponent", 5, 90, 0, new int[]{4}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Lower Speed", 40);
        moves.add(new Move("Sky Strike", 7, "1 Opponent", 5, 90, 0, new int[]{1}, effects, 85, 1, 1));
        effects.clear();
        
        //0040
        effects.put("Target - Lower Energy", 40);
        moves.add(new Move("Landslide", 8, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Lower Defense", 40);
        moves.add(new Move("Iron Punch", 9, "1 Opponent", 5, 90, 0, new int[]{1, 5}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Lower HP", 40);
        moves.add(new Move("Life Drain", 10, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Wound", 20);
        moves.add(new Move("Tactical Assault", 11, "1 Opponent", 5, 90, 0, new int[]{1}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Attract", 20);
        moves.add(new Move("Heart Beam", 12, "1 Opponent", 5, 90, 0, new int[]{7}, effects, 85, 1, 1));
        effects.clear();
        
        //0045
        effects.put("Target - Confuse", 20);
        moves.add(new Move("Crazy Cannon", 13, "1 Opponent", 5, 90, 0, new int[]{0}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Suffocate", 20);
        moves.add(new Move("Spacevoid Slam", 14, "1 Opponent", 5, 90, 0, new int[]{1}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Enchant", 20);
        moves.add(new Move("Enchanting Pulse", 15, "1 Opponent", 5, 90, 0, new int[]{7}, effects, 85, 1, 1));
        effects.clear();
        
        effects.put("Target - Lower Luck", 40);
        moves.add(new Move("Colorful Clash", 16, "1 Opponent", 5, 90, 0, new int[]{1}, effects, 85, 1, 1));
        effects.clear();
        
        moves.add(new Move("Rush", 1, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        //0050
        moves.add(new Move("Water Jet", 2, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Flame Rush", 3, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Speedy Leaf", 4, "1 Opponent", 3, 95, 1, new int[]{4}, null, 50, 1, 1));
        
        moves.add(new Move("Thunder Rush", 5, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Icy Sprint", 6, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        //0055
        moves.add(new Move("Air Stream", 7, "1 Opponent", 3, 95, 1, new int[]{0}, null, 50, 1, 1));
        
        moves.add(new Move("Muddy Mash", 8, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Silver Speedstrike", 9, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Jumpscare", 10, "1 Opponent", 3, 95, 1, new int[]{0}, null, 50, 1, 1));
        
        moves.add(new Move("Quick Fire", 11, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        //0060
        moves.add(new Move("Quick Kiss", 12, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Hyperactive Rush", 13, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Rocket Blast", 14, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        moves.add(new Move("Portal Punch", 15, "1 Opponent", 3, 95, 1, new int[]{1, 5}, null, 50, 1, 1));
        
        moves.add(new Move("Rainbow Run", 16, "1 Opponent", 3, 95, 1, new int[]{1}, null, 50, 1, 1));
        
        //0065
        moves.add(new Move("Double Attack", 1, "2 Opponents", 5, 100, 0, new int[]{1}, null, 55, 1, 1));
        
        moves.add(new Move("Wave", 2, "2 Opponents", 5, 100, 0, new int[]{0}, null, 55, 1, 1));
        
        moves.add(new Move("Firestorm", 3, "2 Opponents", 5, 100, 0, new int[]{0}, null, 55, 1, 1));
        
        moves.add(new Move("Leafstorm", 4, "2 Opponents", 5, 100, 0, new int[]{4}, null, 55, 1, 1));
        
        moves.add(new Move("Electro Slice", 5, "2 Opponents", 5, 100, 0, new int[]{6}, null, 55, 1, 1));
        
        //0070
        moves.add(new Move("Frosty Winds", 6, "2 Opponents", 5, 100, 0, new int[]{0}, null, 55, 1, 1));
        
        moves.add(new Move("Wind Burst", 7, "2 Opponents", 5, 100, 0, new int[]{0}, null, 55, 1, 1));
        
        moves.add(new Move("Rockfall", 8, "2 Opponents", 5, 100, 0, new int[]{4}, null, 55, 1, 1));
        
        moves.add(new Move("Metal Shards", 9, "2 Opponents", 5, 100, 0, new int[]{4}, null, 55, 1, 1));
        
        moves.add(new Move("Shadow Pulse", 10, "2 Opponents", 5, 100, 0, new int[]{7}, null, 55, 1, 1));
        
        //0075
        moves.add(new Move("Barrage", 11, "2 Opponents", 5, 100, 0, new int[]{1}, null, 55, 1, 1));
        
        moves.add(new Move("Love Pulse", 12, "2 Opponents", 5, 100, 0, new int[]{7}, null, 55, 1, 1));
        
        moves.add(new Move("Odd Pulse", 13, "2 Opponents", 5, 100, 0, new int[]{7}, null, 55, 1, 1));
        
        moves.add(new Move("Starfall", 14, "2 Opponents", 5, 100, 0, new int[]{4}, null, 55, 1, 1));
        
        moves.add(new Move("Ricochet Spell", 15, "2 Opponents", 5, 100, 0, new int[]{0}, null, 55, 1, 1));
        
        //0080
        moves.add(new Move("Prismatic Pulse", 16, "2 Opponents", 5, 100, 0, new int[]{7, 8}, null, 55, 1, 1));
        
        
        



        return moves;
    }

}


import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
    
    public static double[][] categoryChart = {
    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,.5, 1, 1,.5,.5, 1},
    { 1,.5, 2,.5, 1, 1, 1, 2,.5, 1, 2, 1, 1, 1, 1,.5}, 
    { 1,.5,.5, 2, 1, 2, 1,.5, 2, 1, 1, 1, 1, 1,.5, 1}, 
    { 1, 2,.5,.5, 1, 1,.5, 2,.5, 1, 1, 1, 2, 1, 1, 1}, 
    { 1, 2, 1,.5,.5, 1, 2, 0, 2,.5, 1,.5, 2, 1, 1, 2}, 
    { 1, 2,.5, 2, 1,.5, 1, 1,.5, 1, 2, 0, 1,.5, 1, 2}, 
    {.5, 1, 1, 2, 1, 1, 1, 2,.5,.5,.5, 1,.5, 2, 2,.5}, 
    { 1, 1, 1, 1, 2, 1, 0, 1, 2, 1,.5, 1, 1, 0, 1,.5}, 
    { 1,.5,.5, 1,.5, 2,.5, 2,.5, 1, 1, 1, 1, 2,.5, 0}, 
    { 0, 1, 1, 1, 1, 1, 1, 1,.5, 2,.5, 2, 1,.5, 0, 1}, 
    { 2, 1, 1, 1, 1, 2, 2, 2, 1, 0, 2,.5, 2, 2,.5, 2}, 
    {.5, 1, 2, 1, 1, 2, 1,.5,.5, 1, 2, 1,.5, 1, 2, 1}, 
    { 1, 1, 1,.5, 2,.5, 1, 1,.5, 1, 1, 2, 2, 1, 1,.5}, 
    { 1, 1, 1, 1, 1,.5, 1,.5,.5, 0, 2, 1, 1, 0, 1, 2}, 
    { 1, 1, 2, 1, 2, 1,.5,.5,.5, 2, 2,.5, 0,.5, 2, 1}, 
    { 1,.5, 1, 1,.5, 2, 2, 1, 0, 1,.5, 1, 1, 1, 1,.5}};

    public static String[] categoryNames = {"Neutral", "Water", "Fire", "Plant", "Electric", "Ice", "Air", "Earth", "Metal", "Spirit", "Army", "Love", "Crazy", "Space", "Magic", "Rainbow"};

    static ArrayList<Move> moves = MoveSetup.setup();

    static int playerLeft;
    static int playerRight;
    static int opponentLeft;
    static int opponentRight;

    static int pLTarget;
    static int pRTarget;
    static int oLTarget;
    static int oRTarget;

    static boolean isCrit;

    static String weather;

    public static double findEffectiveness(int moveCat, int cat1) {
        return categoryChart[moveCat - 1][cat1 - 1];
    }

    public static double findEffectiveness(int moveCat, int cat1, int cat2) {
        return (categoryChart[moveCat - 1][cat1 - 1] * categoryChart[moveCat - 1][cat2 - 1]);
    }

    public static void startBattle(Team player, Team opponent, String oppName) {
        boolean isGameOver = false;
        playerLeft = 0;
        opponentLeft = 0;
        Scanner scan = new Scanner(System.in);
        weather = "Clear";
        if (opponent.teamMembers.length > 1) {
            opponentRight = 1;
            System.out.println("\n" + oppName + " sent out " + opponent.teamMembers[0].name + " and " + opponent.teamMembers[1].name + ".\n");
        } else {
            opponentRight = -1;
            System.out.println("\n" + oppName + " sent out " + opponent.teamMembers[0].name + ".\n");
        }
        if (player.teamMembers.length > 1) {
            playerRight = 1;
            System.out.println("Go, " + player.teamMembers[0].name + " and " + player.teamMembers[1].name + "!\n\n");
        } else {
            playerRight = -1;
            System.out.println("Go, " + player.teamMembers[0].name + "!\n\n");
        }

        // BATTLE LOOP
        int turns = 1;
        while (isGameOver == false) {
            displayBoard(player, opponent, playerLeft, playerRight, opponentLeft, opponentRight);
            int oppLeftMove = calcEnemyMove(player, opponent, playerLeft, playerRight, opponentLeft) - 1;
            int oppRightMove = calcEnemyMove(player, opponent, playerLeft, playerRight, opponentRight) - 1;
            System.out.println("===== TURN " + turns + " =====\n");
            System.out.println("===== SELECT MOVES =====\n");
            int playerLeftMove = -1;
            int playerRightMove = -1;
            if (playerLeft != -1) {
                playerLeftMove = calcPlayerMove(player, opponent, opponentLeft, opponentRight, playerLeft, scan) - 1;
            }
            if (playerRight != -1) {
                playerRightMove = calcPlayerMove(player, opponent, opponentLeft, opponentRight, playerRight, scan) - 1;
            }
            //calculate turn order
            ArrayList<Integer> turnOrder = deterimineTurnOrder(player, opponent, playerLeftMove, playerRightMove, oppLeftMove, oppRightMove);

            System.out.println("===== EXECUTING MOVES =====\n");
            for (int i = 0; i < turnOrder.size(); i++) {
                
                if (turnOrder.get(i) == 1) {
                    if (opponentLeft != -1) {
                        if (opponent.teamMembers[opponentLeft].currEN >= moves.get(oppLeftMove).energyCost) {
                            opponent.teamMembers[opponentLeft].useEnergy(moves.get(oppLeftMove).energyCost);
                            move(opponent.teamMembers[opponentLeft], false, oLTarget, moves.get(oppLeftMove), player, opponent);
                        } else {
                            //not enough energy, use Energize
                        }
                    }
                } else if (turnOrder.get(i) == 2) {
                    if (opponentRight != -1) {
                        if (opponent.teamMembers[opponentRight].currEN >= moves.get(oppRightMove).energyCost) {
                            opponent.teamMembers[opponentRight].useEnergy(moves.get(oppRightMove).energyCost);
                            move(opponent.teamMembers[opponentRight], false, oRTarget, moves.get(oppRightMove), player, opponent);
                        } else {
                            //not enough energy, use Energize
                        }
                    }
                } else if (turnOrder.get(i) == 3) {
                    if (playerLeft != -1) {
                        if (player.teamMembers[playerLeft].currEN >= moves.get(playerLeftMove).energyCost) {
                            player.teamMembers[playerLeft].useEnergy(moves.get(playerLeftMove).energyCost);
                            move(player.teamMembers[playerLeft], true, pLTarget, moves.get(playerLeftMove), player, opponent);
                        } else {
                            //not enough energy, use Energize
                        }
                    }
                } else if (turnOrder.get(i) == 4) {
                    if (playerRight != -1) {
                        if (player.teamMembers[playerRight].currEN >= moves.get(playerRightMove).energyCost) {
                            player.teamMembers[playerRight].useEnergy(moves.get(playerRightMove).energyCost);
                            move(player.teamMembers[playerRight], true, pRTarget, moves.get(playerRightMove), player, opponent);
                        } else {
                            //not enough energy, use Energize
                        }
                    }
                }

                checkForFaints();
            }

            

            //check for game over, if over break loop

            //if not over:
            //do weather, status, powers, etc.
            //regain energy for benched or sleeping kreechins
            //run script again

            System.out.println("\n");
            turns += 1;
            
        }

        scan.close();

    }

    // BATTLE HELPER FUNCTIONS
    public static void move(Kreechin user, boolean isPlayer, int target, Move move, Team player, Team opponent) {
        if (!(isPlayer)) {
            System.out.print("The opposing ");
        }
        System.out.println(user.name + " used " + move.name + "!");
        double sameCatBonus = 1.0;
        if (user.cat1 == move.cat || user.cat2 == move.cat) {
            sameCatBonus = 1.5;
        }
        int damage = 0;

        switch (target) {
            case 1:
                damage = prepMove(user, opponent.teamMembers[opponentLeft], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentLeft], move, damage);
                }
                break;
            case 2:
                damage = prepMove(user, opponent.teamMembers[opponentRight], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentRight], move, damage);
                }
                break;
            case 3:
                damage = prepMove(user, player.teamMembers[playerLeft], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerLeft], move, damage);
                }
                break;
            case 4:
                damage = prepMove(user, player.teamMembers[playerRight], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerRight], move, damage);
                }
                break;
            case 5:
                damage = prepMove(user, opponent.teamMembers[opponentLeft], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentLeft], move, damage);
                }
                damage = prepMove(user, opponent.teamMembers[opponentRight], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentRight], move, damage);
                }
                break;
            case 6:
                damage = prepMove(user, player.teamMembers[playerLeft], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerLeft], move, damage);
                }
                damage = prepMove(user, player.teamMembers[playerRight], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerRight], move, damage);
                }
                break;
            case 7:

            case 8:

            case 9:

            case 10:

            case 11:

            case 12:
        }
        System.out.println();
    }

    public static int prepMove(Kreechin user, Kreechin target, Move move, boolean userIsPlayer, boolean targetIsPlayer, Team player, Team opponent, double catBonus) {
        int dmg = 0;
        if (move.baseAccuracy != 100) {
            if (!(user.accuracyCheck(move.baseAccuracy, target, userIsPlayer, targetIsPlayer))) {
                if (userIsPlayer == targetIsPlayer) {
                    System.out.println("The attack missed!");
                } else {
                    if (targetIsPlayer) {
                        System.out.println(target.name + " dodged the attack!");
                    } else {
                        System.out.println("The opposing " + target.name + " dodged the attack.");
                    }
                }
                return -1;
                }
            }
            dmg = calcDamage(user, target, move, catBonus, targetIsPlayer);
            return dmg;
    }

    public static int calcDamage(Kreechin user, Kreechin target, Move move, double sameCat, boolean targetIsPlayer) {
        double effect = 1.0;
        if (target.cat2 != 0) {
            effect = findEffectiveness(move.cat, target.cat1, target.cat2);
        } else {
            effect = findEffectiveness(move.cat, target.cat1);
        }
        if (!(move.isAttack)) {
            if (effect == 0.0) {
                System.out.print("It had no effect on ");
                if (!(targetIsPlayer)) {
                    System.out.print("the opposing ");
                }
                System.out.println(target.name + ".");
                return -1;
            }
            return 0;
        }  else if (effect == 0.0) {
            System.out.print("It had no effect on ");
            if (!(targetIsPlayer)) {
                System.out.print("the opposing ");
            }
            System.out.println(target.name + ".");
            return -1;
        } else if (effect == 0.25) {
            System.out.print("It's extremely ineffective on ");
            if (!(targetIsPlayer)) {
                System.out.print("the opposing ");
            }
        } else if (effect == 0.5) {
            System.out.print("It's very ineffective on ");
            if (!(targetIsPlayer)) {
                System.out.print("the opposing ");
            }
        } else if (effect == 2.0) {
            System.out.print("It's very effective on ");
            if (!(targetIsPlayer)) {
                System.out.print("the opposing ");
            }
        } else if (effect == 4.0) {
            System.out.print("It's extremely effective on ");
            if (!(targetIsPlayer)) {
                System.out.print("the opposing ");
            }
        }
        if (effect != 1.0) {
            System.out.println(target.name + ".");
        }
        
        isCrit = false;
        double crit = 1.0;
        if (calcCritChance(user.FOC)) {
            isCrit = true;
            crit = 1.5;
            System.out.print("A critical hit on ");
            if (!(targetIsPlayer)) {
                System.out.print("the opposing ");
            }
            System.out.println(target.name + "!");
        }

        double multiplier = 1.0;
        //DO MULTIPLIERS HERE (powers, weather, etc.)

        int rand = (int) ((Math.random() * 21) - 10);
        int damage = (int) Math.round(((effect * sameCat * crit * multiplier) * (move.basePower + rand) * ((user.ATK * 1.0) / target.DEF)) / 1.75);
        if (damage < 1) {
            return 1;
        }
        return damage;
    }

    public static void useMove(Kreechin user, Kreechin target, Move move, int damage) {
        if (move.isAttack) {
            target.damage(damage);
        }
        //DO MOVE SECONDARY EFFECTS HERE
    }

    public static boolean calcCritChance(int focus) {
        if ((Math.random() * 100) < (2.8 + (focus / 25.00))) {
            return true;
        }
        return false;
    }
    
    public static int calcEnemyMove(Team player, Team opponent, int pL, int pR, int attacker) {
        if (attacker != -1) {
            //enemy AI to choose move goes here
            //change targeting using targetSelector as needed
            if (attacker == opponentLeft) {
                oLTarget = 3;
            } else {
                oRTarget = 4;
            }
            return opponent.teamMembers[attacker].moves[0];
        }
        return 0;
    }

    public static int calcPlayerMove(Team player, Team opponent, int oL, int oR, int attacker, Scanner scan) {
        displayMoveOptions(player.teamMembers[attacker]);
        char choice = scan.nextLine().charAt(0);
        System.out.print("\n");
        if (choice == '1' || choice == '2' || choice == '3' || choice == '4' || choice == '5') {
            if (playerLeft == attacker) {
                pLTarget = targetSelector(3, moves.get(player.teamMembers[attacker].moves[Character.getNumericValue(choice) - 1] - 1), player, opponent, scan);
            } else {
                pRTarget = targetSelector(4, moves.get(player.teamMembers[attacker].moves[Character.getNumericValue(choice) - 1] - 1), player, opponent, scan);
            }
            System.out.println("\n");
            return player.teamMembers[attacker].moves[Character.getNumericValue(choice) - 1];

        } else {
            return 0;
        }
    }
 
    public static void displayBoard(Team player, Team opponent, int pL, int pR, int oL, int oR) {
        System.out.println("===== BATTLE STATUS =====\n\nOpponent - Left Side:");
        if (oL != -1) {
            System.out.println(opponent.teamMembers[oL].toString() + "\n");
        } else {
            System.out.println("Empty Slot\n");
        }
        System.out.println("Opponent - Right Side:");
        if (oR != -1) {
            System.out.println(opponent.teamMembers[oR].toString() + "\n");
        } else {
            System.out.println("Empty Slot\n");
        }
        System.out.println("Player - Left Side:");
        if (pL != -1) {
            System.out.println(player.teamMembers[pL].toString() + "\n");
        } else {
            System.out.println("Empty Slot\n");
        }
        System.out.println("Player - Right Side:");
        if (pR != -1) {
            System.out.println(player.teamMembers[pR].toString() + "\n\n");
        } else {
            System.out.println("Empty Slot\n\n");
        }
    }

    public static void displayMoveOptions(Kreechin kreechin) {
        System.out.println(kreechin.name + "'s Moves:");
        for (int i = 0; i < 5; i++) {
            if (kreechin.moves[i] != 0) {
                System.out.println((i + 1) + ") " + moves.get(kreechin.moves[i] - 1).toString());
            } else {
                System.out.println((i + 1) + ") Empty Move Slot");
            }
        }
        System.out.print("S) Switch Kreechin\nI) Use Item\nF) Flee Battle\n\nChoose a move:  ");
    }

    public static int targetSelector(int user, Move move, Team player, Team opponent, Scanner scan) {
        if (user == 3 || user == 4) {
            //UPDATE CASE 1 Opp AND 1 Ally to account for empty slots
            switch (move.targeting) {
                case "1 Opponent" :
                    if (user == 3) {
                        System.out.print("Target Selection:\n1) Opponent's " + opponent.teamMembers[opponentLeft].name + "\n2) Opponent's " + opponent.teamMembers[opponentRight].name + "\n3) Your " + player.teamMembers[playerRight].name + "\n\nChoose a target:  ");
                        char choiceOpp = scan.nextLine().charAt(0);
                        if (choiceOpp == '1') {
                            return 1;
                        } else if (choiceOpp == '2') {
                            return 2;
                        } else {
                            return 4;
                        }
                    } else {
                        System.out.print("Target Selection:\n1) Opponent's " + opponent.teamMembers[opponentLeft].name + "\n2) Opponent's " + opponent.teamMembers[opponentRight].name + "\n3) Your " + player.teamMembers[playerLeft].name + "\n\nChoose a target:  ");
                        char choiceOpp = scan.nextLine().charAt(0);
                        if (choiceOpp == '1') {
                            return 1;
                        } else if (choiceOpp == '2') {
                            return 2;
                        } else {
                            return 3;
                        }
                    }
                case "2 Opponents" :
                    return 5;
                case "1 Ally" :
                    System.out.print("Target Selection:\n1) Your " + player.teamMembers[playerLeft].name + "\n2) Your " + player.teamMembers[playerRight].name + "\n\nChoose a target:  ");
                    char choiceAlly = scan.nextLine().charAt(0);
                    if (choiceAlly == '1') {
                        return 3;
                    } else {
                        return 4;
                    }
                case "2 Allies" :
                    return 6;
                case "Partner" :
                    if (user == 3) {
                        return 4;
                    } else {
                        return 3;
                    }
                case "Self" :
                    return user;
                case "All But Self" :
                    if (user == 3) {
                        return 9;
                    } else {
                        return 10;
                    }
                case "All" :
                    return 11;
                case "None" :
                    return 12;
        }
        } else if (user == 1 || user == 2) {
            return 3;
        }
        return 0;
    }

    public static ArrayList<Integer> deterimineTurnOrder(Team player, Team opponent, int pL, int pR, int oL, int oR) {
        ArrayList<Integer> order = new ArrayList<Integer>();
        ArrayList<Integer> speeds = new ArrayList<Integer>();
        ArrayList<Integer> priorities = new ArrayList<Integer>();
        if (pL != -1) {
            order.add(3);
            speeds.add(player.teamMembers[playerLeft].SPD);
            priorities.add(moves.get(pL).priority);
        }
        if (pR != -1) {
            order.add(4);
            speeds.add(player.teamMembers[playerRight].SPD);
            priorities.add(moves.get(pR).priority);
        }
        if (oL != -1) {
            order.add(1);
            speeds.add(opponent.teamMembers[opponentLeft].SPD);
            priorities.add(moves.get(oL).priority);
        }
        if (oR != -1) {
            order.add(2);
            speeds.add(opponent.teamMembers[opponentRight].SPD);
            priorities.add(moves.get(oR).priority);
        }

        ArrayList<Integer> finalOrder = new ArrayList<Integer>();
        int amt = order.size();
        for (int i = 0; i < amt; i++) {
            int maxPri = -10;
            int maxSpeed = -1;
            int index = -1;
            for (int j = 0; j < order.size(); j++) {
                if (priorities.get(j) > maxPri) {
                    maxPri = priorities.get(j);
                    maxSpeed = speeds.get(j);
                    index = j;
                } else if (priorities.get(j) == maxPri) {
                    if (speeds.get(j) > maxSpeed) {
                        maxSpeed = speeds.get(j);
                        index = j;
                    } else if (speeds.get(j) == maxSpeed) {
                        if (Math.random() < 0.5) {
                            index = j;
                        }
                    }
                }
            }
            finalOrder.add(order.get(index));
            order.remove(index);
            speeds.remove(index);
            priorities.remove(index);
        }

        return finalOrder;
    }

    public static void checkForFaints() {

    }

}

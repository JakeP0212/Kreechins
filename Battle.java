import java.util.ArrayList;
import java.util.HashMap;
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
    static boolean isAirClean;
    static int weatherTurn;

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
        isAirClean = true;
        weatherTurn = 0;
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
        boolean win = true;
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

            int playerHP = 0;
            int oppHP = 0;
            for (Kreechin k : player.teamMembers) {
                playerHP += k.currHP;
            }
            for (Kreechin k : opponent.teamMembers) {
                oppHP += k.currHP;
            }
            if (oppHP == 0) {
                isGameOver = true;
                win = true;
                System.out.println("\n\n===== GAME OVER =====\n\nYou win!");
                break;
            } else if (playerHP == 0) {
                isGameOver = true;
                win = false;
                System.out.println("\n\n===== GAME OVER =====\n\nYou lose.");
                break;
            }
            
            if (!(isGameOver)) {
            
                //weather
                if (weather != "Clear") {
                    weatherTurn--;
                    if (weatherTurn == 0) {
                        weather = "Clear";
                        System.out.println("The weather cleared up.");
                    }
                }
                switch (weather) {
                    case "Rain":
                        System.out.println("It is pouring rain!");
                        break;
                    case "Sun":
                        System.out.println("It is extremely sunny!");
                        break;
                    case "Snow":
                        System.out.println("It is snowing!");
                        break;
                    case "Thunder":
                        System.out.println("A lightning storm ensues!");
                        break;
                    case "Wind":
                        System.out.println("The winds are strong!");
                        break;
                    case "Sand":
                        System.out.println("A sandstorm fills the battlefield!");
                        break;
                    case "Fog":
                        System.out.println("An ominous fog fills the battlefield!");
                        break;
                    case "Rainbow":
                        System.out.println("The sky is filled with rainbows!");
                        break;
                }
                if (playerLeft != -1) {
                    checkWeather(player.teamMembers[playerLeft]);
                }
                if (playerRight != -1) {
                    checkWeather(player.teamMembers[playerRight]);
                }
                if (opponentLeft != -1) {
                    checkWeather(opponent.teamMembers[opponentLeft]);
                }
                if (opponentRight != -1) {
                    checkWeather(opponent.teamMembers[opponentRight]);
                }

                //status conds.
                if (playerLeft != -1) {
                    checkStatus(player.teamMembers[playerLeft], true, player, opponent);
                }
                if (playerRight != -1) {
                    checkStatus(player.teamMembers[playerRight], true, player, opponent);
                }
                if (opponentLeft != -1) {
                    checkStatus(opponent.teamMembers[opponentLeft], false, player, opponent);
                }
                if (opponentRight != -1) {
                    checkStatus(opponent.teamMembers[opponentRight], false, player, opponent);
                }


                //regain energy for benched kreechins
                for (int i = 0; i < 6; i++) {
                    if (player.teamMembers.length >= i + 1) {
                        if (i != playerLeft && i != playerRight) {
                            int enGain = (int) (Math.random() * 3 + 1);
                            if (player.teamMembers[i].specialPower == 1) {
                                enGain *= 3;
                            }
                            if (player.teamMembers[i].status == 9) {
                                enGain *= 3;
                            }
                            opponent.teamMembers[i].gainEnergy(enGain);
                        }
                    }
                    if (opponent.teamMembers.length >= i + 1) {
                        if (i != opponentLeft && i != opponentRight) {
                            int enGain = (int) (Math.random() * 3 + 1);
                            if (opponent.teamMembers[i].specialPower == 1) {
                                enGain *= 3;
                            }
                            if (opponent.teamMembers[i].status == 9) {
                                enGain *= 3;
                            }
                            opponent.teamMembers[i].gainEnergy(enGain);
                        }
                    }
                }

                //next turn
                System.out.println("\n");
                turns += 1;
            }
        }

        if (win) {

        } else {

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
                    useMove(user, opponent.teamMembers[opponentLeft], move, damage, player, opponent, isPlayer);
                }
                break;
            case 2:
                damage = prepMove(user, opponent.teamMembers[opponentRight], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentRight], move, damage, player, opponent, isPlayer);
                }
                break;
            case 3:
                damage = prepMove(user, player.teamMembers[playerLeft], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerLeft], move, damage, player, opponent, isPlayer);
                }
                break;
            case 4:
                damage = prepMove(user, player.teamMembers[playerRight], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerRight], move, damage, player, opponent, isPlayer);
                }
                break;
            case 5:
                damage = prepMove(user, opponent.teamMembers[opponentLeft], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentLeft], move, damage, player, opponent, isPlayer);
                }
                damage = prepMove(user, opponent.teamMembers[opponentRight], move, isPlayer, false, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, opponent.teamMembers[opponentRight], move, damage, player, opponent, isPlayer);
                }
                break;
            case 6:
                damage = prepMove(user, player.teamMembers[playerLeft], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerLeft], move, damage, player, opponent, isPlayer);
                }
                damage = prepMove(user, player.teamMembers[playerRight], move, isPlayer, true, player, opponent, sameCatBonus);
                if (damage != -1) {
                    useMove(user, player.teamMembers[playerRight], move, damage, player, opponent, isPlayer);
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
        int acc = move.baseAccuracy;
        if (user.status == 3) {
            if (!determineChance(user.FOC, 80)) {
                System.out.println(user.name + " is Shocked!");
                System.out.println(user.name + " is unable to move!");
                return -1;
            }
        } else if (user.status == 4) {
            System.out.println(user.name + " is Frozen!");
            System.out.println(user.name + " is unable to move!");
            return -1;
        } else if (user.status == 6) {
            //add target check
            if (!determineChance(user.FOC, 50)) {
                System.out.println(user.name + " is Attracted!");
                System.out.println(user.name + " is unable to move!");
                return -1;
            }
            //add 10% chance of curing
        } else if (user.status == 9) {
            System.out.println(user.name + " is Asleep!");
            System.out.println(user.name + " is unable to move!");
            return -1;
        } else if (user.status == 10 && move.isAttack) {
            if (!determineChance(user.FOC, 25)) {
                System.out.println(user.name + " is Confused!");
                System.out.println(user.name + " hurt itself!");
                user.damage((int) ((move.basePower * 0.25) + (user.ATK / 10.00)));
                if (determineChance(user.FOC, 33)) {
                    user.status = 0;
                    user.statusTurns = 0;
                    System.out.println(user.name + " healed from its confusion!");
                }
                return -1;
            }
        } 
        if (weather.equals("Wind")) {
            for (int i = 0; i < move.moveTypes.length; i++) {
                if (move.moveTypes[i] == 4) {
                    acc -= 10;
                    break;
                }
            }
        } else if (weather.equals("Fog")) {
            acc -= 10;
        }
        if (acc != 100) {
            if (!(user.accuracyCheck(acc, target, userIsPlayer, targetIsPlayer))) {
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
        //Multipliers
        switch (weather) {
            case "Rain":
                if (move.cat == 2) {
                    multiplier *= 1.25;
                } else if (move.cat == 3 || move.cat == 6) {
                    multiplier *= 0.75;
                }
                break;
            case "Sun":
                if (move.cat == 3) {
                    multiplier *= 1.25;
                } else if (move.cat == 2 || move.cat == 6) {
                    multiplier *= 0.75;
                }
                break;
            case "Snow":
                if (move.cat == 6) {
                    multiplier *= 1.25;
                } else if (move.cat == 2 || move.cat == 3) {
                    multiplier *= 0.75;
                }
                break;
            case "Thunder":
                if (move.cat == 2 || move.cat == 5) {
                    multiplier *= 1.25;
                }
                break;
            case "Wind":
                if (move.cat == 4 || move.cat == 7) {
                    multiplier *= 1.25;
                }
                break;
            case "Sand":
                if (move.cat == 8) {
                    multiplier *= 1.25;
                }
                break;
            case "Fog":
                if (move.cat == 10 || move.cat == 14) {
                    multiplier *= 1.25;
                }
                break;
            case "Rainbow":
                if (move.cat == 16) {
                    multiplier *= 1.25;
                }
                if (target.cat1 == 16 || target.cat2 == 16) {
                    multiplier *= 0.75;
                }
                break;
        }
        if (user.status == 3) {
            multiplier *= 0.75;
        }
        switch (user.specialPower) {
            case 2:
                if (((user.currHP * 1.0) / user.HP) <= 0.5 && move.cat == 2) {
                    multiplier *= 1.25;
                }
                break;
            case 3:
                if (((user.currHP * 1.0) / user.HP) <= 0.5 && move.cat == 3) {
                    multiplier *= 1.25;
                }
                break;
            case 4:
                if (((user.currHP * 1.0) / user.HP) <= 0.5 && move.cat == 4) {
                    multiplier *= 1.25;
                }
                break;
        }

        int rand = (int) ((Math.random() * 21) - 10);
        int damage = (int) Math.round(((effect * sameCat * crit * multiplier) * (move.basePower + rand) * ((user.ATK * 1.0) / target.DEF)) / 1.75);
        if (damage < 1) {
            return 1;
        }
        return damage;
    }

    public static void useMove(Kreechin user, Kreechin target, Move move, int damage, Team player, Team opponent, boolean isPlayer) {
        if (move.isAttack) {
            target.damage(damage);
        }
        
        if (move.effects != null) {
            moveSecondaryEffects(user, target, move.effects, player, opponent, isPlayer);
        }
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
            if (weather.equals("Wind") && (player.teamMembers[playerLeft].cat1 == 7 || player.teamMembers[playerLeft].cat2 == 7)) {
                speeds.add((int) (player.teamMembers[playerLeft].SPD * 1.25));
            } else {
                speeds.add(player.teamMembers[playerLeft].SPD);
            }
            priorities.add(moves.get(pL).priority);
        }
        if (pR != -1) {
            order.add(4);
            if (weather.equals("Wind") && (player.teamMembers[playerRight].cat1 == 7 || player.teamMembers[playerRight].cat2 == 7)) {
                speeds.add((int) (player.teamMembers[playerRight].SPD * 1.25));
            } else {
                speeds.add(player.teamMembers[playerRight].SPD);
            }
            priorities.add(moves.get(pR).priority);
        }
        if (oL != -1) {
            order.add(1);
            if (weather.equals("Wind") && (opponent.teamMembers[opponentLeft].cat1 == 7 || opponent.teamMembers[opponentLeft].cat2 == 7)) {
                speeds.add((int) (opponent.teamMembers[opponentLeft].SPD * 1.25));
            } else {
                speeds.add(opponent.teamMembers[opponentLeft].SPD);
            }
            priorities.add(moves.get(oL).priority);
        }
        if (oR != -1) {
            order.add(2);
            if (weather.equals("Wind") && (opponent.teamMembers[opponentRight].cat1 == 7 || opponent.teamMembers[opponentRight].cat2 == 7)) {
                speeds.add((int) (opponent.teamMembers[opponentRight].SPD * 1.25));
            } else {
                speeds.add(opponent.teamMembers[opponentRight].SPD);
            }
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

    public static boolean determineChance(int stat, int chance) {
        if ((Math.random() * 100) <= (chance + (stat / 25.00) - 2.2)) {
            return true;
        }
        return false;
    }

    public static int determineChance(int stat, int min, int max, boolean isHigherBetter) {
        int range = max - min + 1;
        double rand = Math.random() * 100;
        int amt = 0;
        switch (range) {
            case 2:
                if (stat <= 20) {
                    amt = min;
                } else if (21 <= stat && stat <= 40) {
                    if (rand < 85.00) {
                        amt = min;
                    } else {
                        amt = max;
                    }
                } else if (41 <= stat && stat <= 60) {
                    if (rand < 70.00) {
                        amt = min;
                    } else {
                        amt = max;
                    }
                } else if (61 <= stat && stat <= 80) {
                    if (rand < 50.00) {
                        amt = min;
                    } else {
                        amt = max;
                    }
                } else if (81 <= stat && stat <= 100) {
                    if (rand < 30.00) {
                        amt = min;
                    } else {
                        amt = max;
                    }
                } else if (101 <= stat && stat <= 120) {
                    if (rand < 15.00) {
                        amt = min;
                    } else {
                        amt = max;
                    }
                } else if (stat >= 121) {
                    amt = max;
                }
                break;
            case 3:
                if (stat <= 20) {
                    amt = min;
                } else if (21 <= stat && stat <= 40) {
                    if (rand < 70.00) {
                        amt = min;
                    } else {
                        amt = min + 1;
                    }
                } else if (41 <= stat && stat <= 60) {
                    if (rand < 40.00) {
                        amt = min;
                    } else if (rand < 85.00) { 
                        amt = min + 1;
                    } else {
                        amt = max;
                    }
                } else if (61 <= stat && stat <= 80) {
                    if (rand < 33.33) {
                        amt = min;
                    } else if (rand < 66.67) { 
                        amt = min + 1;
                    } else {
                        amt = max;
                    }
                } else if (81 <= stat && stat <= 100) {
                    if (rand < 15.00) {
                        amt = min;
                    } else if (rand < 60.00) { 
                        amt = min + 1;
                    } else {
                        amt = max;
                    }
                } else if (101 <= stat && stat <= 120) {
                    if (rand < 30.00) {
                        amt = min + 1;
                    } else {
                        amt = max;
                    }
                } else if (stat >= 121) {
                    amt = max;
                }
                break;
            case 4:
                if (stat <= 20) {
                    amt = min;
                } else if (21 <= stat && stat <= 40) {
                    if (rand < 55.00) { 
                        amt = min;
                    } else if (rand < 90.00) { 
                        amt = min + 1;
                    } else {
                        amt = max - 1;
                    }
                } else if (41 <= stat && stat <= 60) {
                    if (rand < 30.00) {
                        amt = min;
                    } else if (rand < 75.00) { 
                        amt = min + 1;
                    } else if (rand < 90.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (61 <= stat && stat <= 80) {
                    if (rand < 25.00) {
                        amt = min;
                    } else if (rand < 50.00) { 
                        amt = min + 1;
                    } else if (rand < 75.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (81 <= stat && stat <= 100) {
                    if (rand < 10.00) {
                        amt = min;
                    } else if (rand < 25.00) { 
                        amt = min + 1;
                    } else if (rand < 70.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (101 <= stat && stat <= 120) {
                    if (rand < 10.00) { 
                        amt = min + 1;
                    } else if (rand < 45.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (stat >= 121) {
                    amt = max;
                }
                break;
            case 5:
                if (stat <= 20) {
                    amt = min;
                } else if (21 <= stat && stat <= 40) {
                    if (rand < 50.00) {
                        amt = min;
                    } else if (rand < 85.00) { 
                        amt = min + 1;
                    } else if (rand < 95.00) { 
                        amt = min + 2;
                    } else {
                        amt = max - 1;
                    }
                } else if (41 <= stat && stat <= 60) {
                    if (rand < 25.00) {
                        amt = min;
                    } else if (rand < 55.00) { 
                        amt = min + 1;
                    } else if (rand < 80.00) { 
                        amt = min + 2;
                    } else if (rand < 95.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (61 <= stat && stat <= 80) {
                    if (rand < 20.00) {
                        amt = min;
                    } else if (rand < 40.00) { 
                        amt = min + 1;
                    } else if (rand < 60.00) { 
                        amt = min + 2;
                    } else if (rand < 80.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (81 <= stat && stat <= 100) {
                    if (rand < 5.00) {
                        amt = min;
                    } else if (rand < 20.00) { 
                        amt = min + 1;
                    } else if (rand < 45.00) { 
                        amt = min + 2;
                    } else if (rand < 75.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (101 <= stat && stat <= 120) {
                    if (rand < 5.00) {
                        amt = min + 1;
                    } else if (rand < 15.00) { 
                        amt = min + 2;
                    } else if (rand < 50.00) { 
                        amt = max - 1;
                    } else {
                        amt = max;
                    }
                } else if (stat >= 121) {
                    amt = max;
                }
                break;
        }
        if (!(isHigherBetter)) {
            int temp = amt;
            amt = ((max + min) - temp);
        }
        return amt;
    }

    public static void checkWeather(Kreechin target) {
        if (weather == "Rain") {
            if (target.cat1 != 2 && target.cat2 != 2) {
                if ((Math.random() * 100) < 10.00) {
                    System.out.println(target.name + " was Soaked due to the rainstorm!");
                    target.status = 1;
                }
            }
        } else if (weather == "Sun") {
            if (target.cat1 != 3 && target.cat2 != 3) {
                if ((Math.random() * 100) < 10.00) {
                    System.out.println(target.name + " was Burned due to the extreme sunlight!");
                    target.status = 2;
                }
            }
        } else if (weather == "Snow") {
            if (target.cat1 != 6 && target.cat2 != 6) {
                if ((Math.random() * 100) < 10.00) {
                    System.out.println(target.name + " was Frozen due to the snowstorm!");
                    target.status = 4;
                }
            }
        } else if (weather == "Thunder") {
            if (target.cat1 == 5 || target.cat2 == 5) {
                if ((Math.random() * 100) < (3.50 + (target.LUK / 75))) {
                    System.out.println(target.name + " was struck by a lightning bolt!");
                    target.statChange(3, 2);
                    target.statChange(3, 4);
                }
            } else if (target.cat1 != 8 && target.cat2 != 8) {
                if ((Math.random() * 100) < (3.50 - (target.LUK / 75))) {
                    System.out.println(target.name + " was damaged by a lightning bolt!");
                    System.out.println(target.name + " was Shocked!");
                    target.damage(50);
                    target.status = 3;
                }
            } 
        } else if (weather == "Sand") {
            isAirClean = false;
            if (target.cat1 != 8 && target.cat2 != 8 && target.cat1 != 9 && target.cat2 != 9) {
                System.out.println(target.name + " was hurt by the sandstorm!");
                target.damage(determineChance(target.LUK, 8, 10, false));
            }
        } else if (weather == "Fog") {
            isAirClean = false;
            System.out.println(target.name + "'s stat changes were reset due to the unusual fog!");
            target.resetStats();
        } else if (weather == "Rainbow") {
            System.out.println(target.name + " was healed by the magical rainbows!");
            if (target.cat1 == 16 || target.cat2 == 16) {
                target.heal(2 * determineChance(target.LUK, 6, 10, true));
            } else {
                target.heal(determineChance(target.LUK, 6, 10, true));
            }
        }
    }

    public static void checkStatus(Kreechin target, boolean isPlayer, Team player, Team opponent) {
        switch (target.status) {
            case 0:
                target.statusTurns = 0;
                break;
            case 1:
                if (determineChance(target.LUK, 15) && target.statusTurns >= 3) {
                    System.out.println(target.name + " is no longer Soaked!");
                    target.status = 0;
                    target.statusTurns = 0;
                } else {
                    System.out.println(target.name + " is Soaked!");
                    target.statChange(-1, 4);
                    target.statusTurns++;
                }
                break;
            case 2:
                if (determineChance(target.LUK, 15) && target.statusTurns >= 3) {
                    System.out.println(target.name + " is no longer Burned!");
                    target.status = 0;
                    target.statusTurns = 0;
                } else {
                    System.out.println(target.name + " is Burned!");
                    System.out.println(target.name + " was hurt by its burn!");
                    target.damage(determineChance(target.LUK, 8, 10, false));
                    target.statusTurns++;
                }
                break;
            case 3:
                System.out.println(target.name + " is Shocked!");
                target.statusTurns++;
                break;
            case 4:
                if (determineChance(target.LUK, 30) && target.statusTurns >= 2) {
                    System.out.println(target.name + " is no longer Frozen!");
                    target.status = 0;
                    target.statusTurns = 0;
                } else {
                    System.out.println(target.name + " is Frozen!");
                    target.statusTurns++;
                }
                break;
            case 5:
                System.out.println(target.name + " is Wounded!");
                System.out.println(target.name + " was hurt by its wound!");
                target.damage(determineChance(target.LUK, 16, 20, false));
                if ((Math.random() * 100) < 10.00) {
                    target.statChange(-2, ((int) (Math.random() * 2) + 1));
                }
                target.statusTurns++;
                break;
            case 6:
                if (isPlayer) {
                    if (target.attractedKreechin == opponentLeft || target.attractedKreechin == opponentRight) {
                        System.out.println(target.name + " is in love with " + opponent.teamMembers[target.attractedKreechin].name + "!");
                        target.statusTurns++;
                    } else {
                        System.out.println(target.name + " is no longer Attracted!");
                        target.status = 0;
                        target.statusTurns = 0;
                        target.attractedKreechin = -10;
                    }
                } else {
                    if (target.attractedKreechin == playerLeft || target.attractedKreechin == playerRight) {
                        System.out.println(target.name + " is in love with " + player.teamMembers[target.attractedKreechin].name + "!");
                        target.statusTurns++;
                    } else {
                        System.out.println(target.name + " is no longer Attracted!");
                        target.status = 0;
                        target.statusTurns = 0;
                        target.attractedKreechin = -10;
                    }
                }
                break;
            case 7:
                if (determineChance(target.LUK, 25) && target.statusTurns >= 3 && isAirClean) {
                    System.out.println(target.name + " is no longer Suffocated!");
                    target.status = 0;
                    target.statusTurns = 0;
                } else {
                    System.out.println(target.name + " is Suffocated!");
                    System.out.println(target.name + " was hurt by suffocation!");
                    target.statusTurns++;
                    target.damage((int) Math.pow(2.0, target.statusTurns));
                }
                break;
            case 8:
                System.out.println(target.name + " is Enchanted!");
                if (target.cat1 != 15 && target.cat2 != 15) {
                    target.statChange(((int) Math.random() * 2 - 2), ((int) Math.random() * 7 + 1));
                } else if (Math.random() < 0.5) {
                    target.statChange(((int) Math.random() * 2 - 2), ((int) Math.random() * 7 + 1));
                }
                target.statusTurns++;
                break;
            case 9:
                if ((target.currEN * 1.0) / target.EN >= 0.75) {
                    if (determineChance(target.FOC, 80) && target.statusTurns >= 2) {
                        System.out.println(target.name + " is no longer Asleep!");
                        target.status = 0;
                        target.statusTurns = 0;
                    } else {
                        System.out.println(target.name + " is Asleep!");
                        System.out.println(target.name + " gained some energy!");
                        target.gainEnergy(determineChance(target.FOC, 2, 4, true));
                        target.statusTurns++;
                    }
                } else {
                    if (determineChance(target.FOC, 20) && target.statusTurns >= 2) {
                        System.out.println(target.name + " is no longer Asleep!");
                        target.status = 0;
                        target.statusTurns = 0;
                    } else {
                        System.out.println(target.name + " is Asleep!");
                        System.out.println(target.name + " gained some energy!");
                        target.gainEnergy(determineChance(target.FOC, 4, 6, true));
                        target.statusTurns++;
                    }
                }
                break;
            case 10:
                System.out.println(target.name + " is Confused!");
                target.statusTurns++;
                break;
        }
    }

    public static void checkForFaints() {

    }

    public static void doSwitches() {

    }

    public static void moveSecondaryEffects(Kreechin user, Kreechin target, HashMap<String, Integer> effects, Team player, Team opponent, boolean isPlayer) {

        //TARGET STAT CHANGES
        if (effects.containsKey("Target - Slightly Lower HP")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower HP"))) {
                target.statChange(-1, 1);
            }
        }
        if (effects.containsKey("Target - Lower HP")) {
            if (determineChance(user.LUK, effects.get("Target - Lower HP"))) {
                target.statChange(-2, 1);
            }
        }
        if (effects.containsKey("Target - Significantly Lower HP")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower HP"))) {
                target.statChange(-3, 1);
            }
        }
        if (effects.containsKey("Target - Slightly Raise HP")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise HP"))) {
                target.statChange(1, 1);
            }
        }
        if (effects.containsKey("Target - Raise HP")) {
            if (determineChance(user.LUK, effects.get("Target - Raise HP"))) {
                target.statChange(2, 1);
            }
        }
        if (effects.containsKey("Target - Significantly Raise HP")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise HP"))) {
                target.statChange(3, 1);
            }
        }
        if (effects.containsKey("Target - Slightly Lower Attack")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower Attack"))) {
                target.statChange(-1, 2);
            }
        }
        if (effects.containsKey("Target - Lower Attack")) {
            if (determineChance(user.LUK, effects.get("Target - Lower Attack"))) {
                target.statChange(-2, 2);
            }
        }
        if (effects.containsKey("Target - Significantly Lower Attack")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower Attack"))) {
                target.statChange(-3, 2);
            }
        }
        if (effects.containsKey("Target - Slightly Raise Attack")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise Attack"))) {
                target.statChange(1, 2);
            }
        }
        if (effects.containsKey("Target - Raise Attack")) {
            if (determineChance(user.LUK, effects.get("Target - Raise Attack"))) {
                target.statChange(2, 2);
            }
        }
        if (effects.containsKey("Target - Significantly Raise Attack")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise Attack"))) {
                target.statChange(3, 2);
            }
        }
        if (effects.containsKey("Target - Slightly Lower Defense")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower Defense"))) {
                target.statChange(-1, 3);
            }
        }
        if (effects.containsKey("Target - Lower Defense")) {
            if (determineChance(user.LUK, effects.get("Target - Lower Defense"))) {
                target.statChange(-2, 3);
            }
        }
        if (effects.containsKey("Target - Significantly Lower Defense")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower Defense"))) {
                target.statChange(-3, 3);
            }
        }
        if (effects.containsKey("Target - Slightly Raise Defense")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise Defense"))) {
                target.statChange(1, 3);
            }
        }
        if (effects.containsKey("Target - Raise Defense")) {
            if (determineChance(user.LUK, effects.get("Target - Raise Defense"))) {
                target.statChange(2, 3);
            }
        }
        if (effects.containsKey("Target - Significantly Raise Defense")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise Defense"))) {
                target.statChange(3, 3);
            }
        }
        if (effects.containsKey("Target - Slightly Lower Speed")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower Speed"))) {
                target.statChange(-1, 4);
            }
        }
        if (effects.containsKey("Target - Lower Speed")) {
            if (determineChance(user.LUK, effects.get("Target - Lower Speed"))) {
                target.statChange(-2, 4);
            }
        }
        if (effects.containsKey("Target - Significantly Lower Speed")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower Speed"))) {
                target.statChange(-3, 4);
            }
        }
        if (effects.containsKey("Target - Slightly Raise Speed")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise Speed"))) {
                target.statChange(1, 4);
            }
        }
        if (effects.containsKey("Target - Raise Speed")) {
            if (determineChance(user.LUK, effects.get("Target - Raise Speed"))) {
                target.statChange(2, 4);
            }
        }
        if (effects.containsKey("Target - Significantly Raise Speed")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise Speed"))) {
                target.statChange(3, 4);
            }
        }
        if (effects.containsKey("Target - Slightly Lower Energy")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower Energy"))) {
                target.statChange(-1, 5);
            }
        }
        if (effects.containsKey("Target - Lower Energy")) {
            if (determineChance(user.LUK, effects.get("Target - Lower Energy"))) {
                target.statChange(-2, 5);
            }
        }
        if (effects.containsKey("Target - Significantly Lower Energy")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower Energy"))) {
                target.statChange(-3, 5);
            }
        }
        if (effects.containsKey("Target - Slightly Raise Energy")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise Energy"))) {
                target.statChange(1, 5);
            }
        }
        if (effects.containsKey("Target - Raise Energy")) {
            if (determineChance(user.LUK, effects.get("Target - Raise Energy"))) {
                target.statChange(2, 5);
            }
        }
        if (effects.containsKey("Target - Significantly Raise Energy")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise Energy"))) {
                target.statChange(3, 5);
            }
        }
        if (effects.containsKey("Target - Slightly Lower Focus")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower Focus"))) {
                target.statChange(-1, 6);
            }
        }
        if (effects.containsKey("Target - Lower Focus")) {
            if (determineChance(user.LUK, effects.get("Target - Lower Focus"))) {
                target.statChange(-2, 6);
            }
        }
        if (effects.containsKey("Target - Significantly Lower Focus")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower Focus"))) {
                target.statChange(-3, 6);
            }
        }
        if (effects.containsKey("Target - Slightly Raise Focus")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise Focus"))) {
                target.statChange(1, 6);
            }
        }
        if (effects.containsKey("Target - Raise Focus")) {
            if (determineChance(user.LUK, effects.get("Target - Raise Focus"))) {
                target.statChange(2, 6);
            }
        }
        if (effects.containsKey("Target - Significantly Raise Focus")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise Focus"))) {
                target.statChange(3, 6);
            }
        }
        if (effects.containsKey("Target - Slightly Lower Luck")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Lower Luck"))) {
                target.statChange(-1, 7);
            }
        }
        if (effects.containsKey("Target - Lower Luck")) {
            if (determineChance(user.LUK, effects.get("Target - Lower Luck"))) {
                target.statChange(-2, 7);
            }
        }
        if (effects.containsKey("Target - Significantly Lower Luck")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Lower Luck"))) {
                target.statChange(-3, 7);
            }
        }
        if (effects.containsKey("Target - Slightly Raise Luck")) {
            if (determineChance(user.LUK, effects.get("Target - Slightly Raise Luck"))) {
                target.statChange(1, 7);
            }
        }
        if (effects.containsKey("Target - Raise Luck")) {
            if (determineChance(user.LUK, effects.get("Target - Raise Luck"))) {
                target.statChange(2, 7);
            }
        }
        if (effects.containsKey("Target - Significantly Raise Luck")) {
            if (determineChance(user.LUK, effects.get("Target - Significantly Raise Luck"))) {
                target.statChange(3, 2);
            }
        }


        //USER STAT CHANGES
        if (effects.containsKey("User - Slightly Lower HP")) {
            if (determineChance(user.LUK, effects.get("User - Slightly Lower HP"))) {
                user.statChange(-1, 1);
            }
        }


        //TARGET STATUSES
        if (effects.containsKey("Target - Soak")) {
            if (determineChance(user.LUK, effects.get("Target - Soak"))) {
                target.status = 1;
                System.out.println(target.name + " became Soaked!");
            }
        }
        if (effects.containsKey("Target - Burn")) {
            if (determineChance(user.LUK, effects.get("Target - Burn"))) {
                target.status = 2;
                System.out.println(target.name + " became Burned!");
            }
        }
        if (effects.containsKey("Target - Shock")) {
            if (determineChance(user.LUK, effects.get("Target - Shock"))) {
                target.status = 3;
                System.out.println(target.name + " became Shocked!");
            }
        }
        if (effects.containsKey("Target - Freeze")) {
            if (determineChance(user.LUK, effects.get("Target - Freeze"))) {
                target.status = 4;
                System.out.println(target.name + " became Frozen!");
            }
        }
        if (effects.containsKey("Target - Wound")) {
            if (determineChance(user.LUK, effects.get("Target - Wound"))) {
                target.status = 5;
                System.out.println(target.name + " became Wounded!");
            }
        }
        if (effects.containsKey("Target - Attract")) {
            if (determineChance(user.LUK, effects.get("Target - Attract"))) {
                if (user.isMale != target.isMale) {
                    target.status = 6;
                    System.out.println(target.name + " fell in love with " + user.name + "!");
                    if (isPlayer) {
                        for (int i = 0; i < player.teamMembers.length; i++) {
                            if (player.teamMembers[i].equals(user)) {
                                target.attractedKreechin = i;
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < opponent.teamMembers.length; i++) {
                            if (opponent.teamMembers[i].equals(user)) {
                                target.attractedKreechin = i;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (effects.containsKey("Target - Suffocate")) {
            if (determineChance(user.LUK, effects.get("Target - Suffocate"))) {
                target.status = 7;
                System.out.println(target.name + " became Suffocated!");
            }
        }
        if (effects.containsKey("Target - Enchant")) {
            if (determineChance(user.LUK, effects.get("Target - Enchant"))) {
                target.status = 8;
                System.out.println(target.name + " became Enchanted!");
            }
        }
        if (effects.containsKey("Target - Sleep")) {
            if (determineChance(user.LUK, effects.get("Target - Sleep"))) {
                target.status = 9;
                System.out.println(target.name + " fell Asleep!");
            }
        }
        if (effects.containsKey("Target - Confuse")) {
            if (determineChance(user.LUK, effects.get("Target - Confuse"))) {
                target.status = 10;
                System.out.println(target.name + " became Confused!");
            }
        }


        //USER STATUSES


        //WEATHER SETTING


        //PROTECTING MOVES
    }

}

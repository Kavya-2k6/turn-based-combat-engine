import java.util.Scanner;

public class Main {
    static void printStatus(Character c) {
    System.out.println(
        c.getName() + " HP: " + c.getHp() + "/" + c.getMaxHp()
        + " | Mana: " + c.getMana()
    );
}

    public static void main(String[] args) {
        Character hero = new Character("hero", 100, 15);
        Character enemy = new Character("orc", 98, 13);
        Scanner sc = new Scanner(System.in);

      while (hero.isAlive() && enemy.isAlive()) {

    System.out.println("\n--- YOUR TURN ---");
    printStatus(hero);
    printStatus(enemy);


    System.out.println("choose action:");
    System.out.println("1. Attack");
    System.out.println("2. Heal");
    System.out.println("3. Special Attack (Mana: " + hero.getMana() + ")");
    System.out.print("> ");

    String choice = sc.nextLine();

    if (choice.equals("1")) hero.attack(enemy);
    else if (choice.equals("2")) hero.heal();
    else if (choice.equals("3")) hero.specialAttack(enemy);
    else System.out.println("Invalid choice.");

    // SHOW result of player action
  
    System.out.println("------------");
    printStatus(hero);
    printStatus(enemy);


    if (!enemy.isAlive()) break;

    System.out.println("\n--- ENEMY TURN ---");
    enemy.takeTurn(hero);

    // SHOW result of enemy action
    System.out.println("------------");
    printStatus(hero);
    printStatus(enemy);

    hero.reduceCooldown();
    enemy.reduceCooldown();
}


        System.out.println(hero.isAlive() ? "Hero wins!" : "Enemy wins!");
        sc.close();
    }
}

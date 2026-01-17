import java.util.Scanner;
class Character{
    String name;
    int hp;
    int attack;
    int maxHp;
    int healCooldown;
    int healsLeft;
    int mana;


    Character(String name ,int hp ,int attack){
        this.name=name;
        this.hp=hp;
        this.maxHp = hp; 
        this.attack=attack;
        this.mana = 20;
        this.healsLeft = 3;
        this.healCooldown = 0;

    }

    void takedamage(int dmg){
        hp-=dmg;
        if (hp<0) hp=0;
    }

    void attack(Character target){
        int dmg = attack + (int) (Math.random()*5);// +0 to +4
        System.out.println(name+ " attacks "+ target.name + " for "+ dmg);
        target.takedamage(dmg);
    }
    
    void specialAttack(Character target) {
    int manaCost = 5;

    if (mana < manaCost) {
        System.out.println(name + " tried special attack but not enough mana!");
        return;
    }

    int dmg = attack * 2;
    mana -= manaCost;

    System.out.println(name + " uses SPECIAL ATTACK for " + dmg +
                       " (Mana left: " + mana + ")");
    target.takedamage(dmg);
}

    void heal() {
    if (healsLeft <= 0) {
        System.out.println(name + " has no heals left!");
        return;
    }

    if (healCooldown > 0) {
        System.out.println(name + " cannot heal yet (Cooldown: " + healCooldown + ")");
        return;
    }

    int healAmount = maxHp / 5; // heal 20% of max HP

    // Bonus heal if HP is low (< 1/3 max)
    if (hp <= maxHp / 3) {
        healAmount += maxHp / 10; // extra 10%
    }

    hp += healAmount;
    if (hp > maxHp) hp = maxHp;

    healsLeft--;
    healCooldown = 2;

    System.out.println(
        name + " heals for " + healAmount +
        " (HP: " + hp + "/" + maxHp +
        ", Heals left: " + healsLeft + ")"
    );
}

void enemyTurn(Character target) {
    // Priority 1: Heal if critically low
    if (hp < maxHp / 4 && healsLeft > 0 && healCooldown == 0) {
        heal();
    }
    // Priority 2: Burst if healthy and has mana
    else if (mana >= 5 && hp > maxHp / 2) {
        specialAttack(target);
    }
    // Priority 3: Default
    else {
        attack(target);
    }
}

    boolean isAlive(){
        return hp>0;
    }
}

public class Main{
    public static void main(String[]args){
        Character hero = new Character("hero", 100, 15);
        Character enemy = new Character("orc",98,13 );
        Scanner sc = new Scanner(System.in);
    
    while (hero.isAlive() && enemy.isAlive()){
       System.out.println("choose action:");
       System.out.println("1. Attack");
       System.out.println("2. heal");
       System.out.println("3. Special Attack (Mana: " + hero.mana + ")");
       System.out.print("> ");

       String choice = sc.nextLine();

       if (choice.equals("1")){
        hero.attack(enemy);
       }
       else if (choice.equals("2")){
        hero.heal();
       }
       else if (choice.equals("3")) {
    hero.specialAttack(enemy);
       }
       else {
        System.out.println("Invalid choice. Turn wasted.");
    }
    System.out.println("Enemy HP: " + enemy.hp + "/" + enemy.maxHp);
    System.out.println("Hero HP: " + hero.hp + "/" + hero.maxHp);

    System.out.println("------------");
     if (!enemy.isAlive()) break;
    
// Enemy turn (always attacks)
    enemy.enemyTurn(hero);
    System.out.println("Hero HP: " + hero.hp + "/" + hero.maxHp);

    System.out.println("------------");
    if (!hero.isAlive()) {
        break;
}
if (hero.healCooldown > 0) hero.healCooldown--;
if (enemy.healCooldown > 0) enemy.healCooldown--;
    }
  


   if (hero.isAlive()) {
    System.out.println("Hero wins!");
} else {
    System.out.println("Enemy wins!");
}
sc.close();

    }
    }

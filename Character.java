public class Character {
    private String name;
    private int hp;
    private int attack;
    private int maxHp;
    private int healCooldown;
    private int healsLeft;
    private int mana;

    Character(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.mana = 20;
        this.healsLeft = 3;
        this.healCooldown = 0;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0)
            hp = 0;
    }

    public void attack(Character target) {
        int dmg = attack + (int) (Math.random() * 5);
        System.out.println(name + " attacks " + target.getName() + " for " + dmg);
        target.takeDamage(dmg);
    }

    public void specialAttack(Character target) {
        int manaCost = 5;
        if (mana < manaCost) {
            System.out.println(name + " tried special attack but not enough mana!");
            return;
        }
        int dmg = attack * 2;
        mana -= manaCost;
        System.out.println(name + " uses SPECIAL ATTACK for " + dmg);
        target.takeDamage(dmg);
    }

    public void heal() {
        if (healsLeft <= 0 || healCooldown > 0)
            return;

        int healAmount = maxHp / 5;
        if (hp <= maxHp / 3)
            healAmount += maxHp / 10;

        hp += healAmount;
        if (hp > maxHp)
            hp = maxHp;

        healsLeft--;
        healCooldown = 2;

        System.out.println(name + " heals for " + healAmount);
    }

    private boolean shouldHeal() {
        return hp < maxHp / 4 && healsLeft > 0 && healCooldown == 0;
    }

    private boolean shouldUseSpecial() {
        return mana >= 5 && hp > maxHp / 2;
    }

    public void takeTurn(Character target) {
        if (shouldHeal()) {
            heal();
        } else if (shouldUseSpecial()) {
            specialAttack(target);
        } else {
            attack(target);
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMana() {
        return mana;
    }

    public boolean canHeal() {
        return healCooldown == 0 && healsLeft > 0;
    }

    public void reduceCooldown() {
        if (healCooldown > 0)
            healCooldown--;
    }

    public String getName() {
        return name;
    }




}

}

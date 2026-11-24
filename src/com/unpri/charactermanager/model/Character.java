package com.unpri.charactermanager.model;

public class Character {

    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    public Character(String name, CharacterType type, int level) {
        validateName(name);
        validateType(type);
        validateLevel(level, type);

        this.id = 0;
        this.name = name.trim();
        this.type = type;
        this.mana = 0;
        setLevel(level);
    }

    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {
        validateName(name);
        validateType(type);
        validateLevel(level, type);

        this.id = id;
        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama tidak boleh kosong"
            );
        }
    }

    private void validateType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException(
                    "Role wajib dipilih"
            );
        }
    }

    private void validateLevel(int level, CharacterType type) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                    "Level harus 1-99"
            );
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                    "MAGE tidak boleh memiliki level di atas 60"
            );
        }
    }

    private int calculateBaseHP(int level) {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack(int level) {
        return 10 + (level * 5);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        validateLevel(level, type);

        this.level = level;
        this.hitPoints = calculateBaseHP(level);
        this.attackPower = calculateBaseAttack(level);
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                    "Mana tidak boleh negatif!"
            );
        }

        this.mana = mana;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hp=" + hitPoints +
                ", atk=" + attackPower +
                ", mana=" + mana +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Character)) return false;

        Character other = (Character) obj;

        return this.name.equalsIgnoreCase(other.name);
    }
}
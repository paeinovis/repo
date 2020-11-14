public class Pakuri {
    private int attack, defense, speed;
    private String speciesName;


    public Pakuri(String species) {
        int nameLength = species.length();

        this.attack = (nameLength * 7) + 9;
        this.defense = (nameLength * 5) + 17;
        this.speed = (nameLength * 6) + 13;
    }


    public String getSpecies() {
        return speciesName;
    }


    public int getAttack() {
        return attack;
    }


    public int getDefense() {
        return defense;
    }


    public int getSpeed() {
        return speed;
    }


    public void setAttack(int newAttack) {
        attack = newAttack;
    }


    public void evolve() {
        attack *= 2;
        defense *= 4;
        speed *= 3;
    }











}

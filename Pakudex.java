import java.util.ArrayList;

public class Pakudex {

    private ArrayList pakuMorphList = new ArrayList();
    private Pakuri[] pakuriList;
    private Pakuri paku;

    public Pakudex() {
        pakuriList = new Pakuri[20];
    }


    public Pakudex(int capacity) {
        pakuriList = new Pakuri[capacity];
    }


    public int getSize() {
        int size = 0;
        size = pakuMorphList.size();
        return size;
    }


    public int getCapacity() {
        int capacity = pakuriList.length;
        return capacity;
    }


    public String[] getSpeciesArray() {
        int size = getSize();
        int capacity = getCapacity();

        String[] speciesArray = new String[capacity];

        if (size == 0) {
            speciesArray = null;
            return speciesArray;
        }
        else {
            for (int i = 0; i < size; i++) {
                speciesArray[i] = String.valueOf(pakuMorphList.get(i));
            }
            return speciesArray;
        }
    }


    public int[] getStats(String species) {
        int attack;
        int defense;
        int speed;
        int[] statsGot = null;

        try {
            attack = paku.getAttack();
            defense = paku.getDefense();
            speed = paku.getSpeed();

            statsGot = new int[]{attack, defense, speed};
        } catch(Exception ignored) {}

        return statsGot;
    }


    public void sortPakuri() {
        String tempPakSpot;
        int size = getSize();
        String[] speciesArray = getSpeciesArray();
        boolean sorted = false;
        int j = 0;

        do {
            int swaps = 0;
            for (int i = 0; i + 1 < size; i++) {

                char a = speciesArray[i].toLowerCase().charAt(0);
                char b = speciesArray[i + 1].toLowerCase().charAt(0);
                if (a > b) {
                    tempPakSpot = speciesArray[i];
                    speciesArray[i] = speciesArray[i + 1];
                    speciesArray[i + 1] = tempPakSpot;
                    swaps++;
                }
                else if (a == b) {
                    for (j = j; j < size; j++) {
                        a = speciesArray[i].toLowerCase().charAt(j);
                        b = speciesArray[i + 1].toLowerCase().charAt(j);

                        if (a > b) {
                            tempPakSpot = speciesArray[i];
                            speciesArray[i] = speciesArray[i + 1];
                            speciesArray[i + 1] = tempPakSpot;
                            swaps++;
                        }
                    }
                }
            }
            if (swaps == 0) {
                sorted = true;
            }
        } while (!sorted);
        for (int k = 0; k < size; k++) {
            pakuMorphList.set(k, speciesArray[k]);
        }
    }


    public boolean addPakuri(String species) {
        int capacity = getCapacity();
        boolean added = false;

        if (getSize() != capacity) {
            added = true;
            paku = new Pakuri(species);
            pakuMorphList.add(species);
        }

        if (duplicate(species, getSpeciesArray())) {
            added = false;
        }

        return added;
    }


    public boolean evolveSpecies(String species) {
        boolean canEvolve;
        if (pakuriList == null) {
            canEvolve = false;
        }
        else {
            canEvolve = (duplicate(species, getSpeciesArray()));
        }
        if (canEvolve == true) {
            paku.evolve();
        }
        return canEvolve;
    }


    public static boolean duplicate(String species, String[] pakuList) {
        boolean duplicate = false;
        try {
            for (String s : pakuList) {
                if (species.equals(s)) {
                    duplicate = true;
                    break;
                }
            }
        } catch (Exception ignored) {}
        return duplicate;
    }
}

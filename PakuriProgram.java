import java.util.Arrays;
import java.util.Scanner;

public class PakuriProgram {


    public static void menu() {
        String menu =
                "\nPakudex Main Menu" +
                "\n-----------------" +
                "\n1. List Pakuri" +
                "\n2. Show Pakuri" +
                "\n3. Add Pakuri" +
                "\n4. Evolve Pakuri" +
                "\n5. Sort Pakuri" +
                "\n6. Exit\n";
        System.out.print(menu);
    }

    public static void stats(String species, Pakudex paku) {
        int[] statList =  paku.getStats(species);

        int attack = statList[0];
        int defense = statList[1];
        int speed = statList[2];

        System.out.println();
        System.out.println("Species: " + species);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: "  + defense);
        System.out.println("Speed: "  + speed);
    }


    public static void printList(String[] pakuList, Pakudex paku) {
        int size;
        size = pakuList.length;
        System.out.println("Pakuri In Pakudex:");
        try {
            for (int i = 0; i < size; i++) {
                System.out.print((i + 1) + ". " + pakuList[i]);
                System.out.println();
            }
        } catch (Exception ignored) {}
    }



    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        int capacity = 20;
        String userInput;
        String[] pakuList = {};
        String species;
        boolean accepted;
        String pakTest;

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        do {
            System.out.print("Enter max capacity of the Pakudex: ");
            accepted = true;
            try {
                capacity = scnr.nextInt();
                if (capacity <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                accepted = false;
                System.out.println("Please enter a valid size.");
                scnr.next();
            }
        } while (!accepted);

        System.out.println("The Pakudex can hold " + capacity +" species of Pakuri.");

        Pakudex paku = new Pakudex(capacity);

        do {
            menu();
            System.out.print("\nWhat would you like to do? ");

            userInput = scnr.next();

            switch (userInput) {
                case "1": {
                    pakuList = paku.getSpeciesArray();
                    try {
                        pakTest = pakuList[0];
                    } catch (Exception e) {
                        System.out.println("No Pakuri in Pakudex yet!");
                        break;
                    }
                    printList(pakuList, paku);
                    break;
                }
                case "2": {
                    System.out.print("Enter the name of the species to display: ");
                    species = scnr.next();
                    boolean found = Pakudex.duplicate(species, pakuList);

                    if (!found) {
                        System.out.println("Error: No such Pakuri!");
                        break;
                    }
                    else {
                        stats(species, paku);
                        break;
                    }
                }
                case "3": {
                     if (paku.getSize() >= paku.getCapacity()) {
                        System.out.println("Error: Pakudex is full!");
                        break;
                     }

                    System.out.print("Enter the name of the species to add: ");
                    species = scnr.next();

                    boolean duplicate = Pakudex.duplicate(species, pakuList);

                    if (!duplicate) {
                        paku.addPakuri(species);
                        pakuList = paku.getSpeciesArray();
                        System.out.println("Pakuri species " + species + " successfully added!");
                    }
                    else {
                        System.out.println("Error: Pakudex already contains this species!");
                    }
                    break;
                }
                case "4" : {
                    System.out.print("Enter the name of the species to evolve: ");
                    species = scnr.next();
                    boolean canEvolve = paku.evolveSpecies(species);
                    try {
                        if (!canEvolve) {
                            System.out.println("Error: No such Pakuri!");
                        }
                        else {
                            System.out.println(species + " has evolved!");
                        }
                    } catch(NullPointerException nex) {
                        System.out.println("Error: No Pakuri in Pakudex yet!");
                    }
                    break;
                }
                case "5": {
                    paku.sortPakuri();
                    System.out.println("Pakuri have been sorted!");
                    break;
                }
                case "6": {
                    System.out.print("Thanks for using Pakudex! Bye!");
                    break;
                }
                default: {
                    System.out.println("Unrecognized menu selection!");
                    break;
                }
            }
        } while (!userInput.equals("6"));
    }
















//
        /*    do {
        System.out.println("Enter max capacity of the Pakudex: ");

        if (scnr.hasNextInt() && (scnr.nextInt() > 0)) {
            accepted = true;
            capacity = scnr.nextInt();
        }
        else {
            System.out.println("Please enter a valid size.");
            accepted = false;
        }
    } while (!accepted);
      */








}

import java.util.Scanner;

public class NumericConversion {




    public static short hexCharDecode(char digit) {        //Decodes a single hexadecimal digit and returns its value.
        short newDigit = 0;

        if (Character.isDigit(digit)) {                    //https://www.tutorialspoint.com/java/lang/character_isdigit.htm &
            newDigit = (short)(digit-48);                  //dumb work-around for char printing out like ascii or something https://stackoverflow.com/questions/17984975/convert-int-to-char-in-java & https://way2java.com/casting-operations/char-to-short-java/
        }
        else {
            switch (digit) {
                case 'a':
                case 'A':
                    newDigit = 10;
                    break;
                case 'b':
                case 'B':
                    newDigit = 11;
                    break;
                case 'c':
                case 'C':
                    newDigit = 12;
                    break;
                case 'd':
                case 'D':
                    newDigit = 13;
                    break;
                case 'e':
                case 'E':
                    newDigit = 14;
                    break;
                case 'f':
                case 'F':
                    newDigit = 15;
                    break;
                default:
                    break;
            }
        }
            return newDigit;
    }

    public static long hexStringDecode(String hex) {           //Decodes an entire hexadecimal string and returns its value.
        long totalHex = 0;
        long checkExtra = 0;
        int decodeAbs = hex.length();
            if (decodeAbs > 1) {
                char maxChar = (hex.charAt(0));
                char maxMin = (hex.charAt(1));
                String check = new StringBuilder().append(maxChar).append(maxMin).toString();        //https://www.javatpoint.com/string-concatenation-in-java
                if (check.equals("0b") || check.equals("0B") || check.equals("0x") || check.equals("0X")) {
                checkExtra = 2;
                }
            }
            for (int i = decodeAbs - 1; i >= checkExtra; i--) {
                char charDecode = (hex.charAt(i));                                                       //iterates by number of loops per digit place
                long newDigit = ((long) Math.pow(16, decodeAbs - i - 1) * (hexCharDecode(charDecode)));  //converts result to long and multiplies the given char with 16 to the power of 0 -> inf
                totalHex += newDigit;
            }
        return totalHex;
    }

    public static short binaryStringDecode(String binary) {    //Decodes a binary string and returns its value.
        short totalBin = 0;
        int checkExtra = 0;
        int decodeAbs = binary.length();
            if (decodeAbs > 1) {
                char maxChar = (binary.charAt(0));
                char maxMin = (binary.charAt(1));
                String check = new StringBuilder().append(maxChar).append(maxMin).toString();        //https://www.javatpoint.com/string-concatenation-in-java
                if (check.equals("0b") || check.equals("0B") || check.equals("0x") || check.equals("0X")) {
                checkExtra = 2;
                }
            }
            for (int i = decodeAbs - 1; i >= checkExtra; i--) {
                char charDecode = (binary.charAt(i));
                short newDigit = ((short) ((Math.pow(2, decodeAbs - i - 1) * (charDecode - 48))));        //converts result to short and multiplies the given char (1 or 0) (minus 48 because ASCII) with 2 to the power of 0 -> inf
                totalBin += newDigit;
            }
        return totalBin;
    }

    public static String binaryToHex(String binary) {         //Decodes a binary string, re-encodes it as hexadecimal, and returns the hexadecimal string.

        long numDec = binaryStringDecode(binary);              //Obtaining decimal version of it just because I'm more used to decimals I guess
        String hexCharIndiv = "";
        String totalHexChar = "";
        int divRem;
        int numDiv;

        do {
            double expNum = (Math.log10(numDec) / Math.log10(16));  //finding to what exponent is the maximum that the deci values is of 16
            double expRound = Math.floor(expNum);                   //rounding to the lowest integer which will serve as how many loops to do down to 0

            if (numDec > 16) {
                numDiv = (int) Math.floor((numDec / Math.pow(16, expRound)));   //Dividing 16 to the power of the rounded max exponent found, then rounding that number and converting it to an integer
                divRem = (int) (numDec % 16);
            }
            else {           //for if the binary decimal ends up being 0-15
                numDiv = (int) numDec;
                divRem = 0;
            }

            switch (numDiv) {
                    case 0:
                        hexCharIndiv = "0";
                        break;
                    case 1:
                        hexCharIndiv = "1";
                        break;
                    case 2:
                        hexCharIndiv = "2";
                        break;
                    case 3:
                        hexCharIndiv = "3";
                        break;
                    case 4:
                        hexCharIndiv = "4";
                        break;
                    case 5:
                        hexCharIndiv = "5";
                        break;
                    case 6:
                        hexCharIndiv = "6";
                        break;
                    case 7:
                        hexCharIndiv = "7";
                        break;
                    case 8:
                        hexCharIndiv = "8";
                        break;
                    case 9:
                        hexCharIndiv = "9";
                        break;
                    case 10:
                        hexCharIndiv = "A";
                        break;
                    case 11:
                        hexCharIndiv = "B";
                        break;
                    case 12:
                        hexCharIndiv = "C";
                        break;
                    case 13:
                        hexCharIndiv = "D";
                        break;
                    case 14:
                        hexCharIndiv = "E";
                        break;
                    case 15:
                        hexCharIndiv = "F";
                        break;
                    default:
                        break;
                   }
                numDec -= numDiv * Math.pow(16, expRound);
                totalHexChar += hexCharIndiv;
            } while (divRem != 0);
            return totalHexChar;
    }

    public static void main(String[] args) {
        int userChoice;
        Scanner scnr = new Scanner(System.in);

        do {
            System.out.println("Decoding Menu \n------------- \n1. Decode hexadecimal \n2. Decode binary \n3. Convert binary to hexadecimal \n4. Convert hexadecimal to decimal \n5: Quit \n\nPlease enter an option: ");
            userChoice = scnr.nextInt();

            switch(userChoice) {
                case 1: {
                    System.out.println("Please enter the numeric string to convert: ");
                    String numDecode = scnr.next();
                    int decodeAbs = numDecode.length();
                    if (decodeAbs == 1) {
                        char charDecode = (numDecode.charAt(0));                        //https://www.javatpoint.com/java-string-to-char
                        System.out.println("Result: " + hexCharDecode(charDecode));
                    }
                    else if (decodeAbs > 1) {
                        System.out.println("Result: " + hexStringDecode(numDecode));
                    }
                    break;
                }
                case 2: {
                    System.out.println("Please enter the numeric string to convert: ");
                    String numDecode = scnr.next();
                    System.out.println("Result: " + binaryStringDecode(numDecode));
                    break;
                }
                case 3: {
                    System.out.println("Please enter the numeric string to convert: ");
                    String numDecode = scnr.next();
                    System.out.println("Result: " + (binaryToHex(numDecode)));
                    break;
                }
                case 4: {
                    System.out.println("Please enter the numeric string to convert: ");
                    String numDecode = scnr.next();
                    System.out.println("Result: " + hexStringDecode(numDecode));
                    break;
                }
                default: {
                    System.out.println("Goodbye!");
                    break;
                }
            }
        } while (userChoice != 5);
    }
}

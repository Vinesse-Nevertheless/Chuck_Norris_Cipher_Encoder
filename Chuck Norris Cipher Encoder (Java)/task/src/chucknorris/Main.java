package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().promptUser();
    }

    void promptUser() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = in.nextLine();

            switch (operation) {
                case "encode" -> {
                    System.out.println("Input string:");
                    String message = in.nextLine();
                    String binaryStr = getBinaryString(message);
                    printEncryption(binaryStr);
                }
                case "decode" -> {
                    System.out.println("Input encoded string:");
                    String message = in.nextLine();
                    String binaryStr = decryptToBinary(message);
                    if (!binaryStr.isEmpty()) {
                        printDecrypted(binaryStr);
                    }
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    in.close();
                    return;
                }
                default -> {
                    System.out.println("There is no '" + operation + "' operation\n");
                }
            }

        }
    }

    private String decryptToBinary(String message) {
        String[] mess = message.split(" ");
        StringBuilder bStr = new StringBuilder();
        for (int i = 0; i < mess.length; i+=2) {
            //if there's a code key, but no 0s to decode or
            //if there are improper code keys
            if ( i + 1 >= mess.length || (!mess[i].equals("00") && !mess[i].equals("0") )){
                System.out.println("Encoded string is not valid.\n");
                return "";
            }
            switch (mess[i]) {
                    case "00" -> convert(mess[i + 1], "0", bStr);
                    case "0" -> convert(mess[i + 1], "1", bStr);
                }
        }
        return bStr.toString();
    }

    private void convert(String message, String c, StringBuilder bStr) {
        for (int i = 0; i < message.length(); i++) {
            if ((bStr.length() + 1) % 8 == 0) {
                bStr.append(" ");
            }
            bStr.append(c);
        }
    }

    private void printDecrypted(String binaryStr) {
        String[] bStr = binaryStr.split(" ");
        StringBuilder decryption = new StringBuilder();

        for (int i = 0; i < bStr.length; i++) {
            //if not 7 bits
            if (bStr[i].length() != 7){
                System.out.println("Encoded string is not valid.\n");
                return;
            }
            binaryStrConverter(bStr[i], decryption);
        }

        System.out.println("Decoded string:");
        System.out.print(decryption);

        System.out.println();
        System.out.println();
    }

    private void binaryStrConverter(String binaryStr, StringBuilder decryption) {
        int sum = 0;
        int pow = 6;
        for (int i = 0; i < binaryStr.length(); i++) {
            int n = Integer.parseInt(binaryStr.substring(i, i + 1));
            sum += (int) (Math.pow(2, pow--)) * n;
        }

        decryption.append( (char) sum);

    }

    private String getBinaryString(String message) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String binaryString = Integer.toBinaryString(message.charAt(i));
            String sevenBitStr = String.format("%7s", binaryString).replace(' ', '0');
            res.append(sevenBitStr);
        }

        return res.toString();
    }

    private void printEncryption(String binaryStr) {
        System.out.println("Encoded string:");

        for (int i = 0; i < binaryStr.length(); i++) {
            if (i > 0 && binaryStr.charAt(i - 1) == binaryStr.charAt(i)) {
                System.out.print("0");
            } else {
                if (i != 0) {
                    System.out.print(" ");
                }
                switch (binaryStr.charAt(i)) {
                    case '0' -> System.out.print("00 0");
                    case '1' -> System.out.print("0 0");
                }
            }
        }
        System.out.println();
        System.out.println();
    }
}
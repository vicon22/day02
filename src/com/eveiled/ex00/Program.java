package com.eveiled.ex00;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    private final static String[] hexSymbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public final static int BITS_PER_HEX_DIGIT = 4;

    private static String toHexFromByte(final byte b)
    {
        byte leftSymbol = (byte)((b >>> BITS_PER_HEX_DIGIT) & 0x0f);
        byte rightSymbol = (byte)(b & 0x0f);

        return (hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
    }

    private static String findMagicNumbers(String path, String[][] type)
    {
        String[] pathFirstsBytes = new String[10];
        try {
            FileInputStream fis = new FileInputStream(path);
            for (int i = 0; i < 10; i++) {
                pathFirstsBytes[i] = toHexFromByte((byte) fis.read()).toUpperCase(Locale.ROOT);
            }
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND");
            return "";
        }
        for (int i = 0; i < type.length; i++)
        {
            int k = 0;
            for (int j = 1; j < type[i].length; j++)
            {
                if (type[i][j].equals(pathFirstsBytes[j - 1])){
                    k++;
                }
                if (k == (type[i].length - 1)){
                    System.out.println("PROCESSED");
                    return type[i][0];
                }
            }
        }
        System.out.println("UNDEFINED");
        return "";
    }

    public static void main(String[] args) {

        String ans = "";
        Scanner scanner = new Scanner(System.in);

        String[][] type = preparingFormatsSignature();
        String path = scanner.nextLine();
        while (!path.equals("42")) {
            String file = findMagicNumbers(path, type);
            if (!file.equals("")) {
                ans = ans + file + "\n";
            }
            path = scanner.nextLine();
        }
        try {
            FileOutputStream fos = new FileOutputStream("result.txt");
            fos.write(ans.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[][] preparingFormatsSignature(){

        int b = 0;
        String str = "";
        String ans = "";
        String[] magicNumbers;
        String[][] type = new String[0][];
        try {
            FileInputStream fis0 = new FileInputStream("signatures.txt");
            while ((b = fis0.read()) != -1) {
                str = str + (char)b;
            }
            magicNumbers = str.split("\n");
            type = new String[magicNumbers.length][];
            for (int i = 0; i < magicNumbers.length; i++) {
                type[i] = magicNumbers[i].split(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (type);
    }
}

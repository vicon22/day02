package com.eveiled.ex01;

import java.io.*;
import java.util.*;

public class Program {

    public static Integer frequencyCounter(String word, String[] splitString) {

        long count = 0L;
        count = Arrays.stream(splitString).filter((x) -> x.equals(word)).count();
        return  (int)count;
    }


    public static void main(String[] args) {

        String[] splitA = extractWordsFromFile(args[0]);
        String[] splitB = extractWordsFromFile(args[1]);

        Set<String> setWords = new HashSet<>();
        setWords.addAll(Arrays.asList(splitA));
        setWords.addAll(Arrays.asList(splitB));


        List<Integer> frequencyA = new ArrayList<>();
        List<Integer> frequencyB = new ArrayList<>();
        for (String word: setWords) {
            frequencyA.add(frequencyCounter(word, splitA));
            frequencyB.add(frequencyCounter(word, splitB));
        }

        double numerator = findNumerator(frequencyA, frequencyB);
        double denominator = findDenominator(frequencyA, frequencyB);

        System.out.printf("Similarity = %.3f\n", numerator / denominator);
    }

    private static String[] extractWordsFromFile(String path) {

        String[] preparedWords = null;
        StringBuilder stringBuilder = new StringBuilder();

        File file = new File(path);
        try(Reader reader = new InputStreamReader(new FileInputStream(file))) {
            int a = reader.read();
            while (a > 0) {
                stringBuilder.append((char) a);
                a = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        preparedWords = stringBuilder.toString().replace('\n', ' ').split(" ");
        return (preparedWords);
    }

    private static float findNumerator(List<Integer> frequencyA, List<Integer> frequencyB){
        int numerator = 0;

        Integer[] arrA = frequencyA.toArray(new Integer[0]);
        Integer[] arrB = frequencyB.toArray(new Integer[0]);

        for (int i = 0; i < arrA.length; i++) {
            numerator += arrA[i] * arrB[i];
        }
        return numerator;
    }

    private static double findDenominator(List<Integer> frequencyA, List<Integer> frequencyB) {

        double a = Math.sqrt(frequencyA.stream().map((x) -> Math.pow(x, 2)).mapToInt((x) -> (int) Math.round(x)).sum());
        double b = Math.sqrt(frequencyB.stream().map((x) -> Math.pow(x, 2)).mapToInt((x) -> (int) Math.round(x)).sum());
        return a * b;
    }

}
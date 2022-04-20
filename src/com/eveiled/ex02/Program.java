package com.eveiled.ex02;

import java.io.File;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command;

        if (args.length == 0) {
            System.out.println("Can't find current-filder in args");
            System.exit(1);
        }
        File currentFolder = new File(args[0].split("=")[1]);
        if (!currentFolder.exists()){
            System.out.println("Entered current folder doesnt exist!");
            System.exit(1);
        }
        System.out.println(currentFolder.getAbsolutePath());

        displayContent(currentFolder);

        while(true){
            command = scanner.nextLine();
            switch (command) {
                case ("ls") -> displayContent(currentFolder);
                case ("cd") -> changeDirectory(currentFolder, command);
                case ("mv") -> System.out.println("-mv-");
                case ("exit") -> System.exit(0);
            }
        }
    }

    private static void changeDirectory(File currentFolder, String command) {

    }

    private static void displayContent(File currentFolder){

        File[] files = currentFolder.listFiles();
        for (File file: files) {
            System.out.println(file.getName() + " " + Math.round(file.length()  / 1024) + " KB");
        }
    }



}

package com.eveiled.ex02;

import java.io.File;
import java.util.*;

//--current-folder=/Users/eveiled/Desktop/day02.2/src/com/eveiled/ex00

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

        while(true){
            command = scanner.nextLine();
            if (command.equals("ls")){
                displayContent(currentFolder);
            }
            else if (command.charAt(0) == 'c' && command.charAt(1) == 'd'){
                File tmpFolder = changeDirectory(currentFolder, command, 1);
                if (currentFolder.equals(tmpFolder)) {
                    System.out.println("cd: no such file or directory: " + command);
                } else {
                    currentFolder = tmpFolder;
                }
                System.out.println(currentFolder.getAbsolutePath());
            }
            else if (command.charAt(0) == 'm' && command.charAt(1) == 'v'){
                moveFiles(currentFolder, command);
            }
            else if (command.equals("exit")){
                System.exit(0);
            }
        }
    }

    private static void moveFiles(File currentFolder, String command) {

        String[] commands = command.split(" ");

        File file = new File(currentFolder.getAbsolutePath() + "/" + commands[1]);
        if (!file.exists()){
            System.out.println("mv: incorrect filename");
        }
        File directory = changeDirectory(currentFolder, command, 2);
        if (!directory.exists()){
            file.renameTo(new File(currentFolder.getAbsolutePath() + "/" + commands[2]));
        }else{
            file.renameTo(new File(directory.getAbsolutePath() + "/" + commands[1]));
        }
    }

    private static File changeDirectory(File currentFolder, String command, Integer flag) {

        File saver = currentFolder;
        List<String> folders = new LinkedList<>(Arrays.stream(currentFolder.getAbsolutePath().split("/")).toList());
        List<String> newFolders = new LinkedList<>(Arrays.stream(command.split(" ")[flag].split("/")).toList());

        for(String folder: newFolders){
            if (folder.equals("..")){
                folders.remove(folders.size() - 1);
            }else if(!folder.equals(".")){
                folders.add(folder);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String folder: folders){
            stringBuilder.append(folder).append("/");
        }
        File file = new File(stringBuilder.toString());
        if (!file.exists()){
            return (saver);
        }

        return (file);
    }

    private static void displayContent(File currentFolder){

        File[] files = currentFolder.listFiles();
        for (File file: files) {
            System.out.println(file.getName() + " " + Math.round(file.length()  / 1024) + " KB");
        }
    }



}

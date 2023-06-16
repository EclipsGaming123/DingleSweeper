import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInfo extends Actor {
    private String fileName;
    public UserInfo(int score) {
        int levelType = SharedData.difficulty;
        if (levelType == 1) {
            fileName = "Easy High Score.txt";
        } else if (levelType == 2) {
            fileName = "Medium High Score.txt";
        } else {
            fileName = "High Scores.txt";
        }
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            int value = 0;
            System.out.println("your score was " + score);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
            }
            scanner.close();
            // Write to the file using PrintWriter
            if (value < score)
            {
                value = score;
            }
            if (value >= score)
            {
                PrintWriter printWriter = new PrintWriter(new FileWriter(file, false));
                System.out.print("Your high score was: " + value + " tiles cleared");
                printWriter.print(value);
                printWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing the file.");
            e.printStackTrace();
        }
    }
}
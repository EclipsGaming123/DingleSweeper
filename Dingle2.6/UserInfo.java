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
    /**
     * 
     * The constructor takes in the score of the other world
     * it takes the score and compares it with the value of the file
     * that was previously there, and gets the highest score out of the two
     * 
     * @param score is the score from the end of the mineworld
     */
    public UserInfo(int score) {
        int levelType = SharedData.difficulty;
        if (levelType == 1) {
            fileName = "Easy High Score.txt";
        } else if (levelType == 2) {
            fileName = "Medium High Score.txt";
        } else {
            fileName = "Hard High Score.txt";
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
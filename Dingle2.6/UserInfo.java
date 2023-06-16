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
    public UserInfo() {
        int levelType = SharedData.difficulty;
        if (levelType == 1) {
            fileName = "Easy High Score.txt";
        } else if (levelType == 2) {
            fileName = "Medium High Score.txt";
        } else {
            fileName = "High Scores.txt";
        }

        try {
            // Read from the file and store the value in an int
            int value = 0;
            File file = new File(fileName);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    value = scanner.nextInt();
                    int score = ((MineWorld)getWorld()).getScore();
                    if (value < score)
                    {
                        value = score;
                    }
                }else
                {
                    //we want to write the score as the high score
                    value = ((MineWorld)getWorld()).getScore();
                }
                scanner.close();
            }

            // Write to the file using PrintWriter
            PrintWriter printWriter = new PrintWriter(new FileWriter(file, false));
            System.out.print("your high score was: ");
            printWriter.print(value);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing the file.");
            e.printStackTrace();
        }
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingsWorld extends World
{
    private SettingsTitle settingsTitle; 
    private SettingsBar settingsBar1;
    private Continue contButton; 
    /**
     * Constructor for objects of class SettingsWorld.
     * 
     */
    public SettingsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 500, 1); 
        settingsTitle = new SettingsTitle(); 
        addObject(settingsTitle, 400 , 100); 
        //int is used to represent difficulty level (1 is easy)
        settingsBar1 = new SettingsBar("LEVEL:", 1, 3, 150, 60);
        addObject(settingsBar1, 250, 250);
        Arrows upArrow1 = new Arrows(true, settingsBar1,1,3);
        addObject(upArrow1, 375, 250);
        Arrows downArrow1 = new Arrows(false, settingsBar1,1,1);
        addObject(downArrow1, 125, 250);
        downArrow1.setRotation(180);
        
        contButton = new Continue("Continue", 100, 50);
        addObject(contButton, 450, 450);
    }
    
    public void act(){
        SharedData.difficulty = settingsBar1.getValue(); 
    }
}

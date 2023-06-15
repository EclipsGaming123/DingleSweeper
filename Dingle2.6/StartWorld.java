import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartWorld here.
 * 
 * @author (Thomson) 
 * @version (2.2)
 */
public class StartWorld extends World
{
    private GreenfootSound startMusic = new GreenfootSound("StartWorldBGM.mp3");
    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        setBackground(new GreenfootImage("titlepage.png")); 
    }
    
    public void act(){
        startMusic.playLoop();
        if(Greenfoot.mouseClicked(this)){
            stopped();
            Greenfoot.setWorld(new SettingsWorld());
        }
    }
    
    public void stopped(){
        startMusic.stop();
    }
}

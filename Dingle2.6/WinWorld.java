import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinWorld extends World
{
    private GreenfootSound winMusic = new GreenfootSound ("WinWorldBGM.mp3");
    private int endCounter = 180; 
    /**
     * Constructor for objects of class WinWorld.
     * 
     */
    public WinWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        UserInfo info = new UserInfo();
    }
    
    public void act(){
        winMusic.playLoop();
        endCounter --;
        if(endCounter == 0){
            //setBackground(new GreenfootImage(("scoreboard.jpg));
            // add label for score, I shall leave this for now. 
        }
    }
    
    public void stopped(){
        winMusic.stop();
    }
}
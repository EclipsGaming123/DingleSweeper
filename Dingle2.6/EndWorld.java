import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndWorld here.
 * 
 * @author () 
 * @version (2.2, 6/15/2023)
 */
public class EndWorld extends World
{
    private GreenfootSound endMusic = new GreenfootSound ("EndWorldBGM.mp3");
    private int endCounter = 180; 
    /**
     * Constructor for objects of class EndWorld.
     * 
     */
    public EndWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        setBackground(new GreenfootImage("youdied.jpg")); 
    }
    
    public void act(){
        endMusic.playLoop();
        endCounter --;
        if(endCounter == 0){
            //setBackground(new GreenfootImage(("scoreboard.jpg));
            // add label for score, I shall leave this for now. 
        }
    }
    
    public void stopped(){
        endMusic.stop();
    }
}

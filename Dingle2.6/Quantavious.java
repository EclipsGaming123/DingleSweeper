import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Quantavious here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quantavious extends SuperSmoothMover
{
    private double speed = 0.2;
    private int difficulty = SharedData.difficulty; 
    private Dude d;
    public Quantavious (Dude d) {
        this.d = d;
        if(difficulty == 2){
            speed = 0.5;
        }else if(difficulty == 3){
            speed = 1.0;
        }
        enableStaticRotation();
    }
    
    /**
     * Act - do whatever the Quantavious wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(d != null && d.getWorld() != null){
            turnTowards(d.getX(), d.getY());
            move(speed);
        }
    }
}



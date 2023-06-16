import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The enemy of the player. After a certain amount of time, Quantavious will spawn
 * in the world, and constantly chase after the player until it can touch them, which
 * kills the player making it a game over. 
 * <p>
 * 
 * @author Wingfung Hu 
 * @version 1.3
 */
public class Quantavious extends SuperSmoothMover
{
    private double speed = 0.2;
    private int difficulty = SharedData.difficulty; 
    private Dude d;
    /**
     * Constructor for Quantavious, takes in the coordinates of the player, and
     * sets the speed of Quantavious accordingly based on the difficulty chosen. Also
     * ensures that Quantavious's image doesn't rotate when moving.
     * 
     * @param d The player instance which is used to take coordinates
     */
    public Quantavious (Dude d) {
        this.d = d;
        if(difficulty == 2){
            speed = 0.5;
        }else if(difficulty == 3){
            speed = 1.0;
        }
        enableStaticRotation();
    }
    

    public void act()
    {
        if(d != null && d.getWorld() != null){
            turnTowards(d.getX(), d.getY());
            move(speed);
        }
    }
}



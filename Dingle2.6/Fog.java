import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fog is a class that creates the worldwide fog effect that covers the grid. It is done
 * by setting the image of the Fog to be larger than what the world size is. Then, by having
 * a transparent circle in the middle, the Fog would then track the location of the player
 * and set its location appropriately, allowing for the circle to be centered onto the player.
 * <p>
 * 
 * @author Wingfung Hu
 * @version 1.6.5
 */
public class Fog extends SuperSmoothMover
{
    private Dude d;
    public Fog (Dude d) {
        this.d = d;
    }

    public void act()
    {
        if(d != null && d.getWorld() != null){
           setLocation (d.getX(), d.getY());
        }
    }
}


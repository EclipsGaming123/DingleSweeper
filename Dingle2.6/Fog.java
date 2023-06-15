import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fog is a class that creates the worldwide fog effect that covers the grid.
 * 
 * @author (WingFung Hu) 
 * @version (1.2)
 * @param
 */
public class Fog extends SuperSmoothMover
{
    private Dude d;
    public Fog (Dude d) {
        this.d = d;
    }
    
    /**
     * Act - do whatever the Quantavious wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(d != null && d.getWorld() != null){
           setLocation (d.getX(), d.getY());
        }
    }
}


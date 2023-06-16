import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * This class is the main character, which is controlled by the player using either WASD,
 * or the arrow keys depending on the player's preference. The player will interact with the
 * tiles like it would in normal minesweeper which only used mouse clicks.
 * <p>
 * 
 * @author Wingfung Hu
 * @version 1.2
 */
public class Dude extends SuperSmoothMover
{
    private double speed;
    private Coordinate currentDestination;
    private ArrayList<Coordinate> destinations;
    private int actCount, redrawFrequency;
    private int difficulty = SharedData.difficulty; 
    public Dude () {
        actCount = 0;
        redrawFrequency = 8;
        destinations = new ArrayList<Coordinate>();
        speed = 2.0;
        enableStaticRotation();
    }
    /**
     * The act method that is run by the Dude class, constantly runs the player movement,
     * and checks to see if he is being touched by the Quantavious class.
     */
    public void act()
    {
       //Act count - so we can only redraw on certain acts rather than every act
        actCount++;
        playerMovement();
        isTouchingQuantavious();
        // If I don't have a destination, and there are destinations
        // in my list, set my current destination to the next one in my list
        if (currentDestination == null && destinations.size() > 0){
            currentDestination = getNextDestination();
        }

        // If I have a destination, move towards it
        if (currentDestination != null){
            moveTowardsDestination();
        }
    }
    /**
     * A method that checks whether the player pressed one of the following keys from WASD, 
     * or the arrow keys, and moves the object accordingly based on rotation and speed.
     */
    private void moveTowardsDestination(){
        // Use method to figure out exact distance between self and destination
        double distanceToDestination = getDistance (new Coordinate(getX(), getY()), currentDestination);
        // If I'm so close to my destination that I'm about to overshoot it, set my
        // location to the exact destination location instead of using calculated movement
        if (distanceToDestination < speed){
            setLocation (currentDestination.getX(), currentDestination.getY());
            destinations.remove(currentDestination);
            currentDestination = null;
        } else { // as long as I'm not close
            // Turn towards and move towards my destination
            turnTowards (currentDestination.getX(), currentDestination.getY());
            move (speed);
        }
    }

    /**
     * Accessor to allow the World to get the Player's path to redraw it
     */
    public ArrayList<Coordinate> getPath(){
        return destinations;
    }

    /**
     * Mutator to allow the World to add a destination to the Player's path
     * (which is triggered by a mouse click in the World)
     * 
     * @param c     The Coordinate to add to the player's destinations
     */
    public void addDestination (Coordinate c){
        destinations.add(c);
    }

    /**
     * Helper method to get the next destination in the List. In a separate method
     * in case we want to add other things to happen when getting next destination
     */
    private Coordinate getNextDestination () {
        return destinations.get(0);
    }
    
    private void playerMovement(){
        if (Greenfoot.isKeyDown("W") || Greenfoot.isKeyDown("Up")){
            setRotation(270);
            move(speed);
        } 
        if (Greenfoot.isKeyDown("D") || Greenfoot.isKeyDown("Right")){
            setRotation(0);
            move(speed);
        }
        if (Greenfoot.isKeyDown("S") || Greenfoot.isKeyDown("Down")){
            setRotation(90);
            move(speed);
        }
        if (Greenfoot.isKeyDown("A") || Greenfoot.isKeyDown("Left")){
            setRotation(180);
            move(speed);
        }
    }
    
    /**
     * Static method that gets the distance between the x,y coordinates of two Actors
     * using Pythagorean Theorum.
     *
     * @param a     First Actor
     * @param b     Second Actor
     * @return distance The distance from the center of a to the center of b.
     */
    public static double getDistance (Coordinate a, Coordinate b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }
    
    public boolean isTouchingQuantavious(){
        if(this.isTouching(Quantavious.class)){
            return true;
        }else{
            return false; 
        }
    }
}
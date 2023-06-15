import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MineSweep here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tiles extends Actor
{
    private boolean mine;
    private boolean isRevealed;
    private boolean flagged;
    private boolean played = false; // For the sound to not spam
    private int neighbouringMines;
    private int gridSize = SharedData.difficulty; 
    private GreenfootSound revealSFX = new GreenfootSound ("TileReveal.wav");
    private GreenfootSound deathSound = new GreenfootSound ("DarkSoul.wav");
    public Tiles(boolean mine){
        this.mine = mine;
        isRevealed = false;
        flagged = false;
        neighbouringMines = 0;
        
        if(gridSize == 2){
            setImage("MTiles.png");
        }else if(gridSize == 3){
            setImage("HTiles - Copy.png");
        }else{ // specify param?
            setImage("unkown.png"); // to change unknown.png to tiles?
        }
    }
    
    /**
     * Act - do whatever the MineSweep wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (this != null && this.getWorld() != null && this.isTouching(Dude.class)){
            if (!played){
                revealSFX.play();
                played = true;
            }
            reveal();
        }
        if (Greenfoot.mouseClicked(this)){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse.getButton() == 3){
                flag();
            }
        }
    }
    
    public void reveal(){
        if (!isRevealed && !flagged){ 
            int neighbourMines = countNeighbouringMines();
            if(neighbourMines == 0){
                if(gridSize == 2){
                    setImage("MRevealed.jpg");
                }else if(gridSize == 3){
                    setImage("HRevealed - Copy.jpg");
                }else{
                    setImage("revealed.jpg");
                }
            }else{
                String strNumber = String.valueOf(neighbourMines);
                GreenfootImage number = new GreenfootImage(strNumber + ".png");
                if (gridSize == 2)
                {
                    number.scale(number.getWidth()/2, number.getHeight()/2);
                }else if (gridSize == 3)
                {
                    number.scale(2*number.getWidth()/5, 2*number.getHeight()/5);
                }
                setImage(number);
            }
            isRevealed = true;
            if (mine){
                if(gridSize == 2){
                    setImage("Mmine.png");
                }else if(gridSize == 3){
                    setImage("Hmine.png");
                }else{
                    setImage("mine.png");
                }
                Greenfoot.delay(60);
                getWorld().stopped();
                deathSound.play();
                EndWorld world = new EndWorld();
                Greenfoot.setWorld(world);
            } else {
                /*neighbouringMines = countNeighbouringMines(this);
                if (neighbouringMines == 0){
                    revealNeighbours();
                } else {
                    // Probably can just take the neighbouringMines variable, and set the image to the appropriate number
                    // Use if else statements (or switch if you can), need Thomson to make the number graphics on the blank squares
                }*/
            }
        }
    }
    
    public void flag(){
        if (!isRevealed && !flagged){
            if(gridSize == 2){
                setImage("Mflagged.png");
            }else if(gridSize == 3){
                setImage("Hflagged.png");
            }else{
                setImage("flagged.png");
            }
            flagged = true;
        }else if(flagged){
            //setImage("unknown.jpg"); <-- add stuff here???
            flagged = false;
        }
    }
    
    private int countNeighbouringMines(){
        // Calculate and return the number of neighbouring mines (Aous can do this)
        int count = 0;
        // Implementation to count neighbouring mines (goodluck Aous)
        
        
        ArrayList<Tiles> tiles;
        if (gridSize == 1)
        {
           tiles = (ArrayList<Tiles>) getObjectsInRange(80 ,Tiles.class); //easy
        }else if (gridSize == 2)
        {
            tiles = (ArrayList<Tiles>) getObjectsInRange(40 ,Tiles.class); //medium
        }
        else
        {
            tiles = (ArrayList<Tiles>) getObjectsInRange(30 ,Tiles.class); //hard
        }
        
        for (Tiles t : tiles){
            if(t.isMine() == true)
            {
                count++;
            }
        }
        return count;
    }
    
    /*private void revealNeighbours(){
        // Reveal neighbouring cells (Aous's part)
        // Implementation to reveal neighbours (goodluck Aous)
        ArrayList <Tiles> tile = (ArrayList<Tiles>) getObjectsInRange(35, Tiles.class);
        if (countNeighbouringMines(this) == 0)
        {
            for (Tiles t: tile)
            revealNeighbours();
        }
    }*/
    public boolean isMine()
    {
        return mine;
    }
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MineSweep here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * 
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
    private GreenfootSound flagPlace = new GreenfootSound ("FlagPlace.wav");
    private GreenfootSound flagRemove = new GreenfootSound ("FlagRemove.wav");
    private GreenfootSound youIdiot = new GreenfootSound ("itsamineyouidiot.wav");
    private GreenfootSound[] flagPlaceSounds, flagRemoveSounds;
    private int flagPlaceSoundsIndex, flagRemoveSoundsIndex;
    
    public Tiles(boolean mine){
        this.mine = mine;
        isRevealed = false;
        flagged = false;
        neighbouringMines = 0;
        
        if(gridSize == 2){
            setImage("MTiles.png");
        }else if(gridSize == 3){
            setImage("HTiles - Copy.png");
        }else{
            setImage("unknown.png");
        }

        flagPlaceSoundsIndex = 0;
        flagRemoveSoundsIndex = 0;
        flagPlaceSounds = new GreenfootSound[20];
        flagRemoveSounds = new GreenfootSound[20];
        for (int i = 0; i < flagPlaceSounds.length; i++){
            flagPlaceSounds[i] = new GreenfootSound("FlagPlace.wav");
        } for (int i = 0; i < flagRemoveSounds.length; i++){
            flagRemoveSounds[i] = new GreenfootSound("FlagRemove.wav");
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
                GreenfootImage revealedImage = new GreenfootImage("revealed.jpg");
                ((MineWorld)getWorld()).resizeImage(revealedImage);
                setImage(revealedImage);
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
                youIdiot.play();
                GreenfootImage mineImage = new GreenfootImage("mine.png");
                ((MineWorld)getWorld()).resizeImage(mineImage);
                setImage(mineImage);
                Greenfoot.delay(30);
                ((MineWorld)getWorld()).die();
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
            flagPlaceSounds[flagPlaceSoundsIndex].play();
            flagPlaceSoundsIndex++;
            if (flagPlaceSoundsIndex > flagPlaceSounds.length - 1){
                flagPlaceSoundsIndex = 0;
            }
            GreenfootImage flagImage = new GreenfootImage("flagged.png");
            ((MineWorld)getWorld()).resizeImage(flagImage);
            setImage(flagImage);
            
            flagged = true;
        } else if(!isRevealed && flagged){
            flagRemoveSounds[flagRemoveSoundsIndex].play();
            flagRemoveSoundsIndex++;
            if (flagRemoveSoundsIndex > flagRemoveSounds.length - 1){
                flagRemoveSoundsIndex = 0;
            }
            GreenfootImage tileImage = new GreenfootImage("unknown.png");
            ((MineWorld)getWorld()).resizeImage(tileImage);
            setImage(tileImage);
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

    public boolean isMine()
    {
        return mine;
    }
    
    public void setMine(boolean mineValue)
    {
        this.mine = mineValue;
    }
    
    public boolean isRevealed()
    {
        return this.isRevealed;
    }
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Credits 
 * 
 *      Graphics - Quantavious.jpg: https://static.wikia.nocookie.net/1ae8d919-556a-45c4-be5a-395efe5f268c/scale-to-width/755  
 *               - Tiles.png https://tutorialflow.com/wp-content/uploads/2018/12/t.png
 *               - MTiles.png: resized Tiles.png
 *               - HTiles.png: resized Tiles.png
 *               - revealed.jpg: recolored Tiles.png
 *               - MRevealed.jpg: resized revealed.jpg 
 *               - Hrevealed.jpg: resized revealed.jpg 
 *               - EndWorld youdied.jpg: 
 *               - Dude.jpg: 
 *               - Fog.jpg By Wingfun Who 
 *               - Numbers 1, 2, 3, 4, 5, 6, 7, 8: 
 *               - Number 9.png: https://i.redd.it/j8v39e7a98m51.png by lolimfakexd
 *               - Resized numbers: resized numbers.png
 * 
 * 
 *      Audio   - StartWorld BGM: https://www.youtube.com/watch?v=3LEJGxXzuAE 
 *                 Author: Morgentau Sound Effects
 *
 *               - Settings Click SFX: https://www.youtube.com/watch?v=g3H9cnQDdvM 
 *                 Author: Spanghew
 *
 *               - MineWorld BGM: https://www.youtube.com/watch?v=ipNqY9wHPqg 
 *                 Author: Pianogeist 
 *
 *               - MineWorld BGM (When Quantavious has spawned in): https://www.youtube.com/watch?v=IQdSM3l8y3Q 
 *                 Author: Scary Halloween Sounds
 *
 *               - Flag SFX (Placement): https://www.youtube.com/watch?v=TV2SunP1S1U 
 *                 Author: Spent200Quid4Mic
 *
 *               - Flag SFX (Remove): https://www.youtube.com/watch?v=bA4qC1OQREw 
 *                 Author: Info Teknologi
 *
 *               - Tile Reveal SFX: https://www.youtube.com/watch?v=iDQj9AjQFBs 
 *                 Author: Free Sound Stock
 *
 *               - Mine SFX (Because they got it wrong): https://www.youtube.com/watch?v=3GDr-eyhsSM 
 *                 Author: EverGrown
 *
 *               - Mine SFX (Explosion): https://www.youtube.com/watch?v=4iaO4MQOaVI 
 *                 Author: Car Features
 *
 *               - Quantavious SFX: https://www.youtube.com/watch?v=AsHkmuQXPRg 
 *                 Author: Distorted Room
 *
 *               - EndWorld BGM: https://www.youtube.com/watch?v=XdFke8MXFys
 *                 Author: MARAN
 *      
 * The world for the game, where tiles, mines, the player avatar, QUANTAVIOUS,the timer and the fog are spawned. 
 * Uses 2D array to make a fixed grid to spawn the mines in.  
 * 
 * @author (Thomson) 
 * @version (2.2)
 */

public class MineWorld extends World
{
    
    /*
     *   Easy mode - 10 mines, 10 x 10
     *   Medium - 40 mines, 40 flags, 16x 16
     *   Hard - 99 flags, 99 mines, 16 x 30
     *   difficulty level is represented by an int value from SettingsWorld that is shared using a public class. 
     */
    private int flagNumber; 
    private int flagsLeft;
    private int row = 10; 
    private int col = 10; 
    private Tiles [][] grid; 
    private int maxMines; //placeholder 
    private int currentMines = 0;
    private boolean isMine;
    Dude dude;
    private int levelType = SharedData.difficulty;
    private Quantavious quantavious; 
    private Fog fog;
    private int actCounter; // Placeholder
    private boolean gameOver = false;
    private boolean quantaviousSpawned = false;
    private Label displayTime;
    private SimpleTimer timer;
    private GreenfootSound mineWorldMusic = new GreenfootSound ("MineWorldBGM.mp3");
    private GreenfootSound quantaviousMusic = new GreenfootSound ("QuantaviousBGM.mp3");
    private GreenfootSound quantaviousSFX = new GreenfootSound("QuantaviousSFX.wav");
    private GreenfootSound deathSound = new GreenfootSound ("DarkSoul.wav");
    private int Width, Length, Row, Col;
    // Width is the vertical pixel size of image and Length is horizontal
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MineWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500,500, 1); 
        setPaintOrder(Label.class, Fog.class, Dude.class, Quantavious.class, Tiles.class);
        if(levelType == 1){ //easy 
            flagNumber = 10; 
            maxMines = flagNumber; 
            Width = 50; // added code for flag 
            Length = 50;
            Row = 10;
            Col = 10;
        }else if(levelType == 2){//medium 
            row = 20;  
            flagNumber = 40; 
            Row = 16;
            Col = 16;
        }else if(levelType == 3){ //hard
            row = 25; 
            flagNumber = 99; 
            Row = 16; 
            Col = 16;
        }
        col = row;
        maxMines = flagNumber;
        flagsLeft = flagNumber; 
        Tiles [][] grid = new Tiles [row][col]; 
        //double for loop for adding the grid using 2D array 
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int incValue = 25; 
                if(levelType == 2){
                    incValue = 12; 
                }else if(levelType == 3){
                    incValue = 10; 
                }
                isMine = false; 
                if(currentMines < maxMines){
                    int mineChance = Greenfoot.getRandomNumber(20);
                    if(mineChance == 1){
                        isMine = true;
                        currentMines++;
                    }
                }
                Tiles tile = new Tiles(isMine);
                grid[j][i] = tile;
                addObject(tile, getWidth()/row * i + incValue, getHeight()/col * j + incValue);//incValue varies per diffculty
            }
        }
        //code for adding mines 
        while (currentMines < maxMines) {
            int incValue = 25; 
            if(levelType == 2){
                incValue = 12;
            }else if(levelType == 3){
                incValue = 10;
            }
            int tileX = Greenfoot.getRandomNumber(row);
            int tileY = Greenfoot.getRandomNumber(col);
            List <Tiles> tile = getObjectsAt((getWidth()/row * tileX) + incValue, (getHeight()/col * tileY) + incValue, Tiles.class);
            for(Tiles t: tile)
            {
                if(!t.isMine())
                {
                   removeObject(t);
                   isMine = true;
                   addObject(new Tiles(isMine), (getWidth()/row * tileX) + incValue, (getHeight()/col * tileY) + incValue);
                   currentMines++;
                }
            }        
        }
        timer = new SimpleTimer();
        displayTime = new Label("time elapsed: 0", 24);
        addObject(displayTime, 100, 20);        
        dude = new Dude();
        addObject(dude,250,250); 
        fog = new Fog(dude);
        //addObject(fog, 250, 250);
    }
    
    public void act (){
        if (!quantaviousSpawned){
            mineWorldMusic.playLoop();
        } else {
            quantaviousMusic.playLoop();
            quantaviousSFX.playLoop();
        }
        if(dude.isTouchingQuantavious()){
            die();
        }
        
        

        // added code for flag 
        if (Greenfoot.mouseClicked(null)){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            // Easy 50 x 50 Medium 
            if (mouse.getButton() == 3){
                int x = mouse.getX(), y = mouse.getY();
                int sX = Length / 2, sY = Width / 2;
                for (int i = 0; i < Row; i++){
                    for (int j = 0; j < Col; j++){
                        int currentY = sY + i * Width, currentX = sX + j * Length;
                        if (currentY - sY <= y && y <= currentY + sY && currentX - sX <= x && x <= currentX + sX){
                            Tiles tile = (Tiles)getObjectsAt(x, y, Tiles.class).get(0);
                            tile.flag();
                        }
                    }
                }
            }
        }
        actCounter++;
        if (actCounter == 1200){
            quantavious = new Quantavious(dude);
            addObject(quantavious, 0, 0);
            quantaviousSpawned = true;
            stopped();
        }
        displayTime.setValue("Time elapsed: " + timer.millisElapsed()/1000 + "s");
    }
    
    public void stopped(){
        mineWorldMusic.stop();
        quantaviousMusic.stop();
        quantaviousSFX.stop();
    }
    
    public void die()
    {
        stopped();
        deathSound.play();
        removeObject(quantavious);
        EndWorld world = new EndWorld();
        Greenfoot.setWorld(world); 
    }
}


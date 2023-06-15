import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Continue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Continue extends Actor
{
    private int xSize; 
    private int ySize; 
    private String label; 
    /**
     * Act - do whatever the Continue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Continue(String label, int xSize, int ySize){
        this.label = label; 
        this.xSize = xSize; 
        this.ySize = ySize;
        updateImage();
    }
       
    private void updateImage(){
        GreenfootImage image = new GreenfootImage(xSize, ySize);
        Font font = new Font("Verdana",true, false, 15); // create a Font object with size 18
        image.setFont(font); // set the font of the GreenfootImage
        image.setColor(Color.WHITE);// change to greyy
        image.fillRect(0, 0, xSize, ySize);
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, xSize - 1, ySize - 1); 
        image.drawString(label, xSize/2- 35, ySize/2+5);
        setImage(image);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new MineWorld());
        }
    }
}

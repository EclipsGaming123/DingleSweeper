import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class SettingsBar, used for the SettingsWorld. It holds the 
 * value of increments and decreases of the arrows.
 * 
 * @author Thomson 
 * @version June 2023
 */
public class SettingsBar extends Actor
{
    private int value;
    private int maxValue;
    private int xSize; 
    private int ySize;
    private String label; 
    /**
     * Act - do whatever the SettingsBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SettingsBar(String label, int value, int maxValue, int xSize, int ySize) {
        this.label = label; 
        this.value = value;
        this.maxValue = maxValue;
        this.xSize = xSize;
        this.ySize = ySize;
        updateImage();
    }

    /**
     * Changes to easy, medium and hard difficulty. Assign int to each string,
     * int represents grid size (if in mineworld).
     */
    public void incrementValue() {
        value ++;
        if (value > maxValue) {
            value = maxValue;
        }
        updateImage();
    }
    /**
     * Changes to easy, medium and hard difficulty. Assign int to each string,
     * int represents grid size (if in mineworld).
     */
    public void decrementValue(int decrementValue, int minValue) {
        value -= decrementValue;
        if (value <= minValue) {
            value = minValue;
        }
        updateImage();
    }
    
    public int getValue(){
        return value;
    }
    /**
     * Updates the image with correct font and value.
     */
    private void updateImage() {
    GreenfootImage image = new GreenfootImage(xSize, ySize);
    Font font = new Font("Verdana",true, false, 40); // create a Font object with size 18
    image.setFont(font); // set the font of the GreenfootImage
    //image.setColor(Color.WHITE);// change to greyy
    //image.fillRect(0, 0, xSize, ySize);
    image.setColor(Color.BLACK);
    //image.drawRect(0, 0, xSize - 1, ySize - 1); 
    //image.drawString(label, xSize/2 - 30 , ySize/2 -5 ); 
    if(value == 1){
        image.drawString("EASY", xSize/2 - 60, ySize/2 + 20);
    }else if(value == 2){
        Font font2 = new Font("Verdana", true, false, 30);
        image.setFont(font2);
        image.drawString("MEDIUM", xSize/2 - 75, ySize/2 + 10);
    }else if(value == 3){
        image.drawString("HARD", xSize/2 - 60, ySize/2 + 20);
    }
    //image.drawString(String.valueOf(value),xSize/2, ySize/2+20);
    setImage(image);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Arrows creates the arrow used for SettingsBar. the arrows are used 
 * in the display for the settings.
 * 
 * @author Thomson 
 * @version June 2023
 */
public class Arrows extends Actor
{
    /**
     * 
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isUpArrow;
    private SettingsBar displayBar;
    private int decrementValue;
    private int minValue; 
    private GreenfootSound[] clickSounds;
    private int clickSoundsIndex;
    public Arrows(boolean isUpArrow, SettingsBar displayBar, int decrementValue, int minValue){
        this.isUpArrow = isUpArrow;
        this.displayBar = displayBar; 
        this.decrementValue = decrementValue;
        this.minValue = minValue;
        clickSoundsIndex = 0;
        clickSounds = new GreenfootSound[20];
        for (int i = 0; i < clickSounds.length; i++){
            clickSounds[i] = new GreenfootSound("SettingsClick.wav");
        }
    }
    /**
     * Act method checks if the player clicked the arrow.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            clickSounds[clickSoundsIndex].play();
            clickSoundsIndex++;
            if (clickSoundsIndex > clickSounds.length - 1){
                clickSoundsIndex = 0;
            }
            if(isUpArrow){
                displayBar.incrementValue();
            }else{
                displayBar.decrementValue(decrementValue, minValue);
            }
        }
    }
}

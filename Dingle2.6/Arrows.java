import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class arrows creates the arrow used for SettingsBar
 * 
 * 
 * @author (Thomson Lam) 
 * @version (1.1)
 */
public class Arrows extends Actor
{
    /**
     * Brady loves his mother so much he zxza
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

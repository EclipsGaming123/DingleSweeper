import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class SettingsTitle, used as the title for SettingsWorld.
 * 
 * @author Thomson 
 * @version June 2023
 */
public class SettingsTitle extends Actor
{
    private GreenfootImage settingsTitle;
    private Font textFont; 
    private Font textFont2;
    private String text;
    private int fontSize;
    private int fontSize2; 
    private Color background, textColor;
    
    public SettingsTitle(){
        fontSize = 50;
        textFont = new Font ("Verdana", true, false, fontSize);
        settingsTitle = new GreenfootImage (600, 600);//rect size 
        background = new Color(0, 0, 0, 0);
        textColor = new Color(255, 0, 0);
        settingsTitle.setColor(background); 
        settingsTitle.fill();
        
        this.setImage(settingsTitle);
        settingsTitle.setFont(textFont);
        text = ("Dififculty");
        settingsTitle.setColor(textColor);
        settingsTitle.drawString(text, 30, 300);
    }

}
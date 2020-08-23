import sacco.*;

public class ImageLoader
{
    public static Picture noboost = Picture.loadFromJar("space/shipnoboost.png");
    public static Picture boost = Picture.loadFromJar("space/shipwithboost.png");
    public static Picture[] explosion = SpriteLoader.getExplosionPics();
    public static Picture bullet = Picture.loadFromJar("space/bullet.png");
    public static Picture powerUp = Picture.loadFromFile("saccoface.png");
    public static Picture asteroid1 = Picture.loadFromJar("space/asteroid1.png");
    public static Picture asteroid2 = Picture.loadFromJar("space/asteroid2.png");
    public static Picture asteroid3 = Picture.loadFromJar("space/asteroid3.png");
    public static Picture asteroid4 = Picture.loadFromJar("space/asteroid4.png");
    public static Picture asteroid5 = Picture.loadFromJar("space/asteroid5.png");
}
import sacco.*;

public class PowerUp extends Asteroids
{
    Picture p = AsteroidsBoard.loader.powerUp;

    public PowerUp(double x, double y)
    {
        super();
        setX(x);
        setY(y);
        setWidth(40);
        setHeight(40);
        setRotation(0);
        setXVelocity(5);
        setYVelocity(5);
    }
    
    public void Update()
    {
        super.update();
    }

    public void paintSprite(Canvas c)
    {
        c.drawPicture (p, (int)getX() - getWidth() / 2, (int)getY() - getHeight() / 2, getWidth(), getHeight());
    }
}
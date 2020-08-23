import sacco.*;
public class Bullet extends AsteroidSprite
{
    private Picture pic;
    private double speed;
    private int count;
    private double maxCount;

    public Bullet(double x, double y, int direction)
    {
        speed = 10;
        pic = AsteroidsBoard.loader.bullet;
        setX(x);
        setY(y);
        setWidth(10);
        setHeight(10);
        double xV = Math.cos(Math.toRadians(direction));
        double yV = -Math.sin(Math.toRadians(direction));
        setXVelocity(speed*xV*2);
        setYVelocity(speed*yV*2);
        maxCount = 12.25;
    }
    
    @Override
    public void update()
    {
        count ++;
        super.update();
    }
    
    public boolean isTooOld()
    {
        if(count > maxCount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void paintSprite(Canvas c)
    {
        c.drawPicture(pic, (int)getX() - getWidth() / 2, (int)getY() - getHeight() / 2, getWidth(), getHeight());
    }
}
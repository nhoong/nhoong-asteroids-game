import sacco.*;

public class Asteroids extends RotatingSquares
{
    private static final int SMALL = 30;
    private static final int MEDIUM = 50;
    private static final int LARGE = 70;
    private Picture p;

    public Asteroids()
    {
        int rng1 = (int)(Math.random()*2) + 1;
        int rng2 = (int)(Math.random()*3) + 1;
        int rng3 = (int)(Math.random()*5) + 1;

        if (rng1 == 1)
        {
            setX(Math.random()*350);
            setY(0);
        }
        if (rng1 == 2)
        {
            setX(0);
            setY(Math.random()*250);
        }
        if (rng2 == 1)
        {
            setWidth(SMALL);
            setHeight(SMALL);
        }
        if (rng2 == 2)
        {
            setWidth(MEDIUM);
            setHeight(MEDIUM);
        }
        if (rng2 == 3)
        {
            setWidth(LARGE);
            setHeight(LARGE);
        }
        if (rng3 == 1)
            p = AsteroidsBoard.loader.asteroid1;
        if (rng3 == 2)
            p = AsteroidsBoard.loader.asteroid2;
        if (rng3 == 3)
            p = AsteroidsBoard.loader.asteroid3;
        if (rng3 == 4)
            p = AsteroidsBoard.loader.asteroid4;
        if (rng3 == 5)
            p = AsteroidsBoard.loader.asteroid5;
    }
    
    public Asteroids(double x, double y, int size, Picture p)
    {
        setX(x);
        setY(y);
        setWidth(size);
        setHeight(size);
        this.p = p;
    }
    
    public Picture getPicture()
    {
        return p;
    }
    
    public void paintSprite(Canvas c)
    {
        c.drawPicture(p, (int)getX()-getWidth()/2, (int)getY()-getHeight()/2, getWidth(), getHeight());
    }
}
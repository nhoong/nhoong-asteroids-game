import sacco.*;

public class DriftingSquare extends AsteroidSprite
{
    int r,g,b;

    public DriftingSquare()
    {
        super();

        randomizeVectors();

        r = (int)(Math.random()*256);
        g = (int)(Math.random()*256);
        b = (int)(Math.random()*256);
    }

    public void randomizeVectors()
    {
        double randXV = Math.random()*10+3;
        int posNegXV = (int)(Math.random()*2);
        if( posNegXV ==0)
            setXVelocity(randXV);
        else
            setXVelocity(-randXV);

        double randYV = Math.random()*10+3;
        int posNegYV = (int)(Math.random()*2);

        if( posNegYV ==0)
            setYVelocity(randYV);
        else
            setYVelocity(-randYV);
    }

    public void paintSprite(Canvas c)
    {
        c.setColor(r,g,b);
        c.fillRectangle((int)getX()-getWidth()/2,(int)getY()-getHeight()/2,getWidth(),getHeight());

        c.setColor("black");
        c.drawRectangle((int)getX()-getWidth()/2,(int)getY()-getHeight()/2,getWidth(),getHeight());
    }
}
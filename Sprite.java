import sacco.*;

public abstract class Sprite
{
    private double x;
    private double y;
    private int width;
    private int height;
    private double xVel;
    private double yVel;
    private BoundingBox bound;

    public Sprite(double x, double y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getXVelocity()
    {
        return xVel;
    }

    public double getYVelocity()
    {
        return yVel;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public BoundingBox getBoundary()
    {
        return bound;
    }

    public void setX(double tmpX)
    {
        this.x = tmpX;
    }

    public void setY(double tmpY)
    {
        this.y = tmpY;
    }

    public void setXVelocity(double tmpVel)
    {
        this.xVel = tmpVel;
    }

    public void setYVelocity(double tmpVel)
    {
        this.yVel = tmpVel;
    }

    public void setWidth(int tmpWidth)
    {
        this.width = tmpWidth;
    }

    public void setHeight(int tmpHeight)
    {
        this.height = tmpHeight;
    }

    public void setBoundary(BoundingBox bound)
    {
        this.bound = bound;
    }

    public void move()
    {
        x += xVel;
        y += yVel;
    }

    public void update()
    {
        this.move();
        this.processBoundary();
    }
    
    public abstract void paintSelf(Canvas c);
    
    public abstract void processBoundary();
}
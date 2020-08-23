import sacco.*;

public class Ship extends AsteroidSprite
{
    private Picture pic;
    private double maxSpeed;
    private boolean b;

    public Ship(int x, int y)
    {
        super();
        setWidth(40);
        setHeight(40);
        setRotation(90);
        int rand = (int)(Math.random() * 4) + 1;
        pic = AsteroidsBoard.loader.noboost;
        maxSpeed = 10;
    }

    @Override
    public void paintSprite(Canvas c)
    {
        c.drawPicture(pic, (int)getX() - getWidth() / 2,(int)getY() - getHeight() / 2, getWidth(), getHeight());
    }

    public void turnLeft()
    {
        int rot=getRotation();
        setRotation(rot + 15);
    }

    public void turnRight()
    {
        int rot=getRotation();
        setRotation(rot - 15);
    }

    public void limitVectors()
    {
        //get the velocities
        double xV = getXVelocity();
        double yV = getYVelocity() ;

        //Calculate the speed using the distance formula
        double speed = Math.sqrt(xV * xV + yV * yV);

        //If the velocities made the speed too large
        if (speed > maxSpeed)
        {
            //normalize the vectors to be at the maxSpeed
            setXVelocity(maxSpeed * xV / speed);
            setYVelocity(maxSpeed * yV / speed);
        }
    }

    public void boost()
    {
        double diffXV = Math.cos(Math.toRadians(getRotation()));
        double diffYV = -Math.sin(Math.toRadians(getRotation()));
        double xVel = getXVelocity();
        double yVel = getYVelocity();
        setXVelocity(diffXV + xVel);
        setYVelocity(diffYV + yVel);
        this.limitVectors();
    }

    public void brake()
    {
        setXVelocity(.7 * getXVelocity());
        setYVelocity(.7 * getYVelocity() );
    }
    
    public void setPic()
    {
        this.pic = AsteroidsBoard.loader.boost;
    }
    
    public void shipNoBoost()
    {
        this.pic = AsteroidsBoard.loader.noboost;
    }
}

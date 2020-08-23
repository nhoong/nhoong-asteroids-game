import sacco.*;

public abstract class AsteroidSprite extends Sprite
{
    private boolean b = true;
    private int rotation;
    private boolean paintHitBox = true;

    public AsteroidSprite()
    {
        super(350,250,50,50);
        this.setBoundary(new BoundingBox(0,0,700,500));
    }

    public void processBoundary()
    {
        BoundingBox bound = getBoundary();
        int leftX = bound.getX()-this.getWidth()/2;
        int rightX = bound.getX()+bound.getWidth()+this.getWidth()/2;
        int bottom = bound.getY() + bound.getHeight();

        if (this.getX() < leftX)
            this.setX(rightX);
        if (this.getX() > rightX)
            this.setX(leftX);
        if (this.getY() > bottom)
            this.setY(0);
        if (this.getY() < 0)
            this.setY(bottom);
    }

    public boolean isActive()
    {
        return b;
    }

    public void setActive(boolean b)
    {
        this.b = b;
    }

    public void update()
    {
        if (b == true)
        {
            super.update();
        }
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    public abstract void paintSprite(Canvas c);

    public void paintSelf(Canvas c)
    {
        c.rotateAroundPoint(rotation, getX(), getY());
        paintSprite(c);
        c.resetRotation();
        if(paintHitBox == true)
        {
            BoundingBox box = getHitBox();
            // c.drawRectangle((int)box.getX() - box.getWidth() / 2, (int)box.getY() - box.getHeight() / 2, box.getWidth(), b.getHeight());
        }
    }

    public BoundingBox getHitBox()
    {
        BoundingBox box = new BoundingBox((int)getX() - getWidth() / 2 + 5, (int)getY() - getHeight() / 2 + 5, getWidth() - 10, getHeight() - 10);
        return box;
    }
}
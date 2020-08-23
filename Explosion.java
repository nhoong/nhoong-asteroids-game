import sacco.*;

public class Explosion
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Picture pic[];
    private int index;

    public Explosion(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        pic = AsteroidsBoard.loader.explosion;
        index=0;
    }

    public void update()
    {
        if(index<32)
        {
            index++;
        }
    }

    public void paintSelf(Canvas c)
    {
        if(index<32)
        {
            c.drawPicture(pic[index],x-width/2,y-height/2,width,height);
        }
    }
}
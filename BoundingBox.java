public class BoundingBox
{
    private int x;
    private int y;
    private int width;
    private int height;
    
    public BoundingBox(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public boolean overlaps(BoundingBox other)
    {
        int x1 = this.getX();
        int y1 = this.getY();
        int firstWidth = this.getWidth();
        int firstHeight = this.getHeight();
        
        int x2 = other.getX();
        int y2 = other.getY();
        int secondWidth = other.getWidth();
        int secondHeight = other.getHeight();
        
        boolean b = true;
        
        
        //this left of other
        if(x1 + width < x2)
        {
            b = false;
        }
       
        //this right of other
        if(x2 + secondWidth < x1)
        {
            b = false;
        }
        
        //this above other
        if(y2 + secondHeight < y1)
        {
            b = false;
        }
        
        //this below other
        if(y2 > firstHeight + y1)
        {
            b = false;
        }
        
        return b;
    }
}
import sacco.*;

public class RotatingSquares extends DriftingSquare
{
    private int rotationSpeed;
    
    public RotatingSquares()
    {
        super();
        this.rotationSpeed = ((int)(Math.random()*7)) + 3;
        super.setRotation((int)(Math.random()*361));
    }
    
    public void update()
    {
        super.update();
        super.setRotation(rotationSpeed + this.getRotation());
    }
}
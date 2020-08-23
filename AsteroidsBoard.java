import sacco.*;
import java.util.ArrayList;

public class AsteroidsBoard extends SaccoWindow
{
    ArrayList < AsteroidSprite > astList;
    private Picture background = Picture.loadFromJar("space/bluespace.png");
    private Ship ship;
    ArrayList <Bullet> bulletList;
    ArrayList <PowerUp> powerList;
    private boolean up;
    private boolean left;
    private boolean right,down;
    private int level;
    private Explosion explosion;
    public static final ImageLoader loader = new ImageLoader();

    public AsteroidsBoard()
    {
        astList = new ArrayList < AsteroidSprite >();
        loadAsteroids(1);
        ship = new Ship(350, 250);
        bulletList = new ArrayList <Bullet>();
        powerList = new ArrayList<PowerUp>();
        PowerUp p = new PowerUp((int)(Math.random()*700), (int)(Math.random()*500));
        powerList.add(p);
        level = 1;
    }

    public void loadDriftingSquares(int num)
    {
        for( int i = 0; i < num; i ++)
            astList.add( new DriftingSquare());
    }

    public void loadRotatingSquares(int num)
    {
        for (int i = 0; i < num; i ++)
            astList.add(new RotatingSquares());
    }

    public void loadAsteroids(int num)
    {
        for (int i = 0; i < num; i ++)
            astList.add(new Asteroids());
    }

    public void launch()
    {
        this.setSize(700,500);
        this.setVisible(true);
        this.start();
    }

    @Override
    public void paintWindow(Canvas c)
    {
        //paint everything in the astList
        c.drawPicture(background, 0, 0, 700, 900);
        c.setColor("WHITE");
        c.setFontSize(20);

        if (level == 2)
        {
            c.drawString("Level: Formal?", 30, 30);
            c.drawString("Ye?", 30, 50);
        }
        else
        c.drawString("Level: " + level, 30, 30);

        for (AsteroidSprite ast: astList)
        {
            ast.paintSelf(c);
        }

        if (ship.isActive())
            ship.paintSelf(c);

        for (Bullet b : bulletList)
        {
            b.paintSelf(c);
        }

        for (PowerUp p: powerList)
            p.paintSelf(c);

        if(explosion!=null)
            explosion.paintSelf(c);

        if (ship.isActive() == false)
        {
            c.setColor("RED");
            c.setFontSize(50);
            if (level == 1)
            {
                c.setFontSize(20);
                c.drawString ("See that dumpster? You should go back there, you belong there", 60, 250);
            }
            else
                c.drawString ("You're okay", 210, 250);
        }
    }

    @Override
    public void onTimerTick()
    {
        //update everything in the astList
        for (AsteroidSprite ast: astList)
            ast.update();

        ship.update();
        bulletsVsAsteroids();

        if (shipVsAsteroids() == true)
            stopGame();

        for (Bullet b : bulletList)
            b.update();

        if (left == true)
            ship.turnLeft();
        if (right == true)
            ship.turnRight();
        if (up == true)
            ship.boost(); 
        if (down == true)
            ship.brake();

        if (astList.size() == 0)
        {
            level ++;
            loadAsteroids(2*level + 2);
        }

        if(explosion!=null)
            explosion.update();

        powerUpVsShip();
        powerList.get(0).update();
        removeOldBullets();
    }

    @Override
    public void onKeyPress(int keyCode)
    {
        if (keyCode == 87 || keyCode == 38)
        {
            up = true;
            ship.setPic();
        }
        if (keyCode == 65 || keyCode == 37)
            left = true;
        if (keyCode == 68 || keyCode == 39)
            right = true;
        if (keyCode == 'S' || keyCode == 40)
            down = true;
        if (keyCode == 32)
        {
            Bullet bull = new Bullet(ship.getX(), ship.getY(), ship.getRotation());
            bulletList.add(bull);
        }
        if (keyCode == 82)
            reset();
    }

    @Override
    public void onKeyRelease(int keyCode)
    {
        if (keyCode == 87 || keyCode == 38)
        {
            up = false;
            ship.shipNoBoost();
        }
        if (keyCode == 65 || keyCode == 37)
            left = false;
        if (keyCode == 68 || keyCode == 39)
            right = false;
        if (keyCode == 'S' || keyCode == 40)
            down = false;
    }

    public void removeOldBullets()
    {
        for (int i = 0; i < bulletList.size(); i ++)
        {
            if (bulletList.get(i).isTooOld() == true)
            {
                bulletList.remove(i);
            }
        }
    }

    public void bulletsVsAsteroids()
    {
        for (int i = 0; i < astList.size(); i ++)
        {
            for (int k = 0; k < bulletList.size(); k ++)
            {
                AsteroidSprite a = astList.get(i);
                Bullet b = bulletList.get(k);
                BoundingBox asteroid = a.getHitBox();
                BoundingBox bullet = b.getHitBox();

                if (asteroid.overlaps(bullet) == true)
                {
                    Asteroids ast = (Asteroids)astList.remove(i);

                    if (ast.getWidth() == 70)
                    {
                        double x = ast.getX();
                        double y = ast.getY();
                        Picture pic = ast.getPicture();
                        Asteroids newAsteroid = new Asteroids(x, y, 50, pic);
                        Asteroids newAsteroid2 = new Asteroids(x, y, 50, pic);
                        astList.add(newAsteroid);
                        astList.add(newAsteroid2);
                    }
                    if (ast.getWidth() == 50)
                    {
                        double x = ast.getX();
                        double y = ast.getY();
                        Picture pic =ast.getPicture();
                        Asteroids newAsteroid = new Asteroids(x, y, 30, pic);
                        Asteroids newAsteroid2 = new Asteroids(x, y, 30, pic);
                        astList.add(newAsteroid);
                        astList.add(newAsteroid2);
                    }

                    bulletList.remove(b);
                    return;
                }
            }
        }
    }

    public boolean shipVsAsteroids()
    {
        boolean b = false;

        for (int k = 0; k < astList.size(); k ++)
        {
            AsteroidSprite a = astList.get(k);
            BoundingBox asteroid = a.getHitBox();
            BoundingBox s = ship.getHitBox();

            if (asteroid.overlaps(ship.getHitBox()) && ship.isActive())
            {
                b = true;
            }
        }

        return b;
    }

    public void stopGame()
    {
        ship.setActive(false);

        for (int i = 0; i < astList.size(); i ++)
            astList.get(i).setActive(false);
        for (int i = 0; i < bulletList.size(); i ++)
            bulletList.get(i).setActive(false);

        explosion = new Explosion((int)ship.getX(), (int)ship.getY(), 70, 70);
    }

    public void reset()
    {
        ship = new Ship(350, 250);
        bulletList = new ArrayList<Bullet>();
        astList = new ArrayList<AsteroidSprite>();
        loadAsteroids(5);
        level = 1;
    }

    public boolean powerUp()
    {
        BoundingBox p = powerList.get(0).getHitBox();
        BoundingBox s = ship.getHitBox();

        if(ship.isActive() == true)
        {
            if(s.overlaps(p))
            {
                return true;
            }
        }

        return false;
    }

    public void powerUpVsShip()
    {
        int rot = 360;
        int count = 0;

        if(powerUp()==true)
        {
            powerList.remove(0);

            while(count<15)
            {
                Bullet b = new Bullet(ship.getX(), ship.getY(), rot -= 24);
                bulletList.add(b);
                b.update();
                count ++;
            }

            PowerUp p = new PowerUp((int)(Math.random()*700),(int)(Math.random()*500));
            powerList.add(p);
        }

        count=0;
    }

    public static void main()
    {
        AsteroidsBoard myBoard = new AsteroidsBoard();
        myBoard.launch();
    }
}
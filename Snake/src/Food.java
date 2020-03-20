import java.util.Random;

public class Food
{
    private int x;
    private int y;

    public void createApple(int bound1, int bount2)
    {
        Random r = new Random();
        do{
            x = r.nextInt((bound1-50) + 1);
            y = r.nextInt((bount2-26) + 1);

        }while(!(x % 10 == 0 && y % 10 == 0));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
